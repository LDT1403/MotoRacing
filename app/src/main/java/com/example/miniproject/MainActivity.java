package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private SeekBar sBmoto1,sBmoto2,sBmoto3,sBmoto4;
    private CheckBox cBmoto1,cBmoto2,cBmoto3,cBmoto4;

    private EditText editMoto1,editMoto2,editMoto3,editMoto4;


    private Button btnStart,btnReset;

    private TextView txtTotalMoney,txtUser;

    private Handler handler =new Handler();

    private Runnable runnable;

    private Random random =new Random();

    private int money = 1000;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sBmoto1 =(SeekBar) findViewById(R.id.sbMoto1);
        sBmoto2 =(SeekBar) findViewById(R.id.sbMoto2);
        sBmoto3 =(SeekBar) findViewById(R.id.sbMoto3);
        sBmoto4 =(SeekBar) findViewById(R.id.sbMoto4);
        cBmoto1 =(CheckBox) findViewById(R.id.cbMoto1);
        cBmoto2 =(CheckBox) findViewById(R.id.cbMoto2);
        cBmoto3 =(CheckBox) findViewById(R.id.cbMoto3);
        cBmoto4 =(CheckBox) findViewById(R.id.cbMoto4);
        editMoto1=(EditText) findViewById(R.id.editMoto1);
        editMoto2=(EditText) findViewById(R.id.editMoto2);
        editMoto3=(EditText) findViewById(R.id.editMoto3);
        editMoto4=(EditText) findViewById(R.id.editMoto4);
        btnStart=(Button) findViewById(R.id.btnStart);
        txtUser=(TextView) findViewById(R.id.txtUser);
        txtTotalMoney=(TextView) findViewById(R.id.txtTotalMoney);
        imageView =(ImageView) findViewById(R.id.imgUser);
        btnReset =(Button) findViewById(R.id.btnReset);


        String username = getIntent().getStringExtra("username");
        Intent intent = getIntent();
        if (intent.hasExtra("currentMoney")) {
            money = intent.getIntExtra("currentMoney", 1000);
        } else {
            money = 1000;
        }
        editMoto1.setVisibility(View.GONE);
        editMoto2.setVisibility(View.GONE);
        editMoto3.setVisibility(View.GONE);
        editMoto4.setVisibility(View.GONE);
        txtUser.setText(username);
        imageView.setImageResource(R.drawable.avatar);
        txtTotalMoney.setText("Total Money :" + money);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reset();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start();
            }
        });
        cBmoto1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editMoto1.setVisibility(View.VISIBLE);
                }else{
                    editMoto1.setVisibility(View.GONE);
                }
            }
        });
        cBmoto4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editMoto4.setVisibility(View.VISIBLE);
                }else{
                    editMoto4.setVisibility(View.GONE);

                }
            }
        });
        cBmoto2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editMoto2.setVisibility(View.VISIBLE);
                }else{
                    editMoto2.setVisibility(View.GONE);

                }
            }
        });
        cBmoto3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editMoto3.setVisibility(View.VISIBLE);
                }else{
                    editMoto3.setVisibility(View.GONE);

                }
            }
        });


    }
    private void Reset(){
        sBmoto1.setProgress(0);
        sBmoto2.setProgress(0);
        sBmoto3.setProgress(0);
        sBmoto4.setProgress(0);
        cBmoto1.setChecked(false);
        cBmoto2.setChecked(false);
        cBmoto3.setChecked(false);
        cBmoto4.setChecked(false);
        txtTotalMoney.setText("Total Money :" + money);

    }
    private boolean validate() {
        // Ensure at least one tank is selected and the bets are valid
        boolean isValid = false;
        if (cBmoto1.isChecked()) isValid = validateBet(editMoto1);
        if (cBmoto2.isChecked()) isValid = validateBet(editMoto2);
        if (cBmoto3.isChecked()) isValid = validateBet(editMoto3);
        if (cBmoto4.isChecked()) isValid = validateBet(editMoto4);

        if (!isValid) {
            Toast.makeText(this, "Please select a moto and enter a valid bet!", Toast.LENGTH_SHORT).show();
        }

        return isValid;
    }
    private boolean validateBet(EditText betField) {
        String betStr = betField.getText().toString();
        if (!betStr.isEmpty()) {
            int bet = Integer.parseInt(betStr);
            if (bet > 0 && bet <= money) {
                return true;
            }
        }
        return false;
    }

    private void Start(){
        sBmoto1.setProgress(0);
        sBmoto2.setProgress(0);
        sBmoto3.setProgress(0);
        sBmoto4.setProgress(0);
        btnStart.setEnabled(false);

        runnable = new Runnable() {
            @Override
            public void run() {
                int progress1 = sBmoto1.getProgress();
                int progress2 = sBmoto2.getProgress();
                int progress3 = sBmoto3.getProgress();
                int progress4 = sBmoto4.getProgress();

                if (progress1 < 100 && progress2 < 100 && progress3 < 100 && progress4 < 100) {
                    progress1 += random.nextInt(5);
                    progress2 += random.nextInt(5);
                    progress3 += random.nextInt(5);
                    progress4 += random.nextInt(5);

                    sBmoto1.setProgress(progress1);
                    sBmoto2.setProgress(progress2);
                    sBmoto3.setProgress(progress3);
                    sBmoto4.setProgress(progress4);

                    handler.postDelayed(runnable, 100);
                } else {
                    determineWinner(progress1, progress2, progress3, progress4);
                }
            }
        };
        handler.postDelayed(runnable, 100);
    }

    private void determineWinner(int progress1, int progress2, int progress3, int progress4) {
        List<Integer> winners = new ArrayList<>();

        // Determine the winners
        if (progress1 >= 100) winners.add(1);
        if (progress2 >= 100) winners.add(2);
        if (progress3 >= 100) winners.add(3);
        if (progress4 >= 100) winners.add(4);
        List<Integer> selectedMoto = getSelectedMoto();
        List<Integer> selectedTanks = getSelectedMoto();
        List<Integer> betAmounts = getBetAmounts(selectedTanks);
        boolean isWinner = false;
        boolean isTie = winners.size() > 1;

        int totalBet = 0;
        int winnings = 0;
        int losses = 0;
        for (int betAmount : betAmounts) {
            totalBet += betAmount;
        }
        for (int i = 0; i < selectedMoto.size(); i++) {
            if (winners.contains(selectedMoto.get(i))) {
                winnings += betAmounts.get(i);
            } else {
                losses += betAmounts.get(i);
            }
        }
        int newMoney = calculateMoney(winners, selectedTanks, betAmounts);

        // Start ResultActivity
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putIntegerArrayListExtra("selectedMoto", new ArrayList<>(selectedTanks));
        intent.putIntegerArrayListExtra("winningMoto", new ArrayList<>(winners));
        intent.putIntegerArrayListExtra("betAmounts", new ArrayList<>(betAmounts));
        intent.putExtra("isWinner", isWinner);
        intent.putExtra("isTie", isTie);
        intent.putExtra("newMoney", newMoney);
        intent.putExtra("betAmount", totalBet);
        intent.putExtra("winnings", winnings);
        intent.putExtra("losses", losses);
        intent.putExtra("totalBet", totalBet);
        startActivity(intent);


        btnStart.setEnabled(true);
    }
    private List<Integer> getSelectedMoto() {
        List<Integer> selectedMoto = new ArrayList<>();
        if (cBmoto1.isChecked()) selectedMoto.add(1);
        if (cBmoto2.isChecked()) selectedMoto.add(2);
        if (cBmoto3.isChecked()) selectedMoto.add(3);
        if (cBmoto4.isChecked()) selectedMoto.add(4);
        return selectedMoto;
    }
    private List<Integer> getBetAmounts(List<Integer> selectedMoto) {
        List<Integer> betAmounts = new ArrayList<>();
        for (int tank : selectedMoto) {
            switch (tank) {
                case 1:
                    betAmounts.add(Integer.parseInt(editMoto1.getText().toString()));
                    break;
                case 2:
                    betAmounts.add(Integer.parseInt(editMoto2.getText().toString()));
                    break;
                case 3:
                    betAmounts.add(Integer.parseInt(editMoto3.getText().toString()));
                    break;
                case 4:
                    betAmounts.add(Integer.parseInt(editMoto4.getText().toString()));
                    break;
            }
        }
        return betAmounts;
    }
    private int calculateMoney(List<Integer> winners, List<Integer> selectedMoto, List<Integer> betAmounts) {
        int totalWinnings = 0;
        int totalLosses = 0;

        for (int i = 0; i < selectedMoto.size(); i++) {
            int selectedTank = selectedMoto.get(i);
            int selectedMotoNumber = selectedMoto.get(i);
            int betAmount = betAmounts.get(i);

            if (winners.contains(selectedTank)) {
                totalWinnings += betAmount;
            } else {
                totalWinnings -= betAmount;
            }
        }

        money += totalWinnings - totalLosses;
        return money;
    }

}