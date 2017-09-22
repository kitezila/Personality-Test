package com.marchuk.affinitas.personalitytest.screens;

import android.app.Fragment;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marchuk.affinitas.personalitytest.data.IQuestion;
import com.marchuk.affinitas.personalitytest.databinding.QaPageBinding;
import com.marchuk.affinitas.personalitytest.screens.widgets.AnswerSelector;
import com.marchuk.affinitas.personalitytest.screens.widgets.AnswerViewFabric;

import java.lang.ref.WeakReference;

/**
 * Page of question/answer. Answer will be selected/specified by user
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public class QAPageFragment extends Fragment {
    private static final String TAG = QAPageFragment.class.getSimpleName();

    private IQuestion mQuestion;
    private WeakReference<OnAnswerConfirmListener> mAnswerListener;

    {
        setRetainInstance(true);
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
     * @param question to be shown
     */
    public void setQuestion(IQuestion question) {
        Log.d(TAG, "Setting question: " + question);

        this.mQuestion = question;
    }

    /**
     * @param listener for answer on question, confirmed by user
     */
    public void setAnswerListener(OnAnswerConfirmListener listener) {
        mAnswerListener = new WeakReference<>(listener);
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
         * Submit {@link #answer} to listener {@link OnAnswerConfirmListener}.
         */
        public void submitAnswer() {
            Log.d(TAG, String.format("Submitting: '%s' > '%s'", question, answer));

            OnAnswerConfirmListener listener =
                    mAnswerListener == null ? null : mAnswerListener.get();

            if (listener != null) {
                listener.onAnswerConfirmed(question, answer.get());
            }
        }
    }

    /**
     * listener for answer from question page
     */
    interface OnAnswerConfirmListener {
        /**
         * @param question that was asked from user
         * @param answer   selected answer
         */
        void onAnswerConfirmed(String question, String answer);
    }
}
