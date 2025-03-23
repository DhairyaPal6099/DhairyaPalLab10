// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.D4P;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentD4pBinding;

public class D4P extends Fragment {
    private FragmentD4pBinding binding;
    private int counter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentD4pBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.dhaAdView.setOnClickListener(view -> {
            counter++;
            Toast.makeText(getContext(), getString(R.string.full_name_space) + counter, Toast.LENGTH_LONG).show();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}