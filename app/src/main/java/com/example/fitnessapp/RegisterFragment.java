package com.example.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    Button bRegister;
    EditText etUsername, etEmail, etPassword;

    @Nullable
    public View onCreateView(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText etPassword = (EditText) getView().findViewById(R.id.etPassword);
        final EditText etUsername = (EditText) getView().findViewById(R.id.etUsername);
        final EditText etEmail = (EditText) getView().findViewById(R.id.etEmail);
        System.out.println(etPassword);
        final Button bRegister = (Button) getView().findViewById(R.id.bRegister);
        bRegister.setOnClickListener((View.OnClickListener) this);
        return inflater.inflate(R.layout.fragment_register, view, false);
    }
}