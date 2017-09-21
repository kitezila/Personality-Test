package com.marchuk.affinitas.personalitytest.data.source;

import android.support.annotation.NonNull;
import android.util.Log;

import com.marchuk.affinitas.personalitytest.data.AnswerVariants;
import com.marchuk.affinitas.personalitytest.data.IQuestion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ievgen on 20.09.2017.
 *
 * @deprecated stub data source. Not for production.
 */
public class QuestionDataStub implements QuestionDataSource {
    private static final String TAG = QuestionDataSource.class.getSimpleName();
    private final List<IQuestion> mData;

    {
        mData = new ArrayList<>();
        mData.add(new QuestionStub("What is your favorite animal?",
                new AnswerOptionStub("Elephant", "Kangaroo", "Dog")));
        mData.add(new QuestionStub("What year today?",
                new AnswerOptionStub("2012", "1212", "777")));
//        mData.add(new QuestionStub("What is your age?", new AnswerRangeStub(10, 14)));
    }

    @Override
    public List<IQuestion> getQuestions() {
        Log.d(TAG, "Data request started");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Data request finished");
        return mData;
    }

    private class QuestionStub implements IQuestion {

        private final String mQuestion;
        private final AnswerVariants mAnswer;

        private QuestionStub(String question, AnswerVariants answers) {
            this.mQuestion = question;
            mAnswer = answers;
        }

        @NonNull
        @Override
        public String getQuestion() {
            return mQuestion;
        }

        @NonNull
        @Override
        public AnswerVariants getAnswerProposition() {
            return mAnswer;
        }

        @Override
        public List<IQuestion> checkCondition(String answer) {
            return null;
        }
    }

    private class AnswerOptionStub implements AnswerVariants.Options {
        private final List<String> mAnswers;

        private AnswerOptionStub(String... answers) {
            mAnswers = Arrays.asList(answers);
        }

        @Override
        public List<String> getListOfAnswers() {
            return mAnswers;
        }
    }

    private class AnswerRangeStub implements AnswerVariants.Range {
        private final int mFrom;
        private final int mTo;

        private AnswerRangeStub(int mFrom, int mTo) {
            this.mFrom = mFrom;
            this.mTo = mTo;
        }

        @Override
        public int getFrom() {
            return mFrom;
        }

        @Override
        public int getTo() {
            return mTo;
        }
    }
}
