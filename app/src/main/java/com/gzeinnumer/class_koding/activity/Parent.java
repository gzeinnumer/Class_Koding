package com.gzeinnumer.class_koding.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gzeinnumer.class_koding.R;
import com.gzeinnumer.class_koding.fragment.HomeFragment;
import com.gzeinnumer.class_koding.fragment.LearnFragment;
import com.gzeinnumer.class_koding.fragment.NotificationFragment;
import com.gzeinnumer.class_koding.fragment.ProfilFragment;
import com.gzeinnumer.class_koding.helper.SessionManager;

public class Parent extends SessionManager {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.learn_fragment:
                    fragment = new LearnFragment();
                    break;
                case R.id.nofication_fragment:
                    fragment = new NotificationFragment();
                    break;
                case R.id.profil_fragment:
                    fragment = new ProfilFragment();
                    break;
            }
            setFragmentToParent(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Belajar Yuk?...");

        //firts start
        fragment = new HomeFragment();
        setFragmentToParent(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.search_dropmenu:
                shortToast("tekan");
                break;
            case R.id.search_materi:
                shortToast("tekan");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
