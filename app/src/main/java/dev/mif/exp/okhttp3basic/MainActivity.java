package dev.mif.exp.okhttp3basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import okhttp3.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tvResponse;
    Button btOkhttp, btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResponse = findViewById(R.id.tvResponse);
        btOkhttp = findViewById(R.id.btOkHttp);
        btReset = findViewById(R.id.btReset);

        btOkhttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvResponse.setText("");
            }
        });
    }

    public void getOkHttp(){
        OkHttpClient client = new OkHttpClient();

         /*
         * basic GET request:
         String url = "https://api.github.com";
            Request request = new Request.Builder()
            .url(url)
            .get()
            .build();
         */

         /*
         * basic POST request:
              RequestBody body = RequestBody.create(JSON, json);
              Request request = new Request.Builder()
                  .url(url)
                  .post(body)
                  .build();
         */

        String url = "https://publicobject.com/helloworld.txt";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        /*
         * Basic Response
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            String jsonResponse = response.body.string();
         */

        // #nb enquee: waiting process
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                tvResponse.setText("error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String strResponse = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvResponse.setText(strResponse);
                    }
                });
            }
        });
    }
}
