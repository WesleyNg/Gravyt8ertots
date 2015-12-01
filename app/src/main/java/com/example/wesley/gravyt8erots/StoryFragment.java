package com.example.wesley.gravyt8erots;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Blake Martin on 11/30/2015.
 */
public class StoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View storyview = inflater.inflate(R.layout.story_fragment,
                container, false);

        return storyview;
    }
}
