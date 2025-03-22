// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.Dh1airya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import dhairya.pal.n01576099.dp.databinding.FragmentDh1airyaBinding;

public class Dh1airya extends Fragment {
    private FragmentDh1airyaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDh1airyaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}