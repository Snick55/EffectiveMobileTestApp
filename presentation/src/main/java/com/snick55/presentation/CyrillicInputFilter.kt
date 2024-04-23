package com.snick55.presentation

import android.text.InputFilter
import android.text.Spanned

class CyrillicInputFilter : InputFilter {
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        for (i in start until end) {
            if (!Character.isLetter(source[i]) || !isCyrillic(source[i])) {
                return ""
            }
        }
        return null
    }

    private fun isCyrillic(c: Char): Boolean {
        return (c.code in 0x0400..0x04FF) || (c.code in 0x0500..0x052F)
    }
}