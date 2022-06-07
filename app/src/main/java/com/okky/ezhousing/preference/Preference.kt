package com.okky.ezhousing.preference

import android.content.Context
import com.okky.ezhousing.model.UserModel

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val TOKEN = "token"
        private const val IS_LOGIN = "islogin"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun setUser(value: UserModel) {
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putString(TOKEN, value.token)
        editor.putBoolean(IS_LOGIN, value.isLogin)
        editor.apply()
    }

    fun getUser() : UserModel {
        val model = UserModel()
        model.name = preferences.getString(NAME, "").toString()
        model.email = preferences.getString(EMAIL, "").toString()
        model.token = preferences.getString(TOKEN, "").toString()
        model.isLogin = preferences.getBoolean(IS_LOGIN, false)

        return model
    }

    fun logoutUser() {
        editor.clear()
        editor.apply()
    }
}