package com.wiedii.wiicandy.UI.ViewModel

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import com.wiedii.wiicandy.DAO.Compra
import com.wiedii.wiicandy.R

class CompraListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CompraListAdapter.CompraViewHolder>() {
    private var compras = emptyList<Compra>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class CompraViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val producto: TextView = mView.findViewById(R.id.textViewProducto)
        val cantidad: TextView = mView.findViewById(R.id.textViewCantidad)
        val total: TextView = mView.findViewById(R.id.textViewTotal)
        val fecha: TextView = mView.findViewById(R.id.textViewFecha)
        override fun toString(): String {
            Log.e("testT" , javaClass.simpleName)

            return super.toString() + " '" + producto.text + "'"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompraViewHolder {
        Log.d("testViewModel", javaClass.name)
        val view = inflater.inflate(R.layout.fragment_compratest, parent, false)
        return CompraViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompraViewHolder, position: Int) {
        val item = compras[position]

        holder.producto.text = item.producto
        holder.cantidad.text = item.cantidad
        holder.total.text = item.total.toString()
        holder.fecha.text = item.fecha

        Log.e("testT" ,"teste.toUpperCase()")
    }

    internal fun setCompras(compras: List<Compra>) {
        this.compras = compras
        notifyDataSetChanged()
        Log.e(javaClass.name, compras[0].toString())
    }

    override fun getItemCount(): Int = compras.size


}
