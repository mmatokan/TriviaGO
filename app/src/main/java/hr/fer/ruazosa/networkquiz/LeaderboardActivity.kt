package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.nfc.Tag
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import hr.fer.ruazosa.networkquiz.entity.*
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_choose_players.*
import kotlinx.android.synthetic.main.activity_leaderboard.*
import kotlinx.android.synthetic.main.activity_leaderboard.returnButton

class LeaderboardActivity : AppCompatActivity() {
    lateinit var adapter : LeaderboardUserAdapter
    lateinit var data : MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        data = mutableListOf<User>()
        GetLeaderboard().execute()

        returnButton.setOnClickListener {
            onBackPressed()
            finish()
        }

        leaderboardUsersRecyclerView.layoutManager = LinearLayoutManager(application)
        adapter = LeaderboardUserAdapter(data)
        leaderboardUsersRecyclerView.adapter = adapter
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.decorator1
            )!!
        )
        leaderboardUsersRecyclerView.addItemDecoration(decorator)
    }

    private inner class GetLeaderboard:AsyncTask<String, Void, MutableList<User>?>(){

        override fun doInBackground(vararg usernameToExclude: String): MutableList<User>? {
            val rest = RestFactory.instance
            return rest.getLeaderboard()
        }

        override fun onPostExecute(users: MutableList<User>?) {
            if (users != null) {
                data = users
            }
        }
    }
}
