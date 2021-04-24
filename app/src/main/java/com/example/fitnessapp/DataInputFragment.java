package com.example.fitnessapp;

import android.content.Context;
import android.os.Bundle;
import android.security.identity.PersonalizationData;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.Person;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class DataInputFragment extends Fragment implements View.OnClickListener {

    Context context;
    EditText editTextAge;
    EditText editTextGender;
    EditText editTextHeight;
    EditText editTextWeight;

    String age,gender,height,weight;
    int i=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        return inflater.inflate(R.layout.datainput_layout, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginBtn = getView().findViewById(R.id.btnLogin);
        loginBtn.setOnClickListener(this);

        editTextAge = (EditText) getView().findViewById(R.id.editTextAge);
        editTextGender = (EditText) getView().findViewById(R.id.editTextGender);
        editTextHeight = (EditText) getView().findViewById(R.id.editTextHeight);
        editTextWeight = (EditText) getView().findViewById(R.id.editTextWeight);

        /* Refreshes text on age*/
        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                age = editTextAge.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        /* Refreshes text on Gender*/
        editTextGender.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                gender = editTextGender.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        /* Refreshes text on Height*/
        editTextHeight.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                height = editTextHeight.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        /* Refreshes text on Weight*/
        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weight = editTextWeight.getText().toString();
            }@Override public void afterTextChanged(Editable s) {}
        });

        /*Receive info from DatabaseManager (In progress)*/
        try {
            String arg = getArguments().getString("username");
            System.out.println(arg);
        }   catch (Exception e){
        }


    }


    public void onClick(View v){
        //TODO ADD INFO TO CREATED USER
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse("data.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Element root = document.getDocumentElement();

        ArrayList<Person> person_list = new ArrayList<>();

        NodeList nList = document.getDocumentElement().getElementsByTagName("person");

        for (int i =0; i< nList.getLength() ; i++){
            Node node =nList.item(i);

        }



    }
}
