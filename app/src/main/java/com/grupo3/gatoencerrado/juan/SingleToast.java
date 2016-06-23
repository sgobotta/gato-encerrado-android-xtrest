package com.grupo3.gatoencerrado.juan;

import android.content.Context;
import android.widget.Toast;

public class SingleToast {

    private static Toast mToast = null;

    public static void show(Context context, String text, int duration) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }
}
