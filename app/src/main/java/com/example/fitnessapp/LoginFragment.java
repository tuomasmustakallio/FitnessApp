package com.example.fitnessapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import static com.example.fitnessapp.DatabaseManager.checkLogin;
import static com.example.fitnessapp.DatabaseManager.createNewAccount;
import static com.example.fitnessapp.PasswordManager.checkPassword;
import static com.example.fitnessapp.PasswordManager.passwordRules;


public class LoginFragment extends Fragment implements View.OnClickListener {

    EditText etUsername, etPassword;
    String username, password = " ";
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.login_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginBtn = getView().findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(this);

        etUsername = getView().findViewById(R.id.etUsername);
        etPassword = getView().findViewById(R.id.etPassword);

        /*Refreshes username text*/
        etUsername.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                username = etUsername.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        /*Refreshes password text*/
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = etPassword.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });


    }

    /*onClick for login button which checks if user exists and logs it in through the database*/
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View v){
        if (!checkLogin(context, username)){
            createNewAccount(context, username, password);
            Toast.makeText(getContext(), "Account created", Toast.LENGTH_SHORT).show();

            /*if (passwordRules(password)){
                createNewAccount(username, password);
                Toast.makeText(getContext(), "Account created", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Bad password", Toast.LENGTH_SHORT).show();
            }*/
            //TODO PASSWORD CHECK REMOVED DURING TESTING
        }else if(checkPassword(context, username, password)){
            //user login
            Toast.makeText(getContext(), "Logged in", Toast.LENGTH_SHORT).show();
            /*Send info to DataInputFragment*/
            Bundle bundle = new Bundle();
            bundle.putString("key", username);
            DataInputFragment dataInputFragment = new DataInputFragment();
            dataInputFragment.setArguments(bundle);
        }else{
            Toast.makeText(getContext(), "Invalid password", Toast.LENGTH_SHORT).show();
        }
    }
}