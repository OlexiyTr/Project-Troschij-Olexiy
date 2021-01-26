package com.example.first_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.first_project.databinding.GameScreenBinding

class GameScoreActivity : AppCompatActivity() {

    private lateinit var binding: GameScreenBinding
    private lateinit var winner: TeamScore
    private lateinit var loser: TeamScore
    private var nameA = ""
    private var nameB = ""
    private var counterA = 0
    private var counterB = 0

    companion object {
        private const val TIMER_EXTRA = "TIMER_TAG"
        private const val TEAM_A_EXTRA = "TEAM_A_TAG"
        private const val TEAM_B_EXTRA = "TEAM_B_TAG"

        fun start(context: Context, nameA: String, nameB: String, time: Int) {
            val intent = Intent(context, GameScoreActivity::class.java)
            intent.putExtra(TIMER_EXTRA, time)
            intent.putExtra(TEAM_A_EXTRA, nameA)
            intent.putExtra(TEAM_B_EXTRA, nameB)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
        setupTimer()
    }


    private fun setupBinding() {
        binding = GameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val time = intent.getIntExtra(TIMER_EXTRA, 0)
        nameA = intent.getStringExtra(TEAM_A_EXTRA).toString()
        nameB = intent.getStringExtra(TEAM_B_EXTRA).toString()
        val timeLine = "Time: $time"
        val counterLineA = "Score: $counterA"
        val counterLineB = "Score: $counterB"

        binding.tvTimer.text = timeLine
        binding.tvNameA.text = nameA
        binding.tvNameB.text = nameB
        binding.scoreA.text = counterLineA
        binding.scoreB.text = counterLineB
    }

    private fun setupListeners() {

        binding.btnScoreA.setOnClickListener {
            scoreCounterTeamA()
        }
        binding.btnScoreB.setOnClickListener {
            scoreCounterTeamB()
        }
    }

    private fun scoreCounterTeamA() {
        counterA += 1
        val counterLineA = "Score: $counterA"
        binding.scoreA.text = counterLineA
    }

    private fun scoreCounterTeamB() {
        counterB += 1
        val counterLineB = "Score: $counterB"
        binding.scoreB.text = counterLineB
    }

    private fun setupTimer() {
        val milliSeconds = (intent.getIntExtra(TIMER_EXTRA, 10) * 1000).toLong()
        val timer = object : CountDownTimer(milliSeconds, 1000) {
            override fun onFinish() {
                enableGameButtons(false)
                resultOfGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLine = "Time: ${millisUntilFinished / 1000}"
                binding.tvTimer.text = timeLine
            }
        }
        binding.btnCancel.setOnClickListener {
            enableGameButtons(false)
            this.finish()
            timer.cancel()
        }
        binding.btnStartGame.setOnClickListener {
            enableGameButtons(true)
            binding.btnStartGame.isEnabled = false
            timer.start()
        }
    }

    private fun enableGameButtons(condition: Boolean) {
        binding.btnScoreA.isEnabled = condition
        binding.btnScoreB.isEnabled = condition
        binding.btnCancel.isEnabled = condition

    }

    private fun resultOfGame() {
        when {
            counterA > counterB -> {
                winner = TeamScore(nameA, counterA)
                loser = TeamScore(nameB, counterB)
                WinnersScreen.adapter.addItemToList(winner)
                WinnerActivity.start(this@GameScoreActivity, winner, loser)
            }
            counterA < counterB -> {
                winner = TeamScore(nameB, counterB)
                loser = TeamScore(nameA, counterA)
                WinnersScreen.adapter.addItemToList(winner)
                WinnerActivity.start(this@GameScoreActivity, winner, loser)
            }
            else -> {
                val messageDraw = "Draw!"
                Toast.makeText(this@GameScoreActivity, messageDraw, Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }


}
