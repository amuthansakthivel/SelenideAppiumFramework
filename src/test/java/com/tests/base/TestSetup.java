package com.tests.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TestSetup {

  @BeforeEach
  protected void openApp() {
    SelenideAppium.launchApp();
  }

  @AfterEach
  protected void closeApp() {
    Selenide.closeWebDriver();
  }
}