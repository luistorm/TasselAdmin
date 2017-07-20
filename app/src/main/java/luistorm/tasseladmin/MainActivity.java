package luistorm.tasseladmin;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mDrawerList;
    private RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private TextView tV,tV2,tV3,tV4;
    private CardView cV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerMain);

        // Populate the Navigation Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);
        mNavItems.add(new NavItem(R.drawable.menu,getString(R.string.products)));

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        tV = (TextView) findViewById(R.id.textView);
        tV2 = (TextView) findViewById(R.id.textView2);
        tV3 = (TextView) findViewById(R.id.textView3);
        tV4 = (TextView) findViewById(R.id.textView4);
        Typeface tF = Typeface.createFromAsset(getAssets(),"tf1.ttf");
        float s1 = tV.getTextSize();
        tV.setTypeface(tF);
        tV.setTextSize(s1*2);
        float s2 = tV2.getTextSize();
        tV2.setTypeface(tF);
        tV2.setTextSize(s2*2);
        float s3 = tV3.getTextSize();
        tV3.setTypeface(tF);
        tV3.setTextSize(s3*2);
        float s4 = tV4.getTextSize();
        tV4.setTypeface(tF);
        tV4.setTextSize(s4*2);
        cV = (CardView) findViewById(R.id.cardView3);
        cV.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    /*
    * Called when a particular item from the navigation drawer
    * is selected.
    * */
    private void selectItemFromDrawer(int position) {

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == cV.getId()) {
            Intent intent = new Intent(this,Ingredients.class);
            startActivity(intent);
        }
    }
}
