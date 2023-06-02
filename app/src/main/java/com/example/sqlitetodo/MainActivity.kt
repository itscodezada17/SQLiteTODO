package com.example.sqlitetodo

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitetodo.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var  todos = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todos.add("absc")
        todos.add("absjd")
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)


        val todoAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.simple_list_item_1, todos)


         binding.lv.adapter = todoAdapter
        binding.addBtn.setOnClickListener {
            val newTodo = binding.editToDo.text.toString()
            todos.add(newTodo)
            saveData()
            binding.editToDo.hint= "write Your Tasks"
            todoAdapter.notifyDataSetChanged()

        }


    }

   /* private fun loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        // creating a variable for gson.
        val gson = Gson()

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        val json = sharedPreferences.getString("todos", null)


        // below line is to get the type of our array list.
        val type: Type = object : TypeToken<ArrayList<String>>() {}.type

        // in below line we are getting data from gson
        // and saving it to our array list
        gson.fromJson<String>(json, type).also { todos = it as ArrayList<String> }

        // checking below if the array list is empty or not
    }
*/
    private fun saveData() {
        // method for saving the data in array list.
        // creating a variable for storing data in
        // shared preferences.
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)

        // creating a variable for editor to
        // store data in shared preferences.
        val editor = sharedPreferences.edit()

        // creating a new variable for gson.
        val gson = Gson()

        // getting data from gson and storing it in a string.
        val json: String = gson.toJson(todos)

        // below line is to save data in shared
        // prefs in the form of string.
        editor.putString("todos", json)

        // below line is to apply changes
        // and save data in shared prefs.
        editor.apply()

        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show()
    }
}