package com.example.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.audio_color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,
                R.raw.audio_color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,
                R.raw.audio_color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.audio_color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.audio_color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.audio_color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.audio_color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.audio_color_white));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);


        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view,int position,long l) {
                releaseMediaPlayer();
                Word word = words.get(position);

                mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}