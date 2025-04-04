// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dhairya.pal.n01576099.dp.databinding.ActivityMainBinding;

public class PalActivity10 extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.dha_nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.dha_navigation_dh1airya, R.id.dha_navigation_pa2l, R.id.dha_navigation_n013576099, R.id.dha_navigation_d4p, R.id.dha_navigation_dpnd)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.dha_nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.dhaNavView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.dhaContacts) {
            // Intent to open Contacts app
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}