package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private TextView txtWinner, txtTotalBet, txtWinnings, txtNewTotalMoney,txtChoice,txtResult,txtLosses;
    private int newMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtChoice = findViewById(R.id.txtChoice);
        txtLosses = findViewById(R.id.txtLosses);
        txtResult = findViewById(R.id.txtResult);
        txtWinner = findViewById(R.id.txtWinner);
        txtTotalBet = findViewById(R.id.txtTotalBet);
        txtWinnings = findViewById(R.id.txtWinnings);
        txtNewTotalMoney = findViewById(R.id.txtNewTotalMoney);


        Intent intent = getIntent();
        ArrayList<Integer> winningMoto = intent.getIntegerArrayListExtra("winningMoto");
        ArrayList<Integer> selectedMoto = intent.getIntegerArrayListExtra("selectedMoto");
        boolean isTie = intent.getBooleanExtra("isTie", false);
        newMoney = intent.getIntExtra("newMoney", 1000);
        int totalBet = intent.getIntExtra("totalBet", 0);
        int winnings = intent.getIntExtra("winnings", 0);
        int losses = intent.getIntExtra("losses", 0);

        boolean isWinner = false;
        for (int winner : winningMoto) {
            if (selectedMoto.contains(winner)) {
                isWinner = true;
                break;
            }
        }


        if (isWinner) {
            txtResult.setText("You win");
        } else {
            txtResult.setText("You lose");
        }
        txtChoice.setText("Your Choice: Moto " + selectedMoto);


        if (isTie) {
            txtWinner.setText("It's a tie!");
        } else {
            StringBuilder winnersText = new StringBuilder("Winning Moto(s): ");
            for (int winner : winningMoto) {
                winnersText.append("Moto ").append(winner).append(" ");
            }
            txtWinner.setText(winnersText.toString());
        }

        txtTotalBet.setText("Total Bet: " + totalBet);
        txtWinnings.setText("Winnings: " + winnings);
        txtLosses.setText("Losses: " + losses);
        txtNewTotalMoney.setText("New Total Money: " + newMoney);
    }

    public void onBackClicked(View view) {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.putExtra("currentMoney", newMoney);
        startActivity(intent);
        finish();
    }
}
