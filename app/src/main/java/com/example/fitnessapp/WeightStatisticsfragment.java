package com.example.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

import static com.example.fitnessapp.DatabaseManager.setResults;
import static com.example.fitnessapp.DatabaseManager.setUserInfo;

public class WeightStatisticsfragment extends Fragment {

    Context context;
    EditText editTextWeight2;
    EditText editTextReps;
    String typedWeight,typedReps = "0";
    Movement selectedMovement, saveMovement;
    String selectedMovementName;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.weightstatistics_layout, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitButton = getView().findViewById(R.id.submitButton2);
        submitButton.setOnClickListener(this::onClick);

        Movement squats = new Movement();
        squats.setMovementName("Squats");
        Movement benchPress = new Movement();
        benchPress.setMovementName("Bench Press");
        Movement deadLift = new Movement();
        deadLift.setMovementName("Deadlift");

        ArrayList<Movement> movementArrayList = new ArrayList<>();
        movementArrayList.add(squats);
        movementArrayList.add(benchPress);
        movementArrayList.add(deadLift);

        editTextWeight2 = (EditText) getView().findViewById(R.id.editTextWeight2);
        editTextReps = (EditText) getView().findViewById(R.id.editTextReps);

        Spinner weightSpinner = (Spinner) getView().findViewById(R.id.spinner1);

        ArrayAdapter<Movement> myAdapter = new ArrayAdapter<Movement>(this.context, android.R.layout.simple_list_item_1, movementArrayList);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightSpinner.setAdapter(myAdapter);
        weightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        Movement selectedMovement = (Movement) parent.getItemAtPosition(position);
                                                        selectedMovementName = selectedMovement.getMovementName();

                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });

                /* Refreshes text on age*/
                editTextWeight2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        typedWeight = editTextWeight2.getText().toString();
                    }
                    @Override
                    public void afterTextChanged(Editable s) { }
                });

        editTextReps.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                typedReps = editTextReps.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });


    }
    public void onClick(View v){
        int weight=Integer.parseInt(typedWeight);
        int reps=Integer.parseInt(typedReps);
        Movement saveMovement = new Movement(selectedMovementName, weight, reps);
        setResults(context, saveMovement, "eka");
        Toast.makeText(getContext(), "Results saved", Toast.LENGTH_SHORT).show();
    }
}
