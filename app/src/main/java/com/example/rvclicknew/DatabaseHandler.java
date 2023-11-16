package test.database.sqltest;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME = "radiosManager";
    private static final String TABLE_RADIOS = "radios";
    private static final String KEY_ID = "id";
    private static final String KEY_RADIONAME = "radio_name";
    private static final String KEY_RADIOURL = "radio_url";
    private static final String KEY_RADIOPIC = "radio_pic";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RADIOS_TABLE = "CREATE TABLE " + TABLE_RADIOS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RADIONAME + " TEXT,"
                + KEY_RADIOURL + " TEXT," + KEY_RADIOPIC + " TEXT" + ")";
        db.execSQL(CREATE_RADIOS_TABLE);
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RADIOS);
        // Create tables again
        onCreate(db);
    }

    // code to add the new radio
    void addContact(Radio radio) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_RADIONAME, radio.getRadioName()); // Radio Name
        values.put(KEY_RADIOURL, radio.getRadioUrl()); // Radio Phone
        values.put(KEY_RADIOPIC,radio.getRadioPic());
        // Inserting Row
        db.insert(TABLE_RADIOS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    Radio getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RADIOS, new String[] { KEY_ID,
                        KEY_RADIONAME, KEY_RADIOURL,KEY_RADIOPIC}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Radio contact = new Radio(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return contact
        return contact;
    }

    // code to get all contacts in a list view
    public List<Radio> getAllContacts() {
        List<Radio> contactList = new ArrayList<Radio>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RADIOS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Radio contact = new Radio();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.setRadioName(cursor.getString(1));
                contact.setRadioUrl(cursor.getString(2));
                contact.setRadioPic(cursor.getString(3));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Radio contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_RADIONAME, contact.getRadioName());
        values.put(KEY_RADIOURL, contact.getRadioUrl());
        values.put(KEY_RADIOPIC, contact.getRadioPic());

        // updating row
        return db.update(TABLE_RADIOS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }

    // Deleting single contact
    public void deleteContact(Radio contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RADIOS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_RADIOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}

