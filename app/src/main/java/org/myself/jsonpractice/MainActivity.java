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

        textView_Name=findViewById(R.id.textView_Name);

        populateList();
    }

    private void populateList() {
        new AppUpdater(this, "https://raw.githubusercontent.com/RazM10/Data/master/DummyData.json", new UpdateListener() {
            @Override
            public void onJsonDataReceived(final UpdateModel updateModel, JSONObject jsonObject) {
//                OnlineData onlineData = new Gson().fromJson(jsonObject.toString(), OnlineData.class);
//                classeModels = onlineData.getClasses();
//                adapderClass.replaceArrayList(classeModels);
                try {
                    JSONArray array=jsonObject.getJSONArray("story");
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String name=object.getString("adviseBangla");
                        textView_Name.setText(name);
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
