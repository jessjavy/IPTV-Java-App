package com.jesusjavier.iptvapp.network;

import androidx.annotation.NonNull;

import com.jesusjavier.iptvapp.model.Channel;
import com.jesusjavier.iptvapp.model.M3UParser;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class M3UService {
    private final OkHttpClient client = new OkHttpClient();

    public interface M3UResponseListener {
        void onChannelsLoaded(List<Channel> channels);

        void onError(Exception e);
    }

    public void fetchChannels(String url, M3UResponseListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String content = response.body().string();

                    M3UParser parser = new M3UParser();
                    List<Channel> channels =parser.parse(content);

                    listener.onChannelsLoaded(channels);
                } else{
                    listener.onError(new IOException("Error en el servidor"));
                }
            }
        });
    }
}
