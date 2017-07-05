package edu.galileo.mvp

/**
 * Created by steve on 7/5/17.
 */
interface LoginModel {
    interface OnLoginFinishedListener {
        fun onCanceled(): Unit
        fun onPasswordError(): Unit
        fun onSuccess(): Unit
    }

    fun login(username: String, password: String, listener: OnLoginFinishedListener): Unit
}
