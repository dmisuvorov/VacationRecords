package com.media.dmitry68.vacationrecords.color;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;


import com.media.dmitry68.vacationrecords.R;

public class ColorImageFactory {
    private Context context;

    public ColorImageFactory(Context context) {
        this.context = context;
    }

    public Drawable getImageColor(String colorHex) {
        Drawable color = ContextCompat.getDrawable(context, R.drawable.rectangle);
        if (color != null)
            color.setColorFilter(new PorterDuffColorFilter(Color.parseColor(colorHex), PorterDuff.Mode.SRC_IN));
        return color;
    }

}
