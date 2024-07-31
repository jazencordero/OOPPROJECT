package com.example.oopandroidapi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {


    private EditText editMunicipalityName;
    private Button buttonSearch;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editMunicipalityName = findViewById(R.id.editCity);
        buttonSearch = findViewById(R.id.buttonSearch);
        rootView = findViewById(android.R.id.content); // Root view of the activity

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick();
            }
        });

    }
    public void onSearchButtonClick() {
        Context context = this;
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();
        //StudentDataRetriever studentDataRetriever = new StudentDataRetriever();
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();
        String cityName = editMunicipalityName.getText().toString();



        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            MunicipalityDataRetriever.getMunicipalityCodesMap();
            ArrayList<MunicipalityData> municipalityDataArrayList = municipalityDataRetriever.getData(context, editMunicipalityName.getText().toString());

            if (municipalityDataArrayList == null) {
                return;
            }

            //ArrayList<StudentData> studentpopulationData = studentDataRetriever.getData(context, editMunicipalityName.getText().toString());
            WeatherData weatherData = weatherDataRetriever.getData(editMunicipalityName.getText().toString());

            runOnUiThread(() -> {

                InfoAdapter adapter = new InfoAdapter(municipalityDataArrayList, weatherData, cityName);
                RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCity);
                recyclerView.setAdapter(adapter);
            });
        });
    }}