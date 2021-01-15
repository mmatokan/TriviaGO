package hr.fer.ruazosa.networkquiz

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.join_game_dialog.view.*

class MyProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        var user = intent.getSerializableExtra("user") as? User

        usernameTextView?.text = user?.username

        UserRank().execute(user?.username)

        startNewGameButton.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            startActivity(intent)
        }
        leaderboardButton.setOnClickListener {
            val intent = Intent(this, LeaderboardActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        }

    }

    private fun loadUsername(): String?{
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("USERNAME", null)
    }

    private inner class UserRank: AsyncTask<String, Void, User?>() {

        override fun doInBackground(vararg user: String): User? {
            val rest = RestFactory.instance
            return rest.getUserRank(user[0])

        }

        override fun onPostExecute(user: User?) {
            positionNumberView?.text = user?.rank.toString()
            gamesNumberView?.text = user?.gamesPlayed.toString()
            accuracyPercentageView?.text = user?.accuracy.toString()
            pointsNumberView?.text = user?.score.toString()
        }
    }

}