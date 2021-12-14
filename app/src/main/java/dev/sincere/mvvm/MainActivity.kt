package dev.sincere.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dev.sincere.mvvm.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        observe()
        setContentView(binding.root)
    }



    private fun bind() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    private fun observe() {
        lifecycleScope.launch {
            vm.notes.flowWithLifecycle(lifecycle).collect { data ->
                with(binding.recyclerview) {
                    val adapter = adapter as? NoteAdapter ?: NoteAdapter { vm.deleteNote(it) }.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = this
                    }
                    adapter.notes = data
                }
            }
        }
    }
}