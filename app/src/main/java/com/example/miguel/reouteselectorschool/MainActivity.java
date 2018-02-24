package com.example.miguel.reouteselectorschool;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private String valorPrueba = "hola mundos";

    ArrayList<Bus> buses = new ArrayList<>();

    String url = "https://api.myjson.com/bins/10yg1t";
    JSONObject jsonObject;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button =  findViewById(R.id.pruebaButton);

        new AsyncTaskData().execute(url);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(valorPrueba);
            }
        });
    }

    public class AsyncTaskData extends AsyncTask<String, String, String[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(String... urls) {

            try {
                jsonObject = JsonAdministrator.readJsonFromUrl(urls[0]);

                System.out.println("Response: " + jsonObject.getString("response"));
                JSONArray busList = jsonObject.getJSONArray("school_buses");

                for (int i = 0; i < busList.length(); i++) {
                    JSONObject bus = busList.getJSONObject(i);
                    buses.add(createBus(bus));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
        }

        protected Bus createBus( JSONObject jBus ) throws JSONException, IOException {
            Bus newBus = new Bus();
            JSONObject jsonRoute;
            String stops_url;

            System.out.println("############################################");
            System.out.println("id: " + jBus.getString("id"));
            newBus.setId(Integer.valueOf(jBus.getString("id")));
            System.out.println("name: " + jBus.getString("name"));
            newBus.setName(jBus.getString("name"));
            System.out.println("description: " + jBus.getString("description"));
            newBus.setDescription(jBus.getString("description"));
            System.out.println("stops_url: " + jBus.getString("stops_url"));

            stops_url = jBus.getString("stops_url");
            jsonRoute = JsonAdministrator.readJsonFromUrl(stops_url);
            newBus.setRoute(createRoute(jsonRoute));

            System.out.println("img_url: " + jBus.getString("img_url"));
            newBus.setImageUrl(stops_url);

            return newBus;
        }

        protected Route createRoute( JSONObject jRoute ) throws JSONException {
            Route newRoute = new Route();
            JSONArray jsonStops;

            jsonStops = jRoute.getJSONArray("stops");

            for (int i = 0; i < jsonStops.length(); i++) {
                newRoute.addLatitude(Float.valueOf(jsonStops.getJSONObject(i).getString("lat")));
                newRoute.addLongitude(Float.valueOf(jsonStops.getJSONObject(i).getString("lng")));
            }

            newRoute.setEstimatedTimeMilliseconds(Integer.valueOf(jRoute.getString("estimated_time_milliseconds")));
            newRoute.setRetryTimeMilliseconds(Integer.valueOf(jRoute.getString("retry_time_milliseconds")));
            return newRoute;
        }
    }
}
