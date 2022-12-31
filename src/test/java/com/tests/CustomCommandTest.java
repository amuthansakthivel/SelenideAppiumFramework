package com.tests;

import com.screens.ProductsListingScreen;
import com.tests.base.TestSetup;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;

class CustomCommandTest extends TestSetup {

  private ProductsListingScreen productsListingScreen;

  @Test
  void testScrollToElement() {
    productsListingScreen = page(ProductsListingScreen.class);

    productsListingScreen
        .selectOneSieProduct()
        .checkWhetherAddToCartButtonPresent();
  }
}