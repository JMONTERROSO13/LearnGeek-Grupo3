package gt.edu.kinal.jmonterroso.learngeek_grupo3;

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


public class MainActivity extends ActionBarActivity  implements NavigationDrawerFragment.FragmentDrawerListener {

    private Toolbar mTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTool = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mTool);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Fragment
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer_fragmnet);

        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout), mTool, R.id.navigation_drawer_fragmnet);
        drawerFragment.setDrawerListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onDrawerItemSelected(View view, int position) {
        Fragment fragment = null;
        String title = getString(R.string.app);

        switch (position){
            case 1:
               /* fragment =  new SettingFragment();
                title = getString(R.string.settings);*/
                break;
            case 2:
               /* fragment =  new MoviesFragment();
                title = getString(R.string.peli);*/
                break;
            case 3:
              /*  fragment =  new FavoritesFragment();
                title = getString(R.string.favorites);*/
                break;
            case 4:
              /*  fragment =  new HoursFragment();
                title = getString(R.string.hours);*/
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

