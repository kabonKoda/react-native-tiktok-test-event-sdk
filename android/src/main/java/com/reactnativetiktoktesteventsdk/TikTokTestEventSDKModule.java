package com.reactnativetiktoktesteventsdk;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.tiktok.TiktokBusinessSdk;
import com.tiktok.TiktokBusinessSdk.TTConfig;
import com.tiktok.TiktokBusinessSdk.TTInitCallback;
import com.tiktok.TiktokBusinessSdk.EventName;
import com.tiktok.TiktokBusinessSdk.TTBaseEvent;
import com.tiktok.TiktokBusinessSdk.TTAddToCartEvent;
import com.tiktok.TiktokBusinessSdk.TTContentParams;

import java.util.HashMap;
import java.util.Map;

public class TikTokModule extends ReactContextBaseJavaModule {

    private static final String MODULE_NAME = "TikTokTestEventSDK";

    public TikTokModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    /**
     * Initialize the TikTok SDK
     *
     * @param appId      Your Android package name or iOS listing ID
     * @param ttAppId    Your TikTok App ID from Events Manager
     * @param debugMode  Enable debug mode (optional)
     */
    @ReactMethod
    public void initializeSdk(String appId, String ttAppId, boolean debugMode) {
        Context context = getReactApplicationContext().getApplicationContext();
        TTConfig ttConfig = new TTConfig(context)
                .setAppId(appId)
                .setTTAppId(ttAppId);

        if (debugMode) {
            ttConfig.openDebugMode()
                    .setLogLevel(TiktokBusinessSdk.LogLevel.DEBUG);
        }

        TiktokBusinessSdk.initializeSdk(ttConfig, new TTInitCallback() {
            @Override
            public void success() {
                // Initialization successful
                // You can send an event or log if needed
            }

            @Override
            public void fail(int code, String msg) {
                // Initialization failed
                // Handle failure
            }
        });

        // Start tracking after initialization
        TiktokBusinessSdk.startTrack();
    }

    /**
     * Track a standard event without properties
     *
     * @param eventName Name of the event to track
     */
    @ReactMethod
    public void trackEvent(String eventName) {
        TiktokBusinessSdk.trackTTEvent(eventName);
    }

    /**
     * Track a standard event with an event ID
     *
     * @param eventName Name of the event to track
     * @param eventId   ID of the event
     */
    @ReactMethod
    public void trackEventWithId(String eventName, String eventId) {
        TiktokBusinessSdk.trackTTEvent(eventName, eventId);
    }

    /**
     * Track a custom event with properties
     *
     * @param eventName Name of the custom event
     * @param properties Map of properties to include
     */
    @ReactMethod
    public void trackCustomEvent(String eventName, Map<String, Object> properties) {
        TTBaseEvent event = TTBaseEvent.newBuilder(eventName);
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            event.addProperty(entry.getKey(), entry.getValue().toString());
        }
        TiktokBusinessSdk.trackTTEvent(event);
    }

    /**
     * Identify user with external IDs
     *
     * @param externalId       Unique advertiser ID
     * @param externalUserName Username from advertiser
     * @param phoneNumber      User's phone number in E.164 format
     * @param email            User's email
     */
    @ReactMethod
    public void identifyUser(String externalId, String externalUserName, String phoneNumber, String email) {
        TiktokBusinessSdk.identify(externalId, externalUserName, phoneNumber, email);
    }

    /**
     * Logout user
     */
    @ReactMethod
    public void logoutUser() {
        TiktokBusinessSdk.logout();
    }

    /**
     * Enable Auto IAP Tracking
     *
     * @param appId   Your Android package name
     * @param ttAppId Your TikTok App ID
     */
    @ReactMethod
    public void enableAutoIapTracking(String appId, String ttAppId) {
        Context context = getReactApplicationContext().getApplicationContext();
        TTConfig ttConfig = new TTConfig(context)
                .setAppId(appId)
                .setTTAppId(ttAppId)
                .enableAutoIapTrack();

        TiktokBusinessSdk.initializeSdk(ttConfig);
    }

    // Add more methods as needed based on the SDK functionalities
}
