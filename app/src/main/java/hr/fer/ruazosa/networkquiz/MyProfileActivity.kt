package hr.fer.ruazosa.networkquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_my_profile.*


class MyProfileActivity : AppCompatActivity() {

    var position : TextView? = null
    var gamesNumber: TextView? = null
    var accuracyPercentage : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        position = findViewById<TextView?>(R.id.positionNumberView)
        gamesNumber = findViewById<TextView?>(R.id.gamesNumberView)
        accuracyPercentage = findViewById<TextView?>(R.id.accuracyPercentageView)


        startNewGameButton.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Not yet implemented!", Toast.LENGTH_LONG)
            toastMessage.show()
        }
        leaderboardButton.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Not yet implemented!", Toast.LENGTH_LONG)
            toastMessage.show()
        }

    }


}