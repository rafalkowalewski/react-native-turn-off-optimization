package com.reactlibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNTurnOffOptimizationModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNTurnOffOptimizationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNTurnOffOptimization";
    }

    @ReactMethod
    public void isOptimizationTurnedOn(Promise promise) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager pm = (PowerManager) reactContext.getSystemService(reactContext.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(reactContext.getPackageName())) {
                promise.resolve(true);
                return;
            }
        }
        promise.resolve(false);
    }

    @ReactMethod
    public void openTurnOffOptimizationModal() {
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        i.setData(Uri.parse("package:" + reactContext.getPackageName()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        reactContext.startActivity(i);
    }
}