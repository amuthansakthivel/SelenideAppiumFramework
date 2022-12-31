### Selenide-Appium Example

This project will give an example of how we can automate native mobile application using Selenide-Appium Library. 

To know more - [Selenide-Appium](https://github.com/selenide/selenide-appium)

#### How to choose mobile OS at runtime?

App under test is having same flow in both android and ios. So we need to tell Selenide-Appium which implementation to choose. We have two implementation classes, each for opening android and ios apps.

We can choose which app we want to open by setting.

1. Setting Configuration.browser value via code.

`Configuration.browser = SauceLabAndroidDriverProvider.class.getName();` //For opening android session
`Configuration.browser = SauceLabIosDriverProvider.class.getName();` //For opening ios session

Typical test may look like this

```java
class SauceLabsTest {

  @Test
  void testSauceLabsApp() {
    Configuration.browser = SauceLabAndroidDriverProvider.class.getName();
    SelenideAppium.launchApp();
    //test
  }
}
```

The problem with this approach is to change the value manually or write some logic to find which class implementation to use based on needs.

2. Pass `Configuration.browser` value as System property. It is also recommended to have default implementation set in the selenide.properties file

`mvn clean test -Dselenide.browser=com.provider.SauceLabAndroidDriverProvider` to run test on android
`mvn clean test -Dselenide.browser=com.provider.SauceLabIosDriverProvider` to run test on ios

#### Custom Commands

We can create our own custom conditions by implementing Command Interface.

For usage purpose, I have implemented custom command to scroll to an element.

```java
public class ProductsListingScreen {

  @AndroidFindBy(accessibility = "//android.widget.TextView[@text='$7.99']/preceding-sibling::android.view.ViewGroup/android.widget.ImageView")
  @iOSXCUITFindBy(accessibility = "Sauce Labs Onesie")
  private WebElement oneSieProduct;
  
  public ProductDetailsScreen selectOneSieProduct() {
    $(oneSieProduct).execute(new ScrollToElement()).shouldBe(visible).click();
    return page(ProductDetailsScreen.class);
  }
}
```

#### Custom Condition

We can create custom conditions to match our requirements.
In my case, for Android - I want to use `text` attribute and for iOS - I want to use `label` attribute for assertion.

```java
public final class CustomCondition {

  private CustomCondition() {
  }

  public static Condition attributeMatching(String androidAttribute, String iosAttribute, String expectedValue) {
    boolean androidDriver = AppiumDriverRunner.isAndroidDriver();
    String attributeInContext = androidDriver ? androidAttribute : iosAttribute;

    return new Condition("element should have " + attributeInContext + " with value " + expectedValue) {
      @Nonnull
      @Override
      public CheckResult check(Driver driver, WebElement element) {
        String actualValue = element.getAttribute(attributeInContext);
        return new CheckResult(actualValue.contains(expectedValue), actualValue);
      }
    };
  }
}
```
```java
public class ProductsListingScreen {

  private final By FOOTER_ANDROID = AppiumSelectors.withText("All Rights Reserved");
  private final By FOOTER_IOS = AppiumSelectors.withName("All Rights Reserved");

  public void checkWhetherFooterIsPresent() {
    getLocator(FOOTER_ANDROID, FOOTER_IOS)
        .execute(new ScrollToElement())
        .shouldHave(CustomCondition.attributeMatching("text", "label", "Sauce Labs"));
  }
}
```

