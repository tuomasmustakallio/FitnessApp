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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.fitnessapp.DatabaseManager.setUserInfo;


public class DataInputFragment extends Fragment implements View.OnClickListener {

    Context context;
    EditText editTextAge;
    EditText editTextGender;
    EditText editTextHeight;
    EditText editTextWeight;

    String age,gender,height,weight, username;
    int i=0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();

        /*Receive info from DatabaseManager (In progress)*/
        String username = getArguments().getString("username");


        return inflater.inflate(R.layout.datainput_layout, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitButton = getView().findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

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


        TextView textViewResult = getView().findViewById(R.id.text_view_result);

        //EXECUTE GET REQUEST
        Retrofit retrofit = new Retrofit.Builder()
                //METHOD TO GET BASE  URL
                .baseUrl("https://type.fit/api/")
                //DEFINE GSON USAGE
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //INITIALIZE EXECUTE
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        //EXECUTE BACKGROUND
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+ response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts){
                    String content = "";
                    content += post.getText() + "\n";
                    content += "Author: " + post.getAuthor() + "\n\n\n";

                    textViewResult.append(content);
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText("Network Error :: " + t.getLocalizedMessage());
            }
        });
    }

    /*Set user info*/
    public void onClick(View v){
        Person person = new com.example.fitnessapp.Person(age, gender, height, weight);
        setUserInfo(context, person, username);
    }
}
