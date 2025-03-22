// Dhairya Pal N01576099

package dhairya.pal.n01576099.dp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class DhairyaPalSplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dhairyapalsplash_layout);
        getSupportActionBar().hide();
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(DhairyaPalSplash.this, PalActivity10.class));
            finish();
        }, 3000);
    }
}