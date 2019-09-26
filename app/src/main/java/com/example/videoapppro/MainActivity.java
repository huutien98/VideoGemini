package com.example.videoapppro;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.videoapppro.Notification.AlarmReceiver;
import com.example.videoapppro.SlideImage.ImageModel;
import com.example.videoapppro.SlideImage.SlidingImage_Adapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem  tabvideohot,tabvideocategory;
    PageController pageController;
    ConectionDetector cd;
    NavigationView navigationView;
    FragmentTransaction fragmentTransaction;
    androidx.appcompat.widget.Toolbar toolbar;
    CoordinatorLayout coordinator;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.image2, R.drawable.image3,
            R.drawable.image4,R.drawable.vangavatar};


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        cd = new ConectionDetector(this);

        // alarm service

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 20);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
        // alarm service


        coordinator = findViewById(R.id.coordinator);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        toggle = new ActionBarDrawerToggle(this,drawer,R.string.Open,R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();



        tabvideohot = findViewById(R.id.tabvideohot);
        tabvideocategory = findViewById(R.id.tabvideocategory);

        pageController = new PageController(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageController);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });




    }

    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private ArrayList<ImageModel> populateList(){

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    @Override
    protected void onResume() {

        if (cd.isConnected()){
            Toast.makeText(getBaseContext(),"Kết nối mạng ổn định",Toast.LENGTH_SHORT).show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("No internet");
            builder.setMessage("vui lòng kiểm tra lại đường truyền !");
            builder.setIcon(android.R.drawable.ic_notification_clear_all);
            builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });



        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(true);
        int id = item.getItemId();

       switch (id){
           case R.id.home:
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
           break;
           case R.id.account:
              AccountFragment accountFragment = new AccountFragment();
              fragmentTransaction = getSupportFragmentManager().beginTransaction();
              fragmentTransaction.replace(R.id.coordinator,accountFragment);
              fragmentTransaction.commit();
               break;
           case R.id.history:
               HistoryFragment historyFragment = new HistoryFragment();
               fragmentTransaction = getSupportFragmentManager().beginTransaction();
               fragmentTransaction.replace(R.id.coordinator,historyFragment);
               fragmentTransaction.commit();
               break;

           case R.id.share:
               Intent myintent = new Intent(Intent.ACTION_SEND);
               myintent.setType("text/plain");
               String shareBody = "Your body here";
               String shareSub = "You Subject here";
               myintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
               myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
               startActivity(Intent.createChooser(myintent,"Share using"));
               break;


           case R.id.signout:

               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
               builder.setTitle("bạn muốn thoát ?");
               builder.setMessage("xác nhận để thoát !");
               builder.setIcon(android.R.drawable.ic_notification_clear_all);
               builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       onBackPressed();
                   }
               });
               builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });
               builder.show();
               break;
       }

        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
