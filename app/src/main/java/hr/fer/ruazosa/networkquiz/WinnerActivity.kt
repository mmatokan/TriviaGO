package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruazosa.networkquiz.entity.User

class WinnerActivity : AppCompatActivity() {

    var usernameTextView: TextView?=null
    var scoreTextView: TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        val score = intent.getSerializableExtra("score") as Long
        val username = intent.getSerializableExtra("username") as String
        val user = intent.getSerializableExtra("user") as User

        usernameTextView = findViewById<TextView?>(R.id.winner_un)
        scoreTextView = findViewById<TextView?>(R.id.score)
        usernameTextView?.text = username
        scoreTextView?.text = score.toString()


        val finishButton = findViewById<Button>(R.id.finish_button)
        finishButton.setOnClickListener{
            var profilIntent = Intent(this, MyProfileActivity::class.java)
            profilIntent.putExtra("user", user)
            startActivity(profilIntent)
            finish()

        }
        }
}