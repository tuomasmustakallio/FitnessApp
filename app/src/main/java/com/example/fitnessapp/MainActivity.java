package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test asdfasdfasdfasdf");

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment fragment;

                if (view == findViewById(R.id.btn_reg)) {
                    System.out.println("RegisterNow");
                }

                fragment = new RegisterFragment();

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.screen1,fragment);
                transaction.commit();

            }
        };

        Button btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(listener);
    }
}