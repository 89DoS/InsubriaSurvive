package com.example.surviveinsubria;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ((Toolbar) findViewById(R.id.toolbar)).setLogo(R.drawable.ic_action_name);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.apertura_navigazione, R.string.chiusura_navigazione);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_esami:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Esami()).commit();
                break;
            case R.id.nav_orario:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Orari()).commit();
                break;
            case R.id.nav_time:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Timeline()).commit();
                break;
            case R.id.nav_battaglia:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Battaglia()).commit();
                break;
            case R.id.nav_mappa:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Mappa()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
