package com.example.rajatjain.surveymonk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Surveys extends Fragment {
    private Firebase mRef;
    String value ;
    int i=0;
    int j=0;
    ArrayList array_values;
    int addvalues = 0 ;
    TextView[] text=new TextView[50];
    private static String LOG_TAG = "MREF";

    private LinearLayout linearLayout;
    private String[] values=new String[50];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.surveys, container, false);
        Firebase.setAndroidContext(getContext());
        linearLayout = new LinearLayout(getContext());

        mRef = new Firebase("https://surveymonk-dcab0.firebaseio.com/Surveyforms");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                value  = dataSnapshot.getKey();
              //  array_values.add(addvalues,value);
                //addvalues++;
                text[i] = new TextView(getContext());
                text[i].setText(value);
                text[i].setId(R.id.my_text_1);
                linearLayout = (LinearLayout) getView().findViewById(R.id.survey);
                linearLayout.addView(text[i]);
                text[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "id" + getId(), Toast.LENGTH_SHORT).show();
                        String str = ((TextView) v).getText().toString();
                        Log.i(LOG_TAG, str);
                        Intent i = new Intent(getContext(), CardActivity.class);

                        i.putExtra("topic", str);
                        startActivity(i);
                    }
                });


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
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        /*TextView txt = (TextView)v.findViewById(R.id.my_text_1);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CardActivity.class));
            }
        });*/
        /*text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),CardActivity.class));
            }
        });*/
       /* for(int i = 0 ; i<3;i++) {
            text[i] = new TextView(getContext());
            text[i].setText(""+array_values.get(i));
            text[i].setId(R.id.my_text_1);

            linearLayout = (LinearLayout) v.findViewById(R.id.survey);
            linearLayout.addView(text[i]);
            text[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "id" + getId(), Toast.LENGTH_SHORT).show();
                    String str = ((TextView) v).getText().toString();
                    Log.i(LOG_TAG, str);
                    Intent i = new Intent(getContext(), CardActivity.class);

                    i.putExtra("topic", str);
                    startActivity(i);
                }
            });
        }*/

        return v;
    }


}
