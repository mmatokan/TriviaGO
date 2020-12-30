package hr.fer.ruazosa.networkquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton.setOnClickListener {
            var emailAddress: String = emailEditText.text.toString()
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches())
                Toast.makeText(this, "Invalid e-mail format", Toast.LENGTH_LONG).show()
            }
        signUpButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}