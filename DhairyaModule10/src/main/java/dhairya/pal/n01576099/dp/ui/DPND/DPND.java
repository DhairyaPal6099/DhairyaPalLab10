// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.DPND;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentDpndBinding;

public class DPND extends Fragment {
    private FragmentDpndBinding binding;

    public DPND() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDpndBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText courseNameEditText = binding.dhaCourseNameEditText;
        EditText courseDescriptionEditText = binding.dhaCourseDescriptionEditText;

        courseNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String uppercaseEditable = editable.toString().toUpperCase();
                if (!editable.toString().equals(uppercaseEditable)) {
                    courseNameEditText.setText(uppercaseEditable);
                    courseNameEditText.setSelection(uppercaseEditable.length());
                }
            }
        });

        return root;
    }
}