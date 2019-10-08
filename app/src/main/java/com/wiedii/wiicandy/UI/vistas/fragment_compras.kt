package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wiedii.wiicandy.R
import com.wiedii.wiicandy.UI.ViewModel.CompraListAdapter
import com.wiedii.wiicandy.UI.ViewModel.CompraViewModel

/**
 * A simple [Fragment] subclass.
 */
class fragment_compras : Fragment() {
private lateinit var compraViewModel: CompraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compratest_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context=activity!!.applicationContext
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.listRecyclerView)

        //set adapter
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        //set viewModel


        Toast.makeText(context,"Welcome",Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compraViewModel = ViewModelProviders.of(this).get(CompraViewModel::class.java)
        compraViewModel.allCompras.observe(this, Observer {
                compras -> compras?.let { adapter.setCompras(it) }
        })
    }

}
