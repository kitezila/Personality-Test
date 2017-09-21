package com.marchuk.affinitas.personalitytest.screens.widgets;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.marchuk.affinitas.personalitytest.data.AnswerVariants;

/**
 * Represents answers in option form with single selet
 * Created by Ievgen on 20.09.2017.
 */
class SingleOptionView extends RadioGroup implements AnswerSelector {
    private final ObservableField<String> mAnswer = new ObservableField<>();

    public SingleOptionView(Context context) {
        super(context);
    }

    public SingleOptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @UiThread
    public void setOptions(AnswerVariants.Options options) {
        removeAllViews();
        for (String variant : options.getListOfAnswers()) {
            RadioButton rbtn = new RadioButton(getContext());
            rbtn.setText(variant);
            rbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        mAnswer.set(buttonView.getText().toString());
                    }
                }
            });
            addView(rbtn);
        }
    }

    @NonNull
    @Override
    public ObservableField<String> getAnswerObservable() {
        return mAnswer;
    }
}
