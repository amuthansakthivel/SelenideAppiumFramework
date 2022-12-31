package com.screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.appium.AppiumSelectors;
import com.commands.ScrollToElement;
import com.conditions.CustomCondition;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static com.locator.LocatorIdentifier.getLocator;

public class ProductsListingScreen {

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='$9.99']/preceding-sibling::android.view.ViewGroup/android.widget.ImageView")
  @iOSXCUITFindBy(accessibility = "Sauce Labs Bike Light")
  private WebElement bikeLightProduct;

  @AndroidFindBy(xpath = "//android.widget.TextView[@text='$7.99']/preceding-sibling::android.view.ViewGroup/android.widget.ImageView")
  @iOSXCUITFindBy(accessibility = "Sauce Labs Onesie")
  private ElementsCollection oneSieProduct;

  private static final By FOOTER_ANDROID = AppiumSelectors.withText("All Rights Reserved");
  private static final By FOOTER_IOS = AppiumSelectors.withName("All Rights Reserved");

  public ProductDetailsScreen selectBikeLightProduct() {
    $(bikeLightProduct).shouldBe(visible).click();
    return page(ProductDetailsScreen.class);
  }

  public ProductDetailsScreen selectOneSieProduct() {
    oneSieProduct.get(0).execute(new ScrollToElement()).shouldBe(visible).click();
    return page(ProductDetailsScreen.class);
  }

  public void checkWhetherFooterIsPresent() {
    $(getLocator(FOOTER_ANDROID, FOOTER_IOS))
        .execute(new ScrollToElement())
        .shouldHave(CustomCondition.attributeMatching("text", "label", "Sauce Labs"));
  }
}