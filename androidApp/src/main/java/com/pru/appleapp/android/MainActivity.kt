package com.pru.appleapp.android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.pru.appleapp.android.databinding.ActivityMainBinding
import com.pru.appleapp.android.passengers.PassengersViewModel
import com.pru.appleapp.utils.ApiState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<PassengersViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFetch.setOnClickListener {
            viewModel.getProduct()
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.productData.collect {
                        when (it) {
                            is ApiState.Failure -> Toast.makeText(
                                this@MainActivity,
                                it.errorMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                            is ApiState.Loading -> Toast.makeText(
                                this@MainActivity,
                                "Loading",
                                Toast.LENGTH_SHORT
                            ).show()
                            is ApiState.Success -> Log.i("Prudhvi Log", "onCreate: ${it.data}")
                            else -> Unit
                        }
                    }
                }
                launch {
                    viewModel.productsData.collect {
                        when (it) {
                            is ApiState.Failure -> Log.i("Prudhvi Log", "onCreate: $it")
                            is ApiState.Initial -> Log.i("Prudhvi Log", "onCreate: $it")
                            is ApiState.Loading -> Log.i("Prudhvi Log", "onCreate: $it")
                            is ApiState.Success -> Log.i("Prudhvi Log", "onCreate: $it")
                        }
                    }
                }
            }
        }
    }
}
