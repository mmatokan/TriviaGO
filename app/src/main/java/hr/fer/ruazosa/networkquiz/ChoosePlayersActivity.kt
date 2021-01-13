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


class ChoosePlayersActivity : AppCompatActivity() {

    lateinit var adapter : UserAdapter
    var newGameText: TextView? = null // Unnecessary?
    var searchText : EditText ? = null
    var startGameButton: Button? = null
    lateinit var opponents : MutableList<SelectableUser>
    var questions: MutableList<Question>? = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_players)

        // Initializing usernames
        opponents = mutableListOf<SelectableUser>()
        var user = intent.getSerializableExtra("user") as? User
        var category = intent.getSerializableExtra("category") as? Int

        Users().execute(user?.username)
        GetQuestions().execute(category)

        returnButton.setOnClickListener{

            val returnIntent = Intent(this, CategoryActivity::class.java) // (NewGame -> Category -> Opponents)
            returnIntent.putExtra("user", user)
            onBackPressed()
            //startActivity(returnIntent)
            //finish()
        }


        usersRecyclerView.layoutManager = LinearLayoutManager(application)

        // Instantiating adapter and adding it to the RecyclerView
        adapter = UserAdapter(opponents)
        usersRecyclerView.adapter = adapter

        // Instantiating decorator/divider and adding it to the RecyclerView
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.decorator1
            )!!
        )
        usersRecyclerView.addItemDecoration(decorator)


        // initializing search functionality ( see: filterPlayers , UserAdapter )
        searchText = findViewById(R.id.editSearchText)
        searchText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filterPlayers(p0?.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        startGameButton = findViewById(R.id.startButton)
        startGameButton?.setOnClickListener {
            Log.w("Pitanja", questions.toString())
            for (question in questions!!) {
                Log.w("Sacuvano", question.question)
            }
            //treba napraviti instancu igre s odabranim igracima i pitanjima te poslati to na bazu
            //val game = Game(questions = questions!!)
            //val startGame = Intent(this, JoinGameActivity::class.java)
            //startGame.putExtra("question 1", questions?.get(0)?.question)
            //startActivity(startGame)

        }


    }

    private fun filterPlayers(filter: String?) {
        var filteredList = mutableListOf<SelectableUser>()
        if (filter != null ){
            for ( tempUser in opponents) {
                if (tempUser.user.username.toLowerCase().contains(filter.toLowerCase()))
                    filteredList.add(tempUser)
            }
        }
        adapter.filterPlayers(filteredList)
    }

    // Get all users EXCEPT the currently logged-in ( all available opponents )
    private inner class Users:AsyncTask<String, Void, List<User>?>(){

        override fun doInBackground(vararg usernameToExclude: String): List<User>? {
            val rest = RestFactory.instance
            return rest.getOpponents(usernameToExclude[0])
        }

        override fun onPostExecute(users: List<User>?) {
            if (users != null) {
                for (user in users ) {
                    val tempUser = SelectableUser(user, false)
                    opponents.add(tempUser)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    private inner class GetQuestions:AsyncTask<Int, Void, CatQuestions>(){
        override fun doInBackground(vararg category_Id: Int?): CatQuestions? {
            val rest = RestFactory.instance
            val dobivena = category_Id?.get(0)?.let { rest.getQuestions(it) };
            Log.w("Ovo se dobilo", dobivena.toString())
            return dobivena
        }

        override fun onPostExecute(result: CatQuestions?) {
            //ovdje dodati game_id
            if (result?.clues != null) {
                var i = 0
                for (question in result.clues ) {
                    if(i > 4) break;
                    //Log.w("Ovo je pitanje", question.toString())
                    this@ChoosePlayersActivity.questions?.add(question)
                    i++

                }
                //Log.w("Zbirka pitanja", questions.toString())
            }
        }

    }


}
