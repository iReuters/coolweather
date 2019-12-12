package com.example.firstandroidcode.chapter12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.firstandroidcode.MyApplication;
import com.example.firstandroidcode.MyToast;
import com.example.firstandroidcode.R;
import com.example.firstandroidcode.chapter11.Chapter11;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chapter12 extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private FloatingActionButton floatingActionButton;

    private Fruit[] fruits = {new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon),
            new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon),
            new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon),
            new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon), new Fruit("Apple", R.drawable.apple_icon),};

    private List<Fruit> fruitList = new ArrayList<>();

    private FruitAdapter fruitAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter12);
        Toolbar toolbar = findViewById(R.id.chapter12_tb1);
        navigationView = findViewById(R.id.chapter12_nv1);
        floatingActionButton = findViewById(R.id.chapter12_fab1);
        swipeRefreshLayout = findViewById(R.id.chapter12_srl1);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.chapter12_rv1);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();

            }
        });

        drawerLayout = findViewById(R.id.chapter12_dl1);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            //actionBar.setHomeAsUpIndicator(R.drawable.apple_icon);
            actionBar.setHomeButtonEnabled(true);
        }

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_call:
                        MyToast.makeShortText(Chapter12.this, "You clicked call button");
                        break;
                    case R.id.nav_friends:
                        MyToast.makeShortText(Chapter12.this, "You clicked friends button");
                        break;
                    case R.id.nav_location:
                        Intent intent = new Intent(Chapter12.this, Chapter11.class);
                        startActivity(intent);
                        MyToast.makeShortText(Chapter12.this, "You clicked location button");
                        break;
                    case R.id.nav_mail:
                        MyToast.makeShortText(Chapter12.this, "You clicked mail button");
                        break;
                    case R.id.nav_task:
                        MyToast.makeShortText(Chapter12.this, "You clicked task button");
                        break;
                        default:
                            break;
                }
                return true;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data delete", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyToast.makeShortText(Chapter12.this, "wocao wuqing");
                    }
                }).show();
            }
        });

    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50 ; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void refreshFruits() {
        new Thread() {
            @Override
            public void run() {
                try {

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        fruitAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);

                    }
                });
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               // MyToast.makeShortText(MyApplication.getContext(), "You clicked home button");
                MyToast.makeText1("You clicked home button");
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:

                MyToast.makeShortText(this, "You clicked backup button");
                break;
            case R.id.delete:
                MyToast.makeShortText(this, "You clicked delete button");
                break;
            case R.id.setting:
                MyToast.makeShortText(this, "You clicked setting button");
                break;
                default:
                    break;
        }
        return true;
    }
}
