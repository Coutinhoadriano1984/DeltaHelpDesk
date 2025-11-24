package br.unip.hermesbot.util

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import br.unip.hermesbot.model.Chamado
import java.io.File
import java.io.FileOutputStream

object PdfHelper {

    fun generateChamadosPdf(lista: List<Chamado>, filename: String): File {
        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = doc.startPage(pageInfo)
        val c: Canvas = page.canvas
        val p = Paint().apply { textSize = 12f }

        var y = 40
        c.drawText("Relatório de Chamados", 20f, y.toFloat(), p)
        y += 30
        c.drawText("ID | Usuário | Título | Status | Prioridade", 20f, y.toFloat(), p)
        y += 20

        for (it in lista) {
            val id = it.chamadoId ?: -1
            val usuario = it.usuarioSolicitanteId ?: -1
            val titulo = it.titulo ?: ""
            val status = it.status ?: ""
            val prioridade = it.prioridade ?: ""
            val text = "$id | $usuario | ${if (titulo.length > 30) titulo.take(30) + "..." else titulo} | $status | $prioridade"
            c.drawText(text, 20f, y.toFloat(), p)
            y += 18
            if (y > 800) break
        }

        doc.finishPage(page)

        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        if (!dir.exists()) dir.mkdirs()
        val file = File(dir, filename)
        doc.writeTo(FileOutputStream(file))
        doc.close()
        return file
    }
}
