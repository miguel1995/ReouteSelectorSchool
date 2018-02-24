package com.example.miguel.reouteselectorschool;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by MIGUEL on 24/02/2018.
 */

public class JsonAdministrator {
    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {

        InputStream inputStream = new URL(url).openStream(); //inicia conexion a url

        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String jsonText = readAll(bufferedReader);

            return new JSONObject(jsonText);
        } finally {
            inputStream.close();
        }
    }

    public static String readAll(Reader reader) throws IOException {
        //Lee cada caracter del archivo para almacenarlo en un String.

        StringBuilder stringBuilder = new StringBuilder();
        int cp;

        while((cp = reader.read()) != -1){
            stringBuilder.append((char) cp);
        }

        return stringBuilder.toString();
    }
}
