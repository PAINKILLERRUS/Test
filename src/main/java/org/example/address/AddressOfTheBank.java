package org.example.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.address.model.RequestAddressBank;
import org.example.address.model.ResponseAddressBank;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Класс AddressOfTheBank с объектом <b>mapper</b>
 * и методом <b>getAddressBanks</b>
 */
public class AddressOfTheBank {

    /**
     * Обьект класса ObjectMapper для работы с JSON
     */
    private ObjectMapper mapper = new ObjectMapper();


    /**
     * Метод получения списка точек, местонахождения банка
     *
     * @param requestAddressBank - аргумент, содержащий ссылку на объект класса RequestAddressBank
     * @return возвращает десериализованный JSON  в объекте класса ResponseAddressBank
     * @throws IOException
     * @throws InterruptedException
     */
    public ResponseAddressBank getAddressBanks(RequestAddressBank requestAddressBank) throws IOException, InterruptedException {
        //Обьект класса HttpClient для отправки запросов
        HttpClient client = HttpClient.newHttpClient();
        //Обьект класса HttpRequest для создания http-запроса
        HttpRequest request = HttpRequest.newBuilder()
                //с помощью метода uri() задаем URI (или URL), к которому будет отправлен http-запрос.
                .uri(URI.create("https://slt-test.info.api.unistream.com:443/api/v1/poses/search?location="
                        + requestAddressBank.getLocation() + "&radius=" + requestAddressBank.getRadius() + "&" +
                        "maxResults=" + requestAddressBank.getMaxResult() +
                        "&filter.country=" + requestAddressBank.getFilterCountry()))
                .build();

        /*Обьект класса HttpResponse для принятия http-ответа
            метод client.send(); отправляет запрос и передводит тело ответа в String формат
        */
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //возврат десериализованного тела ответа в объекте класса ResponseAddressBank
        return mapper.readValue(response.body(), ResponseAddressBank.class);
    }
}
