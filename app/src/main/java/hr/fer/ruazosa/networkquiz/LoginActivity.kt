package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUpButton.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            val username: String = usernameEditText.text.toString()
            val password: String = passwordEditText.text.toString()

            val user = ShortUser(
                username,
                password
            )

            LoginUser().execute(user)
        }
    }

    private inner class LoginUser: AsyncTask<ShortUser, Void, User?>() {

        override fun doInBackground(vararg user: ShortUser): User? {
            val rest = RestFactory.instance
            return rest.loginUser(user[0])
        }

        override fun onPostExecute(user: User?) {
            if (user != null) {
                //if user successfully logged in start profile activity
            }
            else{
                val toast = Toast.makeText(applicationContext ,"Login failed!",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

        }
    }
}