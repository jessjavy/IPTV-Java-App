package com.jesusjavier.iptvapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jesusjavier.iptvapp.R;
import com.jesusjavier.iptvapp.model.Channel;
import com.jesusjavier.iptvapp.view.adapters.ChannelAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChannelAdapter adapter;
    private List<Channel> channelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewChannels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        channelList = new ArrayList<>();
        channelList.add(new Channel("Canal de prueba 1", "Canal de prueba 2", "Canal de prueba 3", "Canal de prueba 4"));
        adapter = new ChannelAdapter(channelList);
        recyclerView.setAdapter(adapter);
    }
}