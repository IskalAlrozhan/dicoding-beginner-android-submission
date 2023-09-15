package com.alrozhan.valorantagent

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        supportActionBar!!.setTitle(Html.fromHtml("<font color=\"#ffffff\">"+getString(R.string.app_name)+"</font>"))

        val data = intent.getParcelableExtra<Agent>("DATA")

        if (data != null){
            val nameText = data.name
            val roleText = data.role
            val bioText = data.biography
            val imgRole = data.roleicon
            val imgBanner = data.banner

            val tvItemName = findViewById<TextView>(R.id.tv_item_name)
            val tvItemRole = findViewById<TextView>(R.id.tv_item_role)
            val tvItemBiography = findViewById<TextView>(R.id.tv_item_biography)
            val ivRole = findViewById<ImageView>(R.id.img_item_role)
            val ivBanner = findViewById<ImageView>(R.id.img_item_banner)

            tvItemName.text = nameText
            tvItemRole.text = roleText
            tvItemBiography.text = bioText
            ivRole.setImageResource(imgRole)
            ivBanner.setImageResource(imgBanner)
        }

        val btnShare: Button = findViewById(R.id.action_share)
        btnShare.setOnClickListener{
            shareContent()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun goAboutPage() {
        val intent = Intent(this@DetailedActivity, AboutActivity::class.java)
        startActivity(intent)
    }

    private fun shareContent() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hai ini adalah fitur share yang saya buat")

        startActivity(Intent.createChooser(shareIntent, "Bagikan menggunakan"))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.about_page ->{
                goAboutPage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}