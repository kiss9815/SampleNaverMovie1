package com.example.tacademy.samplenavermovie1;

import com.begentgroup.xmlparser.SerializedName;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-02-05.
 */
public class NaverMovies {
    String title;
    String description;
    int total;
    int start;
    int display;
    @SerializedName("item")
    ArrayList<MovieItem> items;
}
