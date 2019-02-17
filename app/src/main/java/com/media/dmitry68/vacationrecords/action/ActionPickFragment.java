package com.media.dmitry68.vacationrecords.action;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.media.dmitry68.vacationrecords.R;
import com.media.dmitry68.vacationrecords.ui.BasePickFragment;

import java.util.List;

public class ActionPickFragment extends BasePickFragment implements ActionCallback {
    public static final String ACTION_PICK_FRAGMENT_TAG = "action_pick_fragment_tag";
    private ActionFactory actionFactory = new ActionFactory();
    private List<ActionEntity>  actionEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_action_pick, container, false);
        initToolbar();
        getActionEntities();
        return rootView;
    }

    @Override
    public void onActionLoaded(List<ActionEntity> actionEntities) {
        this.actionEntities = actionEntities;
        initRadioGroup(actionEntities.size());
    }

    private void getActionEntities() {
        actionFactory.getActionEntities(this);
    }

    private void initRadioGroup(int size) {
        RadioGroup radioGroup = new RadioGroup(getContext());
        int sizeOfImageInPx = (int)convertDpToPx(50);
        LinearLayout.LayoutParams layoutParamsForImage = new LinearLayout.LayoutParams(sizeOfImageInPx, sizeOfImageInPx);
        for (int i = 0; i < size; i++) {
            ActionEntity currentActionEntity = actionEntities.get(i);
            ImageView colorActionImageView = new ImageView(getContext());
            colorActionImageView.setId(View.generateViewId());
            colorActionImageView.setLayoutParams(layoutParamsForImage);
            colorActionImageView.setBackgroundColor(Color.parseColor(currentActionEntity.getColorHex()));

            RadioButton actionRadioButton = new RadioButton(getContext());
            actionRadioButton.setId(View.generateViewId());
            actionRadioButton.setText(currentActionEntity.getName());
            actionRadioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, colorActionImageView.getDrawable(), null);

            radioGroup.addView(actionRadioButton);
        }
        ((ViewGroup) rootView.findViewById(R.id.action_radio_group)).addView(radioGroup);
    }

    private float convertDpToPx(float dp) {
        Resources resources = getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.getDisplayMetrics()
        );
    }
}
