package com.av.game;

import com.av.game.screen.screen.AdScreen;
import com.badlogic.gdx.Gdx;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

class AdListener implements RewardedVideoAdListener {
    private RewardedVideoAd ad;
    private AdScreen screen;

    AdListener(RewardedVideoAd ad) {
        this.ad = ad;
    }

    void setMenu(AdScreen menu) {
        this.screen = menu;
    }


    @Override
    public void onRewardedVideoAdLoaded() {
        ad.show();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        screen.setRewardPlayer();
        Gdx.app.log("AdListener", rewardItem.toString());
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        screen.setAdLoadError();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        screen.setAdClosed();
    }


    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }
}
