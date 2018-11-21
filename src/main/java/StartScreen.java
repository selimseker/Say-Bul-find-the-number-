package net.selimseker.sayibulmaca;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartScreen extends Activity {

    ImageView backgroundOne;
    ImageView backgroundTwo;
    ImageButton play, howtoplay;
    final Context context = this;
    View layout, layout_dialog;
    ImageButton next, previous;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);


        layout = findViewById(R.id.layout);
        textView = (TextView) findViewById(R.id.text_how);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next_btn);
        layout_dialog = findViewById(R.id.layout_dialog);
        layout_dialog.setVisibility(View.INVISIBLE);

        backgroundOne = (ImageView) findViewById(R.id.background_one);
        backgroundTwo = (ImageView) findViewById(R.id.background_two);


        final ValueAnimator animator = ValueAnimator.ofFloat(4.5f, 0.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(5900);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = 105;
                final float translationX = width * progress;
                backgroundOne.setTranslationX(translationX);
                backgroundTwo.setTranslationX(translationX -width);
            }
        });
        animator.start();

        play = (ImageButton) findViewById(R.id.play);
        howtoplay = (ImageButton) findViewById(R.id.howtoplay);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setVisibility(View.INVISIBLE);
                previous.setVisibility(View.INVISIBLE);
                layout_dialog.setVisibility(View.VISIBLE);
                textView.setText(R.string.dialog1);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        textView.setText(R.string.dialog2);
                        previous.setVisibility(View.VISIBLE);
                        previous.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                textView.setText(R.string.dialog1);
                                previous.setVisibility(View.INVISIBLE);
                            }
                        });
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                layout_dialog.setVisibility(View.INVISIBLE);
                                layout.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                });

            }
        });


    }
}
