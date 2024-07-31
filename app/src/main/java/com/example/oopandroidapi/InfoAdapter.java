package com.example.oopandroidapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private ArrayList<MunicipalityData> municipalityDataArrayList;

    private WeatherData weatherData;
    private String cityName;

    private ImageView imageWeatherIcon;

    public InfoAdapter(ArrayList<MunicipalityData> municipalityDataArrayList, WeatherData weatherData, String cityName) {
        this.municipalityDataArrayList = municipalityDataArrayList;
        //this.studentpopulationData = studentpopulationData;
        this.weatherData = weatherData;
        this.cityName = cityName;

    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view_holder, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        if (municipalityDataArrayList != null && position < municipalityDataArrayList.size()) {
            MunicipalityData municipalityData = municipalityDataArrayList.get(position);
            if (municipalityData != null) {
                holder.textCityName.setText(cityName);
                holder.textCityPopulation.setText("Population: " + municipalityData.getPopulation());
            }
        }


        holder.textWeather.setText("Weather: " + weatherData.getName() + ", " +
                "Temperature: " + weatherData.getTemperature() + "°C, " +
                "Wind Speed: " + weatherData.getWindSpeed() + "m/s");

        double temperature = Double.parseDouble(weatherData.getTemperature());
        int weatherIconResource = getWeatherIconResource(temperature);
        holder.imageWeatherIcon.setImageResource(weatherIconResource);


    }

    private int getWeatherIconResource(double temperature) {
        if (temperature > 0) {
            return R.drawable.sun;
        } else {
            return R.drawable.rain;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class InfoViewHolder extends RecyclerView.ViewHolder {
        TextView textCityName, textCityPopulation, textStudentPopulation, textWeather;

        ImageView imageWeatherIcon;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            textCityName = itemView.findViewById(R.id.textCityName);
            textCityPopulation = itemView.findViewById(R.id.textCityPopulation);
            textWeather = itemView.findViewById(R.id.textWeather);

            imageWeatherIcon = itemView.findViewById(R.id.imageWeatherIcon);
        }
    }

}
