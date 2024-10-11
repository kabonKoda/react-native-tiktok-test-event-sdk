package com.reactnativetiktoktesteventsdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.tiktok.TikTokBusinessSdk;
import com.tiktok.TikTokBusinessSdk.TTConfig;
import com.tiktok.appevents.TTBaseEvent;

import org.json.JSONObject;

public class TikTokTestEventSDKModule extends ReactContextBaseJavaModule {

    public TikTokTestEventSDKModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TikTokTestEventSDK";
    }

    @ReactMethod
    public void initialize(String appId, String tiktokAppId, boolean debug, Promise promise) {
        try {
            TTConfig ttConfig = new TTConfig(getReactApplicationContext())
                    .setAppId(appId)
                    .setTTAppId(tiktokAppId);

            if (debug) {
                ttConfig.openDebugMode()
                        .setLogLevel(TikTokBusinessSdk.LogLevel.DEBUG);
            }

            TikTokBusinessSdk.initializeSdk(ttConfig);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("INITIALIZATION_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void trackEvent(String eventName, String eventId, ReadableMap properties, Promise promise) {
        try {
            TTBaseEvent.Builder eventBuilder = TTBaseEvent.newBuilder(eventName);
            
            if (eventId != null && !eventId.isEmpty()) {
                eventBuilder.setEventID(eventId);
            }

            if (properties != null) {
                JSONObject jsonProperties = new JSONObject(properties.toHashMap());
                for (Iterator<String> it = jsonProperties.keys(); it.hasNext(); ) {
                    String key = it.next();
                    eventBuilder.addProperty(key, jsonProperties.getString(key));
                }
            }

            TikTokBusinessSdk.trackTTEvent(eventBuilder.build());
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("TRACK_EVENT_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void identify(String externalId, String externalUsername, String phoneNumber, String email, Promise promise) {
        try {
            TikTokBusinessSdk.identify(externalId, externalUsername, phoneNumber, email);
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("IDENTIFY_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void logout(Promise promise) {
        try {
            TikTokBusinessSdk.logout();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("LOGOUT_ERROR", e.getMessage());
        }
    }

    @ReactMethod
    public void startTrack(Promise promise) {
        try {
            TikTokBusinessSdk.startTrack();
            promise.resolve(null);
        } catch (Exception e) {
            promise.reject("START_TRACK_ERROR", e.getMessage());
        }
    }
}