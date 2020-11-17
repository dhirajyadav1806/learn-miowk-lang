package com.jeeduniya.miwokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    /**
     * Handles playback of all the sound files
     */
    private MediaPlayer mMediaPlayer;


    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

//        //create an array of words using array data structure
//        String[] words = new String[10];
//        words[0] = "one";
//        words[1] = "two";
//        words[2] = "three";
//        words[3] = "four";
//        words[4] = "five";
//        words[5] = "six";
//        words[6] = "seven";
//        words[7] = "eight";
//        words[8] = "nine";
//        words[9] = "ten";
//
//        // Verify the contents of the list by printing out each element to the logs
//        for (int i = 0; i <= 9; i++) {
//            Log.i("NumbersActivity", "Word at index " + i + ": " + words[i]);
//        }


//        // Create a list of words using arrayList data structure
//        ArrayList<String> words = new ArrayList<String>();
//        words.add("one");
//        words.add("two");
//        words.add("three");
//        words.add("four");
//        words.add("five");
//        words.add("six");
//        words.add("seven");
//        words.add("eight");
//        words.add("nine");
//        words.add("ten");

//        // Verify the contents of the list by printing out each element to the logs
//        for (int i = 0; i <= 9; i++) {
//            Log.i("NumbersActivity", "Word at index " + i + ": " + words.get(i));
//        }

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        int index = 0;
//
//        TextView wordView = new TextView(this);
//        wordView.setText(words.get(index));
//        rootView.addView(wordView);
//
//        index += 1;
//
//        TextView wordView2 = new TextView(this);
//        wordView2.setText(words.get(index));
//        rootView.addView(wordView2);
//
//        index += 1;
//
//        TextView wordView3 = new TextView(this);
//        wordView3.setText(words.get(index));
//        rootView.addView(wordView3);

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        int index = 0;
//
//        while (index < words.size()) {
//            TextView wordView = new TextView(this);
//            wordView.setText(words.get(index));
//
//            //set margins for TextView programmatically
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(50,50,10,10);
//            wordView.setLayoutParams(params);
//
//            rootView.addView(wordView);
//
//            // Increment the index variable by 1
//            index++;
//        }


//        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
//        // adapter knows how to create layouts for each item in the list, using the
//        // simple_list_item_1.xml layout resource defined in the Android framework.
//        // This list item layout contains a single {@link TextView}, which the adapter will set to
//        // display a single word.
//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);
//
//        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
//        // There should be a {@link ListView} with the view ID called list, which is declared in the
//        // activity_numbers.xml layout file.
//        GridView listView = (GridView) findViewById(R.id.list);
//
//        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
//        // {@link ListView} will display list items for each word in the list of words.
//        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
//        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
//        listView.setAdapter(itemsAdapter);


        final ArrayList<Word> words = new ArrayList<>();
        Word w1 = new Word("one", "lutti", R.drawable.number_one, R.raw.number_one);
        words.add(w1);

        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(itemsAdapter);

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);


//      Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                /**
                 * this might happen if, for example, the user quickly tapped multiple list items in a row. The device mey not have enough time to finish playing
                 * each audio file, so the OnCompletionListener might not get triggered.
                 * So if we're in the middle of playing a sound and the user clicks on a completely different sound, then we need to stop and release the media
                 * player resources and immediately create a new media player object for the current sound
                 */
                // Release the media player if it currently exists because we are about to play a different sound file
                releaseMediaPlayer();

                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated with the current word
                //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.sound_file_1);
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());//getAudioResourceId() is a method in our Word class that we've created

                // Start the audio file
                mMediaPlayer.start();// mediaPlayer.start(); // no need to call prepare(); create() does that for you

                // Setup a listener on the media player, so that we can stop and release the media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);/** instead of creating a new OnClickListener every time we click on a list item we can
                 *  just create a single instance of this OnClickListener and then reuse it each time. so
                 *   doing it that way would be more efficient because we wouldn't be creating a new
                 *    object each time that would take up new resources.
                 */
            }
        });
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that setting the media player to null is an easy way to tell that the media player is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();//Now here instead of just calling the release() method directly on the MediaPlayer object(mMediaPlayer.release()), we'r calling the
                             //releaseMediaPlayer() helper method that we added earlier. the reason that we use the releaseMediaPlayer() method instead of just the
                             //release() method is that this one(releaseMediaPlayer() helper method) has some actual logic about setting the media player variable
                             //back to null if it's not currently configured to play a sound file.
        //mMediaPlayer.release();
    }
}