package com.smpete.fuelfinder.data.provider;

import java.util.Arrays;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import com.smpete.fuelfinder.Constants;

public class StationProvider extends ContentProvider {
    private static final String TAG = StationProvider.class.getSimpleName();

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "com.smpete.fuelfinder.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    public static final String QUERY_NOTIFY = "QUERY_NOTIFY";
    public static final String QUERY_GROUP_BY = "QUERY_GROUP_BY";

    private static final int URI_TYPE_STATION = 0;
    private static final int URI_TYPE_STATION_ID = 1;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, StationColumns.TABLE_NAME, URI_TYPE_STATION);
        URI_MATCHER.addURI(AUTHORITY, StationColumns.TABLE_NAME + "/#", URI_TYPE_STATION_ID);

    }

    private DatabaseHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_STATION:
                return TYPE_CURSOR_DIR + StationColumns.TABLE_NAME;
            case URI_TYPE_STATION_ID:
                return TYPE_CURSOR_ITEM + StationColumns.TABLE_NAME;

        }
        return null;
    }

    /**
     * Performs a replace
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (Constants.ENABLE_LOGGING) {
            Log.d(TAG, "insert uri=" + uri + " values=" + values);
        }
        final String table = uri.getLastPathSegment();
        final long rowId = mDatabaseHelper.getWritableDatabase().replace(table, null, values);
        String notify;
        if (rowId != -1 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
    }

    /**
     * Performs a replace
     * @param uri
     * @param values
     * @return
     */
    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (Constants.ENABLE_LOGGING) {
            Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        }
        final String table = uri.getLastPathSegment();
        final SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int res = 0;
        db.beginTransaction();
        try {
            for (final ContentValues v : values) {
                final long id = db.replace(table, null, v);
                if (id != -1) {
                    res++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return res;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (Constants.ENABLE_LOGGING) {
            Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        }
        final QueryParams queryParams = getQueryParams(uri, selection);
        final int res = mDatabaseHelper.getWritableDatabase().update(queryParams.table, values, queryParams.selection, selectionArgs);
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (Constants.ENABLE_LOGGING) {
            Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        }
        final QueryParams queryParams = getQueryParams(uri, selection);
        final int res = mDatabaseHelper.getWritableDatabase().delete(queryParams.table, queryParams.selection, selectionArgs);
        String notify;
        if (res != 0 && ((notify = uri.getQueryParameter(QUERY_NOTIFY)) == null || "true".equals(notify))) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return res;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final String groupBy = uri.getQueryParameter(QUERY_GROUP_BY);
        if (Constants.ENABLE_LOGGING) {
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + groupBy);
        }
        final QueryParams queryParams = getQueryParams(uri, selection);
        final Cursor res = mDatabaseHelper.getReadableDatabase().query(queryParams.table, projection, queryParams.selection, selectionArgs, groupBy,
                null, sortOrder == null ? queryParams.orderBy : sortOrder);
        res.setNotificationUri(getContext().getContentResolver(), uri);
        return res;
    }

    private static class QueryParams {
        public String table;
        public String selection;
        public String orderBy;
    }

    private QueryParams getQueryParams(Uri uri, String selection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_STATION:
            case URI_TYPE_STATION_ID:
                res.table = StationColumns.TABLE_NAME;
                res.orderBy = StationColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_STATION_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = BaseColumns._ID + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = BaseColumns._ID + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }

    public static Uri notify(Uri uri, boolean notify) {
        return uri.buildUpon().appendQueryParameter(QUERY_NOTIFY, String.valueOf(notify)).build();
    }

    public static Uri groupBy(Uri uri, String groupBy) {
        return uri.buildUpon().appendQueryParameter(QUERY_GROUP_BY, groupBy).build();
    }
}
