// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.Pa2l;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import dhairya.pal.n01576099.dp.databinding.FragmentPa2lBinding;

public class Pa2l extends Fragment {
    private FragmentPa2lBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPa2lBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}