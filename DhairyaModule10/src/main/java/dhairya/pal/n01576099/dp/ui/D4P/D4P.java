// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.D4P;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentD4pBinding;

public class D4P extends Fragment {
    private FragmentD4pBinding binding;
    private ActivityResultLauncher<String> locationPermissionLauncher;
    private int counter;
    private double latitude;
    private double longitude;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentD4pBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.dhaAdView.setOnClickListener(view -> {
            counter++;
            Toast.makeText(getContext(), getString(R.string.full_name_space) + counter, Toast.LENGTH_LONG).show();
        });

        //Setting up request launcher for location permissions
        locationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean o) {
                if (o) {
                   getAndShowLocation();
                } else {
                    Snackbar snackbar = Snackbar.make(getView(), "Permission denied", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("DISMISS", view1 -> snackbar.dismiss());
                    snackbar.show();
                }
            }
        });

        binding.dhaButtonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAndShowLocation();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

   private void getAndShowLocation() {
       if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
       } else {
           FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
           fusedLocationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null).addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
               @Override
               public void onSuccess(Location location) {
                   if (location != null) {
                       latitude = location.getLatitude();
                       longitude = location.getLongitude();
                       Snackbar snackbar = Snackbar.make(getView(), String.format("Latitude: %f, Longitude: %f", latitude, longitude), Snackbar.LENGTH_INDEFINITE);
                       snackbar.setAction("DISMISS", view1 -> snackbar.dismiss());
                       snackbar.show();
                   }
               }
           });
       }
   }
}