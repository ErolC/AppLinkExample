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
            share("https://erolc.github.io/example", filter = WECHAT_FRIENDS)
        }

        findViewById<TextView>(R.id.copy).setOnClickListener {
            val i = Intent()
            val parse = Uri.parse("https://erolc.github.io/applink")
            i.data = parse
            clipboard.copyUri(object : ContentResolver(this) {
            },parse)
            Toast.makeText(this,"copy",Toast.LENGTH_SHORT).show()
        }


        val i = Intent()
        i.data = Uri.parse("https://erolc.github.io/applink")

        Log.e("shixiangyu", i.toUri(Intent.URI_INTENT_SCHEME))

    }
}
