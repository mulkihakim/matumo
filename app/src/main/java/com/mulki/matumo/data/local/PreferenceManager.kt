package com.mulki.matumo.data.local

import android.content.Context

class PreferenceManager(

    context: Context

) {

    private val sharedPreferences =
        context.getSharedPreferences(
            "matumo_preferences",
            Context.MODE_PRIVATE
        )

    fun getPassword(): String {

        return sharedPreferences.getString(
            "password",
            "user"
        ) ?: "user"
    }

    fun savePassword(
        passwordBaru: String
    ) {

        sharedPreferences.edit()
            .putString(
                "password",
                passwordBaru
            )
            .apply()
    }
}