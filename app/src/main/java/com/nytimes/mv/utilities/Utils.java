package com.nytimes.mv.utilities;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.View;

import androidx.annotation.Nullable;

import com.nytimes.mv.BuildConfig;

import java.util.Date;

import io.reactivex.disposables.Disposable;

import static android.text.format.DateUtils.DAY_IN_MILLIS;
import static android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE;
import static android.text.format.DateUtils.HOUR_IN_MILLIS;

public final class Utils {

    public static void disposeSafe(@Nullable Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static void setVisible(@Nullable View view, boolean show) {
        if (view == null) return;

        int visibility = show ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    private Utils() {
        throw new AssertionError("No instances");
    }
}