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

public class FamilyFragment extends Fragment {

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
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.audio_family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.audio_family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.audio_family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.audio_family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,
                R.raw.audio_family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,
                R.raw.audio_family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister,
                R.raw.audio_family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister,
                R.raw.audio_family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother,
                R.raw.audio_family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather,
                R.raw.audio_family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);


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