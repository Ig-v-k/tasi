package com.iw.submit;

import com.iw.Submit;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public final class TasiSubmit implements Submit {
    @Override
    public String format(long timestamp) {
        return DateFormat.getDateTimeInstance(
                DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.getDefault()).format(new Date(timestamp));
    }
}
