package com.example.justinlee.myapplication;

import android.widget.TextView;

import com.google.android.gms.drive.query.Query;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by justinlee on 10/8/16.
 */

public class FirebaseHelper {
    static FirebaseHelper sharedInstance = new FirebaseHelper();
    DatabaseReference mDatabase;
    ArrayList<String> categoryNames;
    public FirebaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        DatabaseReference typeOfDataRef = mDatabase.child("meta/view/columns");
        typeOfDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                try {
                    ArrayList<HashMap> dataCategories = (ArrayList<HashMap>) dataSnapshot.getValue();


                    categoryNames = new ArrayList<>(dataCategories.size());
                    HashMap<String, String> category;
                    for (int i = 0; i < dataCategories.size(); i++) {
                        if (i == dataCategories.size())
                            break;
                        category = dataCategories.get(i);
                        categoryNames.add(category.get("fieldName"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public [Crime] getLocations(LatLng atPoint, double withRadiusInDegrees) {
        ArrayList crimes = new ArrayList();
        DatabaseReference dataRef = mDatabase.child("data");
        com.google.firebase.database.Query locationQuery = dataRef.orderByChild("23").startAt(atPoint.longitude - withRadiusInDegrees).endAt(atPoint.longitude + withRadiusInDegrees).limitToFirst(20);//lon
        locationQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    ArrayList crimeDesc = (ArrayList) dataSnapshot.getValue();

                    int locIndex = categoryNames.indexOf("location");
                    ArrayList locationData = (ArrayList) crimeDesc.get(locIndex);
                    double latitude = Double.valueOf((String) locationData.get(1));
                    double longitude = Double.valueOf((String) locationData.get(2));

                    //sets danger value
                    int catIndex = categoryNames.indexOf("category");
                    String typeOfCrime = (String) crimeDesc.get(catIndex);
                    typeOfCrime = typeOfCrime.replaceAll(" ", "_").toUpperCase();
                    int valueOfCrime = 0;
                    for (crimeCategory crimeTypes : crimeCategory.values()) {
                        if (typeOfCrime.equals(crimeTypes.name())) {
                            valueOfCrime = crimeTypes.getDanger();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                crimes.add(new Crime());
            }
        });
    }
}
