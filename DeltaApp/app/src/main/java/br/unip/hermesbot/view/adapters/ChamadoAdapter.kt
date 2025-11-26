package br.unip.hermesbot.view.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.unip.hermesbot.R
import br.unip.hermesbot.controller.ChamadoController
import br.unip.hermesbot.model.Chamado
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChamadoAdapter(
    private val lista: MutableList<Chamado>,
    private val role: String
) : RecyclerView.Adapter<ChamadoAdapter.Holder>() {

    inner class Holder(v: View) : RecyclerView.ViewHolder(v) {
        val titulo: TextView = v.findViewById(R.id.itemTitulo)
        val status: TextView = v.findViewById(R.id.itemStatus)
        val data: TextView = v.findViewById(R.id.itemData)
        val btnFinalizar: Button = v.findViewById(R.id.btnFinalizar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_chamado, parent, false)
        return Holder(v)
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val c = lista[position]

        holder.titulo.text = c.titulo ?: ""
        holder.status.text = c.status ?: ""
        holder.data.text = c.dataAbertura?.take(10) ?: ""

        if (!role.equals("admin", ignoreCase = true)) {
            holder.btnFinalizar.visibility = View.GONE
            return
        }

        holder.btnFinalizar.visibility = View.VISIBLE
        holder.btnFinalizar.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Fechar Chamado")
                .setMessage("Deseja realmente fechar o chamado #${c.chamadoId}?")
                .setPositiveButton("Sim") { _, _ ->

                    holder.btnFinalizar.isEnabled = false

                    MainScope().launch {
                        val controller = ChamadoController()

                        val r = withContext(Dispatchers.IO) {
                            controller.atualizarChamado(
                                c.chamadoId ?: 0,
                                c.copy(status = "Fechado")
                            )
                        }

                        if (r.isSuccess) {
                            holder.status.text = "Fechado"
                            holder.btnFinalizar.visibility = View.GONE
                            Toast.makeText(holder.itemView.context, "Chamado fechado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(holder.itemView.context, "Erro ao fechar", Toast.LENGTH_LONG).show()
                            holder.btnFinalizar.isEnabled = true
                        }
                    }
                }
                .setNegativeButton("Não", null)
                .show()
        }
    }

    fun updateList(nova: List<Chamado>) {
        lista.clear()
        lista.addAll(nova)
        notifyDataSetChanged()
    }
}
