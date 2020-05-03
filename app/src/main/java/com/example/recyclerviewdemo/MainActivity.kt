package com.example.recyclerviewdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private var data = mutableListOf<Obj>(
        Obj("123", "This is the first data."),
        Obj("456", "This is the second data."),
        Obj("789", "This is the third data.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupAdapter()
    }
    private fun setupAdapter(){
        rvMain.adapter = ObjAdapter(this, data)
        rvMain.layoutManager = LinearLayoutManager(this)
    }
}

class Obj(val id: String, val detail: String)

class ObjAdapter(val context: Context, val objs: List<Obj>): RecyclerView.Adapter<ObjAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val ib = itemView.findViewById<ImageView>(R.id.ib)
        private val tvId = itemView.findViewById<TextView>(R.id.tvId)
        private val tvDetail = itemView.findViewById<TextView>(R.id.tvDetail)

        fun bind(obj: Obj){
            tvId.text = obj.id
            tvDetail.text = obj.detail
            ib.setOnClickListener(View.OnClickListener {
                val builder = StringBuilder()
                builder.append("Id: ").append(obj.id).append(", Detail: ").append(obj.detail)
                Log.d("button event", builder.toString())
            })
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(objs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_obj, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = this.objs.count()
}