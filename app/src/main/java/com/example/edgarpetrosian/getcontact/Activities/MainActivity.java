package com.example.edgarpetrosian.getcontact.Activities;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.edgarpetrosian.getcontact.R;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> names = new LinkedList<>();
    private List<String> numbers = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        while (cursor.moveToNext()) {
            //get ID
            String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));

            //get Name
            String name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

            Cursor phoneCursor = resolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{id}, null);
            names.add(name);
            while (phoneCursor.moveToNext()) {

                //get PhoneNumber
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                // Log.i("phone",id+" = "+phoneNumber);
                numbers.add(phoneNumber);
                Log.i("names" + id, " =   Name " + name + "  Phone Number =  " + phoneNumber);
            }
        }
        Toast.makeText(this, " Count name = " + names.size() + " Count numbers = " + numbers.size(), Toast.LENGTH_SHORT).show();
    }
}
