package com.example.firstandroidcode.chapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstandroidcode.R;

import java.util.ArrayList;
import java.util.List;

public class Chapter3_1 extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3_1);
        initMsgs();
        inputText = findViewById(R.id.chapter3_1_et1);
        send = findViewById(R.id.chapter3_1_btn1);
        msgRecyclerView = findViewById(R.id.chapter3_1_rv1);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });

    }
    private void initMsgs() {
        Msg msg1 = new Msg("Hello", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("How are you", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("Fine, thank you", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
