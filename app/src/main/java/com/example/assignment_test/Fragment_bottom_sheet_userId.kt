package com.example.assignment_test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import com.example.assignment_test.R.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Fragment_bottom_sheet_userId : BottomSheetDialogFragment() {

    // Declare the listener variable
    private var listener: OnUserIdEnteredListener? = null

    // Declare the view variables
    private lateinit var editTextUserId: EditText
    private lateinit var buttonSave: Button

    // Define the listener interface
    interface OnUserIdEnteredListener {
        fun onInputReceived(input: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_bottom_sheet_user_id, container, false)

        // Find the view references
        editTextUserId = view.findViewById(R.id.userId_changed)
        buttonSave = view.findViewById(R.id.save_button)



        // Set the click listener for the save button
        buttonSave.setOnClickListener {
            // Get the user input
            val userId = editTextUserId.text.toString().trim()

            // Call the listener method to pass the user input to the parent fragment
            listener?.onInputReceived(userId)

            // Dismiss the bottom sheet dialog
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the parent fragment implements the listener interface
        if (parentFragment is OnUserIdEnteredListener) {
            listener = parentFragment as OnUserIdEnteredListener
        } else {
            throw RuntimeException("$context must implement OnUserIdEnteredListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        // Reset the listener variable
        listener = null
    }
}
