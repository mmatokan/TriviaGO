package hr.fer.ruazosa.networkquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            var emailAddress: String = emailEditText.text.toString()
            var beforeEt: Boolean = false
            var containsEt: Boolean = false
            var betweenEtAndDot: Boolean = false
            var containsDot: Boolean = false
            var afterDot: Boolean = false
            for (c in emailAddress) {
                if (beforeEt == true) {
                    if (containsEt == true) {
                        if (c == '@') break
                        if (betweenEtAndDot == true) {
                            if (containsDot == true) {
                                if (afterDot == true) {
                                    break
                                } else {
                                    if (c != '.')
                                        afterDot = true
                                    else
                                        break
                                }
                            } else {
                                if (c == '.')
                                    containsDot = true
                            }
                        } else {
                            if (c == '.')
                                break
                            else
                                betweenEtAndDot = true
                        }
                    } else {
                        if (c == '@')
                            containsEt = true
                    }
                } else {
                    if (c in 'a'..'z' || c in 'A'..'Z' || c in '0'..'9')
                        beforeEt = true
                    else
                        break
                }
            }
            if (beforeEt == true) println(message = "beforeEt je true") else println(message = "beforeEt je false")
            if (containsEt == true) println(message = "containsEt je true") else println(message = "containsEt je false")
            if (betweenEtAndDot == true) println(message = "between je true") else println(message = "between je false")
            if (containsDot == true) println(message = "containsDot je true") else println(message = "containsDot je false")

            if (afterDot == false) {
                Toast.makeText(this, "Email address is not valid", Toast.LENGTH_LONG).show()
            }
        }
    }
}