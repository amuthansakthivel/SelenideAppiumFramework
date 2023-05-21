package com.tests;

import com.screens.ProductsListingScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.appium.ScreenObject.screen;

class CustomConditionTest extends TestSetup {

  private ProductsListingScreen productsListingScreen;

  @Tag("scroll-test")
  @Test
  void testCustomCondition() {
    productsListingScreen = screen(ProductsListingScreen.class);

    productsListingScreen
        .checkWhetherFooterIsPresent();
  }
}