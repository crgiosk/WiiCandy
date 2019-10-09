package com.wiedii.wiicandy

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wiedii.wiicandy.Helpers.Compra

import kotlinx.android.synthetic.main.fragment_mycompra.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMyCompraRecyclerViewAdapter(
    private val mValues: List<Compra>,
    private val mListener: CompraInteractionListener?
) : RecyclerView.Adapter<MyMyCompraRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Compra
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.nuevaCompra(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_mycompra, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.cantidad.text = item.cantidad
        holder.producto.text = item.producto
        holder.total.text = item.total
        holder.fecha.text = item.fecha

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val producto: TextView = mView.textViewProducto
        val cantidad: TextView = mView.textViewCantidad
        val total: TextView = mView.textViewTotal
        val fecha: TextView = mView.textViewFecha

        override fun toString(): String {
            return super.toString() + " '" + producto.text + "'"
        }
    }
}
