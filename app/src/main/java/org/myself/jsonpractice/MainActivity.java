package org.myself.jsonpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import p32929.updaterlib.AppUpdater;
import p32929.updaterlib.UpdateListener;
import p32929.updaterlib.UpdateModel;

public class MainActivity extends AppCompatActivity {

    TextView textView_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidNetworking.initialize(getApplicationContext());

        textView_Name = findViewById(R.id.textView_Name);

        populateList();
    }

    private void populateList() {
        new AppUpdater(this, "https://raw.githubusercontent.com/RazM10/Data/master/DummyData.json", new UpdateListener() {
            @Override
            public void onJsonDataReceived(final UpdateModel updateModel, JSONObject jsonObject) {
                try {
                    JSONArray array = jsonObject.getJSONArray("story");

                    String s = array.toString();
                    JSONArray a = new JSONArray(s);
//                    textView_Name.setText(s);

                    for (int i = 0; i < a.length(); i++) {
                        JSONObject object = a.getJSONObject(i);
                        if (i == 0) {
                            String nameEng = object.getString("hitsEnglish");
                            String nameBan = object.getString("hintsBangla");
                            textView_Name.setText(nameEng+"\n\n\n"+nameBan);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {
                // Do something
            }
        }).execute();
    }
}
