package com.alrozhan.valorantagent

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAgent: RecyclerView
    private val list = ArrayList<Agent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar!!.setTitle(Html.fromHtml("<font color=\"#ffffff\">"+getString(R.string.app_name)+"</font>"))

        rvAgent = findViewById(R.id.rv_agents)
        rvAgent.setHasFixedSize(true)

        list.addAll(getListAgent())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getListAgent(): ArrayList<Agent> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataRole = resources.getStringArray(R.array.data_role)
        val dataIcon = resources.obtainTypedArray(R.array.data_icon)
        val dataBiography = resources.getStringArray(R.array.data_biography)
        val dataRoleIcon = resources.obtainTypedArray(R.array.data_roleicon)
        val dataBanner = resources.obtainTypedArray(R.array.data_banner)
        val listAgent = ArrayList<Agent>()
        for (i in dataName.indices) {
            val agent = Agent(dataName[i], dataRole[i], dataIcon.getResourceId(i, -1), dataBiography[i],
                        dataRoleIcon.getResourceId(i, -1), dataBanner.getResourceId(i, -1))
            listAgent.add(agent)
        }
        dataIcon.recycle()
        dataRoleIcon.recycle()
        dataBanner.recycle()
        return listAgent
    }

    private fun showRecyclerList() {
        rvAgent.layoutManager = LinearLayoutManager(this)
        val listAgentAdapter = ListAgentAdapter(list)
        rvAgent.adapter = listAgentAdapter

        listAgentAdapter.setOnItemClickCallback(object : ListAgentAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Agent) {
                val intentToDetail = Intent(this@MainActivity, DetailedActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
                showSelectedAgent(data)
            }
        })
    }

    private fun showSelectedAgent(agent: Agent) {
        Toast.makeText(this, "You choose " + agent.name, Toast.LENGTH_SHORT).show()
    }

    private fun goAboutPage() {
        val intent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about_page ->{
                goAboutPage()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}
