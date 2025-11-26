package br.unip.hermesbot.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.unip.hermesbot.R
import br.unip.hermesbot.controller.ChamadoController
import br.unip.hermesbot.util.PdfHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AdminDownloadActivity : BaseActivity() {

    private val scope = MainScope()
    private val chamadoController = ChamadoController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_download)

        val userEt = findViewById<EditText>(R.id.etFiltroUser)
        val statusEt = findViewById<EditText>(R.id.etFiltroStatus)
        val btn = findViewById<Button>(R.id.btnBaixar)

        btn.setOnClickListener {
            val userId = userEt.text.toString().toIntOrNull()
            val status = statusEt.text.toString().ifBlank { null }

            scope.launch {
                val r = withContext(Dispatchers.IO) { chamadoController.getChamados() }
                if (r.isFailure) {
                    Toast.makeText(this@AdminDownloadActivity, "Erro ao carregar chamados", Toast.LENGTH_LONG).show()
                    return@launch
                }

                var lista = r.getOrNull() ?: emptyList()
                userId?.let { uid -> lista = lista.filter { it.usuarioSolicitanteId == uid } }
                status?.let { st -> lista = lista.filter { it.status.equals(st, ignoreCase = true) } }

                val file = withContext(Dispatchers.IO) { PdfHelper.generateChamadosPdf(lista, "chamados_export.pdf") }
                Toast.makeText(this@AdminDownloadActivity, "PDF salvo em: ${file.path}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
