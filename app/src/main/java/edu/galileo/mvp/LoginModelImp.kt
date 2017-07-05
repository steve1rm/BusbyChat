package edu.galileo.mvp

import android.os.AsyncTask

/**
 * Created by steve on 7/5/17.
 */
class LoginModelImp : LoginModel {

    private lateinit var listener: LoginModel.OnLoginFinishedListener

    private val DUMMY_CREDENTIALS =
            arrayOf("test@galileo.edu:testtest", "test@galileo.edu:testtest")

    override
    fun login(username: String, password: String, listener: LoginModel.OnLoginFinishedListener) {
        this.listener = listener

        UserLoginTask(username, password).execute()
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class UserLoginTask internal constructor(private val mEmail: String, private val mPassword: String) : AsyncTask<Void, Void, Boolean>() {

        override
        fun doInBackground(vararg params: Void): Boolean? {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                return false
            }

            for(credential in DUMMY_CREDENTIALS) {
                val pieces = credential.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (pieces[0] == mEmail) {
                    // Account exists, return true if the password matches.
                    return pieces[1] == mPassword
                }
            }

            // TODO: register the new account here.
            return false
        }

        override
        fun onPostExecute(success: Boolean?) {
            if(success!!) {
                listener.onSuccess()
            }
            else {
                listener.onPasswordError()
            }
        }

        override
        fun onCancelled() {
            listener.onCanceled()
        }
    }
}