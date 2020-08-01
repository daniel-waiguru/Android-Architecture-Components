package tech.danielwaiguru.androidarchitecturecomponents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*
import tech.danielwaiguru.androidarchitecturecomponents.R
import tech.danielwaiguru.androidarchitecturecomponents.models.Character

class CharacterAdapter(private val listener: CharacterItemListener): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    private var characters = emptyList<Character>()
    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val characterImage:ImageView = itemView.characterImage
        val characterName: TextView = itemView.characterName
        val characterSpecies: TextView = itemView.characterSpecies
        val characterGender: TextView = itemView.characterGender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.character_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        Picasso.get().load(character.image).into(holder.characterImage)
        holder.characterName.text = character.name
        holder.characterSpecies.text = character.species
        holder.characterGender.text = character.gender
        holder.itemView.setOnClickListener {
            listener.onCharacterItemClicked(character)
        }
    }
    internal fun setData(characters : List<Character>){
        this.characters = characters
        notifyDataSetChanged()
    }
    interface CharacterItemListener{
        fun onCharacterItemClicked(character: Character)
    }
}