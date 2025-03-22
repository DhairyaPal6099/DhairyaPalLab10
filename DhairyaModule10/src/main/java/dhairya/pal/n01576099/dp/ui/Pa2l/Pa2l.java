// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.Pa2l;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentPa2lBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Pa2l extends Fragment {
    private FragmentPa2lBinding binding;
    private HashMap cities = new HashMap();
    private SharedPreferences sharedPreferences;

    private RadioGroup radioGroup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPa2lBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Get preferred temperature scale from shared preferences
        sharedPreferences = getContext().getSharedPreferences(getString(R.string.shared_preferences), Context.MODE_PRIVATE);
        int preferredTempScaleId = sharedPreferences.getInt(getString(R.string.sharedprefskey_temperature_scale), 9999);
        if (preferredTempScaleId != 9999) {
            if (preferredTempScaleId == R.id.dhaCelsiusRadioButton) {
                binding.dhaCelsiusRadioButton.setChecked(true);
            } else {
                binding.dhaFahrenheitRadioButton.setChecked(true);
            }
        }

        radioGroup = binding.dhaRadioGroup;
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            //Store preferred temperature scale in shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.sharedprefskey_temperature_scale), radioGroup1.getCheckedRadioButtonId());
            editor.apply();
            if (!binding.dhaSpinnerCities.getSelectedItem().toString().equals(getString(R.string.select_city))) {
                requestWeatherData(binding.dhaSpinnerCities.getSelectedItem().toString());
            }
        });

        //Putting the city values to their corresponding coordinates in the HashMap cities
        for (int i = 0; i < 6; i++) {
            cities.put(getResources().getStringArray(R.array.spinner_cities)[i], getResources().getStringArray(R.array.spinner_cities_lat_lon)[i]);
        }

        binding.dhaSpinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    //Clearing all textViews if user selects 'Select city'
                    binding.dhaTextViewCity.setText(R.string.empty_string);
                    binding.dhaTextViewTemperature.setText(R.string.empty_string);
                    binding.dhaTextViewHumidity.setText(R.string.empty_string);
                    binding.dhaTextViewDescription.setText(R.string.empty_string);
                    binding.dhaTextViewLongitude.setText(R.string.empty_string);
                    binding.dhaTextViewLatitude.setText(R.string.empty_string);
                    binding.dhaTextViewCountry.setText(R.string.empty_string);
                } else {
                    requestWeatherData(getResources().getStringArray(R.array.spinner_cities)[i]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void requestWeatherData(String cityName) {
        String url = getString(R.string.OpenWeatherURL_part1) + cities.get(cityName).toString().split(getString(R.string.comma), 2)[0] + getString(R.string.OpenWeatherURL_part2) + cities.get(cityName).toString().split(getString(R.string.comma), 2)[1] + getString(R.string.OpenWeatherURL_part3);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getContext(), getString(R.string.error_colon) + e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @SuppressLint("StringFormatMatches")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());

                        //Setting the city
                        binding.dhaTextViewCity.setText(String.format(getString(R.string.city_placeholderString), cityName));

                        //Getting and setting the temperature
                        JSONObject main = (JSONObject) jsonObject.get(getString(R.string.main));
                        double temperature = (double) main.get(getString(R.string.temp));
                        if (radioGroup.getCheckedRadioButtonId() == R.id.dhaCelsiusRadioButton) {
                            temperature -= 273.15;
                            binding.dhaTextViewTemperature.setText(String.format(getString(R.string.temperature_placeholderFloat_c), temperature));
                        } else {
                            temperature = (temperature - 273.15)*9/5 + 32;
                            binding.dhaTextViewTemperature.setText(String.format(getString(R.string.temperature_placeholderFloat_f), temperature));
                        }
                        binding.dhaTextViewHumidity.setText(String.format(getString(R.string.humidity_placeholderInt), main.get(getString(R.string.humidity))));

                        //Getting and setting the coordinates
                        JSONObject coord = (JSONObject) jsonObject.get(getString(R.string.coord));
                        binding.dhaTextViewLongitude.setText(String.format(getString(R.string.lon_placeholderFloat), coord.get(getString(R.string.lon))));
                        binding.dhaTextViewLatitude.setText(String.format(getString(R.string.lat_placeholderFloat), coord.get(getString(R.string.lat))));

                        //Getting and setting the country
                        JSONObject sys = (JSONObject) jsonObject.get(getString(R.string.sys));
                        binding.dhaTextViewCountry.setText(String.format(getString(R.string.country_placeholderString), sys.get(getString(R.string.country))));

                        //Getting and setting the description
                        JSONArray weather = jsonObject.getJSONArray(getString(R.string.weather));
                        binding.dhaTextViewDescription.setText(String.format(getString(R.string.desc_placeholderString), weather.getJSONObject(0).get(getString(R.string.description))));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_code_colon) + response.code() + getString(R.string.newline) + response.message(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}