import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.flashcards.R
import com.example.flashcards.databinding.FragmentMainBinding
import com.example.flashcards.ui.card_new.MainFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel



class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModel()
    private lateinit var nav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        val hostManager = childFragmentManager.findFragmentById(R.id.main_fragment_fragment_container)
        navController = hostManager!!.findNavController()

        binding.toolbar.setupWithNavController(navController,
            AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.deckListFragment,
                R.id.categoryListFragment,
                R.id.settingsFragment
            )
        ))
        binding.bottomNavigationView.setupWithNavController(navController)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = requireActivity().findViewById(R.id.bottomNavigationView)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            if (destination.id == R.id.deckDetailsFragment || destination.id == R.id.deckStudyFragment
                || destination.id == R.id.addToCollectionPagerFragment){
                nav.visibility = View.GONE
            }else{
                nav.visibility = View.VISIBLE
            }
        }
    }


        companion object {
        val Tag = "deckSearch"

        fun create(): Fragment {
            return MainFragment()
        }
    }
}