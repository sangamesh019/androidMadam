package com.randroid.madam;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class EffortChart {
    public Intent getIntent(Context context){
      // this is my data of performance; data is collected in array.
       int []Performance = {55, 28, 17};  // [0] for Call, [1] for Meeting, [2] for Email
        CategorySeries series = new CategorySeries("pie"); // adding series to charts. //collect 3 value in array. therefore add three series.
            series.add("IMPROVED APP SECURITY",Performance[0]);            
            series.add("MADAM",Performance[1]);
            series.add("INFECTED PERCENTAGE",Performance[2]);
// add three colors for three series respectively    
            int []colors = new int[]{Color.GREEN, Color.MAGENTA, Color.YELLOW};
// set style for series
            DefaultRenderer renderer = new DefaultRenderer();
            for(int color : colors){
                SimpleSeriesRenderer r = new SimpleSeriesRenderer();
                r.setColor(color);
                r.setDisplayBoundingPoints(true);
                r.setDisplayChartValuesDistance(5);
                r.setDisplayChartValues(true);
                r.setChartValuesTextSize(15);
                renderer.addSeriesRenderer(r);
            }
            renderer.isInScroll();
            renderer.setZoomButtonsVisible(true);   //set zoom button in Graph
            renderer.setApplyBackgroundColor(true);
            renderer.setBackgroundColor(Color.BLUE); //set background color
            renderer.setChartTitle("Spyware Hunted");
            renderer.setChartTitleTextSize((float) 30);
            renderer.setShowLabels(true);  
            renderer.setLabelsTextSize(20);
            renderer.setLegendTextSize(25);
            renderer.setDisplayValues(true);
            return ChartFactory.getPieChartIntent(context, series, renderer, "MADAM Chart");
        }
    }