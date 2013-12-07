package com.smpete.fuelfinder.provider;

import android.content.ContentValues;

public abstract class AbstractContentValuesWrapper {
    protected ContentValues mContentValues = new ContentValues();

    public ContentValues getContentValues() {
        return mContentValues;
    }
}