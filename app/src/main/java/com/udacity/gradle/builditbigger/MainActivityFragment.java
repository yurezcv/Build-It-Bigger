package com.udacity.gradle.builditbigger;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import ua.yurezcv.showjokeactivity.ShowJokeActivity;


public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.AsyncTaskCallback {

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
                tellJoke();
            }
        });

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    private void tellJoke() {
        Intent startJokeActivity = new Intent(getActivity(), ShowJokeActivity.class);
        startJokeActivity.putExtra(ShowJokeActivity.EXTRA_JOKE, mJoke);
        startActivity(startJokeActivity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new EndpointsAsyncTask().execute(this);
    }

    @Override
    public void onAsyncTaskBegins() {
        mButton.setEnabled(false);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAsyncTaskDone(String result) {
        mJoke = result;
        mButton.setEnabled(true);
        mProgressBar.setVisibility(View.GONE);
        mTextView.setText(R.string.instructions);
    }
}
