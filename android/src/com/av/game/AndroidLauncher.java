package com.av.game;

import android.os.Bundle;

import com.av.game.input.Input;
import com.av.game.screen.ads.ActionResolver;
import com.av.game.screen.ads.Device;
import com.av.game.screen.screen.AdScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class AndroidLauncher extends AndroidApplication implements ActionResolver {
    private static String TAG = "AndroidLauncher";
    protected RewardedVideoAd ad;
    private AdListener adListener;

    private static final String AD_UNIT_ID = "ca-app-pub-3019234278401415/5229690625";

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
        ad = MobileAds.getRewardedVideoAdInstance(this);
        adListener = new AdListener(ad);
        ad.setRewardedVideoAdListener(adListener);
	}

	@Override
    public void showOrLoadInterstital(AdScreen screen) {
        adListener.setMenu(screen);
        try {
            runOnUiThread(new Runnable() {
                public void run() {
                    if (ad.isLoaded()) {
                        ad.show();
                        Gdx.app.log(TAG, "Intersticial ad show");
                    } else {
                        ad.loadAd(AD_UNIT_ID, new AdRequest.Builder().addTestDevice("A6A33940F3292A129C15147E0A80FDBB").build());
                        //AdRequest interstitialRequest = new AdRequest.Builder().addTestDevice("A6A33940F3292A129C15147E0A80FDBB").build();
                        //ad.loadAd(interstitialRequest);
                        Gdx.app.log(TAG, "Intersticial ad load");
                    }
                }
            });
        } catch (Exception ignore) {
        }
    }
}
