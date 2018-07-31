package com.example.edgarpetrosian.getcontact.Fragments;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.edgarpetrosian.getcontact.Adapters.RecycleViewAllContactAdapter;
import com.example.edgarpetrosian.getcontact.Engine.Engine;
import com.example.edgarpetrosian.getcontact.Engine.ModelGetContacts;
import com.example.edgarpetrosian.getcontact.R;

import java.util.LinkedList;
import java.util.List;

public class AllContactsFragment extends Fragment {
    private View view;
    private Context context;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<ModelGetContacts> list;
    private ModelGetContacts modelGetContacts;
    private Engine engine;
    private RecycleViewAllContactAdapter adapter;
    private List<String> names;
    private List<String> numbers;
    private List<String> imageURL;
    private int imagePath;


    public AllContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_contacts, container, false);
        context = getContext();
        names = getContactName();
        numbers = getContactPhoneNumber();
        imageURL = getImagePhath();

        list = getContactsInfo(names, numbers);

        engine = Engine.getInstance();
        for (int i = 0; i < names.size(); i++) {
//            if (imageURL.get(i).equals("No Image")) {
//                Toast.makeText(context, "null------->" + imageURL.get(i), Toast.LENGTH_LONG).show();
//
//                imagePath = R.drawable.favourite;
//                modelGetContacts = new ModelGetContacts(names.get(i), numbers.get(i));
//                modelGetContacts.setImagePath(imagePath);
//            } else {
//                imagePath = Integer.parseInt(imagePhath.get(i));
            modelGetContacts = new ModelGetContacts(names.get(i), numbers.get(i));
            modelGetContacts.setUri(imageURL.get(i));
            // }

            engine.getServices(context).save(modelGetContacts);
            Toast.makeText(context, "names = " + names.get(i) + " numbers = " + numbers.get(i) + " image = " + imageURL.get(i), Toast.LENGTH_SHORT).show();
        }

        recyclerView = view.findViewById(R.id.recycleFragID);
        gridLayoutManager = new GridLayoutManager(context, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new RecycleViewAllContactAdapter(engine.getServices(context).getAllinform(""), context);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public List<String> getContactName() {
        List<String> listNames = new LinkedList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            //get ID
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //get Name
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            listNames.add(name);
            Log.i("names" + id, " =   Name " + name);
        }
        return listNames;
    }

    public List<String> getContactPhoneNumber() {
        List<String> phoneNumberLIst = new LinkedList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null
        );
        while (cursor.moveToNext()) {
            //get ID
            String id = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[]{id}, null);
            while (phoneCursor.moveToNext()) {
                //get PhoneNumber
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneNumberLIst.add(phoneNumber);
                Log.i("names" + id, " =   phoneNumber " + phoneNumber);
            }
        }
        return phoneNumberLIst;
    }

    public List<ModelGetContacts> getContactsInfo(List<String> name, List<String> phoneNumber) {
        List<ModelGetContacts> modelList = new LinkedList<>();
        ModelGetContacts modelGetContacts = new ModelGetContacts();
        for (int k = 0; k < name.size(); k++) {
            modelGetContacts.setName(name.get(k));
            modelGetContacts.setNumber(phoneNumber.get(k));
        }
        return modelList;
    }

    public List<String> getImagePhath() {
        List<String> listImage = new LinkedList<>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            //get ID
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //get Name
            String image = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
            if (image == null) {
                //listImage.add("No Image");
                image = "No Image";
            }

            listImage.add(image);
            //}
            Log.i("image " + id, " =   Image " + image);
            //content://com.android.contacts/contacts/263
        }
        return listImage;
    }
}
