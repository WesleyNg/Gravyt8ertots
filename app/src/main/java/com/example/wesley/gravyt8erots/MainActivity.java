package com.example.wesley.gravyt8erots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();
        registerClickCallback();
    }

    private void populateListView() {
        String[] stories = {"Wicked", "Cyborg Book", "Harry Potter"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.story_items, stories);

        ListView list = (ListView) findViewById(R.id.storyList);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.storyList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is the story: " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.AddButton:
                showView();
                break;
            case R.id.action_button:
                storyAttributes();
                break;
        }
    }

    public void showView()
    {
        if (findViewById(R.id.story_fragment_id).getVisibility() == View.VISIBLE)
        {
            findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
        }
        else
        {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
        }
    }

    public void storyAttributes()
    {
        if (Integer.parseInt(findViewById(R.id.action_button).getTag().toString()) == 1)
        {
            findViewById(R.id.action_button).setBackgroundResource(R.drawable.action_selected);
            findViewById(R.id.action_button).setTag(2);
        }
        else
        {
            findViewById(R.id.action_button).setBackgroundResource(R.drawable.action);
            findViewById(R.id.action_button).setTag(1);
        }
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


}
