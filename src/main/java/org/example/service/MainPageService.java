package org.example.service;

import io.qameta.allure.Step;
import org.example.page.MainPage;
import org.example.page.PaymentFormPage;

import static org.example.utils.Constants.MAIN_PAGE_URL;


public class MainPageService {

    private final MainPage mainPage = new MainPage();

    @Step("open Page and Cookie")
    public MainPageService openPageAndCookie() {
        mainPage.openPage(MAIN_PAGE_URL)
                .acceptCookie();
        return this;
    }
    @Step("block Title")
    public String getBlockTitle() {
        return mainPage.getBlockTitle();
    }
   @Step("are All Logos Displayed")
    public boolean areAllLogosDisplayed() {
        return mainPage.isVisaLogoDisplayed() &&
                mainPage.isVerifiedLogoDisplayed() &&
                mainPage.isMastercardLogoDisplayed() &&
                mainPage.isSecureCodeLogoDisplayed() &&
                mainPage.isBelkartLogoDisplayed();
    }
    @Step("click Details Link(")
    public MainPageService clickDetailsLink() {
        mainPage.clickDetailsLink();
        return this;
    }
    @Step(" Current Url")
    public String getCurrentUrl() {
        return mainPage.getCurrentUrl();
    }
   @Step("select Payment Type")
    public PaymentFormService selectPaymentType(String paymentType) {
        PaymentFormPage paymentFormPage = mainPage.selectPaymentType(paymentType);
        return new PaymentFormService(paymentFormPage);
    }
}