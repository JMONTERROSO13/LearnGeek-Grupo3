package gt.edu.kinal.jmonterroso.learngeek_grupo3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.adapters.NavigationDrawerAdapter;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.RecyclerItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FragmentDrawerListener drawerListener;
    private View containerView;

    private int ICONS[] = {R.drawable.ic_rows,R.drawable.ic_rows, R.drawable.ic_rows, R.drawable.ic_rows };
    // private String TITLES[] = {String.valueOf(R.string.settings), String.valueOf(R.string.peli),String.valueOf(R.string.favorites),String.valueOf(R.string.hours)};
    private String TITLES[] = {"Ajustes","Peliculas","Favoritas","Horarios"};
    private String NAME = "Jorge Monterroso";
    private String EMAIL = "jmonterroso-2013175@kinal.edu.gt";
    private int PROFILE = R.mipmap.ic_launcher;


    public NavigationDrawerFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NavigationDrawerAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                drawerListener.onDrawerItemSelected(view, position);
                mDrawerLayout.closeDrawer(containerView);
            }
        }));

        return v;
    }


    public void setUp(DrawerLayout drawerLaout, Toolbar toolbar, int fragmemtId) {
        this.mDrawerLayout = drawerLaout;
        this.mToolbar = toolbar;
        this.containerView = getActivity().findViewById(fragmemtId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLaout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                mToolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public void setDrawerListener(FragmentDrawerListener jDrawerListener){
        this.drawerListener = jDrawerListener;
    }

    public interface FragmentDrawerListener{
        public void onDrawerItemSelected(View view, int position);
    }

}
