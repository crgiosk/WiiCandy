package com.wiedii.wiicandy.UI.vistas


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wiedii.wiicandy.R
import com.wiedii.wiicandy.UI.ViewModel.MyCompraTestRecyclerViewAdapter

/**
 * A simple [Fragment] subclass.
 */
class fragment_compras : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compratest_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        Toast.makeText(context,"Welcome",Toast.LENGTH_LONG).show()
    }

    fun setRecycler(){
        val recyclerView = activity!!.findViewById<RecyclerView>(R.id.listRecyclerView)
        val adapter = MyCompraTestRecyclerViewAdapter()
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(context)
    }


}
