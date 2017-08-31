package com.av.game;

import android.os.Bundle;

import com.av.game.input.Input;
import com.av.game.screen.ads.ActionResolver;
import com.av.game.screen.ads.Device;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {
    private static String TAG = "AndroidLauncher";
    protected InterstitialAd interstitialAd;

    private static final String AD_UNIT_ID_INTERSTITIAL = "ca-app-pub-3019234278401415/7181475663";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //Set static params
		Input.gui_handler = new GUIHandler();
		Input.game_handler = new HeliHandler();
        Device.showAndroidAd = true;
        Device.app = this;

        //Initialize activity
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new HeliGame(), config);

        //load interstitialAd
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(AD_UNIT_ID_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }
        });
	}

	@Override
    public void showOrLoadInterstital() {
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (interstitialAd.isLoaded()) {
                        interstitialAd.show();
                        Gdx.app.log(TAG, "Intersticial ad show");
                    } else {
                        AdRequest interstitialRequest = new AdRequest.Builder().addTestDevice("A6A33940F3292A129C15147E0A80FDBB").build();
                        interstitialAd.loadAd(interstitialRequest);
                        Gdx.app.log(TAG, "Intersticial ad load");
                    }
                }
            });
        } catch (Exception ignore) {
        }
    }
}
