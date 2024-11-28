package pa.lab.weatherappwithapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    // GET request to fetch current weather data
    @GET("current")
    Call<WeatherResponse> getCurrentWeather(
            @Query("access_key") String apiKey, // Your API key
            @Query("query") String cityName     // City name for the weather query
    );
}
