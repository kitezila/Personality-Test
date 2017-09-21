package com.marchuk.affinitas.personalitytest.screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.marchuk.affinitas.personalitytest.R;

/**
 * Introduction screen for question/answer application
 * <p>
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
        startActivity(new Intent(this, QuestionActivity.class));
    }
}
