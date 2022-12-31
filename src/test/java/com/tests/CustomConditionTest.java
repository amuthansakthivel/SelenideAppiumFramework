package com.tests;

import com.screens.ProductsListingScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

class CustomConditionTest extends TestSetup {

  private ProductsListingScreen productsListingScreen;

  @Test
  void testCustomCollection() {
    productsListingScreen = page(ProductsListingScreen.class);

    productsListingScreen
        .checkWhetherFooterIsPresent();
  }
}