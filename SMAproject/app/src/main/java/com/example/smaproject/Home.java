package com.example.smaproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.smaproject.ui.dashboard.DashboardFragment;
import com.example.smaproject.ui.home.HomeFragment;
import com.example.smaproject.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    DatabaseReference rootRef, foodRef;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.nav_view);

        loadFragment(new HomeFragment());

        //getting food list to put in the buttons



        navigation.setOnNavigationItemSelectedListener(this);


    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
/*
public void onClickerEditSettings(){
    BtnSettingsEdit = findViewById(R.id.BtnSettingsEdit);
    BtnSettingsHistory = findViewById(R.id.BtnSettingsHistory);
    BtnSettingsLogOut = findViewById(R.id.BtnSettingsLogOut);

    BtnSettingsEdit.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Perform action on clic

            // currentContext.startActivity(activityChangeIntent);
            Intent intent = new Intent(Home.this, EditActivity.class);
            startActivity(intent);
        }
    });

/*
    BtnSettingsHistory.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), History.class);
            startActivity(intent);
        }
    });


    BtnSettingsLogOut.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {


            //TO DO MORE STUFF


            Intent intent = new Intent(getActivity(), History.class);
            startActivity(intent);
        }
    });
s
*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new NotificationsFragment();
                break;
        }

        return loadFragment(fragment);
    }


}