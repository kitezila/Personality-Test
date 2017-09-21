package com.marchuk.affinitas.personalitytest.data;

import java.util.List;

/**
 * Description of question answer proposition
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public interface AnswerVariants {

    interface Range extends AnswerVariants {
        /**
         * @return start of range
         */
        int getFrom();

        /**
         * @return end of range
         */
        int getTo();
    }

    interface Options extends AnswerVariants {
        /**
         * @return list of possible answers
         */
        List<String> getListOfAnswers();
    }
}
