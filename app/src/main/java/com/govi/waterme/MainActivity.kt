package com.govi.waterme

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.govi.waterme.adapater.PlantAdapter
import com.govi.waterme.adapater.PlantListener
import com.govi.waterme.ui.ReminderDialogFragment
import com.govi.waterme.viewmodel.PlantViewModel
import com.govi.waterme.viewmodel.PlantViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: PlantViewModel by viewModels {
        PlantViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PlantAdapter(PlantListener { plant ->
            val dialog = ReminderDialogFragment(plant.name)
            dialog.show(supportFragmentManager, "WaterReminderDialogFragment")
            true
        })
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        val data = viewModel.plants
        adapter.submitList(data)
    }
}
