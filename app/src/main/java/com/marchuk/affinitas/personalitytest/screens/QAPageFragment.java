package com.marchuk.affinitas.personalitytest.screens;

import android.app.Fragment;
import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marchuk.affinitas.personalitytest.data.IQuestion;
import com.marchuk.affinitas.personalitytest.databinding.QaPageBinding;
import com.marchuk.affinitas.personalitytest.screens.widgets.AnswerSelector;
import com.marchuk.affinitas.personalitytest.screens.widgets.AnswerViewFabric;

/**
 * Page of question/answer. Answer will be selected/specified by user
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public class QAPageFragment extends Fragment {
    private static final String TAG = QAPageFragment.class.getSimpleName();

    private IQuestion mQuestion;

    public void setQuestion(IQuestion question) {
        Log.d(TAG, "Setting question: " + question);

        this.mQuestion = question;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        QaPageBinding binder = QaPageBinding.inflate(inflater);

        final IQuestion question = mQuestion;

        if (question != null) {

            // construct and init answer view

            View answerView = AnswerViewFabric
                    .constructAnswerView(question.getAnswerProposition(), inflater);
            if (answerView instanceof AnswerSelector) { // TODO: there is better solution?!
                binder.setData(new QuestionPageModel(question.getQuestion(),
                        ((AnswerSelector) answerView).getAnswerObservable()));
            } else {
                Log.e(TAG, "Can't show answer. View is not answer selector: " + answerView);
            }

            binder.qaAnswerContainer.addView(answerView);
        } else {
            Log.e(TAG, "Question not specified!");
        }

        return binder.getRoot();
    }

    /**
     * Data container for question page layout
     */
    public class QuestionPageModel {
        public final String question;
        /**
         * User selected answer container
         */
        public final ObservableField<String> answer;

        private QuestionPageModel(String question, ObservableField<String> answer) {
            this.question = question;
            this.answer = answer;
        }

        /**
         * Submit {@link #answer}.
         *
         * @deprecated TODO: implementation required
         */
        public void submitAnswer() {
            Context context = getActivity();
            if (context != null) {
                Toast.makeText(context, "Answer submitted: " + answer.get(), Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
