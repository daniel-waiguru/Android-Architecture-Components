package tech.danielwaiguru.androidarchitecturecomponents.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_rick_and_morty_characters_details.*
import tech.danielwaiguru.androidarchitecturecomponents.R
import tech.danielwaiguru.androidarchitecturecomponents.viewmodel.CharacterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RickAndMortyCharactersDetailsFragment : Fragment() {
    @Inject
    lateinit var characterViewModel: CharacterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_rick_and_morty_characters_details,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getClickedCharacter()
    }
    private fun getClickedCharacter(){
        arguments?.let {
            val args = RickAndMortyCharactersDetailsFragmentArgs.fromBundle(it)
            args.character.let { character ->
                Picasso.get().load(character.image).into(characterImage)
                characterName.text = character.name
                characterStatus.text = character.status
                characterGender.text = character.gender
                characterSpecies.text = character.species
                createdAt.text = character.createdAt
            }
        }
    }
}