

package com.smpete.fuelfinder.data;

import android.database.Cursor;
import com.smpete.fuelfinder.utils.CursorCreator;

/**
 * A trivial example of a contact. This is meant to demonstrate how to create an entire object from a single row of a
 * {@link Cursor}.
 */
public class StationCursorCreator implements CursorCreator<StationCursorCreator> {
    public final int id;
    public final String displayName;
    public final boolean hasPhoneNumber;

    private StationCursorCreator() {
        id = -1;
        displayName = "nothing";
        hasPhoneNumber = true;
    }

    /**
     * Create a new contact
     */
    public StationCursorCreator(int id, String displayName, boolean hasPhoneNumber) {
        this.id = id;
        this.displayName = displayName;
        this.hasPhoneNumber = hasPhoneNumber;
    }

    /**
     * Create a Contact from a single row of a cursor.
     */
    @Override
    public StationCursorCreator createFromCursor(Cursor c) {
        // Read all three values in order
        final int id = c.getInt(0);
        final String name = c.getString(1);
        final boolean hasPhoneNumber = c.getInt(2) == 1;
        return new StationCursorCreator(id, name, hasPhoneNumber);
    }

    public static final StationCursorCreator FACTORY = new StationCursorCreator();
}