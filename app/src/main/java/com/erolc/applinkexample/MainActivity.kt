package com.erolc.applinkexample

import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).setOnClickListener {
            share("http://erolc.github.io.example")
        }

        findViewById<TextView>(R.id.copy).setOnClickListener {
            copy("https://erolc.github.io/applink")
        }
        findViewById<TextView>(R.id.copy1).setOnClickListener {
           copy("https://erolc.io/link")
        }


        val i = Intent()
        i.data = Uri.parse("https://erolc.github.io/applink")

        Log.e("shixiangyu", i.toUri(Intent.URI_INTENT_SCHEME))
    }

    fun copy(url: String) {
        val i = Intent()
        val parse = Uri.parse(url)
        i.data = parse
        clipboard.copyUri(object : ContentResolver(this) {
        },parse)
        Toast.makeText(this,"copy",Toast.LENGTH_SHORT).show()
    }
}
