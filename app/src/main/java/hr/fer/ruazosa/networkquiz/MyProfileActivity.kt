package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.join_game_dialog.view.*

//TODO user se treba slati u svim aktivnostima kada se moze vratiti na profil
class MyProfileActivity : AppCompatActivity() {

    //lateinit var reciever: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        var user = intent.getSerializableExtra("user") as? User

        usernameTextView?.text = user?.username


        UserRank().execute(user)
        /*
        reciever = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent) {
                val message = intent.getStringExtra("message")
                if(message != null){
                    showNotification(message)
                }
            }
        }*/

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

    private fun showNotification(message: String){
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(this@MyProfileActivity).inflate(R.layout.join_game_dialog, null)
        mDialogView.joinGameTextView.text = message
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this@MyProfileActivity)
            .setView(mDialogView)
            .setTitle("Join game")
        //show dialog
        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.joinGameButton.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }
        //cancel button click of custom layout
        mDialogView.declineGameButton.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
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

            val toastMessage =
                Toast.makeText(applicationContext, user!!.id.toString(), Toast.LENGTH_LONG)
            toastMessage.show()
        }
    }

}