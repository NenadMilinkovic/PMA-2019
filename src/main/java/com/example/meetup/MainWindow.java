package com.example.meetup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainWindow extends AppCompatActivity {

    ArrayAdapter adapter;
    private DrawerLayout drawer;
    String[] name = {
            "list1",
            "list2",
            "list3"
    };

    String[] description = {
            "list1",
            "list2",
            "list3"
    };

    int[] image = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_home_black_24dp
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ListView listView = (ListView) findViewById(R.id.meetUpListView);

        DrawerListAdapter dla = new DrawerListAdapter(this, name, description, image);
        listView.setAdapter(dla);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainWindow.this, "Clicked position" + position, Toast.LENGTH_SHORT).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    class DrawerListAdapter extends ArrayAdapter<String> {

        Context context;
        String myTitles[];
        String myDesc[];
        int[] myImg;

        DrawerListAdapter(Context c, String[] titles, String[] desc, int[] img){
            super(c, R.layout.list_item, R.id.textView_name, titles);
            this.context = c;
            this.myDesc = desc;
            this.myImg = img;
            this.myTitles = titles;

        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_item, parent, false);
            ImageView imageView = row.findViewById(R.id.imageView_item);
            TextView textViewName = row.findViewById(R.id.textView_name);
            TextView textViewDesc = row.findViewById(R.id.textView_desc);
            imageView.setImageResource(image[position]);
            textViewName.setText(name[position]);
            textViewDesc.setText(description[position]);



            return row;
        }
    }

}
