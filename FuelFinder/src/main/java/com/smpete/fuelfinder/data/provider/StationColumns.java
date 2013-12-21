package com.smpete.fuelfinder.data.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class StationColumns implements BaseColumns {
    public static final String TABLE_NAME = "stations";
    public static final Uri CONTENT_URI = Uri.parse(StationProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    public static final String _ID = BaseColumns._ID;

    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ZIP = "zip";
    public static final String PHONE = "phone";
    public static final String ACCESS_TIME = "access_time";
    public static final String GEOCODE_STATUS = "geocode_status";
    public static final String FUEL_TYPE = "fuel_type";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String UPDATED_AT = "updated_at";

    public static final String DEFAULT_ORDER = _ID;
}