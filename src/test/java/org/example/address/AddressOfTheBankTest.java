package org.example.address;

import org.example.address.model.Item;
import org.example.address.model.RequestAddressBank;
import org.example.address.model.ResponseAddressBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование класса с информацией об адресе банка")
public class AddressOfTheBankTest {

    /**
     * Поле класса AddressOfTheBank
     */
    private AddressOfTheBank address;

    /**
     * Метод создающий объект <b>address</b> класса <b>AddressOfTheBank</b>
     * для переопределения перед каждым выполнением тестового метода.
     */
    @BeforeEach
    public void setUp() {
        address = new AddressOfTheBank();
    }

    @Test
    @DisplayName("Должен вернуть корректный адрес банка")
    public void shouldReturnCorrectAddressBank() throws IOException, InterruptedException {
        //Создание обьекта класса RequestAddressBank
        RequestAddressBank requestAddressBank = new RequestAddressBank();
        // инициализация полей класса RequestAddressBank через сеттеры
        requestAddressBank.setLocation("55.834846,37.934417");
        requestAddressBank.setRadius(5000);
        requestAddressBank.setMaxResult(100);
        requestAddressBank.setFilterCountry("RUS");
        //переменной responseAddressBank присваивается результат работы метода getAddressBanks();
        ResponseAddressBank responseAddressBank = address.getAddressBanks(requestAddressBank);
        //сравниваем передаваемое значение на NULL, преобразуем item в String(адрес) и сопоставляем с указанным адрессом
        assertThat(responseAddressBank.getItems()).isNotNull()
                .map(item -> item.getAddress())
                .contains("Московская область, Балашиха, Советская, 13");
    }
}
