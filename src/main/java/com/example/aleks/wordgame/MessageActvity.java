package com.example.aleks.wordgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleks.wordgame.Adapter.MessageAdapter;
import com.example.aleks.wordgame.Back.Bot;
import com.example.aleks.wordgame.Back.DictionaryWords;
import com.example.aleks.wordgame.Back.Player;
import com.example.aleks.wordgame.Models.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MessageActvity extends AppCompatActivity {

    Player player;
    Bot bot;

    Integer level;
    TextView score;
    Intent intent;
    EditText text_send;
    ImageButton btn_send;
    String msg;
    MessageAdapter messageAdapter;
    RecyclerView recyclerView;
    List<Chat> chats;
    Map<Boolean,String> messages;
    Context context;
    AlertDialog.Builder ad;
    EditText text_username;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_actvity);

        DictionaryWords.usedWords.clear();



        player = new Player("s");
        chats = new ArrayList<>();
        messageAdapter = new MessageAdapter(MessageActvity.this,chats);
        score = findViewById(R.id.score);
        text_send = findViewById(R.id.text_send);
        btn_send = findViewById(R.id.btn_send);
        Toolbar toolbar = findViewById(R.id.toolbar);
        messages = new HashMap<Boolean, String>();
        Bundle arguments = getIntent().getExtras();

        //intent = getIntent();

        if(arguments!=null) {
            level = arguments.getInt("lvl");
        }
        bot = new Bot(level);



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = text_send.getText().toString();
                if(!msg.equals("")) {
                    if(CheckWordFromUser(msg)) {
                        String prevWordFromBot = chats.get(chats.size()-1).getMessage();
                        sendMessage(msg,true);
                        player.count(prevWordFromBot,msg);
                        score.setText("Ваши очки: " + Integer.toString(player.getScore()) + "\n" +
                                                        "Очки бота: " + Integer.toString(bot.getScore()));
                        text_send.setText("");
                    }
                } else {
                    Toast.makeText(MessageActvity.this, "Введите слово!", Toast.LENGTH_SHORT).show();
                }

            }


        });


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle("");
        String wordFromBot = DictionaryWords.startWord();
        Chat chat = new Chat(wordFromBot,false);
        chats.add(chat);
        UpdateChat();
    }

    private Boolean CheckWordFromUser(String message) {
        if(!DictionaryWords.isWordInDict(message)) {
            Toast.makeText(MessageActvity.this, "Данного слово не существует", Toast.LENGTH_SHORT).show();
            text_send.setText("");
            return false;
        }
        if(DictionaryWords.isWordInUsed(message)) {
            Toast.makeText(MessageActvity.this, "Данного слова уже использовалось", Toast.LENGTH_SHORT).show();
            text_send.setText("");
            return false;
        } else {
            DictionaryWords.addUsedWord(message);
            return  true;
        }
    }

    private void UpdateChat() {

      // Если конец игры то вывести результаты
        if(chats.size() == 20) {
            Intent intent = new Intent(MessageActvity.this, ResultActivity.class);
            intent.putExtra("botScore", bot.getScore());
            intent.putExtra("playerScore", player.getScore());
            startActivity(intent);
        } else {
            messageAdapter = new MessageAdapter(MessageActvity.this, chats);
            recyclerView.setAdapter(messageAdapter);

        }
    }

    private void sendMessage(String msg, boolean queue) {
        Chat chat = new Chat(msg,queue);
        chats.add(chat);
        UpdateChat();
        if(chats.size() < 20) {
            sendMessageFromBot();
            UpdateChat();
            bot.count();
        }

    }

    private void sendMessageFromBot(){
        String wordFromBot;
        while (true) {
            wordFromBot = bot.generateWord(chats.get(chats.size()-1).getMessage());
            if (!DictionaryWords.isWordInUsed(wordFromBot)) {
                DictionaryWords.addUsedWord(wordFromBot);
                break;
            }
        }
       Chat chat = new Chat(wordFromBot, false);
       chats.add(chat);

        //Toast.makeText(MessageActvity.this, Integer.toString(bot.getScore()), Toast.LENGTH_SHORT).show();
    }
}
