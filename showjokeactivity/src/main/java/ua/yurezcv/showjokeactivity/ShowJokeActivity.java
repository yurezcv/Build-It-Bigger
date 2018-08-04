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
        if(intent.hasExtra(EXTRA_JOKE)) {
            String joke = intent.getStringExtra(EXTRA_JOKE);
            jokeTextView.setText(joke);
        } else {
            throw new RuntimeException(getString(R.string.error_no_joke));
        }
    }
}
