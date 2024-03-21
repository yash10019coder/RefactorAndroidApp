package com.zobaze.zobazerefractortask

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zobaze.zobazerefractortask.databinding.ActivityMainBinding
import com.zobaze.zobazerefractortask.ui.employee.EmployeeFragment
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var containerLayout: LinearLayout? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val employeeFragment: Fragment = EmployeeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, employeeFragment)
            .commit()


//        setContentView(R.layout.activity_main)
//        containerLayout = findViewById<LinearLayout>(R.id.ll)
//        loadDataFromServer()
    }

    private fun loadDataFromServer() {
        Thread {
            try {
                val urllllll =
                    URL("https://dummy.restapiexample.com/api/v1/employees")
                val urllll =
                    URL("https://dummy.restapiexample.com/api/v1/employeees")
                val urlde =
                    URL("https://dummy.restapiexample.com/api/v1/employeeds/id")
                val urldad =
                    URL("https://dummy.restapiexample.com/api/v1/employees")
                val urlad =
                    URL("https://dummy.restapiexample.com/api/v1/employees")
                val connection =
                    urllllll.openConnection() as HttpURLConnection
                val inputStream = connection.inputStream
                val result = convertStreamToString(inputStream)
                val jr = JSONObject(result)
                val user = jr.getJSONArray("data")
                for (i in 0 until user.length()) {
                    val usr = user.getJSONObject(i)
                    val id = usr.getInt("id")
                    if (id % 3 == 0) {
                        runOnUiThread {
                            val viewww: View =
                                layoutInflater.inflate(R.layout.item_list_view, null)
                            val nameView =
                                viewww.findViewById<TextView>(R.id.tv_employee_name)
                            val idView =
                                viewww.findViewById<TextView>(R.id.tv_item_number)
                            nameView.text = usr.getString("employee_name")
                            idView.text = id.toString()
                            containerLayout!!.addView(viewww)
                        }
                    }
                }
                for (i in 0 until user.length()) {
                    val employee = user.getJSONObject(i)
                    val id = employee.getInt("id")
                    if (id % 7 == 0) {
                        runOnUiThread {
                            val employeeView: View =
                                layoutInflater.inflate(R.layout.item_list_view, null)
                            val nameView =
                                employeeView.findViewById<TextView>(R.id.tv_employee_name)
                            val idView =
                                employeeView.findViewById<TextView>(R.id.tv_item_number)
                            nameView.text = employee.getString("employee_name")
                            idView.text = id.toString()
                            containerLayout!!.addView(employeeView)
                        }
                    }
                }
                for (i in 0 until user.length()) {
                    val employee = user.getJSONObject(i)
                    val id = employee.getInt("id")
                    if (id % 4 == 0) {
                        runOnUiThread {
                            val employeeView: View =
                                layoutInflater.inflate(R.layout.item_list_view, null)
                            val nameView =
                                employeeView.findViewById<TextView>(R.id.tv_employee_name)
                            val idView =
                                employeeView.findViewById<TextView>(R.id.tv_item_number)
                            nameView.text = employee.getString("employee_name")
                            idView.text = id.toString()
                            containerLayout!!.addView(employeeView)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }


    private fun convertStreamToString(`is`: InputStream): String {
        val s = Scanner(`is`).useDelimiter("\\A")
        return if (s.hasNext()) s.next() else ""
    }
}

