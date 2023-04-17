package com.example.phms;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button mButton,mCancel,mExit;
    EditText mHeightText;
    EditText mWeightText;
    EditText mBPText;
    EditText mSugarText;
    EditText mBGText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button)findViewById(R.id.Submit);
        mCancel = (Button)findViewById(R.id.Cancel);
        mExit = (Button)findViewById(R.id.Exit);
        mHeightText   = (EditText)findViewById(R.id.HeightTextName);
        mWeightText   = (EditText)findViewById(R.id.weightTextName);
        mBPText   = (EditText)findViewById(R.id.bloodPressure);
        mSugarText   = (EditText)findViewById(R.id.sugarLevel);
        mBGText = (EditText)findViewById(R.id.bgTextName);
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        VitalSigns vitals = new VitalSigns( parseInt(mHeightText.getText().toString()),
                                parseInt(mWeightText.getText().toString()),
                                mBPText.getText().toString(),
                                mSugarText.getText().toString(),
                                mBGText.getText().toString());
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        CollectionReference vitalsDB = db.collection("VitalSigns");
                        vitalsDB.add(vitals).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("Smarika", "DocumentSnapshot written with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("Smarika", "Error adding document", e);
                                    }
                                });;
                    }
                });
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        VitalSigns vitals = new VitalSigns( parseInt(mHeightText.getText().toString()),
                                parseInt(mWeightText.getText().toString()),
                                mBPText.getText().toString(),
                                mSugarText.getText().toString(),
                                mBGText.getText().toString());
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        CollectionReference vitalsDB = db.collection("VitalSigns");
                        vitalsDB.add(vitals).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d("Smarika", "DocumentSnapshot written with ID: " + documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("Smarika", "Error adding document", e);
                                    }
                                });;
                    }
                });
    }
}