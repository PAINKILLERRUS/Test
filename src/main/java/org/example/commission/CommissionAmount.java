package org.example.commission;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commission.model.RequestCommission;
import org.example.commission.model.ResponseCommission;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс CommissionAmount с объектом <b>mapper</b>
 * и методом <b>commissionAmount</b>
 */
public class CommissionAmount {
    /**
     * Создаем обьект класса ObjectMapper для работы с JSON
     */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод получения расчета комиссии
     *
     * @param requestCommission - аргумент, содержащий ссылку на объект класса RequestCommission
     * @return возвращает десериализованный JSON в объекте класса ResponseCommission
     * @throws IOException
     * @throws InterruptedException
     */

    public ResponseCommission commissionAmount(RequestCommission requestCommission) throws IOException, InterruptedException {
        //Обьект класса HttpClient для отправки запросов
        HttpClient client = HttpClient.newHttpClient();
        //Обьект класса HttpRequest для создания http-запроса
        HttpRequest request = HttpRequest.newBuilder()
                //с помощью метода uri() задаем URI (или URL), к которому будет отправлен http-запрос.
                .uri(URI.create("https://slt-test.info.api.unistream.com:443/api/v1/transfer/FeeInfo"))
                //задаем http-метод запроса, в который передаем преобразованный JSON в тело POST запроса,(сериализация)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestCommission)))
                .build();
        /*Обьект класса HttpResponse для получения http-ответа,
        метод client.send(); отправляет запрос и приводит тело ответа в String формат
         */
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //возврат десериализованного тела ответа в объект класса ResponseAddressBank
        return mapper.readValue(response.body(), ResponseCommission.class);
    }
}
