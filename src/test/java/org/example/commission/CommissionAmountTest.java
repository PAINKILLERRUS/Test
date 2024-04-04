package org.example.commission;

import org.example.commission.model.RequestCommission;
import org.example.commission.model.ResponseCommission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

@DisplayName("Тестирование класса с информацией о сумме комиссси")
public class CommissionAmountTest {

    /**
     * Поле класса CommissionAmount
     */
    private CommissionAmount commission;

    /**
     * Метод создающий объект <b>commission</b> класса <b>CommissionAmount</b>
     * для переопределения перед каждым выполнением тестового метода.
     */
    @BeforeEach
    public void setUp() {
        commission = new CommissionAmount();
    }

    @Test
    @DisplayName("Должен корректно вернуть сумму комиссии")
    public void shouldCorrectReturnAmountCommission() throws IOException, InterruptedException {
        //Создание обьекта класса RequestCommission
        RequestCommission requestCommission = new RequestCommission();
        //инициализация полей класса RequestCommission через сеттеры
        requestCommission.setSenderBankId(10400);
        requestCommission.setRecipientBankId(null);
        requestCommission.setAcceptedCurrency("RUB");
        requestCommission.setWithdrawCurrency("AMD");
        requestCommission.setAmount(1000.0);
        requestCommission.setAmountType("AcceptedAmount");
        requestCommission.setOperationType("Transfer");
        requestCommission.setFeeCurrency("RUB");
        requestCommission.setFeeDetails(true);
        requestCommission.setCountryCode("ARM");
        //переменной responseCommission присваивается результат работы метода getSum();
        ResponseCommission responseCommission = commission.commissionAmount(requestCommission);
        //объявляем переменную commissionSum и передаем ей сумму подчситанной комиссии
        double commissionSum = responseCommission.getAcceptedSenderBankFee() + responseCommission.getAcceptedRecipientBankFee();
        //выводим переменную commissionSum в консоль
        System.out.println("Сумма комиссии: " + commissionSum);
    }
}
