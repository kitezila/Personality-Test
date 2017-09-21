package com.marchuk.affinitas.personalitytest.data.source;

import com.marchuk.affinitas.personalitytest.data.IQuestion;

import java.util.List;

/**
 * Description of Question data source
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public interface QuestionDataSource {
    /**
     * Synchronous operation of retrieving questions. Possible long execution.
     *
     * @return complete list of questions
     */
    List<IQuestion> getQuestions();
}
