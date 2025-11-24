package br.unip.hermesbot.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.setPadding
import br.unip.hermesbot.R
import br.unip.hermesbot.controller.ChamadoController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class NewChamadoActivity : BaseActivity() {

    private val scope = MainScope()
    private val chamadoController = ChamadoController()

    private lateinit var etTitulo: EditText
    private lateinit var etDescricao: EditText
    private lateinit var etCategoria: EditText
    private lateinit var etPrioridade: EditText
    private lateinit var btnIa: Button
    private lateinit var layoutIA: LinearLayout
    private lateinit var btnEnviar: Button

    private var solucaoSelecionada: String? = null
    private var userId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chamado)

        userId = intent.getIntExtra("user_id", -1)

        etTitulo = findViewById(R.id.etTitulo)
        etDescricao = findViewById(R.id.etDescricao)
        etCategoria = findViewById(R.id.etCategoria)
        etPrioridade = findViewById(R.id.etPrioridade)
        btnIa = findViewById(R.id.btnIa)
        layoutIA = findViewById(R.id.layoutIA)
        btnEnviar = findViewById(R.id.btnEnviar)

        btnIa.setOnClickListener {
            val desc = etDescricao.text.toString().trim()
            if (desc.isEmpty()) {
                Toast.makeText(this, "Preencha a descrição primeiro!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            layoutIA.removeAllViews()
            val loading = ProgressBar(this)
            layoutIA.addView(loading)

            // placeholder: IA/FAQ search -> returns empty list for now
            scope.launch {
                val results = withContext(Dispatchers.IO) {
                    emptyList<String>()
                }
                layoutIA.removeAllViews()
                if (results.isEmpty()) {
                    val tv = TextView(this@NewChamadoActivity)
                    tv.text = "Nenhuma solução automática encontrada."
                    layoutIA.addView(tv)
                } else {
                    results.forEach { s -> layoutIA.addView(criarCardSolucao(s)) }
                }
            }
        }

        btnEnviar.setOnClickListener {
            val chamado = criarChamadoObj(userId)
            scope.launch {
                val r = withContext(Dispatchers.IO) { chamadoController.criarChamado(chamado) }
                if (r.isSuccess) {
                    Toast.makeText(this@NewChamadoActivity, "Chamado criado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@NewChamadoActivity, "Erro ao criar chamado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun criarChamadoObj(userId: Int): br.unip.hermesbot.model.Chamado {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return br.unip.hermesbot.model.Chamado(
            chamadoId = 0,
            titulo = etTitulo.text.toString(),
            descricao = etDescricao.text.toString(),
            status = solucaoSelecionada?.let { "Fechado" } ?: "Aberto",
            prioridade = etPrioridade.text.toString(),
            dataAbertura = sdf.format(Date()),
            dataFechamento = solucaoSelecionada?.let { sdf.format(Date()) },
            usuarioSolicitanteId = userId,
            tecnicoResponsavelId = null,
            categoriaId = etCategoria.text.toString().toIntOrNull()
        )
    }

    private fun criarCardSolucao(solucao: String): View {
        val card = LinearLayout(this)
        card.orientation = LinearLayout.VERTICAL
        card.setPadding(20)
        card.setBackgroundResource(R.drawable.card_bg)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 12, 0, 12)
        card.layoutParams = params

        val tv = TextView(this)
        tv.text = "• $solucao"
        tv.textSize = 16f

        val btn = Button(this)
        btn.text = "Marcar como resolvido"
        btn.setBackgroundColor(resources.getColor(R.color.green_700))
        btn.setTextColor(resources.getColor(android.R.color.white))
        btn.visibility = View.GONE

        card.addView(tv)
        card.addView(btn)

        card.setOnClickListener {
            solucaoSelecionada = solucao
            btn.visibility = View.VISIBLE
        }

        btn.setOnClickListener {
            val chamado = criarChamadoObj(userId)
            scope.launch {
                val r = withContext(Dispatchers.IO) { chamadoController.criarChamado(chamado) }
                if (r.isSuccess) {
                    Toast.makeText(this@NewChamadoActivity, "Chamado resolvido e enviado!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@NewChamadoActivity, "Erro ao enviar solução!", Toast.LENGTH_LONG).show()
                }
            }
        }
        return card
    }
}
