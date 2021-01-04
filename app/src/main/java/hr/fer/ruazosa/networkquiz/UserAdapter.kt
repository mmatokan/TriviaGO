package hr.fer.ruazosa.networkquiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter (val opponents: MutableList<String>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var data : MutableList<String>? = opponents

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameTextView : TextView = view.findViewById(R.id.usernameTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent , false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.usernameTextView.text = data!![position]
        // TODO : Add invitation functionality and change of button image
    }

    override fun getItemCount(): Int {
        return data!!.size
    }
}