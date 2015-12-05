package com.example.wesley.gravyt8erots;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Blake Martin on 11/30/2015.
 *
 */
public class StoryFragment extends Fragment {
    Button saveBtn;
    EditText storyTitle;
    RadioGroup ageGroup;
    RadioButton ageBtn;
    RadioGroup classiGroup;
    RadioButton classiBtn;
    DBHandler appDB = DBHandler.getInstance(getContext());
    String[] stories;
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        View storyview = inflater.inflate(R.layout.story_fragment,
                container, false);

        storyTitle = (EditText) storyview.findViewById(R.id.new_title_edit_text);
        saveBtn = (Button) storyview.findViewById(R.id.storySaveBtn);
        storyTitle = (EditText) storyview.findViewById(R.id.new_title_edit_text);
        ageGroup = (RadioGroup) storyview.findViewById(R.id.age_radio_group);
        classiGroup = (RadioGroup) storyview.findViewById(R.id.classification_radio_group);

        String titleTxt = getArguments().getString("titleKey");
        storyTitle.setText(titleTxt);

        if(titleTxt.equalsIgnoreCase("")){
            saveBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String ageStr = getAge();
                    String classiStr = getClassi();
                    String newTitle = storyTitle.getText().toString();
                    appDB.addStory(new StoryClass(newTitle, "0", ageStr, classiStr));
                    populateListView();
                }
            });
        }
        else{
            String ageIdNum = getArguments().getString("ageKey");
            int ageInt=R.id.age_radioButton;

            if (ageIdNum.equalsIgnoreCase("2131492992")){
                ageInt = R.id.age_radioButton;
            }
            else if(ageIdNum.equalsIgnoreCase("2131492993")){
                ageInt = R.id.age_radioButton2;
            }
            else if(ageIdNum.equalsIgnoreCase("2131492994")){
                ageInt = R.id.age_radioButton3;
            }

            String classiIdNum = getArguments().getString("classiKey");
            int classiInt = R.id.classification_radioButton;

            if(classiIdNum.equalsIgnoreCase("2131492997")){
                classiInt = R.id.classification_radioButton;
            }
            else if(classiIdNum.equalsIgnoreCase("2131492998")){
                classiInt = R.id.classification_radioButton2;
            }

            ageGroup.check(ageInt);
            classiGroup.check(classiInt);
        }

        return storyview;
    }

    public String getAge(){
        ageGroup = (RadioGroup) getView().findViewById(R.id.age_radio_group);
        int selectedAge = ageGroup.getCheckedRadioButtonId();
        String ageStr = Integer.toString(selectedAge);
        return ageStr;
    }

    public String getClassi(){
        classiGroup = (RadioGroup) getView().findViewById(R.id.classification_radio_group);
        int selectedClassi = classiGroup.getCheckedRadioButtonId();
        classiBtn = (RadioButton) getView().findViewById(selectedClassi);
        Log.v("taggy",Integer.toString(selectedClassi));
        String classiStr = classiBtn.getText().toString();
        return classiStr;
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.story_items, stories);

        ListView list = (ListView) getActivity().findViewById(R.id.storyList);
        list.setAdapter(adapter);
    }
}
