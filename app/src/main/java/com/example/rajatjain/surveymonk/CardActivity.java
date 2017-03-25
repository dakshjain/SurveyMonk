package com.example.rajatjain.surveymonk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity implements ValueEventListener{
    public static String topic;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList results;
    ArrayList results1;
    Firebase mRef ;
    String value;
    ProgressDialog progressbar;
    public static String  type,type2;
    String option1 , option2, option3 ,option4,ques;
    int i;
    int cardno = 1;
    ArrayList al,al2;
    ArrayAdapter arrayAdapter,arrayAdapter2;
    public  void getcardview(){
        mRef = new Firebase("https://surveymonk-dcab0.firebaseio.com/Surveyforms/"+topic+"/"+"1");
        mRef.child("Type").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);


                CardActivity card = new CardActivity();
                //card.settype(data);
                Log.i("data","" + data);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
//        this.type
        Log.i("type",""+type);
        //return type;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        topic  = getIntent().getExtras().getString("topic");
        Toast.makeText(this, "topic "+topic, Toast.LENGTH_SHORT).show();


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        for(; cardno<3; cardno++) {
            getDataSet(cardno);
        }
        if(type==null&&type2==null){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
        else {
            al = new ArrayList<String>();
            al.add(type);
            cardno++;
            Log.i("message","call");
           // getDataSet(cardno);

            al.add(type2);
            al.add("python");
            al.add("java");
        }
        al2  = new ArrayList<String>();
        al2.add("daksh");
        al2.add("jain");

        //choose your favorite adapter
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, al );
        arrayAdapter2 = new ArrayAdapter<String>(this,R.layout.item2,R.id.helloText2,al2);
        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter2);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(CardActivity.this, "Left!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(CardActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }
            public void onTopCardExit(Object dataObject){}

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
              /*  al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;*/
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(CardActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<DataObject1> getDataSet2( ) {
        results1 = new ArrayList<DataObject1>();
        mRef = new Firebase("https://surveymonk-dcab0.firebaseio.com/Surveyforms/" + topic + "/" + "2");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return results1;
    }

    private void getDataSet(final int  cardno) {
        results = new ArrayList<DataObject>();

                mRef = new Firebase("https://surveymonk-dcab0.firebaseio.com/Surveyforms/" + topic + "/" +cardno);
                mRef.child("Ques").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue().toString();
                        Log.i("value",""+value);
                        settype(value  , cardno);

                        Toast.makeText(CardActivity.this, "value " + value, Toast.LENGTH_SHORT).show();
                        DataObject obj = new DataObject("" + value);
                        results.add(obj);

                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
        Log.i("typeee",""+type);
        //return type;
    }
    public void settype(String type , int cardno){
        if(cardno == 2) {
            this.type2 = type;
            Log.i("Type2", "" + this.type2);
        }
        else {
            this.type = type;
            Log.i("Type",""+this.type);
        }
    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {

    }
}
