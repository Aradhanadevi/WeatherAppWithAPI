package pa.lab.weatherappwithapi;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText cityEditText;
    private Button getWeatherButton;
    private TextView weatherTextView;

    private static final String BASE_URL = "http://api.weatherstack.com/";
    private static final String API_KEY = "dcd6b53aac27007605551968f993174f";  // Replace with your actual API key (given key is from https://weatherstack.com/)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cityEditText = findViewById(R.id.cityEditText);
        getWeatherButton = findViewById(R.id.getWeatherButton);
        weatherTextView = findViewById(R.id.weatherTextView);

        // Set up Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create Weather API service
        WeatherApiService weatherApiService = retrofit.create(WeatherApiService.class);

        // Set up button click listener to fetch weather data
        getWeatherButton.setOnClickListener(v -> {
            String city = cityEditText.getText().toString();
            fetchWeatherData(weatherApiService, city);
        });
    }

    private void fetchWeatherData(WeatherApiService weatherApiService, String city) {
        // Make the API call
        Call<WeatherResponse> call = weatherApiService.getCurrentWeather(API_KEY, city);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    String weatherInfo = "City: " + weatherResponse.getLocation().getName() + ", " + weatherResponse.getLocation().getCountry() + "\n" +
                            "Temperature: " + weatherResponse.getCurrent().getTemperature() + "°C\n";

                    // Handle multiple weather descriptions
                    if (weatherResponse.getCurrent().getWeather_descriptions() != null && !weatherResponse.getCurrent().getWeather_descriptions().isEmpty()) {
                        String descriptions = String.join(", ", weatherResponse.getCurrent().getWeather_descriptions());
                        weatherInfo += "Condition: " + descriptions;
                    } else {
                        weatherInfo += "Condition: No description available";
                    }

                    weatherTextView.setText(weatherInfo);

                } else {
                    weatherTextView.setText("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherTextView.setText("Request failed: " + t.getMessage());
            }
        });
    }
}