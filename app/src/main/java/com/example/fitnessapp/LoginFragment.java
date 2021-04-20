package com.example.fitnessapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class LoginFragment extends Fragment {

    EditText etUsername, etPassword;
    String username, password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
    public void tryLogin(){
        //TODO CHECK IF USERNAME LEGIT
        // if  username is not recognized Toast.makeText(getContext(), "Invalid username", Toast.LENGTH_SHORT).show();
        //TODO CHECK IF PASSWORD MATHCES WITH checkPassword(username, password) == true
        // if  password is not recognized Toast.makeText(getContext(), "Invalid password", Toast.LENGTH_SHORT).show();
    }
}