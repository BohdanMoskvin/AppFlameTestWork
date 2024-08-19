#Test task Moskvin Bohdan

##  General information about the project

### Running tests from the terminal

### Local test run

```
After cloning the project to your local machine, please put the apk in src\test\resources\apk\ 
and name it apidemos.apk

Also, create the src\test\resources\configs and create emulator.properties and test.properties

```
### emulator.properties example

```
deviceName=Pixel 2 API 31  <code>specify your emulator</code>
platformName=android  <code>specify platformName</code>
version=12.0 <code>specify android version on the emulator</code>
appPackage=io.appium.android.apis
appActivity=io.appium.android.apis.ApiDemos
remoteURL=http://localhost:4723/wd/hub <code>specify remoteURL </code>
app=src/test/resources/apk/apidemos.apk <code>specify apk path</code>

```

### test.properties example

```
deviceHost=emulator <code>currently only emulator can be specified</code>

```

#### Local launch of tests using parameters from the property file test

```
gradle clean test
```

### Functionality of the mobile application is covered

>Developed self-tests for the mobile application.

- [x] Verify the functionality of the search feature and the behavior of the filter reset button.
- [x] Verify that clicking the 'My preference' button X times correctly increments the counter value near the button.
- [x] Verify that all elements on the screen are enabled after enabling the switch and all checkboxes.
- [x] Verify that the position of the 'circle' changes after adjusting the SeekBar value.
- [x] Verify the behavior of the WiFi settings checkbox and text field persistence after application restart


### In this subproject, self-tests are written in <code>Java 11</code> with use <code>Selenide и Appium</code>.
>
> <code>Selenide и Appium</code> are used to control a mobile device using a web driver.
>
> Tests can be run both locally (using Android studio and a mobile device emulator).
>
> <code>Allure Report</code> generates a report on the launch of tests.
>
>It is used for automated assembly of the project <code>Gradle</code>.
>
>It is used as a library for unit testing <code>TestNg</code>.
