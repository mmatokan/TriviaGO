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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        var user = intent.getSerializableExtra("user") as? User

        UserRank().execute(user)

        startNewGameButton.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            startActivity(intent)
            //finish()
        }
        leaderboardButton.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Not yet implemented!", Toast.LENGTH_LONG)
            toastMessage.show()
        }

    }
    private inner class UserRank: AsyncTask<User, Void, User?>() {

        override fun doInBackground(vararg user: User): User? {
            val rest = RestFactory.instance
            return rest.getUserRank(user[0].username)

        }

        override fun onPostExecute(user: User?) {
            positionNumberView?.text = user?.rank.toString()
            gamesNumberView?.text = user?.gamesPlayed.toString()
            accuracyPercentageView?.text = user?.accuracy.toString()
            pointsNumberView?.text = user?.score.toString()
        }
    }

}