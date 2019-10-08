package com.wiedii.wiicandy.UI.ViewModel

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.wiedii.wiicandy.DAO.Compra
import com.wiedii.wiicandy.R

class MyCompraTestRecyclerViewAdapter : RecyclerView.Adapter<MyCompraTestRecyclerViewAdapter.ViewHolder>() {
    private var compras = emptyList<Compra>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_compratest, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = compras[position]

        holder.producto.text = item.producto
        holder.cantidad.text = item.cantidad
        holder.total.text = item.total.toString()
        holder.fecha.text = item.fecha
    }
    internal fun setCompras(compras: List<Compra>){
        this.compras=compras
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = compras.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val producto: TextView = mView.findViewById(R.id.textViewProducto)
        val cantidad: TextView = mView.findViewById(R.id.textViewCantidad)
        val total: TextView = mView.findViewById(R.id.textViewTotal)
        val fecha: TextView = mView.findViewById(R.id.textViewFecha)

        override fun toString(): String {
            return super.toString() + " '" + producto.text + "'"
        }
    }
}
