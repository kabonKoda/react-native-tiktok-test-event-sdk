# React Native TikTok Test Event SDK

This library provides a React Native wrapper for the TikTok App Events SDK, enabling seamless integration of TikTok's event tracking functionality into your React Native applications.

**Important:** Use of the TikTok App Events SDK is governed by the [TikTok For Business Commercial Terms of Service](https://ads.tiktok.com/help/article?aid=10003985). By using the TikTok App Events SDK, you also agree to the [TikTok Business Products (Data) Terms](https://ads.tiktok.com/help/article?aid=10004727) and that you will not share sensitive data with TikTok.

## Table of Contents

- [Installation](#installation)
- [Configuration](#configuration)
  - [Android Configuration](#android-configuration)
- [Usage](#usage)
  - [Initializing the SDK](#initializing-the-sdk)
  - [Tracking Events](#tracking-events)
    - [Tracking Standard Events](#tracking-standard-events)
    - [Tracking Custom Events](#tracking-custom-events)
  - [User Identification](#user-identification)
  - [Logging Out](#logging-out)
  - [Starting Tracking](#starting-tracking)
  - [Enabling Auto IAP Tracking](#enabling-auto-iap-tracking)
- [API Reference](#api-reference)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Installation

Install the library using npm or yarn:

```bash
npm install react-native-tiktok-test-event-sdk
# or
yarn add react-native-tiktok-test-event-sdk
```

## Configuration

### Android Configuration

1. **Add JitPack Repository**

   In your project's `android/build.gradle`, add the JitPack repository:

   ```gradle
   allprojects {
       repositories {
           // ... other repositories
           maven { url 'https://jitpack.io' }
       }
   }
   ```

2. **Add SDK Dependencies**

   In your app module's `android/app/build.gradle`, add the TikTok SDK dependencies:

   ```gradle
   android {
       // ... existing configurations

       compileOptions {
           sourceCompatibility JavaVersion.VERSION_1_8
           targetCompatibility JavaVersion.VERSION_1_8
       }
   }

   dependencies {
       // ... other dependencies
       implementation 'com.github.tiktok:tiktok-business-android-sdk:1.3.6'
       implementation 'androidx.lifecycle:lifecycle-process:2.3.1'
       implementation 'androidx.lifecycle:lifecycle-common-java8:2.3.1'
       implementation 'com.android.billingclient:billing:6.1.0' // If using Auto IAP Tracking
   }
   ```

3. **Add Permissions**

   Add the following permissions to your `android/app/src/main/AndroidManifest.xml`:

   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   <uses-permission android:name="com.google.android.gms.permission.AD_ID" />
   ```

4. **Proguard Configuration (Optional)**

   If you are using Proguard for code obfuscation, add the following rules to your `proguard-rules.pro` file to prevent obfuscation of the TikTok SDK classes:

   ```proguard
   -keep class com.tiktok.** { *; }
   -keep class com.android.billingclient.api.** { *; }
   ```

   Ensure that Proguard is enabled in your `build.gradle`:

   ```gradle
   android {
       // ... existing configurations
       buildTypes {
           release {
               // ... existing configurations
               minifyEnabled true
               proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
           }
       }
   }
   ```

## Usage

### Initializing the SDK

Initialize the TikTok App Events SDK in your app's initialization code (e.g., in the main component or a dedicated initialization script).

```javascript
import TikTokTestEventSDK from "react-native-tiktok-test-event-sdk";

// In your app's initialization code
TikTokTestEventSDK.initialize("YOUR_APP_ID", "YOUR_TIKTOK_APP_ID", __DEV__)
  .then(() => console.log("TikTok Test Event SDK initialized"))
  .catch((error) =>
    console.error("Failed to initialize TikTok Test Event SDK", error)
  );
```

**Parameters:**

- `appId`: Your Android package name (e.g., `com.sample.app`).
- `ttAppId`: Your TikTok App ID from TikTok Events Manager.
- `debug`: A boolean indicating whether to initialize the SDK in debug mode (`__DEV__` is typically `true` in development).

### Tracking Events

#### Tracking Standard Events

To track standard events predefined by the TikTok SDK:

```javascript
TikTokTestEventSDK.trackEvent("ACHIEVE_LEVEL")
  .then(() => console.log("Standard event tracked"))
  .catch((error) => console.error("Failed to track standard event", error));

// Tracking with Event ID
TikTokTestEventSDK.trackEventWithId("ACHIEVE_LEVEL", "unique_event_id")
  .then(() => console.log("Standard event with ID tracked"))
  .catch((error) =>
    console.error("Failed to track standard event with ID", error)
  );
```

#### Tracking Custom Events

To track custom events with additional properties:

```javascript
TikTokTestEventSDK.trackCustomEvent("custom_event_name", {
  currency: "USD",
  value: "99.99",
  content_id: "item123",
  content_type: "product",
  price: "99.99",
  quantity: "1",
})
  .then(() => console.log("Custom event tracked"))
  .catch((error) => console.error("Failed to track custom event", error));
```

### User Identification

Identify a user with external IDs and contact information:

```javascript
TikTokTestEventSDK.identifyUser(
  "external_id",
  "username",
  "+15551234567",
  "email@example.com"
)
  .then(() => console.log("User identified"))
  .catch((error) => console.error("Failed to identify user", error));
```

**Parameters:**

- `externalId`: Unique advertiser ID (e.g., loyalty membership ID).
- `externalUsername`: Username from advertiser.
- `phoneNumber`: User's phone number in E.164 format (e.g., `+15551234567`).
- `email`: User's email address.

### Logging Out

Log out the current user:

```javascript
TikTokTestEventSDK.logoutUser()
  .then(() => console.log("User logged out"))
  .catch((error) => console.error("Failed to log out user", error));
```

### Starting Tracking

Start tracking events. This is typically called after initialization and after the user has agreed to data terms.

```javascript
TikTokTestEventSDK.startTrack()
  .then(() => console.log("Tracking started"))
  .catch((error) => console.error("Failed to start tracking", error));
```

### Enabling Auto IAP Tracking

Enable automatic in-app purchase tracking for Google Play purchases.

```javascript
TikTokTestEventSDK.enableAutoIapTracking("YOUR_APP_ID", "YOUR_TIKTOK_APP_ID")
  .then(() => console.log("Auto IAP Tracking enabled"))
  .catch((error) => console.error("Failed to enable Auto IAP Tracking", error));
```

**Note:** Ensure that you have properly integrated the Google Play Billing Library and handled purchase flows according to [Google's guidelines](https://developer.android.com/google/play/billing/migrate-gpblv6).

## API Reference

### `initialize(appId: string, ttAppId: string, debug: boolean): Promise<void>`

Initializes the TikTok App Events SDK.

**Parameters:**

- `appId`: Your Android package name (e.g., `com.sample.app`).
- `ttAppId`: Your TikTok App ID from TikTok Events Manager.
- `debug`: Enable debug mode (`true` for development, `false` for production).

### `trackEvent(eventName: string): Promise<void>`

Tracks a standard event without properties.

**Parameters:**

- `eventName`: Name of the standard event (e.g., `"ACHIEVE_LEVEL"`).

### `trackEventWithId(eventName: string, eventId: string): Promise<void>`

Tracks a standard event with an event ID.

**Parameters:**

- `eventName`: Name of the standard event.
- `eventId`: Unique identifier for the event.

### `trackCustomEvent(eventName: string, properties: object): Promise<void>`

Tracks a custom event with additional properties.

**Parameters:**

- `eventName`: Name of the custom event.
- `properties`: An object containing key-value pairs of event properties.

### `identifyUser(externalId: string, externalUsername: string, phoneNumber: string, email: string): Promise<void>`

Identifies a user with the provided information.

**Parameters:**

- `externalId`: Unique advertiser ID.
- `externalUsername`: Username from advertiser.
- `phoneNumber`: User's phone number in E.164 format.
- `email`: User's email address.

### `logoutUser(): Promise<void>`

Logs out the current user.

### `startTrack(): Promise<void>`

Starts tracking events. Events are sent to TikTok after this method is called.

### `enableAutoIapTracking(appId: string, ttAppId: string): Promise<void>`

Enables automatic in-app purchase tracking for Google Play purchases.

**Parameters:**

- `appId`: Your Android package name.
- `ttAppId`: Your TikTok App ID.

## Troubleshooting

If you encounter any issues, please ensure that:

1. **SDK Dependencies**: You have correctly added the TikTok SDK dependencies to your `build.gradle` files.

2. **Permissions**: You have added the necessary permissions to your `AndroidManifest.xml`.

3. **Initialization**: You have initialized the SDK before calling any other methods.

4. **Proguard**: If using Proguard, ensure the Proguard rules are correctly added to prevent obfuscation of TikTok SDK classes.

5. **Debug Mode**: Remember to disable debug mode (`debug: false`) in production to avoid marking events as test events.

If problems persist, please open an issue on the [GitHub repository](https://github.com/your-repo/react-native-tiktok-test-event-sdk).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Additional Notes

- **Multiple TikTok App IDs:** If you need to support multiple TikTok App IDs, modify the `initialize` method to accept a comma-separated string of App IDs as per the SDK documentation.

- **Deep Links:** The TikTok App Events SDK does not currently support deferred deep links. Ensure your app handles deep links according to your requirements.

- **Error Handling:** Enhance the native module methods with proper error handling and callbacks to communicate success or failure back to JavaScript as needed.

- **Auto IAP Reporting:** If you choose to enable Auto IAP Reporting, ensure that you have properly integrated the Google Play Billing Library and handled purchase flows according to [Google's guidelines](https://developer.android.com/google/play/billing/migrate-gpblv6).

By following this guide, you can effectively integrate the TikTok App Events SDK into your React Native Android application, enabling you to track and report various user interactions and events to TikTok for analytics and advertising purposes.
