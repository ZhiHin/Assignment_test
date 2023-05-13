import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.assignment_test.Fragment_bottom_sheet_userId
import com.example.assignment_test.ProfileActivity
import com.example.assignment_test.SettingsActivity
import com.example.assignment_test.databinding.FragmentMineBinding

class MineFragment : Fragment(), Fragment_bottom_sheet_userId.OnUserIdEnteredListener {

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
        val userIdLayout=binding.userIdLayout
        buttonSettings.setOnClickListener {
            buttonClicked()
        }

        profilePicture.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }

        val fragmentBottomSheetUserid=Fragment_bottom_sheet_userId()
        userIdLayout.setOnClickListener{
            fragmentBottomSheetUserid.show(childFragmentManager,"BottomSheetUserId")

        }
    }

    private fun buttonClicked() {
        Log.i("Button", "Button Clicked")
        startActivity(Intent(activity, SettingsActivity::class.java))
    }

    override fun onInputReceived(userId: String) {
        binding.userIdValue.text = userId
    }
}


