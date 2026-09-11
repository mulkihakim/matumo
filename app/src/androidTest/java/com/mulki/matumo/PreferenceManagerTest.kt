package com.mulki.matumo

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mulki.matumo.data.local.PreferenceManager
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreferenceManagerTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private lateinit var preferenceManager: PreferenceManager

    @Before
    fun setUp() {
        context.getSharedPreferences("matumo_preferences", 0)
            .edit()
            .clear()
            .commit()
        preferenceManager = PreferenceManager(context)
    }

    @Test
    fun returnsDemoPasswordWhenNoPasswordHasBeenSaved() {
        assertEquals("user", preferenceManager.getPassword())
    }

    @Test
    fun returnsNewPasswordAfterSavingIt() {
        preferenceManager.savePassword("password-baru")

        assertEquals("password-baru", preferenceManager.getPassword())
    }
}
