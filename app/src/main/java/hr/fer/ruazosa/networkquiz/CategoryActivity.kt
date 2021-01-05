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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Categories().execute()
        setContentView(R.layout.activity_category)
        var user = intent.getSerializableExtra("user") as? User

        val category1 = findViewById<Button>(R.id.btnCategory1)
        val category2 = findViewById<Button>(R.id.btnCategory2)
        val category3 = findViewById<Button>(R.id.btnCategory3)
        val category4 = findViewById<Button>(R.id.btnCategory4)
        val category5 = findViewById<Button>(R.id.btnCategory5)
        val category6 = findViewById<Button>(R.id.btnCategory6)



        val returnButtonProf = findViewById<ImageButton>(R.id.imageButton)
        returnButtonProf.setOnClickListener{
            onBackPressed()
            finish()
        }
        //ovo povezati s pitanjima
        category1.setOnClickListener {
            questionCategory = category1.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
            startActivity(intent)
        }
        category2.setOnClickListener {
            questionCategory = category2.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
            startActivity(intent)
        }
        category3.setOnClickListener {
            questionCategory = category3.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
            startActivity(intent)
        }
        category4.setOnClickListener {
            questionCategory = category4.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
            startActivity(intent)
        }
        category5.setOnClickListener {
            questionCategory = category5.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
            startActivity(intent)
        }
        category6.setOnClickListener {
            questionCategory = category6.text as String
            val toastMessage =
                Toast.makeText(applicationContext, "Chosen category is " + questionCategory.toUpperCase(), Toast.LENGTH_LONG)
            toastMessage.show()
            val intent = Intent(this, ChoosePlayersActivity::class.java) //ovisi u kojem redosljedu ide; kategorija -> igraci -> pitanja?
            intent.putExtra("user", user)
            intent.putExtra("category", questionCategory)
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
            this@CategoryActivity.btnCategory1?.text = categories?.get(nextValues[0])?.title
            this@CategoryActivity.btnCategory2?.text = categories?.get(nextValues[1])?.title
            this@CategoryActivity.btnCategory3?.text = categories?.get(nextValues[2])?.title
            this@CategoryActivity.btnCategory4?.text = categories?.get(nextValues[3])?.title
            this@CategoryActivity.btnCategory5?.text = categories?.get(nextValues[4])?.title
            this@CategoryActivity.btnCategory6?.text = categories?.get(nextValues[5])?.title
            //this@Categor.position?.text = rank.toString()
        }
    }

    //override fun onBackPressed() {}
}