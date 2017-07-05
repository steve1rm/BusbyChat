package edu.galileo.mvp

/**
 * Created by steve on 7/5/17.
 */
interface LoginPresenter {
    fun validateCredentials(username: String, password: String): Unit
}
