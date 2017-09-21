package com.marchuk.affinitas.personalitytest.screens.widgets;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

/**
 * Describe view that can select answer
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public interface AnswerSelector {
    /**
     * @return field that contains changeable answer
     */
    @NonNull
    ObservableField<String> getAnswerObservable();
}
