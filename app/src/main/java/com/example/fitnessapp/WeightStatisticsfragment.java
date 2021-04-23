package com.example.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.GenericArrayType;

public class WeightStatisticsfragment extends Fragment {

    Context context;
    EditText editTextWeight2;
    EditText editTextReps;
    String weight2,reps;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.weightstatistics_layout, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextWeight2 = (EditText) getView().findViewById(R.id.editTextWeight2);
        editTextReps = (EditText) getView().findViewById(R.id.editTextWeight2);

        Spinner weightSpinner = (Spinner) getView().findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.context,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightSpinner.setAdapter(myAdapter);

        /* Refreshes text on age*/
        editTextWeight2.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weight2 = editTextWeight2.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        editTextReps.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                reps = editTextReps.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });


    }
}
