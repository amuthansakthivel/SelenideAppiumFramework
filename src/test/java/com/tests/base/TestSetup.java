package com.tests.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TextReportExtension.class)
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