package br.com.bruno.soccernews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import br.com.bruno.soccernews.data.local.AppDatabase;
import br.com.bruno.soccernews.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        AppBarConfiguration appBarConfiguration
                = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard).build();
        NavController navController
                = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        setupLocalDB();
    }

    private void setupLocalDB() {
        db = Room.databaseBuilder(this, AppDatabase.class, "spccer-news")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDb() {
        return db;
    }
}