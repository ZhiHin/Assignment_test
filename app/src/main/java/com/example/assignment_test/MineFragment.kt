import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.assignment_test.ActivityChangePassword
import com.example.assignment_test.Fragment_bottom_sheet_name
import com.example.assignment_test.Fragment_bottom_sheet_userId
import com.example.assignment_test.R
import com.example.assignment_test.SettingsActivity
import com.example.assignment_test.databinding.FragmentMineBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MineFragment : Fragment(), Fragment_bottom_sheet_userId.OnUserIdEnteredListener,
    Fragment_bottom_sheet_name.OnNameEnteredListener,Dialog_weight_picker.OnWeightSelectedListener,
    Dialog_height_picker.OnHeightSelectedListener{

    private lateinit var databaseRef: DatabaseReference
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private lateinit var binding: FragmentMineBinding
    var formatDate=SimpleDateFormat("dd MMMM YYYY", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMineBinding.inflate(inflater, container, false)
        databaseRef = FirebaseDatabase.getInstance().reference.child("SignupUsers").child("123")
        readData()
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

        val fragmentBottomSheetUserid=Fragment_bottom_sheet_userId()
        userIdLayout.setOnClickListener{
            fragmentBottomSheetUserid.show(childFragmentManager,"BottomSheetUserId")
        }

        binding.passwordLayout.setOnClickListener {
            startActivity(Intent(activity, ActivityChangePassword::class.java))

        }

        val fragmentBottomSheetName= Fragment_bottom_sheet_name()
        binding.nameLayout.setOnClickListener{
            fragmentBottomSheetName.show(childFragmentManager,"BottomSheetName")
        }

        //DatePicker
        binding.birthdateLayout.setOnClickListener(View.OnClickListener {
             val getDate:Calendar=Calendar.getInstance()
            val datepicker=DatePickerDialog(requireContext(),android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener
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

        //weight onclick
        binding.weightLayout.setOnClickListener {
            val dialog = Dialog_weight_picker(requireContext())
            dialog.setOnWeightSelectedListener(this)
            dialog.show(childFragmentManager, "weight_picker_dialog")
        }

        binding.heightLayout.setOnClickListener {
            val dialog = Dialog_height_picker(requireContext())
            dialog.setOnHeightSelectedListener(this)
            dialog.show(childFragmentManager, "height_picker_dialog")
        }

        //gender onclick
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

        binding.profilePictureChangeButton.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }


    }


    private fun readData() {
        databaseRef.child("username").child("123")

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userData = dataSnapshot.value as? HashMap<String, Any>
                if (userData != null) {
                    val username = userData["username"].toString()
                    val gender = userData["gender"].toString()
                    val height = userData["height"].toString().toLong()
                    val weight = userData["weight"].toString().toLong()
                    val email = userData["email"].toString()

                    // Update your UI or perform any other operations with the retrieved values
                    binding.nameValue.text = username
                    binding.topnameValue.text = username
                    binding.genderValue.text = gender
                    binding.heightValue.text = height.toString()
                    binding.weightValue.text = weight.toString()
                    binding.emailValue.text = email
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle the error
            }
        })



    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.data // Get URI of selected image

            // Load the selected image into the profile picture view
            Glide.with(this)
                .load(imageUri)
                .error(R.drawable.profilepicture)
                .into(binding.profileImage)
        }
    }

    override fun onWeightSelected(weight: Double) {
        binding.weightValue.text = weight.toInt().toString()
    }

    override fun onHeightSelected(height: Double) {
        binding.heightValue.text = height.toInt().toString()
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


