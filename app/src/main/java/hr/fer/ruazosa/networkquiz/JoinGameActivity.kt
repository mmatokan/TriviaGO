package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hr.fer.ruazosa.networkquiz.entity.Game
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.join_game_dialog.*

class JoinGameActivity : AppCompatActivity() {

    var response: Boolean = true
    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        var message = intent.getStringExtra("message")
        var gameId = intent.getStringExtra("game_id").toInt()
        user = intent.getSerializableExtra("user") as? User

        joinGameTextView.text = message

        joinGameButton.setOnClickListener {
            response = true
            JoinGameResponse().execute(gameId)
        }

        declineGameButton.setOnClickListener {
            response = false
            JoinGameResponse().execute(gameId)
            val intent = Intent(this, MyProfileActivity::class.java)
            //intent.putExtra("user", user)
            startActivity(intent)

        }

    }

    private inner class JoinGameResponse: AsyncTask<Int, Void, Game?>(){

        override fun doInBackground(vararg id: Int?): Game? {
            val rest = RestFactory.instance
            return rest.joinGameResponse(id[0]!!, response, user!!)
        }

        override fun onPostExecute(result: Game?) {
            if (result != null){
                if(response){
                    //TODO("not implemented) go to wait for game start
                }
            }
        }
    }
}
