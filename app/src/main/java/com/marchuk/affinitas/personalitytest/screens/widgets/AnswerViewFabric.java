package com.marchuk.affinitas.personalitytest.screens.widgets;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.marchuk.affinitas.personalitytest.data.AnswerVariants;

/**
 * Fabric for different answer view types
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public class AnswerViewFabric {

    public static View constructAnswerView(@NonNull AnswerVariants answerVariants,
                                           LayoutInflater inflater) {

        if (answerVariants instanceof AnswerVariants.Options) {
            return new SingleOptionView(inflater.getContext())
                    .showOptions((AnswerVariants.Options) answerVariants);
        } else {
            // TODO: make implementation more flexible
            throw new UnsupportedOperationException("Unknown answer type: " + answerVariants);
        }
    }
}