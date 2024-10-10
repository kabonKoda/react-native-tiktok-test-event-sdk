// File: TikTokModule.js

import { NativeModules } from 'react-native';

const { TikTokModule } = NativeModules;

export default {
  initializeSdk: (appId, ttAppId, debugMode = false) => {
    TikTokModule.initializeSdk(appId, ttAppId, debugMode);
  },
  
  trackEvent: (eventName) => {
    TikTokModule.trackEvent(eventName);
  },
  
  trackEventWithId: (eventName, eventId) => {
    TikTokModule.trackEventWithId(eventName, eventId);
  },
  
  trackCustomEvent: (eventName, properties) => {
    TikTokModule.trackCustomEvent(eventName, properties);
  },
  
  identifyUser: (externalId, externalUserName, phoneNumber, email) => {
    TikTokModule.identifyUser(externalId, externalUserName, phoneNumber, email);
  },
  
  logoutUser: () => {
    TikTokModule.logoutUser();
  },
  
  enableAutoIapTracking: (appId, ttAppId) => {
    TikTokModule.enableAutoIapTracking(appId, ttAppId);
  },
  
  // Add more methods as needed
};
