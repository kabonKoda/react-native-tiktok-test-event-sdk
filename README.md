# React Native TikTok Test Event SDK

This library provides a React Native wrapper for the TikTok Test Event SDK, allowing you to easily integrate TikTok's event tracking functionality into your React Native applications.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
  - [Android Configuration](#android-configuration)
- [Usage](#usage)
  - [Initializing the SDK](#initializing-the-sdk)
  - [Tracking Events](#tracking-events)
  - [User Identification](#user-identification)
  - [Logging Out](#logging-out)
  - [Starting Tracking](#starting-tracking)
- [API Reference](#api-reference)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Installation

```bash
npm install react-native-tiktok-test-event-sdk
# or
yarn add react-native-tiktok-test-event-sdk
```

## Configuration

### Android Configuration

1. Add the following to your project's `android/build.gradle`:

```gradle
allprojects {
    repositories {
        // ... other repositories
        maven { url 'https://jitpack.io' }
    }
}
```

2. Ensure your `android/app/build.gradle` includes the following:

```gradle
dependencies {
    // ... other dependencies
    implementation 'com.github.tiktok:tiktok-business-android-sdk:1.3.6'
    implementation 'androidx.lifecycle:lifecycle-process:2.3.1'
    implementation 'androidx.lifecycle:lifecycle-common-java8:2.3.1'
}
```

3. Add the following permissions to your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="com.google.android.gms.permission.AD_ID" />
```

## Usage

### Initializing the SDK

```javascript
import TikTokTestEventSDK from "react-native-tiktok-test-event-sdk";

// In your app's initialization code
TikTokTestEventSDK.initialize("YOUR_APP_ID", "YOUR_TIKTOK_APP_ID", __DEV__)
  .then(() => console.log("TikTok Test Event SDK initialized"))
  .catch((error) =>
    console.error("Failed to initialize TikTok Test Event SDK", error)
  );
```

### Tracking Events

```javascript
TikTokTestEventSDK.trackEvent("button_click", "unique_event_id", {
  buttonName: "submit",
})
  .then(() => console.log("Event tracked"))
  .catch((error) => console.error("Failed to track event", error));
```

### User Identification

```javascript
TikTokTestEventSDK.identify(
  "external_id",
  "username",
  "phone_number",
  "email@example.com"
)
  .then(() => console.log("User identified"))
  .catch((error) => console.error("Failed to identify user", error));
```

### Logging Out

```javascript
TikTokTestEventSDK.logout()
  .then(() => console.log("User logged out"))
  .catch((error) => console.error("Failed to log out user", error));
```

### Starting Tracking

```javascript
TikTokTestEventSDK.startTrack()
  .then(() => console.log("Tracking started"))
  .catch((error) => console.error("Failed to start tracking", error));
```

## API Reference

### `initialize(appId: string, tiktokAppId: string, debug: boolean): Promise<void>`

Initializes the TikTok Test Event SDK.

### `trackEvent(eventName: string, eventId: string | null, properties: object | null): Promise<void>`

Tracks a custom event.

### `identify(externalId: string | null, externalUsername: string | null, phoneNumber: string | null, email: string | null): Promise<void>`

Identifies a user with the provided information.

### `logout(): Promise<void>`

Logs out the current user.

### `startTrack(): Promise<void>`

Starts tracking events.

## Troubleshooting

If you encounter any issues, please ensure that:

1. You have correctly added the TikTok SDK dependencies to your `build.gradle` file.
2. You have added the necessary permissions to your `AndroidManifest.xml`.
3. You have initialized the SDK before calling any other methods.

If problems persist, please open an issue on the GitHub repository.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
