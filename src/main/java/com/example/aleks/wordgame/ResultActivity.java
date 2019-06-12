package com.example.aleks.wordgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.aleks.wordgame.Back.DictionaryWords;

public class ResultActivity extends AppCompatActivity {


    int botScore;
    int playerScore;
    WebView webView;
    String winOrLose ;
    TextView cong_text;

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        DictionaryWords.usedWords.clear();


//        GifWebView gifWebView = new GifWebView(this,
//                "file:///android_asset/tenor.gif");
//        setContentView(gifWebView);

        webView = findViewById(R.id.gifka);
        cong_text = findViewById(R.id.congratulation_text);


        Bundle arguments = getIntent().getExtras();


        if(arguments!=null) {
            botScore = arguments.getInt("botScore");
            playerScore = arguments.getInt("playerScore");
        }

        if(botScore < playerScore){
            winOrLose = "file:///android_asset/tenor.gif";
            cong_text.setText("Поздравляю! Вы победили!");
        } else {
            winOrLose = "file:///android_asset/ho.gif";
            cong_text.setText("Не расстраивайтесь... В следующий раз повезет!");
        }
        webView.loadUrl(winOrLose);
    }
    @Override
    protected void onPause(){
        super.onPause();
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
