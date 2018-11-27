package edu.fsu.cs.mobile.nudge;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupNavigation();

    }

    public void setupNavigation(){
        BottomNavigationView navBar = (BottomNavigationView) findViewById(R.id.navigationBar);

        if (navBar != null){
            Menu menu = navBar.getMenu();
            selectFragment(menu.getItem(1));

            navBar.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener(){
                        public boolean onNavigationItemSelected(MenuItem item){
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }

    protected void selectFragment(MenuItem item) {
        item.setChecked(true);

        switch(item.getItemId()){
            case R.id.nav_contacts:
                pushFragment(new ContactsFragment());
                break;
            case R.id.nav_home:
                pushFragment(new MyCardsFragment());
                break;
            case R.id.nav_add:
                pushFragment(new MakeCardsFragment());
                break;
        }
    }

    protected void pushFragment(Fragment fragment){
        if (fragment == null)
            return;

        FragmentManager manager = getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.replace(R.id.home_frameLayout, fragment);
        trans.commit();
    }
}
