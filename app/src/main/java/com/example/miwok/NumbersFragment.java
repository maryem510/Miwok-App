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

public class NumbersFragment extends Fragment {
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
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.audio_number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.audio_number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.audio_number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.audio_number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.audio_number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.audio_number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.audio_number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.audio_number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.audio_number_ten));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);


        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
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