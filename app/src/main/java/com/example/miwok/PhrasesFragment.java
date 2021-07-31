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

public class PhrasesFragment extends Fragment {
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
        words.add(new Word("Where are you going?", "minto wuksus",
                R.raw.audio_phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",
                R.raw.audio_phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.audio_phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.audio_phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.audio_phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.audio_phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.audio_phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.audio_phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.audio_phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.audio_phrase_come_here));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_phrases);


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
