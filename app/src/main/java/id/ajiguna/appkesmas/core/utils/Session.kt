package id.ajiguna.appkesmas.core.utils

import android.content.Context
import android.content.SharedPreferences

class Session(var context: Context) {
    var prefs: SharedPreferences = context.getSharedPreferences("appkesmas", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = prefs.edit()
    fun setLogin(
        login: Boolean,
        id: String
    ) {
        editor.putBoolean("login", login)
        editor.putString("id", id)
        editor.commit()
    }

    fun login(): Boolean {
        return prefs.getBoolean("login", false)
    }
}