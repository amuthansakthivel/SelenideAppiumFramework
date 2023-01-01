package com.tests;

import com.screens.ProductsListingScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.appium.ScreenObject.screen;

class CustomConditionTest extends TestSetup {

  private ProductsListingScreen productsListingScreen;

  @Test
  void testCustomCollection() {
    productsListingScreen = screen(ProductsListingScreen.class);

    productsListingScreen
        .checkWhetherFooterIsPresent();
  }
}