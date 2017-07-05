package edu.galileo.mvp

/**
 * Created by steve on 7/5/17.
 */
interface LoginView {
    fun showProgress(showProgress: Boolean): Unit
    fun setUserNameError(messageResId: Int): Unit
    fun setPasswordError(messageResId: Int): Unit
    fun successAction() : Unit
}
