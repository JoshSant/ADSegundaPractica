package com.example.adsegundapractica.view.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.adsegundapractica.R;

public class IntroducirActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir);
        setTitle("Choose a phone");

        Cursor mCursor = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.TYPE },
                ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                        + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL", null,
                ContactsContract.Data.DISPLAY_NAME + " ASC");

        startManagingCursor(mCursor);

        ListAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                mCursor,
                new String[] { ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER },
                new int[] { android.R.id.text1, android.R.id.text2 }
        );
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent result = new Intent();

        Cursor c = (Cursor) getListAdapter().getItem(position);
        int colIdx = c.getColumnIndex(ContactsContract.Data.DISPLAY_NAME);
        String nombre = c.getString(colIdx);
        colIdx = c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        String telef = c.getString(colIdx);

        result.putExtra("nombre", nombre);
        result.putExtra("telef", telef);
        setResult(Activity.RESULT_OK, result);

        finish();
    }
}