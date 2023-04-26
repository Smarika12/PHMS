package com.example.phms;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    Button mSearchSubmit,mVital,mExit;
    EditText searchBarText;
    ListView listView;
    Spinner collectionType;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();
    TextView searchParam;
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchSubmit = (Button)findViewById(R.id.searchButton);
        mVital = (Button)findViewById(R.id.vitalSignsBtn);
        mExit = (Button)findViewById(R.id.exitBtnForSearch);
        searchBarText = (EditText)findViewById(R.id.searchBarText);
        listView = (ListView)findViewById(R.id.searchResultView);
        collectionType = (Spinner)findViewById(R.id.collectionType);
        searchParam = (TextView)findViewById(R.id.searchParamText);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        listView.setAdapter(adapter);
        collectionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3) {
                int index = collectionType.getSelectedItemPosition();
                String queryText = searchBarText.getText().toString();
                if(index==0)
                {
                    mSearchSubmit.setOnClickListener(
                            new View.OnClickListener()
                            {
                                public void onClick(View view)
                                {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    CollectionReference appointmentsDB = db.collection("appointments");
                                    appointmentsDB.orderBy("name").startAt(queryText).endAt(queryText+ "\uf8ff").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                                        }
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                            });
                }
                else if(index==1)
                {
                    mSearchSubmit.setOnClickListener(
                            new View.OnClickListener()
                            {
                                public void onClick(View view)
                                {
                                    listItems.clear();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    CollectionReference doctorsDB = db.collection("doctors");
                                    doctorsDB.whereArrayContains("name",queryText).get()
                                    //doctorsDB.where("name",isGreaterThanorEqualTo: queryText)
                                    //doctorsDB.where("name","array-contains").startAt(queryText).endAt(queryText+ '\uf8ff').get()
                                    //doctorsDB.where("name", ">=", queryText)
                                      //      .where("name", "<=", queryText).get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Log.d(TAG, document.getId() + " => " + document.getData());
                                                            listItems.add(document.getString("name"));
                                                            adapter.notifyDataSetChanged();
                                                        }
                                                        searchParam.setText(listItems.size()+" result(s) found");
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                            });
                }
                else if(index==2)
                {
                    mSearchSubmit.setOnClickListener(
                            new View.OnClickListener()
                            {
                                public void onClick(View view)
                                {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    CollectionReference medicationsDB = db.collection("medications");
                                    medicationsDB.orderBy("name").startAt(queryText).endAt(queryText+ "\uf8ff").get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Log.d(TAG, document.getId() + " => " + document.getData());

                                                        }
                                                    } else {
                                                        Log.d(TAG, "Error getting documents: ", task.getException());
                                                    }
                                                }
                                            });
                                }
                            });
                }
            }
            public void onNothingSelected(AdapterView<?>arg0) {}
        });

        mVital.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view)
                    {
                        startActivity(new Intent(SearchActivity.this, MainActivity.class));
                    }
                }
        );
        mExit.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view)
                    {
                        finishAndRemoveTask();
                    }
                }
        );
    }

}