package com.locator;

import com.codeborne.selenide.appium.AppiumDriverRunner;
import org.openqa.selenium.By;

public final class LocatorIdentifier {

  private LocatorIdentifier() {
  }

  public static By getLocator(By androidBy, By iosBy) {
    return AppiumDriverRunner.isAndroidDriver()
        ? androidBy
        : iosBy;
  }

}