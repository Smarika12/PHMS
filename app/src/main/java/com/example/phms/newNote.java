package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class newNote extends AppCompatActivity
{
    EditText mtitleofnote;
    EditText mcontentofnote;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    FloatingActionButton msaveNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        mtitleofnote = findViewById(R.id.titleOfNote);
        mcontentofnote = findViewById(R.id.ContentOfNote);
        msaveNote = findViewById(R.id.saveNote);

        Toolbar toolbar = findViewById(R.id.toolBarOfCreateNote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for making a back button

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();



//        saving data to the cloudFire store
        msaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String title = mtitleofnote.getText().toString();
                String content = mcontentofnote.getText().toString();

                if(title.isEmpty() || content.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Both field are Required",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DocumentReference documentReference  = firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document();
                    Map<String, Object> note = new HashMap<>();
                    note.put("title", title);
                    note.put("content", content);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void avoid)
                        {
                            Toast.makeText(getApplicationContext(), "Notes Created Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(newNote.this, notesTab.class));

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getApplicationContext(), "Failed to create Note",Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(newNote.this, notesTab.class));
                        }
                    });

                }

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}