import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment_test.ProfileActivity
import com.example.assignment_test.SettingsActivity
import com.example.assignment_test.ViewPagerAdapter
import com.example.assignment_test.databinding.FragmentMineBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MineFragment : Fragment() {

    private lateinit var binding: FragmentMineBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonSettings = binding.buttonSettings
        val profilePicture = binding.profileImage

        buttonSettings.setOnClickListener {
            buttonClicked()
        }

        profilePicture.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

        val tabLayout1=binding.tabLayout1
        val viewPager1=binding.viewPager1
        val adapter=ViewPagerAdapter(childFragmentManager,lifecycle)
        viewPager1.adapter=adapter
        TabLayoutMediator(tabLayout1,viewPager1){tab,position->
            when(position){
                0->{
                    tab.text="Day"
                }
                1->{
                    tab.text="Week"
                }
                2->{
                    tab.text="Month"
                }
            }
        }.attach()

        val tabLayout2=binding.tabLayout2
        val viewPager2=binding.viewPager2
        val adapter2=ViewPagerAdapter(childFragmentManager,lifecycle)
        viewPager2.adapter=adapter2
        TabLayoutMediator(tabLayout2,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Day"
                }
                1->{
                    tab.text="Week"
                }
                2->{
                    tab.text="Month"
                }
            }
        }.attach()

    }

    private fun buttonClicked() {
        Log.i("Button", "Button Clicked")
        startActivity(Intent(activity, SettingsActivity::class.java))
    }
}
