package com.example.justinlee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static android.R.attr.category;
import static com.google.android.gms.internal.zzs.TAG;

public class MainActivity extends Activity {
    ArrayList<String> categoryNames;
    DatabaseReference mDatabase;
    public final static String EXTRA_LATIDUDE = "com.example.myfirstapp.LATIDUDE";
    public final static String EXTRA_LONGITUDE = "com.example.myfirstapp.LONGITUDE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void goToMaps(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        LatLng location = new LatLng(-34, 151);
        intent.putExtra(EXTRA_LATIDUDE, location.latitude);
        intent.putExtra(EXTRA_LONGITUDE, location.longitude);
        startActivity(intent);
    }


    }

