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

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentDh1airyaBinding;

public class Dh1airya extends Fragment {
    private FragmentDh1airyaBinding binding;
    private HashMap imageUrls = new HashMap();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDh1airyaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        for (int i = 0; i < 4; i++) {
            imageUrls.put(getResources().getStringArray(R.array.spinner_items)[i], getResources().getStringArray(R.array.spinner_urls)[i]);
        }

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
                                Toast.makeText(getContext(), getString(R.string.error) + e.getMessage(), Toast.LENGTH_LONG).show();
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