package basti.coryphaei.com.mdtest;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import basti.coryphaei.com.mdtest.fragment.AllFragment;
import basti.coryphaei.com.mdtest.fragment.SecondFragment;
import basti.coryphaei.com.mdtest.fragment.ThirdFragment;


public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private AllFragment allFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init(0);
        initEvent();
    }

    private void initEvent() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.all:
                        init(0);
                        setToolBar(getResources().getString(R.string.all));
                        break;
                    case R.id.note:
                        init(1);
                        setToolBar(getResources().getString(R.string.note));
                        break;
                    case R.id.memo:
                        init(2);
                        setToolBar(getResources().getString(R.string.memo));
                        break;
                    case R.id.like:
                        init(3);
                        setToolBar(getResources().getString(R.string.like));
                        break;
                }

                return false;
            }
        });

    }

    private void setToolBar(String string) {

        mToolbar.setTitle(string);

    }


    private void init(int position) {
        switch (position){
            case 0:
                if (allFragment == null){
                    allFragment = new AllFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame, allFragment)
                            .commit();
                }
                showFragment(allFragment,0);
                break;
            case 1:
                if (secondFragment == null){
                    secondFragment = new SecondFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame, secondFragment)
                            .commit();
                }
                showFragment(secondFragment,1);
                break;
            case 2:
                if (thirdFragment == null){
                    thirdFragment = new ThirdFragment();
                    secondFragment = new SecondFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame, thirdFragment)
                            .commit();
                }
                showFragment(thirdFragment,2);
                break;
            case 3:
                if (thirdFragment == null){
                    thirdFragment = new ThirdFragment();
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frame, thirdFragment)
                            .commit();
                }
                showFragment(thirdFragment,3);
                break;
        }
    }

    private void showFragment(Fragment fragment,int pos) {

        hideAll();
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    private void hideAll() {

        if (allFragment != null){
            Log.i("1","1");
            getSupportFragmentManager().beginTransaction().hide(allFragment).commit();
        }

        if (secondFragment != null){
            Log.i("2","2");
            getSupportFragmentManager().beginTransaction().hide(secondFragment).commit();
        }

        if (thirdFragment != null){
            Log.i("3","3");
            getSupportFragmentManager().beginTransaction().hide(thirdFragment).commit();
        }

        if (thirdFragment != null){
            Log.i("4","4");
            getSupportFragmentManager().beginTransaction().hide(thirdFragment).commit();
        }


    }

    private void initView() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_menu_white_24dp);
        mToolbar.setTitle(getResources().getString(R.string.all));
        mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}
