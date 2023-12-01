package com.whymaull.herbaid.utils.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import com.google.android.material.textfield.TextInputEditText
import com.whymaull.herbaid.R

class CustomEmail : TextInputEditText {
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                val email = s.toString()
                val isValidEmail = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

                if (isValidEmail){
                    error = null
                } else {
                    setError(context.getString(R.string.email_must_be_valid), null)
                }
            }
        })
    }
}