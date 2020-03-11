package com.example.equal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.equal.Fragment.AccountFragment;
import com.example.equal.Fragment.ConsultFragment;
import com.example.equal.Fragment.HomeFragment;
import com.example.equal.Fragment.JobFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if(isConnected(getBaseContext())){
            loadFragment(new HomeFragment());
        }else{
            loadFragment(new NoInternetFragment());
        }
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flMain, fragment)
                    .commit();

            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.menu_home:
                if(isConnected(getBaseContext())){
                    fragment = new HomeFragment();
                }else{
                    fragment = new NoInternetFragment();
                }
                break;
            case R.id.menu_consult:
                if(isConnected(getBaseContext())){
                    fragment = new ConsultFragment();
                }else{
                    fragment = new NoInternetFragment();
                }
                break;
            case R.id.menu_job:
                if(isConnected(getBaseContext())){
                    fragment = new JobFragment();
                }else{
                    fragment = new NoInternetFragment();
                }
                break;
            case R.id.menu_account:
                fragment = new AccountFragment();
                break;
        }

        return loadFragment(fragment);
    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())){
                return true;
            }
        }
            return false;
    }


}
