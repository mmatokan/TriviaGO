package hr.fer.ruazosa.networkquiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.join_game_dialog.*

class JoinGameActivity : AppCompatActivity() {

    var response: Boolean = true
    var userId: Long? = null

    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        var message = intent.getStringExtra("message")
        var gameId = intent.getStringExtra("game_id")

        userId = loadUserId()

        joinGameTextView.text = message

        joinGameButton.setOnClickListener {
            response = true
            JoinGameResponse().execute(gameId.toLong())
        }

        declineGameButton.setOnClickListener {
            response = false
            JoinGameResponse().execute(gameId.toLong())
            val intent = Intent(this, MyProfileActivity::class.java)
            //intent.putExtra("user", user)
            startActivity(intent)

        }

        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action

                Log.i("Receiver", "Broadcast received: $action")

                if(action.equals("begin")) {
                    val gameId = intent?.getStringExtra("game_id")
                    //TODO get questions and start game
                    val toast = Toast.makeText(applicationContext,"game starting", Toast.LENGTH_LONG)
                    toast.show()
                    val intent = Intent(this@JoinGameActivity, MyProfileActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    }

    private fun loadUserId(): Long?{
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getLong("USER_ID", 0)
    }

    private inner class JoinGameResponse: AsyncTask<Long, Void, Boolean?>(){

        override fun doInBackground(vararg id: Long?): Boolean? {
            val rest = RestFactory.instance
            if(id[0] != null && response != null && userId != null){
                return rest.joinGameResponse(id[0]!!, response, userId!!)
            }
            else {
                Log.d("ID", id[0].toString())
                Log.d("RESPONSE", response.toString())
                Log.d("USER_ID", userId.toString())
                return false
            }
        }

        override fun onPostExecute(result: Boolean?) {
            if (result != null){
                if(response){
                    val intent = Intent(this@JoinGameActivity, WaitForGameStart::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            receiver,
            IntentFilter("begin")
        )
    }


    public override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)

    }
}
