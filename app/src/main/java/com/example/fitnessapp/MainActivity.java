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


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment loginFragment;
        loginFragment = new LoginFragment();

        transaction.replace(R.id.screen1,loginFragment);
        transaction.commit();

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment;

                if (view == findViewById(R.id.btn_reg)) {
                    System.out.println("RegisterNow");
                }

                fragment = new RegisterFragment();
                transaction.replace(R.id.screen1,fragment);
                transaction.commit();

            }
        };
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment1;

                if(view == findViewById(R.id.button4)){
                    System.out.println("InputFragment");

                }

                fragment1 = new DataInput();
                transaction.replace(R.id.screen1,fragment1);
                transaction.commit();

            }
        };


        Button inputbtn = findViewById(R.id.button4);
        inputbtn.setOnClickListener(listener1);

        Button btn_reg = findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(listener);
    }
}