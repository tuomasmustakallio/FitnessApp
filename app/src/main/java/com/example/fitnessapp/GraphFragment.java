package com.example.fitnessapp;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Entity;

import java.util.ArrayList;
import java.util.Map;

import static com.example.fitnessapp.DatabaseManager.getResults;
import static com.example.fitnessapp.DatabaseManager.setUserInfo;
import static java.lang.Integer.parseInt;

public class GraphFragment extends Fragment {


    LineChart mpLineChart;
    Context context;
    ArrayList<Entry> squatVals = new ArrayList<Entry>();
    ArrayList<Entry> benchpressVals = new ArrayList<Entry>();
    ArrayList<Entry> deadliftVals = new ArrayList<Entry>();
    ArrayList<ILineDataSet> dataSets;
    String username;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*Receive info from DatabaseManager (In progress)*/
        username = getArguments().getString("username");
        context = container.getContext();
        return inflater.inflate(R.layout.graph_layout, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LineChart mpLineChart;
        mpLineChart = (LineChart) getView().findViewById(R.id.line_chart);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(),"Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();

        Button Squat = getView().findViewById(R.id.Squat);
        Squat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "eka";
                String movement = "Squat";
                String results = getResults(context, username, movement);
                int weight, reps;
                String[] arrOfStr = results.split("/");
                for (String a : arrOfStr){
                    if(!a.equals("")){
                        String [] arrOfA = a.split(":");
                        weight = parseInt(arrOfA[0]);
                        reps = parseInt(arrOfA[1]);
                        squatVals.add(new Entry(weight,reps));
                    }
                }
                LineDataSet squatData = new LineDataSet(squatVals,"Squat data");
                dataSets.set(0, squatData);
                LineData data = new LineData(dataSets);
                mpLineChart.setData(data);
                mpLineChart.invalidate();
            }
        });
        Button Bench_Press = getView().findViewById(R.id.Bench_Press);
        Bench_Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "eka";
                String movement = "Bench Press";
                String results = getResults(context, username, movement);
                int weight, reps;
                String[] arrOfStr = results.split("/");
                for (String a : arrOfStr){
                    String [] arrOfA = a.split(":");
                    weight = parseInt(arrOfA[0]);
                    reps = parseInt(arrOfA[1]);
                    benchpressVals.add(new Entry(weight,reps));
                }
                LineDataSet bpData = new LineDataSet(benchpressVals,"Bench Press data");
                dataSets.set(0, bpData);
                LineData data = new LineData(dataSets);
                mpLineChart.setData(data);
                mpLineChart.invalidate();
            }
        });
        Button Deadlift = getView().findViewById(R.id.Deadlift);
        Deadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "eka";
                String movement = "Deadlift";
                String results = getResults(context, username, movement);
                int weight, reps;
                String[] arrOfStr = results.split("/");
                for (String a : arrOfStr){
                    String [] arrOfA = a.split(":");
                    weight = parseInt(arrOfA[0]);
                    reps = parseInt(arrOfA[1]);
                    deadliftVals.add(new Entry(weight,reps));
                }
                LineDataSet dlData = new LineDataSet(deadliftVals,"Bench Press data");
                dataSets.set(0, dlData);
                LineData data = new LineData(dataSets);
                mpLineChart.setData(data);
                mpLineChart.invalidate();
            }
        });
    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,24));
        dataVals.add(new Entry(2,2));
        dataVals.add(new Entry(3,10));
        dataVals.add(new Entry(4,28));

        return dataVals;
    }
}
