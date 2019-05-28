package com.kalpesh.glabbr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kalpesh.glabbr.Utills.Typefaces;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start_button);
        startButton.setTypeface(Typefaces.getInstance(this).getTypeface(Typefaces.Style.ITALIC));
        startButton.setOnClickListener(this);
    }

    private void loadMessageDetailsActivity() {
        Intent intent = new Intent(MainActivity.this, MessageDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(view == startButton) {
            loadMessageDetailsActivity();
        }
    }
}
