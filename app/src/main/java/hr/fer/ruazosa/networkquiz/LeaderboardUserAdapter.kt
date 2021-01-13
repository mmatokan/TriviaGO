package hr.fer.ruazosa.networkquiz

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruazosa.networkquiz.entity.User

class LeaderboardUserAdapter (val data: MutableList<User>) : RecyclerView.Adapter<LeaderboardUserAdapter.ViewHolder>() {
    private val TYPE_TOP3=0
    private val TYPE_NOT_TOP3=1


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rankTextView: TextView = view.findViewById(R.id.rankTextView)
        var usernameTextView : TextView = view.findViewById(R.id.usernameLeaderboardTextView)
        var scoreTextView: TextView = view.findViewById(R.id.scoreTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardUserAdapter.ViewHolder {
        var layout : Int? =  null
        if (viewType == TYPE_TOP3){ layout = R.layout.leaderboard_item_top3 }
        else { layout = R.layout.leaderboard_item_not_top3 }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent , false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaderboardUserAdapter.ViewHolder, position: Int) {
        holder.usernameTextView.text = data[position].username
        holder.rankTextView.text = data[position].rank.toString()
        holder.scoreTextView.text = data[position].score.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if (data.get(position).rank <= 3){
            return TYPE_TOP3
        } else
            return TYPE_NOT_TOP3
    }
}
