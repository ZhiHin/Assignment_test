import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.assignment_test.Fragment_bottom_sheet_name
import com.example.assignment_test.Fragment_bottom_sheet_userId
import com.example.assignment_test.ProfileActivity
import com.example.assignment_test.SettingsActivity
import com.example.assignment_test.databinding.FragmentMineBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MineFragment : Fragment(), Fragment_bottom_sheet_userId.OnUserIdEnteredListener,Fragment_bottom_sheet_name.OnNameEnteredListener{

    private lateinit var binding: FragmentMineBinding
    var formatDate=SimpleDateFormat("dd MMMM YYYY", Locale.US)

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

        val fragmentBottomSheetName= Fragment_bottom_sheet_name()
        binding.nameLayout.setOnClickListener{
            fragmentBottomSheetName.show(childFragmentManager,"BottomSheetName")
        }

        //DatePicker
        binding.birthdateLayout.setOnClickListener(View.OnClickListener {
             val getDate:Calendar=Calendar.getInstance()
            val datepicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,DatePickerDialog.OnDateSetListener
            { datePicker, i, i2, i3 ->
                val selectDate:Calendar=Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_MONTH,i3)

                // Check if selected date is after today
                if (selectDate.after(Calendar.getInstance())) {
                    Toast.makeText(requireContext(), "Please select a valid date", Toast.LENGTH_SHORT).show()
                } else {
                    val date = formatDate.format(selectDate.time)
                    binding.BirthdateValue.text = date
                }

            },getDate.get(Calendar.YEAR),getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
            datepicker.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datepicker.show()
        })

        binding.genderLayout.setOnClickListener {
            val listGender = arrayOf("Male","Female")
            val builder = AlertDialog.Builder(requireContext())

            builder.setTitle("Choose your gender")
            builder.setSingleChoiceItems(listGender,-1) {
                dialogInterface:DialogInterface,i:Int ->
                binding.genderValue.text=listGender[i]
                dialogInterface.dismiss()

            }

            val dialogGender=builder.create()
            dialogGender.show()
        }
    }


    private fun buttonClicked() {
        Log.i("Button", "Button Clicked")
        startActivity(Intent(activity, SettingsActivity::class.java))
    }


    override fun onInputReceived(userId: String) {
        binding.userIdValue.text = userId
    }

    override fun onNameEntered(name: String) {
        binding.nameValue.text = name
    }


}


