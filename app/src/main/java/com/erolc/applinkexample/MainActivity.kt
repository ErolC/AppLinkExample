package com.erolc.applinkexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.text).setOnClickListener {
            share("https://github.com/ErolC/AppLinkExample/blob/master/example.html",filter = WECHAT)
        }
    }
}
