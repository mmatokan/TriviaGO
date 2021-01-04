package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.squareup.okhttp.ResponseBody
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyProfileActivity : AppCompatActivity() {

    var position : TextView? = null
    var gamesNumber: TextView? = null
    var accuracyPercentage : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        var user = intent.getSerializableExtra("user") as? User
        UserRank().execute(user?.username)

        position = findViewById<TextView?>(R.id.positionNumberView)
        gamesNumber = findViewById<TextView?>(R.id.gamesNumberView)
        accuracyPercentage = findViewById<TextView?>(R.id.accuracyPercentageView)

        gamesNumber?.text = user?.gamesPlayed.toString()
        accuracyPercentage?.text = user?.accuracy.toString()
        usernameTextView?.text = user?.username
        pointsNumberView?.text = user?.score.toString()


        startNewGameButton.setOnClickListener {
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            startActivity(intent)
            finish()
        }
        leaderboardButton.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Not yet implemented!", Toast.LENGTH_LONG)
            toastMessage.show()
        }

    }
    private inner class UserRank: AsyncTask<String, Void, Int?>() {

        override fun doInBackground(vararg username: String): Int? {
            val rest = RestFactory.instance
            return rest.getUserRank(username.toString())
        }

        override fun onPostExecute(rank: Int?) {
            this@MyProfileActivity.position?.text = rank.toString()
        }
    }

}