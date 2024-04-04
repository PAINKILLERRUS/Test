package org.example.commission.model;

/**
 * Класс модель(POJO) RequestCommission с обьявленными переменными,
 * в которых будет храниться полезная информация для сериализации в JSON и отправки в теле POST запроса
 */
public class RequestCommission {
    private Integer senderBankId;
    private Integer recipientBankId;
    private String acceptedCurrency;
    private String withdrawCurrency;
    private Double amount;
    private String amountType;
    private String operationType;
    private String feeCurrency;
    private Boolean feeDetails;
    private String countryCode;

    public Integer getSenderBankId() {
        return senderBankId;
    }

    public void setSenderBankId(Integer senderBankId) {
        this.senderBankId = senderBankId;
    }

    public Integer getRecipientBankId() {
        return recipientBankId;
    }

    public void setRecipientBankId(Integer recipientBankId) {
        this.recipientBankId = recipientBankId;
    }

    public String getAcceptedCurrency() {
        return acceptedCurrency;
    }

    public void setAcceptedCurrency(String acceptedCurrency) {
        this.acceptedCurrency = acceptedCurrency;
    }

    public String getWithdrawCurrency() {
        return withdrawCurrency;
    }

    public void setWithdrawCurrency(String withdrawCurrency) {
        this.withdrawCurrency = withdrawCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public Boolean getFeeDetails() {
        return feeDetails;
    }

    public void setFeeDetails(Boolean feeDetails) {
        this.feeDetails = feeDetails;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
