
import { NativeModules, Platform } from 'react-native';

const { TikTokTestEventSDK } = NativeModules;

class TikTokTestEventSDKModule {
  static initialize(appId, tiktokAppId, debug = false) {
    if (Platform.OS === 'android') {
      return TikTokTestEventSDK.initialize(appId, tiktokAppId, debug);
    }
    return Promise.reject('TikTok Test Event SDK is only supported on Android');
  }

  static trackEvent(eventName, eventId = null, properties = null) {
    if (Platform.OS === 'android') {
      return TikTokTestEventSDK.trackEvent(eventName, eventId, properties);
    }
    return Promise.reject('TikTok Test Event SDK is only supported on Android');
  }

  static identify(externalId, externalUsername, phoneNumber, email) {
    if (Platform.OS === 'android') {
      return TikTokTestEventSDK.identify(externalId, externalUsername, phoneNumber, email);
    }
    return Promise.reject('TikTok Test Event SDK is only supported on Android');
  }

  static logout() {
    if (Platform.OS === 'android') {
      return TikTokTestEventSDK.logout();
    }
    return Promise.reject('TikTok Test Event SDK is only supported on Android');
  }

  static startTrack() {
    if (Platform.OS === 'android') {
      return TikTokTestEventSDK.startTrack();
    }
    return Promise.reject('TikTok Test Event SDK is only supported on Android');
  }
}

export default TikTokTestEventSDKModule;