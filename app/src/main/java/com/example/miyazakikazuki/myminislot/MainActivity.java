package com.example.miyazakikazuki.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 起動時にデータをクリアする
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();


        TextView setkakekin = (TextView)findViewById(R.id.textView10);
        setkakekin.setText("1000");

        Button sendButton = (Button)findViewById(R.id.button7);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), GameActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onPatinkoButtonTapped(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    public void onCntUpButtonTapped(View view){
        TextView Textup = (TextView)findViewById(R.id.textView11);
        int i = Integer.parseInt(Textup.getText().toString()) + 10;
        String s = Integer.toString(i);
        Textup.setText(s);
    }
    public void onCntDownButtonTapped(View view){
        TextView Textup = (TextView)findViewById(R.id.textView11);
        int j = Integer.parseInt(Textup.getText().toString()) - 10;
        String s = Integer.toString(j);
        Textup.setText(s);
    }

    public void onResetButtontapped(View view){
        TextView textView = (TextView)findViewById(R.id.textView10);
        String s = Integer.toString(1000);
        textView.setText(s);
    }

}
