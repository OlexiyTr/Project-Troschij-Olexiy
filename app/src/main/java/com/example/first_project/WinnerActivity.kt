package com.example.first_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.first_project.databinding.WinnerActivityLayoutBinding


class WinnerActivity : AppCompatActivity() {
    private lateinit var binding: WinnerActivityLayoutBinding

    companion object {

        private const val WINNER_NAME_EXTRA = "WINNER_NAME_TAG"
        private const val WINNER_SCORE_EXTRA = "WINNER_SCORE_TAG"
        private const val LOSER_NAME_EXTRA = "LOSER_NAME_TAG"
        private const val LOSER_SCORE_EXTRA = "LOSER_SCORE_TAG"

        fun start(context: Context, winner: TeamScore, loser: TeamScore) {
            val intent = Intent(context, WinnerActivity::class.java)
            intent.putExtra(WINNER_NAME_EXTRA, winner.name)
            intent.putExtra(WINNER_SCORE_EXTRA, winner.score)
            intent.putExtra(LOSER_NAME_EXTRA, loser.name)
            intent.putExtra(LOSER_SCORE_EXTRA, loser.score)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
        setupWinnerInfo()

    }

    private fun setupBinding() {
        binding = WinnerActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnShare.setOnClickListener {
            shareResultOfTheGame()
        }
        binding.btnCheck.setOnClickListener {
            checkListOfWinners()
        }
    }

    private fun setupWinnerInfo() {
        val name = intent.getStringExtra(WINNER_NAME_EXTRA)
        val score = intent.getIntExtra(WINNER_SCORE_EXTRA, 0)
        val lineWinnerName = "Winner : $name"
        val lineWinnerScore ="Score : $score"
        binding.tvNameOfWinner.text = lineWinnerName
        binding.tvScoreOfWinner.text = lineWinnerScore
    }

    private fun checkListOfWinners() {
        val intent = Intent(this, WinnersScreen::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun shareResultOfTheGame() {
        val nameWinner = intent.getStringExtra(WINNER_NAME_EXTRA)
        val scoreWinner = intent.getIntExtra(WINNER_SCORE_EXTRA, 0)
        val nameLoser = intent.getStringExtra(LOSER_NAME_EXTRA)
        val scoreLoser = intent.getIntExtra(LOSER_SCORE_EXTRA, 0)
        val stringResultOfGame =
            "Team $nameWinner beat $nameLoser with a score $scoreWinner : $scoreLoser."
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, stringResultOfGame)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}