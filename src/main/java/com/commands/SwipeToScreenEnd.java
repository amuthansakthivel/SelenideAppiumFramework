package com.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import javax.annotation.Nullable;

import static com.codeborne.selenide.appium.WebdriverUnwrapper.cast;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;

public class SwipeToScreenEnd implements Command<SelenideElement> {

  @Nullable
  @Override
  public SelenideElement execute(
    SelenideElement proxy, WebElementSource locator, @Nullable Object[] objects) {
    AppiumDriver appiumDriver = cast(locator.driver(), AppiumDriver.class).orElseThrow();

    int currentSwipeCount = 0;
    String previousPageSource = "";

    while (isNotEndOfPage(appiumDriver, previousPageSource)
      && isLessThanMaxSwipeCount(currentSwipeCount)) {
      previousPageSource = appiumDriver.getPageSource();
      performScroll(appiumDriver);
      currentSwipeCount++;
    }
    return proxy;
  }

  private boolean isLessThanMaxSwipeCount(int currentSwipeCount) {
    final int maxSwipeCount = 30;
    return currentSwipeCount < maxSwipeCount;
  }

  private boolean isNotEndOfPage(AppiumDriver appiumDriver, String initialPageSource) {
    return !initialPageSource.equals(appiumDriver.getPageSource());
  }

  private Dimension getMobileDeviceSize(AppiumDriver appiumDriver) {
    return appiumDriver.manage().window().getSize();
  }

  private void performScroll(AppiumDriver appiumDriver) {
    Dimension size = getMobileDeviceSize(appiumDriver);
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence sequenceToPerformScroll = getSequenceToPerformScroll(finger, size);
    appiumDriver.perform(singletonList(sequenceToPerformScroll));
  }

  private Sequence getSequenceToPerformScroll(PointerInput finger, Dimension size) {
    int oneFourthHeightOfDevice = (int) (size.getHeight() * 0.25);
    return new Sequence(finger, 1)
      .addAction(
        finger.createPointerMove(
          ofMillis(0),
          PointerInput.Origin.viewport(),
          size.getWidth() / 2,
          size.getHeight() / 2))
      .addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()))
      .addAction(
        finger.createPointerMove(
          ofMillis(200),
          PointerInput.Origin.viewport(),
          size.getWidth() / 2,
          oneFourthHeightOfDevice))
      .addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
  }
}
