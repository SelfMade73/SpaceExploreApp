package com.example.spaceinfo

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

fun Context.toast( msg : String ){
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Context.toast( strRes : Int ){
    Toast.makeText(this, getString(strRes), Toast.LENGTH_LONG).show()
}

fun FragmentActivity.getSharedPref( file_key : Int ) = this.getSharedPreferences(getString(file_key), Context.MODE_PRIVATE)