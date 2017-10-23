package com.av.game.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Record {
    private static int high_score = 0;

    public static int getHighScore() {
        if(high_score == 0) {
            //Get stored value
            Preferences prefs = Gdx.app.getPreferences("My Preferences");
            high_score = prefs.getInteger("score", 0);
        }
        return high_score;
    }

    public static void setHighScore(int score) {
        //Store new value
        Preferences prefs = Gdx.app.getPreferences("My Preferences");
        prefs.putInteger("score", score);
        prefs.flush();

        //Set variable high score
        high_score = score;
    }
}
