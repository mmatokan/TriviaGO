package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.ruazosa.networkquiz.entity.SelectableUser
import hr.fer.ruazosa.networkquiz.entity.ShortUser
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import retrofit2.http.Url
import kotlinx.android.synthetic.main.activity_choose_players.*

class ChoosePlayersActivity : AppCompatActivity() {

    lateinit var adapter : UserAdapter
    var newGameText: TextView? = null // Unnecessary?
    var searchText : EditText ? = null
    var startGameButton: Button? = null
    lateinit var opponents : MutableList<SelectableUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_players)

        // Initializing usernames
        opponents = mutableListOf<SelectableUser>()
        var user = intent.getSerializableExtra("user") as? User
        Users().execute(user?.username)

        returnButton.setOnClickListener{
            val returnIntent = Intent(this, CategoryActivity::class.java) // (NewGame -> Category -> Opponents)
            returnIntent.putExtra("user",user)
            onBackPressed()
            //startActivity(returnIntent)
            //finish()
        }


        usersRecyclerView.layoutManager = LinearLayoutManager(application)

        // Instantiating adapter and adding it to the RecyclerView
        adapter = UserAdapter(opponents)
        usersRecyclerView.adapter = adapter

        // Instantiating decorator/divider and adding it to the RecyclerView
        val decorator = DividerItemDecoration(applicationContext , LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.decorator1)!!)
        usersRecyclerView.addItemDecoration(decorator)


        // initializing search functionality ( see: filterPlayers , UserAdapter )
        searchText = findViewById(R.id.editSearchText)
        searchText?.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                filterPlayers(p0?.toString())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        startGameButton = findViewById(R.id.startNewGameButton)
        startGameButton?.setOnClickListener {
            // TODO: start game intent
            // TODO: intent.extras : player(?), selected players(?) , category(?)
            val text = "Not yet implemented! "
            val toast = Toast.makeText(applicationContext,text,Toast.LENGTH_LONG)
            toast.show()
        }


    }

    private fun filterPlayers(filter:String?) {
        var filteredList = mutableListOf<SelectableUser>()
        if (filter != null ){
            for ( tempUser in opponents) {
                if (tempUser.username.toLowerCase().contains(filter.toLowerCase()))
                    filteredList.add(tempUser)
            }
        }
        adapter.filterPlayers(filteredList)
    }

    // Get all users EXCEPT the currently logged-in ( all available opponents )
    private inner class Users:AsyncTask< String ,Void , List<String>?>(){

        override fun doInBackground(vararg usernameToExclude: String): List<String>? {
            val rest = RestFactory.instance
            return rest.getOpponents(usernameToExclude[0])
        }

        override fun onPostExecute(result: List<String>?) {
            if (result != null) {
                for (res in result ) {
                    val tempUser : SelectableUser = SelectableUser(res , false)
                    opponents.add(tempUser)
                }
                adapter.notifyDataSetChanged()
            }
        }
    }


}