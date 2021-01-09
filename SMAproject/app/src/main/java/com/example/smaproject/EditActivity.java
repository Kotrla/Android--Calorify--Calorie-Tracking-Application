package com.example.smaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {


    FirebaseUser currentUser;
    DatabaseReference rootRef, dataRef;

    EditText edNameSettings, edAgeSettings, edHeightSettings, edWeightSettings;
    Spinner edGoalSettings;
    Button BtnUpdateSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edNameSettings = findViewById(R.id.edNameSettings);
        edAgeSettings = findViewById(R.id.edAgeSettings);
        edHeightSettings = findViewById(R.id.edHeightSettings);
        edWeightSettings = findViewById(R.id.edWeightSettings);
        edGoalSettings = findViewById(R.id.edGoalSettings);
        BtnUpdateSettings = findViewById(R.id.BtnUpdateSettings);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String user_id = currentUser.getUid().toString();

        rootRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        dataRef = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Data");

        ///dropdown goal logic
        String[] items = new String[]{"Fat loss", "Muscle gain", "Maintain weight"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        edGoalSettings.setAdapter(adapter);
        ///


        onClickery();
    }

    public void onClickery(){
        BtnUpdateSettings.setOnClickListener(new View.OnClickListener() {
            String edGoalSettingsToString;

            @Override
            public void onClick(View v) {

                int goal = edGoalSettings.getSelectedItemPosition();
                switch (goal) {
                case 0:
                    this.edGoalSettingsToString = "Fat loss";
                    // Whatever you want to happen when the first item gets selected
                    break;
                case 1:
                    this.edGoalSettingsToString = "Muscle gain";
                    // Whatever you want to happen when the second item gets selected
                    break;
                case 2:
                    this.edGoalSettingsToString = "Maintain weight";
                    // Whatever you want to happen when the thrid item gets selected
                    break;
            }
/*
                final UserClass userUpdate = new UserClass();
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        UserClass userClass = dataSnapshot.getValue(UserClass.class);

                        userUpdate.setName(userClass.getName());
                        userUpdate.setAge(userClass.getAge());
                        userUpdate.setEmail(userClass.getEmail());
                        userUpdate.setId(userClass.getId());
                        userUpdate.setWeight(userClass.getWeight());
                        userUpdate.setHeight(userClass.getHeight());
                        userUpdate.setGender(userClass.getGender());
                        userUpdate.setGoal(userClass.getGoal());


                        System.out.println(userUpdate.getName());
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                dataRef.addListenerForSingleValueEvent(eventListener);*/

                String nameGot;
                int ageGot, weightGot, heightGot;

                nameGot = edNameSettings.getText().toString();
                ageGot = Integer.parseInt(edAgeSettings.getText().toString());
                weightGot = Integer.parseInt(edWeightSettings.getText().toString());
                heightGot = Integer.parseInt(edHeightSettings.getText().toString());


/*
                userUpdate.setWeight(weightGot);
                userUpdate.setHeight(heightGot);
                userUpdate.setAge(ageGot);
                userUpdate.setName(nameGot);
                userUpdate.setGoal(edGoalSettingsToString);
*/


                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("name",nameGot);
                hashMap.put("age", ageGot);
                hashMap.put("weight", weightGot);
                hashMap.put("height", heightGot);
                hashMap.put("goal", edGoalSettingsToString);


                dataRef.updateChildren(hashMap);

                Intent intent = new Intent(EditActivity.this, Home.class);
                startActivity(intent);

            }










        });
    }


}