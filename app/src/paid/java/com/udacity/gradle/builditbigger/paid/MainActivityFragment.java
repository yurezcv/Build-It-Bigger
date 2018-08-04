package com.udacity.gradle.builditbigger.paid;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;

import ua.yurezcv.showjokeactivity.ShowJokeActivity;


public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.AsyncTaskCallback {

    private static final String BUNDLE_KEY_JOKE = "bundle-key-joke";

    private Button mButton;
    private ProgressBar mProgressBar;
    private TextView mTextView;

    private String mJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mButton = root.findViewById(R.id.btn_display_joke);
        mProgressBar = root.findViewById(R.id.pb_loading_jokes);
        mTextView = root.findViewById(R.id.instructions_text_view);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mJoke)) {
                    getJoke();
                } else {
                    startTellJokeActivity();
                }
            }
        });

        return root;
    }

    private void startTellJokeActivity() {
        Intent startJokeActivity = new Intent(getActivity(), ShowJokeActivity.class);
        startJokeActivity.putExtra(ShowJokeActivity.EXTRA_JOKE, mJoke);
        startActivity(startJokeActivity);
    }

    private void getJoke() {
        new EndpointsAsyncTask().execute(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null) {
            mJoke = savedInstanceState.getString(BUNDLE_KEY_JOKE);
            activateViews();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(!TextUtils.isEmpty(mJoke)) {
            outState.putString(BUNDLE_KEY_JOKE, mJoke);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAsyncTaskBegins() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.VISIBLE);
                mTextView.setText(R.string.getting_jokes);
            }
        });
    }

    @Override
    public void onAsyncTaskDone(String result) {
        mJoke = result;
        activateViews();
        startTellJokeActivity();
    }

    private void activateViews() {
        mButton.setEnabled(true);
        mProgressBar.setVisibility(View.GONE);
        mTextView.setText(R.string.instructions);
    }
}

