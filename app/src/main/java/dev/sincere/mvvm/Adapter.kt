package dev.sincere.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.sincere.mvvm.databinding.ItemNoteBinding

class NoteAdapter(private val onDeleteClicked: (note: Note) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding, onDeleteClicked)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    class NoteHolder(private val binding: ItemNoteBinding, private val onDeleteClicked: (note: Note) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.textView.text = note.title
            binding.textView2.text = note.content

            binding.imageView.setOnClickListener {
                onDeleteClicked(note)
            }
        }
    }
}