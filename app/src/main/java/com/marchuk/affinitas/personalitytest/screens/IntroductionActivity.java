package com.marchuk.affinitas.personalitytest.screens;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.marchuk.affinitas.personalitytest.R;

/**
 * Created by Eugene on 13.09.2017.
 */
public class IntroductionActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_screen);
    }

    /**
     * open next page/activity
     */
    public void startQuestionAnswerScreen(View view) {
         // TODO: start next screen
        Toast.makeText(this, "Start button pressed", Toast.LENGTH_SHORT).show();
    }
}
