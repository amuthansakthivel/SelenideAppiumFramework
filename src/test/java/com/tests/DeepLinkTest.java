package com.tests;

import com.codeborne.selenide.appium.AppiumDriverRunner;
import com.codeborne.selenide.appium.SelenideAppium;
import com.screens.ProductDetailsScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.appium.ScreenObject.screen;

class DeepLinkTest extends TestSetup {

  @Test
  void testDeepLinks() {
    openDeepLink();

    screen(ProductDetailsScreen.class)
        .checkWhetherAddToCartButtonPresent();
  }

  private static void openDeepLink() {
    if (AppiumDriverRunner.isAndroidDriver()) {
      SelenideAppium.openAndroidDeepLink("mydemoapprn://product-details/4",
                                         "com.saucelabs.mydemoapp.rn");
    } else {
      SelenideAppium.openIOSDeepLink("mydemoapprn://product-details/4");
    }
  }
}