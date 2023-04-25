package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class notesTab extends AppCompatActivity
{
    FloatingActionButton mcreateNoteFab;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_tab);

        mcreateNoteFab = findViewById(R.id.createNoteFab);
        firebaseAuth = FirebaseAuth.getInstance();

        //getSupportActionBar().setTitle("All Notes");

        //clicking on the noteFab takes out to the new tab to create a new note
        mcreateNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(notesTab.this, newNote.class));

            }
        });
    }}