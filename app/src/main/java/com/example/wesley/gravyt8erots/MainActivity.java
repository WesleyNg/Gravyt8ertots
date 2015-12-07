package com.example.wesley.gravyt8erots;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DBHandler appDB;
    String[] stories;
    Button saveBtn;
    EditText storyTitle;
    Boolean isCreate;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDB = DBHandler.getInstance(this);

        Context context = this;
        SharedPreferences sharePref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String defaultTimeEntry = "true";
        String firstTime = sharePref.getString(getString(R.string.first_time_key),defaultTimeEntry);

        if(firstTime.equalsIgnoreCase("true")){

            appDB.addStory(new StoryClass("Harry Potter","046","2131492993","2131492997"));
            appDB.addStory(new StoryClass("Wowl Witer: The Biwogwaphy", "12", "2131492992", "2131492998"));

            SharedPreferences.Editor editor = sharePref.edit();
            editor.putString(getString(R.string.first_time_key), "false");
            editor.commit();
        }

        findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);

        if (findViewById(R.id.story_fragment_id) != null) {
            Bundle bundle = new Bundle();
            bundle.putString("titleKey","");
            StoryFragment storyFragment = new StoryFragment();
            storyFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.story_fragment_id, storyFragment).commit();
        }


        populateListView();
        registerClickCallback();
    }

    private void populateListView() {

        List<StoryClass> storyList = appDB.getAllStories();
        int storyCount = appDB.getStoryCount();
        //String[] stories = {"Wicked", "Cyborg Book", "Harry Potter"};
        stories = new String[storyCount];

        int i = 0;
        for (StoryClass s : storyList){
            stories[i] = s.getTitle();
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.story_items, stories);

        ListView list = (ListView) findViewById(R.id.storyList);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.storyList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE) {
                    findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
                }

                TextView textView = (TextView) viewClicked;

                String ageG = "2131492993";
                String classi = "2131492998";

                List<StoryClass> storyList = appDB.getAllStories();
                for (StoryClass s : storyList) {
                    String storyT = s.getTitle();
                    if (textView.getText().toString().equalsIgnoreCase(storyT)) {
                        ageG = s.getAge();
                        classi = s.getClassi();
                    }
                }

                if (findViewById(R.id.story_fragment_id) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("titleKey", textView.getText().toString());
                    bundle.putString("ageKey", ageG);
                    bundle.putString("classiKey", classi);
                    StoryFragment oldFrag = new StoryFragment();
                    oldFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, oldFrag).commit();
                }
            }
        });
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.AddButton:

                if (findViewById(R.id.story_fragment_id) != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("titleKey","");
                    StoryFragment newFrag = new StoryFragment();
                    newFrag.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.story_fragment_id, newFrag).commit();
                }
                showView();
                break;
            case R.id.action_button:
                storyAttributes();
                break;
            case R.id.bio_button:
                bioG();
                break;
            case R.id.comedy_button:
                comedyG();
                break;
            case R.id.drama_button:
                dramaG();
                break;
            case R.id.fantasy_button:
                fantasyG();
                break;
            case R.id.horror_button:
                horrorG();
                break;
            case R.id.history_button:
                historyG();
                break;
            case R.id.mystery_button:
                mysteryG();
                break;
            case R.id.romance_button:
                romanceG();
                break;
            case R.id.scifi_button:
                scifiG();
                break;
            case R.id.thriller_button:
                thrillerG();
                break;
        }
    }

    public void showView()
    {
        if (findViewById(R.id.story_fragment_id).getVisibility() == View.INVISIBLE)
        {
            findViewById(R.id.story_fragment_id).setVisibility(View.VISIBLE);
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

    public void bioG(){
        if (Integer.parseInt(findViewById(R.id.bio_button).getTag().toString()) == 1)
        {
            findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio_selected);
            findViewById(R.id.bio_button).setTag(2);
        }
        else
        {
            findViewById(R.id.bio_button).setBackgroundResource(R.drawable.bio);
            findViewById(R.id.bio_button).setTag(1);
        }

    }

    public void comedyG(){
        if (Integer.parseInt(findViewById(R.id.comedy_button).getTag().toString()) == 1)
        {
            findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor_selected);
            findViewById(R.id.comedy_button).setTag(2);
        }
        else
        {
            findViewById(R.id.comedy_button).setBackgroundResource(R.drawable.humor);
            findViewById(R.id.comedy_button).setTag(1);
        }

    }

    public void dramaG(){
        if (Integer.parseInt(findViewById(R.id.drama_button).getTag().toString()) == 1)
        {
            findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama_selected);
            findViewById(R.id.drama_button).setTag(2);
        }
        else
        {
            findViewById(R.id.drama_button).setBackgroundResource(R.drawable.drama);
            findViewById(R.id.drama_button).setTag(1);
        }

    }

    public void fantasyG(){
        if (Integer.parseInt(findViewById(R.id.fantasy_button).getTag().toString()) == 1)
        {
            findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy_selected);
            findViewById(R.id.fantasy_button).setTag(2);
        }
        else
        {
            findViewById(R.id.fantasy_button).setBackgroundResource(R.drawable.fantasy);
            findViewById(R.id.fantasy_button).setTag(1);
        }

    }

    public void horrorG(){
        if (Integer.parseInt(findViewById(R.id.horror_button).getTag().toString()) == 1)
        {
            findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror_selected);
            findViewById(R.id.horror_button).setTag(2);
        }
        else
        {
            findViewById(R.id.horror_button).setBackgroundResource(R.drawable.horror);
            findViewById(R.id.horror_button).setTag(1);
        }

    }

    public void historyG(){
        if (Integer.parseInt(findViewById(R.id.history_button).getTag().toString()) == 1)
        {
            findViewById(R.id.history_button).setBackgroundResource(R.drawable.history_selected);
            findViewById(R.id.history_button).setTag(2);
        }
        else
        {
            findViewById(R.id.history_button).setBackgroundResource(R.drawable.history);
            findViewById(R.id.history_button).setTag(1);
        }

    }
    public void mysteryG(){
        if (Integer.parseInt(findViewById(R.id.mystery_button).getTag().toString()) == 1)
        {
            findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery_selected);
            findViewById(R.id.mystery_button).setTag(2);
        }
        else
        {
            findViewById(R.id.mystery_button).setBackgroundResource(R.drawable.mystery);
            findViewById(R.id.mystery_button).setTag(1);
        }

    }
    public void romanceG(){
        if (Integer.parseInt(findViewById(R.id.romance_button).getTag().toString()) == 1)
        {
            findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance_selected);
            findViewById(R.id.romance_button).setTag(2);
        }
        else
        {
            findViewById(R.id.romance_button).setBackgroundResource(R.drawable.romance);
            findViewById(R.id.romance_button).setTag(1);
        }

    }
    public void scifiG(){
        if (Integer.parseInt(findViewById(R.id.scifi_button).getTag().toString()) == 1)
        {
            findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi_selected);
            findViewById(R.id.scifi_button).setTag(2);
        }
        else
        {
            findViewById(R.id.scifi_button).setBackgroundResource(R.drawable.scifi);
            findViewById(R.id.scifi_button).setTag(1);
        }

    }

    public void thrillerG(){
        if (Integer.parseInt(findViewById(R.id.thriller_button).getTag().toString()) == 1)
        {
            findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller_selected);
            findViewById(R.id.thriller_button).setTag(2);
        }
        else
        {
            findViewById(R.id.thriller_button).setBackgroundResource(R.drawable.thriller);
            findViewById(R.id.thriller_button).setTag(1);
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


    public void closeEditor(View view)
    {
        findViewById(R.id.story_fragment_id).setVisibility(View.INVISIBLE);
    }
}
