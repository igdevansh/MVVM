package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: CounterViewModel = viewModel()
            MVVMTheme {
                CounterApp(vm)
            }
        }
    }
}

@Composable
fun CounterApp(viewModel: CounterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Count : ${viewModel.count.value}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(onClick = { viewModel.increment() }) {
                Text(text = "Increment")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { viewModel.decrement() }) {
                Text(text = "Decrement")
            }
        }
    }
}

class CounterViewModel() : ViewModel() {
    private val _repository: CounterRepository = CounterRepository()

    private val _count = mutableStateOf(_repository.getCounter.count)

    val count: MutableState<Int> = _count

    fun increment() {
        _repository.Incrementcounter()
        _count.value = _repository.getCounter.count
    }

    fun decrement() {
        _repository.Decrementcounter()
        _count.value = _repository.getCounter.count
    }
}


data class CounterModel(var count: Int)

class CounterRepository{
    private var _counter = CounterModel(0)

    var getCounter = _counter

    fun Incrementcounter(){
        _counter.count++
    }

    fun Decrementcounter(){
        _counter.count--
    }

}
