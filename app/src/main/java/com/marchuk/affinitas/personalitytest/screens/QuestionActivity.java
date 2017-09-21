package com.marchuk.affinitas.personalitytest.screens;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentPagerAdapter;

import com.marchuk.affinitas.personalitytest.R;
import com.marchuk.affinitas.personalitytest.data.IQuestion;
import com.marchuk.affinitas.personalitytest.data.source.QuestionDataStub;
import com.marchuk.affinitas.personalitytest.databinding.QuestionScreenBinding;

import java.util.LinkedList;
import java.util.List;

/**
 * Screen for questions and answers
 * <p>
 * Created by Ievgen on 21.09.2017.
 */
public class QuestionActivity extends Activity {

    private final List<IQuestion> mData = new LinkedList<>();
    private QuestionScreenBinding mQScreenBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQScreenBinding = DataBindingUtil
                .setContentView(this, R.layout.question_screen);

        mQScreenBinding.qaViewpagerQuestionContainer
                .setAdapter(new QAAdapter(getFragmentManager(), mData));
        requestDataSync();
    }

    /**
     * @deprecated TODO: should be replaced with async request
     */
    private void requestDataSync() {
        mData.clear();
        mData.addAll(new QuestionDataStub().getQuestions());
        mQScreenBinding.qaViewpagerQuestionContainer.getAdapter().notifyDataSetChanged();
    }

    private static class QAAdapter extends FragmentPagerAdapter {
        private final List<IQuestion> mData;

        QAAdapter(FragmentManager fm, List<IQuestion> data) {
            super(fm);
            mData = data;
        }

        @Override
        public Fragment getItem(int position) {
            QAPageFragment qaPageFragment = new QAPageFragment();
            qaPageFragment.setQuestion(mData.get(position));
            return qaPageFragment;
        }

        @Override
        public int getCount() {
            return mData != null ? mData.size() : 0;
        }
    }
}
