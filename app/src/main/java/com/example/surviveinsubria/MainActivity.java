package com.example.surviveinsubria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


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
            case R.id.nav_logout:
                logout();
                break;
                      }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        mAuth.signOut();
        LoginManager.getInstance().logOut(); // logout facebook
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignIn.getClient(this, googleSignInOptions).signOut(); // logout google
        updateUI();
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
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            updateUI();
        }

    }

    private void updateUI() {
        Toast.makeText(MainActivity.this, "You're logged out", Toast.LENGTH_LONG).show();

        Intent accountIntent = new Intent(MainActivity.this, Login.class);
        startActivity(accountIntent);
        finish();

    }
}
