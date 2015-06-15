package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


public class MainActivity extends ActionBarActivity  implements NavigationDrawerFragment.FragmentDrawerListener {

    private Toolbar mTool;
    private TextView labelWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTool = (Toolbar) findViewById(R.id.toolbar);
        labelWelcome = (TextView) findViewById(R.id.welcome);

        setSupportActionBar(mTool);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        SharedPreferences pref = MainActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);

        labelWelcome.setText(labelWelcome.getText()+ " " + pref.getString(getString(R.string.userRemembered), "hola"));

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragmnet);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), mTool, R.id.navigation_drawer_fragmnet);
        drawerFragment.setDrawerListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.logout) {
            SharedPreferences pref = MainActivity.this.getSharedPreferences(getString(R.string.sharedClass), Context.MODE_PRIVATE);
            pref.edit().clear().apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class );
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        String title = getString(R.string.app);

        switch (position){
            case 1:
                fragment =  new ProfileFragment();
                title = getString(R.string.profile);
                break;
            case 2:
                fragment =  new CoursesFragment();
                title = getString(R.string.courses);
                break;
            case 3:
                fragment =  new MyCoursesFragment();
                title = getString(R.string.myCourses);
                break;
            case 4:
                fragment =  new CategoryFragment();
                title = getString(R.string.category);
                break;
            default:

                break;
        }

        if(fragment != null){
            ((RelativeLayout)findViewById(R.id.ContainerLayout)).removeAllViewsInLayout();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.ContainerLayout, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }
}

