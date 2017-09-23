package com.marchuk.affinitas.personalitytest.screens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.Toast;

import com.marchuk.affinitas.personalitytest.App;
import com.marchuk.affinitas.personalitytest.R;
import com.marchuk.affinitas.personalitytest.data.IQuestion;
import com.marchuk.affinitas.personalitytest.data.source.QuestionDataSource;
import com.marchuk.affinitas.personalitytest.databinding.QuestionScreenBinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Screen for questions and answers
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public class QuestionActivity extends Activity {
    private static final String TAG = QuestionActivity.class.getSimpleName();

    private final List<IQuestion> mData = new LinkedList<>();
    private final CompositeDisposable disposable = new CompositeDisposable();

    private QuestionScreenBinding mQScreenBinding;

    @Inject
    QuestionDataSource mDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getDataSourceComponent().inject(this);

        mQScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.question_screen);

        mQScreenBinding.qaViewpagerQuestionContainer
                .setAdapter(new QAAdapter(getFragmentManager(), mData));

        requestDataAsync();
    }

    /**
     * Request question data in async.
     * <p>
     * On Loading: show progress bar<p>
     * On Success: show questions<p>
     * On Error: show error dialog<p>
     * On Empty: show "No Data" dialog
     */
    private void requestDataAsync() {
        // show progress dialog before data request
        final ProgressDialog progressDialog = ProgressDialog
                .show(this, null, getString(R.string.qa_screen_progress_dialog_text), true);

        disposable.add(Maybe.fromCallable(() -> mDataSource.getQuestions())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEvent((a, b) -> progressDialog.dismiss())
                .subscribe((data) -> {
                    mData.clear(); // TODO: find proper behavior
                    if (data == null || data.isEmpty()) {
                        // TODO: implement empty state
                        Log.i(TAG, "No questions to show");
                    } else {
                        mData.addAll(data);
                    }
                    mQScreenBinding.qaViewpagerQuestionContainer.getAdapter()
                            .notifyDataSetChanged();
                }, (e) -> {
                    Log.e(TAG, "Can't load question data.", e);
                    createAlertDialog().show();
                }, () -> Log.i(TAG, "Question data loading finished")));
    }

    /**
     * @return constructed error dialog for server request failed
     */
    private AlertDialog createAlertDialog() {
        return new AlertDialog.Builder(this)
                .setPositiveButton(R.string.retry, (dialog, which) -> {})
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> finish())
                .create();
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

    /**
     * Question page adapter
     */
    private class QAAdapter extends FragmentPagerAdapter
            implements QAPageFragment.OnAnswerConfirmListener {
        private final List<IQuestion> mData;

        /**
         * Container for selected user answers.
         * FIXME: Possible runtime Localisation issues?!
         */
        private final Map<String, String> mSelectedAnswers = new HashMap<>();

        QAAdapter(FragmentManager fm, List<IQuestion> data) {
            super(fm);
            mData = data;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

        @Override
        public Fragment getItem(int position) {
            QAPageFragment qaPageFragment = new QAPageFragment();
            qaPageFragment.setQuestion(mData.get(position));
            qaPageFragment.setAnswerListener(this);
            return qaPageFragment;
        }

        @Override
        public int getCount() {
            // in same time only one not answered question available
            return mData == null || mData.isEmpty() ? 0 : mSelectedAnswers.size() + 1;
        }

        @Override
        public void onAnswerConfirmed(String question, String answer) {
            // update answers list request update to show new page
            mSelectedAnswers.put(question, answer);
            notifyDataSetChanged();

            Toast.makeText(QuestionActivity.this,
                    "Question: " + question + "\nAnswer: " + answer, Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
