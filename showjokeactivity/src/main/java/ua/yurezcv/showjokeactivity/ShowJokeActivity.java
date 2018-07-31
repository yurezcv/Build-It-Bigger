package ua.yurezcv.showjokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "KEY_EXTRA_JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);

        TextView jokeTextView = findViewById(R.id.tv_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra(EXTRA_JOKE);

        if(TextUtils.isEmpty(joke)) {
            // nobody should use this Android library without passing an awesome joke
            throw new RuntimeException(getString(R.string.error_no_joke));
        } else {
            jokeTextView.setText(joke);
        }
    }
}
