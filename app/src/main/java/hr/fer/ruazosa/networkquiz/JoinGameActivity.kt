package hr.fer.ruazosa.networkquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.join_game_dialog.*

class JoinGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        var message = intent.getStringExtra("message")
        var gameId = intent.getStringExtra("game_id")

        joinGameTextView.text = message

        joinGameButton.setOnClickListener {

        }

        declineGameButton.setOnClickListener {

        }

    }
}
