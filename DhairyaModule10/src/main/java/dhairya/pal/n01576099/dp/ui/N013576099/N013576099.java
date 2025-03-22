// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.N013576099;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dhairya.pal.n01576099.dp.R;
import dhairya.pal.n01576099.dp.databinding.FragmentN013576099Binding;
import dhairya.pal.n01576099.dp.ui.ItemModel;
import dhairya.pal.n01576099.dp.ui.ItemModelAdapter;

public class N013576099 extends Fragment {
    private FragmentN013576099Binding binding;
    private ItemModelAdapter adapter;

    private RecyclerView itemRV;

    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentN013576099Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        itemModelArrayList.add(new ItemModel("Video Title 1", R.drawable.video_icon));
        itemModelArrayList.add(new ItemModel("Video Title 2", R.drawable.video_icon));
        itemModelArrayList.add(new ItemModel("Video Title 3", R.drawable.video_icon));

        itemRV = binding.dhaRecyclerView;
        adapter = new ItemModelAdapter(itemModelArrayList, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        itemRV.setHasFixedSize(true);
        itemRV.setLayoutManager(manager);
        itemRV.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}