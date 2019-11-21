package com.example.firstandroidcode.chapter3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.firstandroidcode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chapter3 extends AppCompatActivity {

    private static final String mTAG = "Chapter3";
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
            "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};
    private List <Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter3);
        Log.d(mTAG, "onCreate: ");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        Button button = findViewById(R.id.chapter3_btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chapter3.this,Chapter3_1.class);
                startActivity(intent);
            }
        });
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.chapter_rv1);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(mTAG, "onDestroy: ");
    }

    private void initFruits() {
        for (int i = 0; i <3; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_icon);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.apple_icon);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.apple_icon);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.apple_icon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.apple_icon);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.apple_icon);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.apple_icon);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.apple_icon);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.apple_icon);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.apple_icon);
            fruitList.add(apple);
            fruitList.add(orange);
            fruitList.add(banana);
            fruitList.add(watermelon);
            fruitList.add(pear);
            fruitList.add(grape);
            fruitList.add(pineapple);
            fruitList.add(strawberry);
            fruitList.add(cherry);
            fruitList.add(mango);

        }

    }
    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i= 0; i < length; i++)
            builder.append(name);
        return builder.toString();
    }
}
