package com.example.vmcassignment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    var names: ArrayList<DataModel>? = null
    var adapter: CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonFileString = Utils.getJsonFromAssets(applicationContext, "data.json")
//        Log.i("data", jsonFileString)
        val gson = Gson()
        val listUserType: Type = object : TypeToken<List<DataModel?>?>() {}.type

        val users: List<DataModel> = gson.fromJson(jsonFileString, listUserType)
        names = ArrayList()

        for (i in users.indices) {
//            Log.i("data", """> Item $i${users[i]}""".trimIndent())
//            Log.i("data",users.get(i).title)
            names!!.add(users.get(i))
        }
        rv!!.setHasFixedSize(true)
        rv!!.layoutManager = LinearLayoutManager(this)
        adapter = CustomAdapter(names)
        rv!!.adapter = adapter
        edtSearch!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
                adapter?.notifyDataSetChanged()
            }
        })
    }

    private fun filter(text: String) {
        val filterdNames: ArrayList<DataModel> = ArrayList()
        for (s in names!!) {
            if (s.title!!.contains(text.toLowerCase())) {
                filterdNames.add(s)
            }
        }
        adapter?.filterList(filterdNames)
    }
}
