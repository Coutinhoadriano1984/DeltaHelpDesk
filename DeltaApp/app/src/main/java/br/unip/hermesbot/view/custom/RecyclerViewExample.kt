package br.unip.hermesbot.view.custom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.unip.hermesbot.R
import android.widget.TextView
class ChamadoAdapter(
    private val lista: List<ChamadoExample>
) : RecyclerView.Adapter<ChamadoAdapter.ChamadoViewHolder>() {

    inner class ChamadoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val descricao = itemView.findViewById<TextView>(R.id.txtDescricao)
        val criticidade = itemView.findViewById<TextView>(R.id.txtCriticidade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChamadoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chamado, parent, false)
        return ChamadoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChamadoViewHolder, position: Int) {
        val item = lista[position]

        holder.titulo.text = item.titulo
        holder.descricao.text = item.descricao
        holder.criticidade.text = item.criticidade
    }

    override fun getItemCount(): Int = lista.size
}
