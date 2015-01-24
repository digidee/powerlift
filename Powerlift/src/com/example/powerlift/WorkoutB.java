package com.example.powerlift;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.LineGraphView;

public class WorkoutB extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout_b,
				container, false);

		GraphViewData[] gd = new GraphViewData[] { 
				new GraphViewData(1, 40),
				new GraphViewData(2, 42.5d), 
				new GraphViewData(3, 45),
				new GraphViewData(4, 47.5d) 
				};

		GraphViewData[] gd2 = new GraphViewData[] { 
				new GraphViewData(1, 50),
				new GraphViewData(2, 52.5d), 
				new GraphViewData(3, 55),
				new GraphViewData(4, 57.5d) 
				};

		// init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries("Squat",
				new GraphViewSeriesStyle(Color.RED, 3), gd);

		GraphViewSeries exampleSeries2 = new GraphViewSeries("Deadlift",
				new GraphViewSeriesStyle(Color.BLUE, 3), gd2);

		GraphView graphView = new LineGraphView(getActivity() // context
				, "Exercise Progress" // heading
		);

//		graphView.setHorizontalLabels(new String[] { "2 days ago", "yesterday",
//				"today", "tomorrow" });
//		graphView.setVerticalLabels(new String[] { "high", "middle", "low" });

		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.BOTTOM);
		graphView.setLegendWidth(200);

		graphView.addSeries(exampleSeries); // data
		graphView.addSeries(exampleSeries2); // data
		

		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.graph1);
		LinearLayout layout2 = (LinearLayout) rootView
				.findViewById(R.id.graph2);
		layout.addView(graphView);


		return rootView;
	}

}
