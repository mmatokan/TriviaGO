package hr.fer.ruazosa.networkquiz

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class WaitForGameStart : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wait_for_game_start)


        receiver = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent?.action

                Log.i("Receiver", "Broadcast received: $action")

                if(action.equals("begin")) {
                    val gameId = intent?.getStringExtra("game_id")
                    //TODO get questions and start game
                    val toast = Toast.makeText(applicationContext,"game starting", Toast.LENGTH_LONG)
                    toast.show()
                    val intent = Intent(this@WaitForGameStart, MyProfileActivity::class.java)
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
