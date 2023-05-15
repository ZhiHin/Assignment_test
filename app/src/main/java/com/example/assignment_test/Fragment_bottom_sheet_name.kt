package com.example.assignment_test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.assignment_test.R.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Fragment_bottom_sheet_name : BottomSheetDialogFragment() {

    // Declare the listener variable
    private var listener: OnNameEnteredListener? = null

    // Declare the view variables
    private lateinit var editTextName: EditText
    private lateinit var buttonSave: Button

    // Define the listener interface
    interface OnNameEnteredListener {
        fun onNameEntered(input: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(layout.fragment_bottom_sheet_name, container, false)

        // Find the view references
        editTextName = view.findViewById(R.id.name_changed)
        buttonSave = view.findViewById(R.id.save_button)



        // Set the click listener for the save button
        buttonSave.setOnClickListener {
            // Get the user input
            val name = editTextName.text.toString().trim()

            // Call the listener method to pass the user input to the parent fragment
            listener?.onNameEntered(name)

            // Dismiss the bottom sheet dialog
            dismiss()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the parent fragment implements the listener interface
        if (parentFragment is Fragment_bottom_sheet_name.OnNameEnteredListener) {
            listener = parentFragment as Fragment_bottom_sheet_name.OnNameEnteredListener
        } else {
            throw RuntimeException("$context must implement OnNameEnteredListener")
        }
    }



    override fun onDetach() {
        super.onDetach()
        // Reset the listener variable
        listener = null
    }
}
