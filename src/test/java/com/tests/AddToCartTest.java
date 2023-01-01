package com.tests;

import com.codeborne.selenide.appium.ScreenObject;
import com.screens.ProductsListingScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Test;

class AddToCartTest extends TestSetup {

  private ProductsListingScreen productsListingScreen;

  @Test
  void testSauceLabsApp() {
    productsListingScreen = ScreenObject.screen(ProductsListingScreen.class);

    productsListingScreen
        .selectBikeLightProduct()
        .checkWhetherAddToCartButtonPresent();
  }
}