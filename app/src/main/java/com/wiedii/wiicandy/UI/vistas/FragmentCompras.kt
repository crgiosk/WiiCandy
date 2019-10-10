package com.wiedii.wiicandy.UI.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wiedii.wiicandy.CompraInteractionListener
import com.wiedii.wiicandy.Helpers.Compra
import com.wiedii.wiicandy.Helpers.CompraCrud
import com.wiedii.wiicandy.R

class FragmentCompras : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var compralist: ArrayList<Compra>
    private var listener: CompraInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_compra_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                val crud=CompraCrud(context)
                crud.triggerGetCompras()
                compralist = ArrayList()

                compralist=crud.getCompras()
                adapter = CompraRecyclerViewAdapter(compralist, listener)
            }
        }
        return view
    }

/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CompraInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }
 */

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FragmentCompras().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
