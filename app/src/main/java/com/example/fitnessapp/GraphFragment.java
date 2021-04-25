package com.example.fitnessapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphFragment extends Fragment {

    private LineGraphSeries<DataPoint> series1;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        double y,x;
        x=-0.5;
        View view = inflater.inflate(R.layout.graph_layout, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        for(int i = 0; i<500; i++ ){
            x=x+0.1;
            y= Math.sin(x);
            series1.appendData(new DataPoint(x, y), true, 500);
        }
        graph.addSeries(series1);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
