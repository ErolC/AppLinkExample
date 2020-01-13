package com.erolc.applinkexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).setOnClickListener {
            share("https://erolc.github.io/example", filter = WECHAT)
        }

        val i = Intent()
        i.data = Uri.parse("https://erolc.github.io/applink")

        Log.e("shixiangyu", i.toUri(Intent.URI_INTENT_SCHEME))

    }
}
