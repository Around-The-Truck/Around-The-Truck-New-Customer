package kr.co.aroundthetruck.customer.layoutController;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by sehonoh on 14. 11. 30..
 */
public class AroundTheTruckApplication extends Application {
    private static final String TAG = AroundTheTruckApplication.class.getSimpleName();
    public static Typeface nanumGothicLight, nanumGothic, nanumGothicBold;

    @Override
    public void onCreate() {
        super.onCreate();

        nanumGothicLight = Typeface.createFromAsset(getAssets(), "NanumBarunGothicLight.otf");
        nanumGothic = Typeface.createFromAsset(getAssets(), "NanumBarunGothic.otf");
        nanumGothicBold = Typeface.createFromAsset(getAssets(), "NanumBarunGothicBold.otf");
    }
}