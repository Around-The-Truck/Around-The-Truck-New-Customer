package kr.co.aroundthetruck.customer.layoutController;

import android.app.Application;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by sehonoh on 14. 11. 30..
 */
public class AroundTheTruckApplication extends Application {
    private static final String TAG = AroundTheTruckApplication.class.getSimpleName();
    public static Typeface nanumGothicLight, nanumGothic, nanumGothicBold;
    public static int color6d,color9a;

    @Override
    public void onCreate() {
        super.onCreate();



        color6d = Color.parseColor("#6d6d6d");
        color9a = Color.parseColor("#9a9a9a");
        nanumGothicLight = Typeface.createFromAsset(getAssets(), "NanumBarunGothicLight.otf");
        nanumGothic = Typeface.createFromAsset(getAssets(), "NanumBarunGothic.otf");
        nanumGothicBold = Typeface.createFromAsset(getAssets(), "NanumBarunGothicBold.otf");
    }
}