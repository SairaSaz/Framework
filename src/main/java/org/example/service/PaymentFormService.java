package org.example.service;

import io.qameta.allure.Step;
import org.example.page.PaymentFormPage;


public class PaymentFormService {

    private final PaymentFormPage paymentFormPage;

    public PaymentFormService(PaymentFormPage paymentFormPage) {
        this.paymentFormPage = paymentFormPage;
    }
    @Step("Placeholder")
    public String getPlaceholder(String paymentType) {
        return paymentFormPage.getPlaceholder(paymentType);
    }
    @Step("is Field Empty")
    public boolean isFieldEmpty(String paymentType) {
        return paymentFormPage.isFieldEmpty(paymentType);
    }
    @Step("enter Data")
    public PaymentFormService enterData(String paymentType, String value, String amount) {
        paymentFormPage.enterData(paymentType, value, amount);
        return this;
    }
    @Step("is Continue Button Enabled")
    public boolean isContinueButtonEnabled() {
        return paymentFormPage.isContinueButtonEnabled();
    }
}