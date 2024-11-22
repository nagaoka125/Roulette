package com.example.roulette

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.EditText
//import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.roulette.ui.theme.RouletteTheme
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")

    //input list
    private val inputList = ArrayList<String>()

    @SuppressLint("MissingInflatedId", "SetTextI18n", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //layout
        setContentView(R.layout.activity_main)

        //get value
        val editTextInput = findViewById<EditText>(R.id.editText)
        val addButton = findViewById<Button>(R.id.addButton)
       //val listView = findViewById<ListView>(R.id.contentText)
        val spinner: Spinner = findViewById(R.id.optionSpinner)
        val button: Button = findViewById(R.id.rollButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val resetButton = findViewById<Button>(R.id.resetButton)

        //edit adapt
        //val editAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inputList)
        //listView.adapter = editAdapter


        addButton.setOnClickListener {
            val userInput = editTextInput.text.toString()
            if (userInput.isNotEmpty()) {
                inputList.add(userInput)
                //editAdapter.notifyDataSetChanged()
                updateSpinner(spinner)
                editTextInput.text.clear()
            }
        }

        button.setOnClickListener{
            if (inputList.isNotEmpty()) {
                val randomOption = inputList.random()
                resultTextView.text = "result: $randomOption"
            }
        }

        resetButton.setOnClickListener{
            inputList.clear()
            spinner.adapter = null
            spinner.setSelection(0)
            updateSpinner(spinner)

        }
    }

    private fun updateSpinner(spinner: Spinner) {
        val options = inputList.toTypedArray()
        val spinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinAdapter

    }

    private fun resetList() {

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RouletteTheme {
        Greeting("Android")
    }
}