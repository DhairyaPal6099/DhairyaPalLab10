// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.Dh1airya;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import dhairya.pal.n01576099.dp.databinding.FragmentDh1airyaBinding;

public class Dh1airya extends Fragment {
    private FragmentDh1airyaBinding binding;
    private HashMap imageUrls = new HashMap();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDh1airyaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageUrls.put("Mosasaurus", "https://www.jurassicworlduniverse.com/wordpress/wp-content/uploads/2014/11/mosasaurus3.jpg");
        imageUrls.put("Salamander", "https://th.bing.com/th/id/OIP.gJxBw1ag8Sjts3jwA-6hPQHaEK?rs=1&pid=ImgDetMain");
        imageUrls.put("Komodo Dragon", "https://th.bing.com/th/id/OIP.tWwVMYghAk-MK3GthH0odAHaE7?w=284&h=189&c=7&r=0&o=5&dpr=1.3&pid=1.7");
        imageUrls.put("Pterodactyl", "https://cdn.mos.cms.futurecdn.net/6vA8wWskCkUgffN9dbQhvh-1200-80.jpg");

        Button buttonDownloadImage = binding.dhaButton;
        ProgressBar progressBar = binding.dhaProgressBar;
        Spinner spinner = binding.dhaSpinner;
        ImageView imageView = binding.dhaImageView;

        buttonDownloadImage.setOnClickListener(view -> {
            imageView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            Picasso.get()
                    .load(imageUrls.get(spinner.getSelectedItem().toString()).toString())
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                progressBar.setVisibility(View.GONE);
                                imageView.setVisibility(View.VISIBLE);
                            }, 5000);
                        }

                        @Override
                        public void onError(Exception e) {
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }, 5000);
                        }
                    });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}