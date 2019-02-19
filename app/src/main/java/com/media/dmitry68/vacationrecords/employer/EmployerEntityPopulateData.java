package com.media.dmitry68.vacationrecords.employer;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.media.dmitry68.vacationrecords.file.FileConstants;
import com.media.dmitry68.vacationrecords.file.FileManager;

import java.util.Map;

public class EmployerEntityPopulateData {
    private FileManager fileManager;

    public EmployerEntityPopulateData(Context context) {
        fileManager = new FileManager(context);
    }

    public EmployerEntity[] populateData() {
        String inputData = fileManager.getStringFromAssetFile(FileConstants.NAME_OF_INPUT_FILE_WITH_EMPLOYERS);
        if (inputData == null) {
            throw new NullPointerException("Input file is null");
        }
        Map<String, String> inputMap = getMapFromJson(inputData);
        EmployerEntity[] employerEntities = new EmployerEntity[inputMap.size()];
        int index = 0;
        for (Map.Entry<String, String> mapEntry : inputMap.entrySet()) {
            EmployerEntity employerEntity = new EmployerEntity(mapEntry.getValue());
            employerEntity.setId(Integer.parseInt(mapEntry.getKey()));
            employerEntities[index] = employerEntity;
            index++;
        }
        return employerEntities;
    }


    private Map<String, String> getMapFromJson(String inputData) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(inputData);
        return new Gson().fromJson(jsonObject, new TypeToken<Map<String, String>>(){}.getType());
    }

}
