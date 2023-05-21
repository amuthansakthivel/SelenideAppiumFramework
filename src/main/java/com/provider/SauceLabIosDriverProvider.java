package com.provider;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.URL;
import java.time.Duration;

public class SauceLabIosDriverProvider implements WebDriverProvider {

  @SneakyThrows
  @Nonnull
  @Override
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    XCUITestOptions options = new XCUITestOptions();
    options.setAutomationName(AutomationName.IOS_XCUI_TEST);
    options.setWdaLaunchTimeout(Duration.ofMinutes(10));
    options.setDeviceName("iPhone 13");
    options.setFullReset(false);
    options.setApp(System.getProperty("user.dir") + "/apps/iOS-Simulator-MyRNDemoApp.1.3.0-162.zip");
    return new IOSDriver(new URL("http://127.0.0.1:4723"), options);
  }
}