package com.screens;

import com.codeborne.selenide.appium.AppiumSelectors;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.locator.LocatorIdentifier.getLocator;

public class ProductDetailsScreen {
  /*
  We can also use conventional @AndroidFindBy @iOSXCUITFindBy
  But it might not be suitable for dynamic locators
  Also AppiumSelectors class contains rich locating strategies we could leverage.
*/
  private static final By ADD_TO_CART_BUTTON_ANDROID = AppiumSelectors.byContentDescription("Add To Cart button");
  private static final By ADD_TO_CART_BUTTON_IOS = AppiumSelectors.byName("Add To Cart button");

  public void checkWhetherAddToCartButtonPresent() {
    $(getLocator(ADD_TO_CART_BUTTON_ANDROID, ADD_TO_CART_BUTTON_IOS)).shouldHave(visible);
  }
}