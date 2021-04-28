package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    Bundle bundle;
    String username;

    DataInputFragment dataInputFragment = new DataInputFragment();
    GraphFragment graphFragment = new GraphFragment();

    /*Creates the nav view to MainActivity and sets the starting fragment to the LoginFragment*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //TODO USER LOGGED IN STUFF
            //TODO SETS HEADER TEXT TO USERNAME
            /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View headerView = navigationView.getHeaderView(0);
                TextView navUsername = (TextView) headerView.findViewById(R.id.headerUserName);
                navUsername.setText(username);*/
        //TODO USER LOGGED IN STUFF

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
        }
    }

    /*Changes the fragment depending on what nav option selected*/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        switch (item.getItemId()) {
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                break;
            case R.id.nav_account:
                DataInputFragment datainput = new DataInputFragment();
                datainput.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, datainput).commit();
                break;
            case R.id.nav_input:
                WeightStatisticsfragment resultinput = new WeightStatisticsfragment();
                resultinput.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultinput).commit();
                //TODO ADD PROGRESS INPUT
                break;
            case R.id.nav_output:
                GraphFragment resultshow = new GraphFragment();
                resultshow.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, resultshow).commit();
                //TODO ADD SHOW DATA
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}