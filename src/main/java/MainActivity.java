package net.selimseker.sayibulmaca;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends Activity implements View.OnClickListener {
    View linear_layout;
    ImageButton plus_thousand, plus_hundred, plus_ten, plus_one;
    ImageButton minus_thousand, minus_hundred, minus_ten, minus_one;
    Button okay;
    Button reset;
    TextView thousand_tv, hundred_tv, ten_tv, one_tv;
    Random rnd = new Random();
    ArrayList<String> number_list = new ArrayList<>();
    ArrayList<String> random_number = new ArrayList<>();
    ArrayList<String> estimate = new ArrayList<>();
    int estimate_number;
    TextView tv1;
    int plus = 0;
    int minus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear_layout = findViewById(R.id.estimate_screen);

        reset = (Button) findViewById(R.id.reset);
        tv1 = (TextView) findViewById(R.id.tv1);
        plus_thousand = (ImageButton) findViewById(R.id.thousands_plus);
        plus_hundred = (ImageButton) findViewById(R.id.hundreds_plus);
        plus_ten = (ImageButton) findViewById(R.id.tens_plus);
        plus_one = (ImageButton) findViewById(R.id.ones_plus);

        okay = (Button) findViewById(R.id.okay);

        minus_thousand = (ImageButton) findViewById(R.id.thousands_minus);
        minus_hundred = (ImageButton) findViewById(R.id.hundreds_minus);
        minus_ten = (ImageButton) findViewById(R.id.tens_minus);
        minus_one = (ImageButton) findViewById(R.id.ones_minus);

        thousand_tv = (TextView) findViewById(R.id.thousands_text);
        hundred_tv = (TextView) findViewById(R.id.hundreds_text);
        ten_tv = (TextView) findViewById(R.id.tens_text);
        one_tv = (TextView) findViewById(R.id.ones_text);


        for (int i = 0; i <= 9; i++) {
            number_list.add(String.valueOf(i));
            Collections.shuffle(number_list);
        }

        for (int i = 1; i <= 4; i++) {
            int chosen_int = rnd.nextInt(number_list.size());
            String chosen = number_list.get(chosen_int);
            random_number.add(chosen);
            number_list.remove(chosen_int);
        }
        Collections.shuffle(number_list);

	//
        for (int i = 0; i < 4; i++) {
            Log.d("chosen", random_number.get(i));	Just for check :D
        }
	//
    }

    @Override
    public void onClick(View view) {
        boolean same_number=false;

        switch (view.getId()) {
            case R.id.thousands_plus:
                if (thousand_tv.getText().toString().equals("9"))
                    thousand_tv.setText("-1");
                int i = Integer.parseInt((String) thousand_tv.getText());
                int j = i + 1;
                thousand_tv.setText(String.valueOf(j));
                break;

            case R.id.hundreds_plus:
                if (hundred_tv.getText().toString().equals("9"))
                    hundred_tv.setText("-1");
                i = Integer.parseInt((String) hundred_tv.getText());
                j = i + 1;
                hundred_tv.setText(String.valueOf(j));
                break;

            case R.id.tens_plus:
                if (ten_tv.getText().toString().equals("9"))
                    ten_tv.setText("-1");
                i = Integer.parseInt((String) ten_tv.getText());
                j = i + 1;
                ten_tv.setText(String.valueOf(j));
                break;

            case R.id.ones_plus:
                if (one_tv.getText().toString().equals("9"))
                    one_tv.setText("-1");
                i = Integer.parseInt((String) one_tv.getText());
                j = i + 1;
                one_tv.setText(String.valueOf(j));
                break;

            case R.id.thousands_minus:
                if (thousand_tv.getText().toString().equals("0"))
                    thousand_tv.setText("10");
                i = Integer.parseInt((String) thousand_tv.getText());
                j = i - 1;
                thousand_tv.setText(String.valueOf(j));
                break;

            case R.id.hundreds_minus:
                if (hundred_tv.getText().toString().equals("0"))
                    hundred_tv.setText("10");
                i = Integer.parseInt((String) hundred_tv.getText());
                j = i - 1;
                hundred_tv.setText(String.valueOf(j));
                break;

            case R.id.tens_minus:
                if (ten_tv.getText().toString().equals("0"))
                    ten_tv.setText("10");
                i = Integer.parseInt((String) ten_tv.getText());
                j = i - 1;
                ten_tv.setText(String.valueOf(j));
                break;

            case R.id.ones_minus:
                if (one_tv.getText().toString().equals("0"))
                    one_tv.setText("10");
                i = Integer.parseInt((String) one_tv.getText());
                j = i - 1;
                one_tv.setText(String.valueOf(j));
                break;

            case R.id.okay:

                estimate_number += 1;

                estimate.add(0, thousand_tv.getText().toString());
                estimate.add(1, hundred_tv.getText().toString());
                estimate.add(2, ten_tv.getText().toString());
                estimate.add(3, one_tv.getText().toString());

                int control = 1;
                for (int e = 0; e < 3; e++) {
                    for (int r=control;r<4;r++){
                        if (estimate.get(e).equals(estimate.get(r))) {
                            same_number = true;
                        }
                    }
                    control++;
                }
                if (same_number == true) {
                    estimate.removeAll(estimate);
                    Toast.makeText(MainActivity.this, "Your estimate must not contain same digits.", Toast.LENGTH_LONG).show();
                    same_number=false;
                    estimate_number--;
                    break;
                }


                for (int a = 0; a < 4; a++) {
                    if (random_number.contains(estimate.get(a))) {
                        if (random_number.get(a).equals(estimate.get(a)))
                            plus++;
                        else
                            minus++;
                    }
                }
                if (estimate_number < 10) {
                    TextView tv = new TextView(this);
                    tv.setText("  " + estimate_number + ". Estimate:     " + estimate + "     " + plus + "+  " + minus + "-");
                    tv.setId(estimate_number);
                    tv.setTextSize(20);
                    tv.setPadding(20, 20, 20, 20);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(875, 105));
                    ((LinearLayout) linear_layout).addView(tv);
                    estimate.removeAll(estimate);
                    plus = 0;
                    minus = 0;
                } else if (estimate_number >= 10) {
                    TextView tv = new TextView(this);
                    tv.setText(estimate_number + ". Estimate:     " + estimate + "     " + plus + "+  " + minus + "-");
                    tv.setId(estimate_number);
                    tv.setTextSize(20);
                    tv.setPadding(20, 20, 20, 20);
                    tv.setLayoutParams(new LinearLayout.LayoutParams(875, 105));
                    ((LinearLayout) linear_layout).addView(tv);
                    estimate.removeAll(estimate);
                    plus = 0;
                    minus = 0;
                } else if (estimate_number == 1) {
                    tv1.setText("  " + estimate_number + ". Estimate:     " + estimate + "     " + plus + "+  " + minus + "-");
                    estimate.removeAll(estimate);
                    plus = 0;
                    minus = 0;
                }
                break;
            case R.id.reset:
                estimate.removeAll(estimate);
                random_number.removeAll(random_number);
                estimate_number=0;
                thousand_tv.setText("0");
                hundred_tv.setText("0");
                ten_tv.setText("0");
                one_tv.setText("0");

        }
    }
}

