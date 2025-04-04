// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.DPND;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.regex.Pattern;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentDpndBinding;
import dhairya.pal.n01576099.dp.ui.CourseAdapterRV;
import dhairya.pal.n01576099.dp.ui.CourseItemRV;

public class DPND extends Fragment {
    private FragmentDpndBinding binding;
    private RecyclerView courseRV;
    private CourseAdapterRV adapter;
    private ArrayList<CourseItemRV> courseItemRVArrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("coursesInDatabase");

    public DPND() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDpndBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        courseRV = binding.dhaRecyclerView;
        EditText courseNameEditText = binding.dhaCourseNameEditText;
        EditText courseDescriptionEditText = binding.dhaCourseDescriptionEditText;
        Button addButton = binding.dhaAddButton;
        Button deleteButton = binding.dhaDeleteButton;
        courseItemRVArrayList = new ArrayList<>();

        loadData();
        buildRecyclerView();

        //Changing lowercase input from course name edit text to uppercase
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

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseItemRVArrayList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    CourseItemRV course = itemSnapshot.getValue(CourseItemRV.class);
                    if (course != null) {
                        courseItemRVArrayList.add(course);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addButton.setOnClickListener(view -> {
            Pattern pattern = Pattern.compile("^[A-Z]{4}-\\d{3,4}$");
            if (courseNameEditText.getText().toString().isEmpty()) {
                courseNameEditText.setError(getString(R.string.empty_not_allowed));
            }
            else if (courseDescriptionEditText.getText().toString().isEmpty()) {
                courseDescriptionEditText.setError(getString(R.string.empty_not_allowed));
            }
            else if (!pattern.matcher(courseNameEditText.getText().toString()).matches()) {
                courseNameEditText.setError(getString(R.string.invalid_input));
            }
            else {
                courseItemRVArrayList.add(new CourseItemRV(courseNameEditText.getText().toString(), courseDescriptionEditText.getText().toString()));
                adapter.notifyItemInserted(courseItemRVArrayList.size());
                saveData();
                courseNameEditText.setText(getString(R.string.empty_string));
                courseDescriptionEditText.setText(getString(R.string.empty_string));
            }
        });

        deleteButton.setOnClickListener(view -> {
            if (courseItemRVArrayList.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.no_data_to_delete), Toast.LENGTH_SHORT).show();
            } else {
                courseItemRVArrayList.clear();
                buildRecyclerView();
                myRef.removeValue();
            }
        });

        return root;
    }

    private void buildRecyclerView() {
        adapter = new CourseAdapterRV(courseItemRVArrayList, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        courseRV.setHasFixedSize(true);
        courseRV.setLayoutManager(manager);
        courseRV.setAdapter(adapter);
    }

    private void loadData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseItemRVArrayList.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    CourseItemRV course = itemSnapshot.getValue(CourseItemRV.class);
                    if (course != null) {
                        courseItemRVArrayList.add(course);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (courseItemRVArrayList == null) {
            courseItemRVArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        myRef.setValue(courseItemRVArrayList);
    }
}