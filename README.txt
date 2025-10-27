
# Travel Planner - Android Studio Project

This is a complete Android Studio Java project for a 5-screen Travel Planner app.

## 🚀 Quick Start
1. Unzip this project.
2. Open Android Studio → **File → Open** → select the unzipped folder.
3. When prompted, let Android Studio download Gradle if necessary.

## ⚙️ Gradle Wrapper Setup (for command line build)
The project is already configured to use Gradle Wrapper.
You only need to add one missing file: `gradle-wrapper.jar`

1. Go to this folder inside the project:
   ```
   gradle/wrapper/
   ```
2. Download the official wrapper JAR from Gradle’s GitHub:
   [https://services.gradle.org/distributions/gradle-7.5.1-bin.zip](https://services.gradle.org/distributions/gradle-7.5.1-bin.zip)
3. Inside that ZIP, extract the file:
   ```
   gradle/wrapper/gradle-wrapper.jar
   ```
   and place it in your project’s same `gradle/wrapper` directory.

Once done, you can run commands like:
```
./gradlew assembleDebug
```
or simply **Build & Run** from Android Studio.

✅ The app has:
- Login screen (user info)
- Destination screen
- Date picker screen
- Budget screen
- Trip summary screen
