package com.example.assignment_test

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.droidsonroids.gif.GifImageView
import java.lang.Math.ceil


class indoorWorkOutLvl3_frag : Fragment() {
    private var currentExercisePosition = 0
    private lateinit var currentExercise: Exercise
    private lateinit var timer: CountDownTimer
    private lateinit var progressBar: ProgressBar
    private var timerStarted = false
    private lateinit var nameTextView: TextView
    private lateinit var gifImageView: GifImageView
    private var window: Window? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_indoor_work_out_lvl1_frag, container, false)

        nameTextView = view.findViewById<TextView>(R.id.nameWorkout)
        gifImageView = view.findViewById<GifImageView>(R.id.gifImageView)
        val timerTextView = view.findViewById<TextView>(R.id.timerDuration)
        val nextButton = view.findViewById<AppCompatImageView>(R.id.nextWorkout)
        val previousButton = view.findViewById<AppCompatImageView>(R.id.previousWorkout)
        val pauseButton = view.findViewById<AppCompatImageView>(R.id.pauseWorkout)
        progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        progressBar.progress = 0
        val window = requireActivity()!!.window
        window!!.setBackgroundDrawableResource(android.R.color.transparent)

        currentExercise = exercises[currentExercisePosition]

        nameTextView.text = currentExercise.name
        gifImageView.setImageResource(currentExercise.gifResourceId)

        startTimer(timerTextView)

        nextButton.setOnClickListener {


            timer.cancel()
            currentExercisePosition++
            if (currentExercisePosition >= exercises.size) {
                // Last exercise completed
                timerStarted = false
                progressBar.progress = 100 // Set progress to 100% to indicate completion
                nameTextView.text = "Completed" // Set text to "Complete"
                timerTextView.text = "Nice !"
                // Set image to check mark icon
                // gifImageView.setImageResource(R.drawable.check_mark)
            } else {
                currentExercise = exercises[currentExercisePosition]
                nameTextView.text = currentExercise.name
                gifImageView.setImageResource(currentExercise.gifResourceId)
                timerTextView.text = "${currentExercise.duration} seconds"
                timerStarted = false
                startTimer(timerTextView)
            }

        }


        previousButton.setOnClickListener {

            if (currentExercisePosition == 0) {
                timer.cancel()
                timerStarted = false
                progressBar.progress = 0
                currentExercise = exercises[currentExercisePosition]
                nameTextView.text = currentExercise.name
                gifImageView.setImageResource(currentExercise.gifResourceId)
                timerTextView.text = "${currentExercise.duration} seconds"
                startTimer(timerTextView)
                Toast.makeText(requireContext(), "You're already at the first exercise!", Toast.LENGTH_SHORT).show()
            } else {
                timer.cancel()
                currentExercisePosition--
                currentExercise = exercises[currentExercisePosition]
                nameTextView.text = currentExercise.name
                gifImageView.setImageResource(currentExercise.gifResourceId)
                timerTextView.text = "${currentExercise.duration} seconds"
                timerStarted = false
                startTimer(timerTextView)
            }
        }


        pauseButton.setOnClickListener {
            if (timerStarted) {
                // pause the timer
                timer.cancel()
                timerStarted = false

                nextButton.isEnabled = false
                previousButton.isEnabled = false

                // change the button icon to play button
                pauseButton.setImageResource(R.drawable.logo_play)
            } else {
                // start the timer
                startTimer(timerTextView)
                timerStarted = true

                nextButton.isEnabled = true
                previousButton.isEnabled = true

                // change the button icon to pause button
                pauseButton.setImageResource(R.drawable.logo_stop_circle)
            }
        }
        return view
    }

    private fun startTimer(timerTextView: TextView) {
        timer = object : CountDownTimer((currentExercise.duration * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = ceil(millisUntilFinished / 1000.0).toInt()
                timerTextView.text = "$remainingSeconds seconds"
                val progress = ((currentExercise.duration - remainingSeconds) * 100) / currentExercise.duration
                progressBar.progress = progress
            }

            override fun onFinish() {
                currentExercisePosition++
                if (currentExercisePosition >= exercises.size) {
                    // Last exercise completed
                    timerStarted = false
                    progressBar.progress = 100 // Set progress to 100% to indicate completion
                    nameTextView.text = "Completed" // Set text to "Complete"
                    timerTextView.text = "Nice !"
//                    gifImageView.setImageResource(R.drawable.) // Set image to check mark icon
                    return // Stop looping
                }
                currentExercise = exercises[currentExercisePosition]
                progressBar.progress = 0
                nameTextView.text = currentExercise.name
                gifImageView.setImageResource(currentExercise.gifResourceId)
                timerStarted = false
                timerTextView.text = "${currentExercise.duration} seconds"
                startTimer(timerTextView)
            }
        }

        if (!timerStarted) {
            timerStarted = true
            timer.start()
        }
    }

    companion object {
        val exercises = arrayOf(
            Exercise("Bent Over Twists", R.drawable.gif_bent_over_twist, 45),
            Exercise("Jumping Jacks", R.drawable.gif_jumping_jacks, 50),
            Exercise("Burpees", R.drawable.gif_burpees, 120),
            Exercise("Squat", R.drawable.gif_squat, 60),
            Exercise("Back Extension", R.drawable.gif_back_extension, 30),
            Exercise("Run in Place", R.drawable.gif_run_in_place, 120),
            Exercise("Reverse Crunches", R.drawable.gif_reverse_crunches, 60),
            Exercise("Arm Circles", R.drawable.gif_arm_circles, 30),
            Exercise("Standing Criss-Cross", R.drawable.gif_standing_criss_cross, 50),
            Exercise("Plank", R.drawable.gif_plank, 120)
        )
    }

    data class Exercise(val name: String, val gifResourceId: Int, val duration: Int)
}