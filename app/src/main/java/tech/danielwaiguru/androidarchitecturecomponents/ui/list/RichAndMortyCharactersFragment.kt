package tech.danielwaiguru.androidarchitecturecomponents.ui.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_rich_and_morty_characters.*
import tech.danielwaiguru.androidarchitecturecomponents.R
import tech.danielwaiguru.androidarchitecturecomponents.adapters.CharacterAdapter
import tech.danielwaiguru.androidarchitecturecomponents.models.Character
import tech.danielwaiguru.androidarchitecturecomponents.viewmodel.CharacterViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RichAndMortyCharactersFragment : Fragment(), CharacterAdapter.CharacterItemListener {
    @Inject
    lateinit var characterViewModel: CharacterViewModel
    private val characterAdapter by lazy {
        CharacterAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterViewModel.fetchData()
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rich_and_morty_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterViewModel.allCharacters.observe(viewLifecycleOwner, Observer { characters ->
            characters?.let {
                characterAdapter.setData(it)
            }
        })
        setupRecyclerView()
    }

    override fun onCharacterItemClicked(character: Character) {
        initCharacterDetailsFragment(character)
    }
    //configure recyclerview
    private fun setupRecyclerView(){
        charactersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        charactersRecyclerView.adapter = characterAdapter
    }
    private fun initCharacterDetailsFragment(character: Character){
        view?.let {
            val action = RichAndMortyCharactersFragmentDirections
                .actionRichAndMortyCharactersFragmentToRickAndMortyCharactersDetailsFragment(character)
            it.findNavController().navigate(action)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_clear -> {
                characterViewModel.clearCharacters()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}