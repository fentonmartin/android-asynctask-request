package fen.code.androidasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    AsyncTask<String, String, String> mTask;
    private static String url = "YOUR URL";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);

        mTask = new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                try {
                    return getJsonFromServer(url);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result != null && result != "")
                    textView.setText(result);
            }
        };

        mTask.execute();
    }

    public static String getJsonFromServer(String url) throws IOException {

        URL jsonUrl = new URL(url);
        URLConnection dc = jsonUrl.openConnection();

        dc.setConnectTimeout(5000);
        dc.setReadTimeout(5000);

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(
                dc.getInputStream()));

        String jsonResult = inputStream.readLine();
        inputStream.close();
        return jsonResult;
    }
}
