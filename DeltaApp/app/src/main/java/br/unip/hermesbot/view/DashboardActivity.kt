package br.unip.hermesbot.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unip.hermesbot.R
import br.unip.hermesbot.controller.ChamadoController
import br.unip.hermesbot.model.Chamado
import br.unip.hermesbot.view.adapters.ChamadoAdapter
import br.unip.hermesbot.view.custom.LineChartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : BaseActivity() {

    private val scope = MainScope()
    private lateinit var tvGreeting: TextView
    private lateinit var tvConcluidos: TextView
    private lateinit var tvEmAndamento: TextView
    private lateinit var tvAtrasados: TextView
    private lateinit var rvChamados: RecyclerView
    private lateinit var chart: LineChartView

    private var userId = -1
    private var userName = ""
    private var userRole = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        tvGreeting = findViewById(R.id.tvGreeting)
        tvEmAndamento = findViewById(R.id.tvEmAndamento)
        tvAtrasados = findViewById(R.id.tvAtrasados)
        rvChamados = findViewById(R.id.rvChamados)
        chart = findViewById(R.id.nativeLineChart)

        rvChamados.layoutManager = LinearLayoutManager(this)

        loadUser()
        setGreeting()
        loadChamados()

        findViewById<View>(R.id.fab).setOnClickListener {
            if (userRole.equals("admin", ignoreCase = true)) {
                startActivity(android.content.Intent(this, AdminDownloadActivity::class.java))
            } else {
                val i = android.content.Intent(this, NewChamadoActivity::class.java)
                i.putExtra("user_id", userId)
                startActivity(i)
            }
        }
    }

    private fun loadUser() {
        val prefs = getSharedPreferences(LoginActivity.PREFS_NAME, Context.MODE_PRIVATE)
        userId = prefs.getInt(LoginActivity.KEY_USER_ID, -1)
        userName = prefs.getString(LoginActivity.KEY_USER_NAME, "Usuário") ?: "Usuário"
        userRole = prefs.getString(LoginActivity.KEY_USER_ROLE, "user") ?: "user"
    }

    private fun setGreeting() {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        val msg = when (hour) {
            in 5..11 -> "Bom dia"
            in 12..17 -> "Boa tarde"
            else -> "Boa noite"
        }
        tvGreeting.text = "$msg, $userName"
    }

    private fun loadChamados() {
        scope.launch {
            try {
                val controller = ChamadoController()
                val r = withContext(Dispatchers.IO) { controller.getChamados() }
                if (r.isFailure) {
                    Toast.makeText(this@DashboardActivity, "Erro ao buscar chamados", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val lista = r.getOrNull() ?: emptyList()
                val finalList = if (userRole.equals("admin", ignoreCase = true)) lista else lista.filter { it.usuarioSolicitanteId == userId }
                updateStatus(finalList)
                updateChart(finalList)
                rvChamados.adapter = ChamadoAdapter(finalList.toMutableList(), userRole)
            } catch (e: Exception) {
                Toast.makeText(this@DashboardActivity, "Falha: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateStatus(list: List<Chamado>) {
        tvConcluidos.text = list.count { it.status.equals("Fechado", ignoreCase = true) }.toString()
        tvEmAndamento.text = list.count { it.status.equals("Andamento", ignoreCase = true) || it.status.equals("Aberto", ignoreCase = true) }.toString()
        tvAtrasados.text = list.count { it.status.equals("Atrasado", ignoreCase = true) }.toString()
    }

    private fun updateChart(list: List<Chamado>) {
        val grouped = list.groupBy { it.dataAbertura?.take(10) ?: "" }
            .toSortedMap()
            .map { it.value.size }
        if (grouped.isEmpty()) return
        chart.setData(grouped)
    }
}
