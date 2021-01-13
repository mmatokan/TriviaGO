package hr.fer.ruazosa.networkquiz

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import hr.fer.ruazosa.networkquiz.entity.Category
import hr.fer.ruazosa.networkquiz.entity.User
import hr.fer.ruazosa.networkquiz.net.RestFactory
import kotlinx.android.synthetic.main.activity_category.*
import kotlin.random.Random

class CategoryActivity : AppCompatActivity() {

    var questionCategory = ""
    lateinit var category1: Category
    lateinit var category2: Category
    lateinit var category3: Category
    lateinit var category4: Category
    lateinit var category5: Category
    lateinit var category6: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Categories().execute()
        setContentView(R.layout.activity_category)
        var user = intent.getSerializableExtra("user") as? User

        val returnButtonProf = findViewById<ImageButton>(R.id.imageButton)
        returnButtonProf.setOnClickListener{
            onBackPressed()
            finish()
        }
        //ovo povezati s pitanjima
        btnCategory1.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category1.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category1.id)
            startActivity(intent)
        }
        btnCategory2.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category2.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category2.id)
            startActivity(intent)
        }
        btnCategory3.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category3.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category3.id)
            startActivity(intent)
        }
        btnCategory4.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category4.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category4.id)
            startActivity(intent)
        }
        btnCategory5.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category5.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category5.id)
            startActivity(intent)
        }
        btnCategory6.setOnClickListener {
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + category6.id, Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", category6.id)
            startActivity(intent)
        }
    }


    private inner class Categories: AsyncTask<Void, Int, List<Category>?>() {

        override fun doInBackground(vararg p0: Void?): List<Category>? {
            val rest = RestFactory.instance
            return rest.getCategories(100)
        }

        override fun onPostExecute(categories: List<Category>?) {
            val nextValues = List(6) { Random.nextInt(0, 100) }
            this@CategoryActivity.category1 = categories!![nextValues[0]]
            this@CategoryActivity.category2 = categories[nextValues[1]]
            this@CategoryActivity.category3 = categories[nextValues[2]]
            this@CategoryActivity.category4 = categories[nextValues[3]]
            this@CategoryActivity.category5 = categories[nextValues[4]]
            this@CategoryActivity.category6 = categories[nextValues[5]]
            this@CategoryActivity.btnCategory1?.text = category1.title
            this@CategoryActivity.btnCategory2?.text = category2.title
            this@CategoryActivity.btnCategory3?.text = category3.title
            this@CategoryActivity.btnCategory4?.text = category4.title
            this@CategoryActivity.btnCategory5?.text = category5.title
            this@CategoryActivity.btnCategory6?.text = category6.title
            //this@Categor.position?.text = rank.toString()
        }
    }

    //override fun onBackPressed() {}
}