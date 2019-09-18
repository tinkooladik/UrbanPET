package ua.prom.data.local

import android.content.SharedPreferences
import java.lang.Double.doubleToRawLongBits
import java.lang.Double.longBitsToDouble

class PreferenceHelper(val prefs: SharedPreferences) {

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun set(key: String, value: Any?) {
        prefs[key] = value
    }

    inline operator fun <reified T : Any> get(key: String, defaultValue: T? = null): T? =
        prefs[key, defaultValue]

    operator fun SharedPreferences.set(key: String, value: Any?) {
        if (value == null) {
            edit { it.remove(key) }
        } else {
            when (value) {
                is String? -> edit { it.putString(key, value) }
                is Int -> edit { it.putInt(key, value) }
                is Boolean -> edit { it.putBoolean(key, value) }
                is Float -> edit { it.putFloat(key, value) }
                is Long -> edit { it.putLong(key, value) }
                is Double -> edit { it.putLong(key, doubleToRawLongBits(value)) }
                else -> throw UnsupportedOperationException("Not yet implemented")
            }
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> if (contains(key)) getInt(key, defaultValue as? Int ?: -1) as T? else null
            Long::class -> if (contains(key)) getLong(key, defaultValue as? Long ?: -1) as T? else null
            Float::class -> if (contains(key)) getFloat(key, defaultValue as? Float ?: -1f) as T? else null
            Boolean::class -> if (contains(key)) getBoolean(key, defaultValue as? Boolean ?: false) as T? else null
            Double::class -> (longBitsToDouble(getLong(key, doubleToRawLongBits(defaultValue as Double)))) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}