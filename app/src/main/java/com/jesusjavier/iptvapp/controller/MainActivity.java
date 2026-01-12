package com.jesusjavier.iptvapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.jesusjavier.iptvapp.R;
import com.jesusjavier.iptvapp.model.Channel;
import com.jesusjavier.iptvapp.network.M3UService;
import com.jesusjavier.iptvapp.view.adapters.ChannelAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChannelAdapter adapter;
    private List<Channel> channelList;

    private M3UService m3UService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewChannels);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        m3UService = new M3UService();
        cargarCanalesIPTV();
    }

    private void cargarCanalesIPTV() {
        String urlM3U = "https://iptv-org.github.io/iptv/countries/mx.m3u";

        m3UService.fetchChannels(urlM3U, new M3UService.M3UResponseListener() {
            @Override
            public void onChannelsLoaded(List<Channel> channels) {
                runOnUiThread(() -> {
                    channelList.clear();
                    channelList.addAll(channels);

                    if (adapter == null) {
                        adapter = new ChannelAdapter(channelList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this,
                            "Error al cargar canales: " + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}