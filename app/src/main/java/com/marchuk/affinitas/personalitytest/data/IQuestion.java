package com.marchuk.affinitas.personalitytest.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Describe question data container
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public interface IQuestion {
    /**
     * @return text of question
     */
    @NonNull
    String getQuestion();

    /**
     * @return related answer proposition
     */
    @NonNull
    AnswerVariants getAnswerProposition();

    /**
     * @return another question if answer satisfy some condition. null - if no related conditions
     */
    @Nullable
    List<IQuestion> checkCondition(String answer);
}
