//package com.example.notekeeper.Adapter
//
//import android.content.Context
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.notekeeper.NoteInfo
//import com.example.notekeeper.R
//
//class NoteAdapter(private val context: Context,private val notes:List<NoteInfo>): RecyclerView.Adapter<NoteAdapter.ViewHolder2>() {
//
//    private val LayoutInflater = android.view.LayoutInflater.from(context)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
//        val itemView = LayoutInflater.inflate(R.layout.item_note_list,parent,false)
//        return ViewHolder2(itemView)
//
//    }
//
//    override fun getItemCount(): Int {
//        return notes.size
//
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
//        val note = notes[position]
//        holder.textCourse.text = note.text
//        holder.textTitle.text = note.course?.title
//    }
//
//    class ViewHolder2(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val textCourse =itemView.findViewById<TextView>(R.id.textCourse)
//        val textTitle =itemView.findViewById<TextView>(R.id.textTitle)
//    }
//}