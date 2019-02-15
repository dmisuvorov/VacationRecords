package com.media.dmitry68.vacationrecords.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.action.ActionFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SettingsActionFragment extends Fragment {
    public static final String SETTINGS_ACTION_FRAGMENT_TAG = "settings_action_fragment_tag";
    private ActionFactory actionFactory;
    private List<String> actionNames;
    private List<String> actionColorHex;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View settingsActionView = inflater.inflate(R.layout.fragment_action_settings, container, false);
        onGetItems();
        return settingsActionView;
    }

    private void onGetItems() {
        actionFactory = new ActionFactory();
    }

    private void initList(View rootView) {
        actionNames = actionFactory.getActionNames();
        actionColorHex = actionFactory.getActionColorHex();
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        if (actionNames.size() == actionColorHex.size()) {
            String ATTRIBUTE_NAME_BACKGROUND = "background";
            String ATTRIBUTE_NAME_TEXT = "text";
            for (int i = 0; i < actionNames.size(); i++) {
                HashMap<String, String> hm = new HashMap<>();
                hm.put(ATTRIBUTE_NAME_TEXT, actionNames.get(i));
                hm.put(ATTRIBUTE_NAME_BACKGROUND, actionColorHex.get(i));
                list.add(hm);
            }
            String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_BACKGROUND};
            int[] to = { R.id.text_action, R.id.color_image_action };
            SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), list, R.layout.listview_action_item, from, to);
            ListView actionListView = rootView.findViewById(R.id.action_list_view);
            actionListView.setAdapter(simpleAdapter);
        }
    }
}
