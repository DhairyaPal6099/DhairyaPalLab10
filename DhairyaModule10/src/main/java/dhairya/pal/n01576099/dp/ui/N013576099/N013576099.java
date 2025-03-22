// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp.ui.N013576099;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

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

    private WebView webView;
    private ArrayList<ItemModel> itemModelArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentN013576099Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        for (int i = 0; i < 3; i++) {
            itemModelArrayList.add(new ItemModel(getResources().getStringArray(R.array.webview_videos)[i], R.drawable.video_icon));
        }

        itemRV = binding.dhaRecyclerView;
        adapter = new ItemModelAdapter(itemModelArrayList, getContext(), position -> {
            webView = binding.dhaWebView;
            webView.getSettings().setJavaScriptEnabled(true);
            String url = getResources().getStringArray(R.array.webview_video_urls)[position];
            String html = getString(R.string.html_body_part1) +
                    getString(R.string.html_body_part2) + url + getString(R.string.html_body_part3) +
                    getString(R.string.html_body_part4);
            webView.loadData(html, getString(R.string.text_html), getString(R.string.utf_8));
        });
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