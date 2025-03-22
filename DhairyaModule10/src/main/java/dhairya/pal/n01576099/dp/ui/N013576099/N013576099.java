// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.N013576099;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import dhairya.pal.n01576099.dp.databinding.FragmentN013576099Binding;

public class N013576099 extends Fragment {
    private FragmentN013576099Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentN013576099Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}