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
import kotlinx.android.synthetic.main.activity_leaderboard.*

class LeaderboardActivity : AppCompatActivity() {
    lateinit var adapter : LeaderboardUserAdapter
    lateinit var data : MutableList<RankUser>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)


    }
}
