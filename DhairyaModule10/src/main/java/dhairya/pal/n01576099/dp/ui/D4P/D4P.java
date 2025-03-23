// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.D4P;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentD4pBinding;

public class D4P extends Fragment {

    private boolean permissionRequestedBefore = false;
    private FragmentD4pBinding binding;
    private ActivityResultLauncher<String> locationPermissionLauncher;
    private ActivityResultLauncher<String> notificationPermissionLauncher;
    private NotificationManagerCompat manager;
    private NotificationCompat.Builder builder;
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
        locationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), o -> {
            if (o) {
               getAndShowLocation();
            } else {
                Snackbar snackbar = Snackbar.make(getView(), R.string.permission_denied, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.dismiss, view1 -> snackbar.dismiss());
                snackbar.show();
            }
        });

        //Setting up request launcher for notifications permissions
        notificationPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), o -> {
            if (o) {
                showNotification();
            }
        });

        //Setting up notification manager and channel
        NotificationChannelCompat channel = new NotificationChannelCompat.Builder(getString(R.string.my_channel_id), NotificationManagerCompat.IMPORTANCE_HIGH)
                .setName(getString(R.string.location_updates))
                .setDescription(getString(R.string.notifications_for_location_updates))
                .setVibrationEnabled(true)
                .build();
        manager = NotificationManagerCompat.from(getContext());
        manager.createNotificationChannel(channel);

        binding.dhaButtonLocation.setOnClickListener(view -> {
            getAndShowLocation();
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
           if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) && permissionRequestedBefore) {
               //User has chose to not ask again
               startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts(getString(R.string.packagee), getActivity().getPackageName(), null)));
           }
           locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
           permissionRequestedBefore = true;
       } else {
           FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
           fusedLocationProviderClient.getCurrentLocation(LocationRequest.QUALITY_HIGH_ACCURACY, null).addOnSuccessListener(getActivity(), location -> {
               if (location != null) {
                   latitude = location.getLatitude();
                   longitude = location.getLongitude();
                   showNotification();
                   Snackbar snackbar = Snackbar.make(getView(), String.format(getString(R.string.latitude_f_longitude_f), latitude, longitude), Snackbar.LENGTH_INDEFINITE);
                   snackbar.setAction(R.string.dismiss, view1 -> snackbar.dismiss());
                   snackbar.show();
               }
           });
       }
   }

   private void showNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            } else {
                builder = new NotificationCompat.Builder(getContext(), getString(R.string.my_channel_id))
                        .setSmallIcon(R.drawable.app_logo)
                        .setContentTitle(getString(R.string.dhairya_pal_location_determined))
                        .setContentText(String.format(getString(R.string.latitude_f_longitude_f), latitude, longitude));
                manager.notify(1, builder.build());
            }
        }
   }
}