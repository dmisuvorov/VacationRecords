package com.media.dmitry68.vacationrecords.color;

import android.content.Context;

import com.media.dmitry68.vacationrecords.R;

import top.defaults.colorpicker.ColorPickerPopup;

public class ColorPickPopup {
    private String colorHex;
    private Context context;
    private ColorCallback colorCallback;
    private ColorFactory colorFactory = new ColorFactory();

    public ColorPickPopup(Context context, ColorCallback colorCallback) {
        this.context = context;
        this.colorCallback = colorCallback;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public void build() {
        new ColorPickerPopup.Builder(context)
                .initialColor(Integer.decode(colorHex))
                .enableAlpha(true)
                .okTitle(context.getString(R.string.btn_text_ok))
                .cancelTitle(context.getString(R.string.btn_text_cancel))
                .showIndicator(true)
                .showValue(true)
                .onlyUpdateOnTouchEventUp(true)
                .build()
                .show(new ColorPickerPopup.ColorPickerObserver() {
                    @Override
                    public void onColorPicked(int color) {
                        colorCallback.onColorPick(colorFactory.getColorHexFromInt(color));
                    }
                });
    }
}
