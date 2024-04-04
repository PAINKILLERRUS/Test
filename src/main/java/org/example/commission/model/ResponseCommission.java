package org.example.commission.model;

/**
 * Класс модель(POJO) ResponseCommission с обьявленными переменными,
 * для десериализации JSON полученного с сервера тела POST ответа
 */
public class ResponseCommission {

    private Double acceptedAmount;
    private Double acceptedTransferAmount;
    private String acceptedTransferCurrency;
    private Double withdrawTransferAmount;
    private String withdrawTransferCurrency;
    private Double transferRate;
    private Double acceptedSenderBankFee;
    private Double acceptedProcessingFee;
    private Double acceptedRecipientBankFee;
    private Double acceptedTotalFeeAmount;
    private Double feeRate;
    private String feeCurrency;
    private String operationType;
    private Integer amountType;
    private Double recipientBankCompensation;
    private Double senderBankCompensation;
    private String error;

    @Override
    public String toString() {
        return "ResponseCommission{" +
                "acceptedAmount=" + acceptedAmount +
                ", acceptedTransferAmount=" + acceptedTransferAmount +
                ", acceptedTransferCurrency='" + acceptedTransferCurrency + '\'' +
                ", withdrawTransferAmount=" + withdrawTransferAmount +
                ", withdrawTransferCurrency='" + withdrawTransferCurrency + '\'' +
                ", transferRate=" + transferRate +
                ", acceptedSenderBankFee=" + acceptedSenderBankFee +
                ", acceptedProcessingFee=" + acceptedProcessingFee +
                ", acceptedRecipientBankFee=" + acceptedRecipientBankFee +
                ", acceptedTotalFeeAmount=" + acceptedTotalFeeAmount +
                ", feeRate=" + feeRate +
                ", feeCurrency='" + feeCurrency + '\'' +
                ", operationType='" + operationType + '\'' +
                ", amountType=" + amountType +
                ", recipientBankCompensation=" + recipientBankCompensation +
                ", senderBankCompensation=" + senderBankCompensation +
                ", error='" + error + '\'' +
                '}';
    }

    public Double getAcceptedAmount() {
        return acceptedAmount;
    }

    public void setAcceptedAmount(Double acceptedAmount) {
        this.acceptedAmount = acceptedAmount;
    }

    public Double getAcceptedTransferAmount() {
        return acceptedTransferAmount;
    }

    public void setAcceptedTransferAmount(Double acceptedTransferAmount) {
        this.acceptedTransferAmount = acceptedTransferAmount;
    }

    public String getAcceptedTransferCurrency() {
        return acceptedTransferCurrency;
    }

    public void setAcceptedTransferCurrency(String acceptedTransferCurrency) {
        this.acceptedTransferCurrency = acceptedTransferCurrency;
    }

    public Double getWithdrawTransferAmount() {
        return withdrawTransferAmount;
    }

    public void setWithdrawTransferAmount(Double withdrawTransferAmount) {
        this.withdrawTransferAmount = withdrawTransferAmount;
    }

    public String getWithdrawTransferCurrency() {
        return withdrawTransferCurrency;
    }

    public void setWithdrawTransferCurrency(String withdrawTransferCurrency) {
        this.withdrawTransferCurrency = withdrawTransferCurrency;
    }

    public Double getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(Double transferRate) {
        this.transferRate = transferRate;
    }

    public Double getAcceptedSenderBankFee() {
        return acceptedSenderBankFee;
    }

    public void setAcceptedSenderBankFee(Double acceptedSenderBankFee) {
        this.acceptedSenderBankFee = acceptedSenderBankFee;
    }

    public Double getAcceptedProcessingFee() {
        return acceptedProcessingFee;
    }

    public void setAcceptedProcessingFee(Double acceptedProcessingFee) {
        this.acceptedProcessingFee = acceptedProcessingFee;
    }

    public Double getAcceptedRecipientBankFee() {
        return acceptedRecipientBankFee;
    }

    public void setAcceptedRecipientBankFee(Double acceptedRecipientBankFee) {
        this.acceptedRecipientBankFee = acceptedRecipientBankFee;
    }

    public Double getAcceptedTotalFeeAmount() {
        return acceptedTotalFeeAmount;
    }

    public void setAcceptedTotalFeeAmount(Double acceptedTotalFeeAmount) {
        this.acceptedTotalFeeAmount = acceptedTotalFeeAmount;
    }

    public Double getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(Double feeRate) {
        this.feeRate = feeRate;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getAmountType() {
        return amountType;
    }

    public void setAmountType(Integer amountType) {
        this.amountType = amountType;
    }

    public Double getRecipientBankCompensation() {
        return recipientBankCompensation;
    }

    public void setRecipientBankCompensation(Double recipientBankCompensation) {
        this.recipientBankCompensation = recipientBankCompensation;
    }

    public Double getSenderBankCompensation() {
        return senderBankCompensation;
    }

    public void setSenderBankCompensation(Double senderBankCompensation) {
        this.senderBankCompensation = senderBankCompensation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
