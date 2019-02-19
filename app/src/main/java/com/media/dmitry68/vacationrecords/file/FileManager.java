package com.media.dmitry68.vacationrecords.file;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager {
    private Context context;

    public FileManager(Context context) {
        this.context = context;
    }

    public String getStringFromAssetFile(String nameOfFile) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(nameOfFile);
            return getStringFromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getStringFromStream(InputStream inputStream) throws IOException {
        String result;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receiveString;
        StringBuilder stringBuilder = new StringBuilder();
        while ((receiveString = bufferedReader.readLine()) != null) {
            stringBuilder.append(receiveString);
        }
        inputStream.close();
        result = stringBuilder.toString();
        return result;
    }
}
