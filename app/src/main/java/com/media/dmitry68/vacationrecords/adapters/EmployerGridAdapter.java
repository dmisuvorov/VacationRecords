package com.media.dmitry68.vacationrecords.adapters;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.employer.EmployerEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

import co.ceryle.fitgridview.FitGridAdapter;

public class EmployerGridAdapter extends FitGridAdapter  {
    private List<EmployerEntity> employerEntities;
    private TreeMap<String, ArrayList<String>> mapFirstCharToName = new TreeMap<>();
    private List<String> listOfFirstCharOfNameOfEntities;
    private List<String> listOfTextButton = new ArrayList<>();
    private boolean modeOfChar = true;

    public EmployerGridAdapter(Context context, List<EmployerEntity> employerEntities) {
        super(context, R.layout.grid_item);
        this.employerEntities = employerEntities;
        makeFirstCharToUpperCase();
        listOfFirstCharOfNameOfEntities = getListFirstCharOfNameEmployers();
        listOfTextButton.addAll(listOfFirstCharOfNameOfEntities);
    }

    @Override
    public void onBindView(int position, View view) {
        final Button gridButton = view.findViewById(R.id.gridButton);
        gridButton.setText(listOfTextButton.get(position));
        gridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOfTextButton.clear();
                if (modeOfChar) {
                    String firstCharOfEmployer = gridButton.getText().toString();
                    listOfTextButton.addAll(Objects.requireNonNull(mapFirstCharToName.get(firstCharOfEmployer)));
                } else {
                    listOfTextButton.addAll(listOfFirstCharOfNameOfEntities);
                }
                modeOfChar = !modeOfChar;
                notifyDataSetChanged();
            }
        });
    }

    private List<String> getListFirstCharOfNameEmployers () {
        for (EmployerEntity employerEntity : employerEntities) {
            String firstChar = String.valueOf(employerEntity.getName().charAt(0));
            if (mapFirstCharToName.get(firstChar) == null) {
                mapFirstCharToName.put(firstChar, new ArrayList<>(Collections.singleton(employerEntity.getName())));
            } else {
                Objects.requireNonNull(mapFirstCharToName.get(firstChar)).add(employerEntity.getName());
            }
        }
        return new ArrayList<>(mapFirstCharToName.keySet());
    }

    private void makeFirstCharToUpperCase() {
        for (int i = 0; i < employerEntities.size(); i++) {
            String name = employerEntities.get(i).getName();
            String[] names = name.split("\\s+");
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < names.length; j++) {
                if (j != 0) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(Character.toUpperCase(names[j].charAt(0)));
                stringBuilder.append(names[j].substring(1).toLowerCase());
            }
            employerEntities.get(i).setName(stringBuilder.toString());
        }
    }
}
