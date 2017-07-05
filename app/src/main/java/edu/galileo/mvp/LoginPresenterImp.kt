package edu.galileo.mvp

/**
 * Created by steve on 7/5/17.
 */
class LoginPresenterImp(_loginView: LoginView) : LoginPresenter, LoginModel.OnLoginFinishedListener {
    private val loginView = _loginView
    private val loginModel: LoginModel

    init {
        this.loginModel = LoginModelImp()
    }

    override
    fun validateCredentials(username: String, password: String) {
        // Check for a valid password, if the user entered one.
        if(!password.isEmpty() && !isPasswordValid(password)) {
            loginView.setPasswordError(R.string.error_invalid_password)
        }
        else if(username.isEmpty()) {
            // Check for a valid email address.
            loginView.setUserNameError(R.string.error_field_required)
        }
        else if(!isEmailValid(username)) {
            loginView.setUserNameError(R.string.error_invalid_email)
        }
        else {
            loginView.showProgress(true)
            loginModel.login(username, password, this)
        }
    }

    override
    fun onCanceled() {
        loginView.showProgress(false)
    }

    override
    fun onPasswordError() {
        loginView.showProgress(false)
        loginView.setPasswordError(R.string.error_incorrect_password)
    }

    override
    fun onSuccess() {
        loginView.showProgress(false)
        loginView.successAction()
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
}