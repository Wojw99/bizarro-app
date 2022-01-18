package com.example.bizarro.utils

object ValidationHelper {
    fun validateRegisterFields(userName: String, email: String, password: String, passwordRepeat: String): String {
        if (userName.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || passwordRepeat.isEmpty()
        ) {
            return Strings.emptyFieldsError
        } else if (!CommonMethods.isValidEmail(email)) {
            return Strings.emailIncorrectError
        } else if (password != passwordRepeat) {
            return Strings.passwordNotEqualsError
        }
        return ""
    }

    fun validateEmail(email: String): String {
        if (email.isEmpty() ) {
            return Strings.emptyFieldsError
        } else if (!CommonMethods.isValidEmail(email)) {
            return Strings.emailIncorrectError
        }
        return Strings.empty
    }

    fun validatePasswordAndCode(code: String, password: String, passwordRepeat: String): String {
        if (code.isEmpty()
            || password.isEmpty()
            || passwordRepeat.isEmpty()
        ) {
            return Strings.emptyFieldsError
        } else if (password != passwordRepeat) {
            return Strings.passwordNotEqualsError
        }
        return Strings.empty
    }
}