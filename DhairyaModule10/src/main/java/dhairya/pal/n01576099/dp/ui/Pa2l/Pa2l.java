// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.Pa2l;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.HashMap;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentPa2lBinding;

public class Pa2l extends Fragment {
    private FragmentPa2lBinding binding;
    private HashMap cities = new HashMap();
    private SharedPreferences sharedPreferences;

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

        RadioGroup radioGroup = binding.dhaRadioGroup;
        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            //Store preferred temperature scale in shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.sharedprefskey_temperature_scale), radioGroup1.getCheckedRadioButtonId());
            editor.apply();
        });

        //Putting the city values to their corresponding coordinates in the HashMap cities
        for (int i = 0; i < 5; i++) {
            cities.put(getResources().getStringArray(R.array.spinner_cities)[i], getResources().getStringArray(R.array.spinner_cities_lat_lon)[i]);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}