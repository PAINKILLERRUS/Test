package org.example.webService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.websSrvice.client.model.Client;
import org.example.websSrvice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса Веб-сервис банковских услуг")
public class BankWebServiceTests {

    /**
     * Константное поле, содержащее информацию из запроса по api содержащее требования к полям клиента
     */
    private static final String CONTENT = "{\n" +
            "  \"id\": \"person.full\",\n" +
            "  \"properties\": {\n" +
            "    \"FirstName\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 1,\n" +
            "      \"maxLength\": 50\n" +
            "    },\n" +
            "    \"LastName\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 1,\n" +
            "      \"maxLength\": 50\n" +
            "    },\n" +
            "    \"MiddleName\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 1,\n" +
            "      \"maxLength\": 50\n" +
            "    },\n" +
            "    \"PhoneNumber\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 11,\n" +
            "      \"maxLength\": 12,\n" +
            "      \"pattern\": \"^[0-9-+() ]+$\"\n" +
            "    },\n" +
            "    \"Gender\": {\n" +
            "      \"enum\": [\n" +
            "        \"Male\",\n" +
            "        \"Female\"\n" +
            "      ]\n" +
            "    },\n" +
            "    \"BirthDate\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"format\": \"date-time\"\n" +
            "    },\n" +
            "    \"BirthPlace\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 1,\n" +
            "      \"maxLength\": 250\n" +
            "    },\n" +
            "    \"CountryOfResidence\": {\n" +
            "      \"type\": \"string\",\n" +
            "      \"minLength\": 3,\n" +
            "      \"maxLength\": 3,\n" +
            "      \"pattern\": \"^[a-zA-Z]+$\"\n" +
            "    },\n" +
            "    \"Address\": {\n" +
            "      \"type\": \"object\",\n" +
            "      \"properties\": {\n" +
            "        \"CountryCode\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"minLength\": 3,\n" +
            "          \"maxLength\": 3,\n" +
            "          \"pattern\": \"^[a-zA-Z]+$\"\n" +
            "        },\n" +
            "        \"PostCode\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"minLength\": 4,\n" +
            "          \"maxLength\": 10,\n" +
            "          \"pattern\": \"^[0-9a-zA-Z]+$\"\n" +
            "        },\n" +
            "        \"State\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 50\n" +
            "        },\n" +
            "        \"City\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"minLength\": 1,\n" +
            "          \"maxLength\": 30\n" +
            "        },\n" +
            "        \"District\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 40\n" +
            "        },\n" +
            "        \"Street\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 250\n" +
            "        },\n" +
            "        \"House\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 40\n" +
            "        },\n" +
            "        \"Building\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 40\n" +
            "        },\n" +
            "        \"Apartment\": {\n" +
            "          \"type\": \"string\",\n" +
            "          \"maxLength\": 20\n" +
            "        }\n" +
            "      },\n" +
            "      \"required\": [ \"CountryCode\", \"City\" ]\n" +
            "    }\n" +
            "  },\n" +
            "  \"required\": [\"FirstName\",  \"LastName\", \"Gender\", \"PhoneNumber\", \"BirthDate\"," +
            "  \"BirthPlace\", \"CountryOfResidence\", \"Address\" ]\n" +
            "}\n";

    /**
     * Константное поле с корректно заполненными JSON полями
     */
    private static final String CORRECT_FILLING_JSON = "{\n" +
            "\t\"FirstName\": \"Ivan\",\n" +
            "\t\"LastName\": \"Antipov\",\n" +
            "\t\"MiddleName\": \"Sergeevich\",\n" +
            "\t\"PhoneNumber\": \"89999223425\",\n" +
            "\t\"Gender\": \"Male\",\n" +
            "\t\"BirthDate\": \"06.04.1994\",\n" +
            "\t\"BirthPlace\": \"Kymertau\",\n" +
            "\t\"CountryOfResidence\": \"RUS\",\n" +
            "\t\"Address\":{\n" +
            "\t\t\"CountryCode\": \"RUS\",\n" +
            "\t\t\"PostCode\": \"RUSSIA\",\n" +
            "\t\t\"State\": \"Moscow\",\n" +
            "\t\t\"City\":\"Moscow\",\n" +
            "\t\t\"District\":\"Moscow\",\n" +
            "\t\t\"Street\": \"Orechoviy Bylvar\",\n" +
            "\t\t\"House\": \"37\",\n" +
            "\t\t\"Building\":\"1\",\n" +
            "\t\t\"Apartment\":\"33\"\n" +
            "\t}\n" +
            "}";

    /**
     * Константное поле с пустыми JSON полями необязательными к заполнению
     */
    private static final String NOT_REQUIRED_JSON_EMPTY_FIELDS = "{\n" +
            "\t\"FirstName\": \"Ivan\",\n" +
            "\t\"LastName\": \"Antipov\",\n" +
            "\t\"MiddleName\": null,\n" +
            "\t\"PhoneNumber\": \"89999223425\",\n" +
            "\t\"Gender\": \"Male\",\n" +
            "\t\"BirthDate\": \"06.04.1994\",\n" +
            "\t\"BirthPlace\": \"Kymertau\",\n" +
            "\t\"CountryOfResidence\": \"RUS\",\n" +
            "\t\"Address\":{\n" +
            "\t\t\"CountryCode\": \"RUS\",\n" +
            "\t\t\"PostCode\": null,\n" +
            "\t\t\"State\": null,\n" +
            "\t\t\"City\":\"Moscow\",\n" +
            "\t\t\"District\":null,\n" +
            "\t\t\"Street\": null,\n" +
            "\t\t\"House\": null,\n" +
            "\t\t\"Building\":null,\n" +
            "\t\t\"Apartment\":null\n" +
            "\t}\n" +
            "}";

    /**
     * Константное поле с пустыми JSON полями обязательными к заполнению
     */
    private static final String EMPTY_REQUIRED_JSON_FIELDS = "{\n" +
            "\t\"FirstName\": null,\n" +
            "\t\"LastName\": null,\n" +
            "\t\"MiddleName\": \"Sergeevich\",\n" +
            "\t\"PhoneNumber\": null,\n" +
            "\t\"Gender\": null,\n" +
            "\t\"BirthDate\": null,\n" +
            "\t\"BirthPlace\": null,\n" +
            "\t\"CountryOfResidence\": null,\n" +
            "\t\"Address\":{\n" +
            "\t\t\"CountryCode\": null,\n" +
            "\t\t\"PostCode\": \"RUSSIA\",\n" +
            "\t\t\"State\": \"Moscow\",\n" +
            "\t\t\"City\":null,\n" +
            "\t\t\"District\":\"Moscow\",\n" +
            "\t\t\"Street\": \"Orechoviy Bylvar\",\n" +
            "\t\t\"House\": \"37\",\n" +
            "\t\t\"Building\":\"1\",\n" +
            "\t\t\"Apartment\":\"33\"\n" +
            "\t}\n" +
            "}";

    /**
     * Константное поле с JSON полями, превышающие допустимый диапозон
     */
    private static final String OUT_OF_RANGE_JSON_FIELD = "{\n" +
            "\t\"FirstName\":\"\",\n" +
            "\t\"LastName\": \"\",\n" +
            "\t\"MiddleName\": \"\",\n" +
            "\t\"PhoneNumber\": \"99999999999999\",\n" +
            "\t\"Gender\": \"\",\n" +
            "\t\"BirthDate\": \"\",\n" +
            "\t\"BirthPlace\": \"\",\n" +
            "\t\"CountryOfResidence\": \"RUSSIA\",\n" +
            "\t\"Address\":{\n" +
            "\t\t\"CountryCode\": \"RUSSIA\",\n" +
            "\t\t\"PostCode\": \"RUSSIAFEDERATION\",\n" +
            "\t\t\"State\": \"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\",\n" +
            "\t\t\"City\":\"\",\n" +
            "\t\t\"District\":\"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\",\n" +
            "\t\t\"Street\": \"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\",\n" +
            "\t\t\"House\": \"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\",\n" +
            "\t\t\"Building\":\"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\",\n" +
            "\t\t\"Apartment\":\"RUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAFEDERATIONRUSSIAF\"\n" +
            "\t}\n" +
            "}";

    /**
     * Обьект класса ObjectMapper для работы с JSON
     */
    private ObjectMapper mapper;

    /**
     * Метод создающий объект <b>mapper</b> класса <b>ObjectMapper</b>
     * для переопределения перед каждым выполнением тестового метода.
     */
    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Должен корректно десериализовать JSON в Root")
    public void shouldCorrectDeserializationJsonToRoot() throws JsonProcessingException {
        //переменной root передается результат десериализации константного поля CONTENT в обьекте класса Root
        Root root = mapper.readValue(CONTENT, Root.class);
        //вывод переменной root в консоль
        System.out.println(root);
    }

    @Test
    @DisplayName("Должен проверить корректно заполненные поля")
    public void shouldCheckCorrectFillingOfFields() throws JsonProcessingException {
        //переменной client передается результат десериализации константного поля CORRECT_FILLING_JSON в обьекте класса client
        Client client = mapper.readValue(CORRECT_FILLING_JSON, Client.class);
        //переменной root передается результат десериализации константного поля CONTENT в обьекте класса Root
        Root root = mapper.readValue(CONTENT, Root.class);
        //вызов метода проверки корректности клиентской JSON переменной
        checkCorrectClientJsonValue(client, root);
    }

    @Test
    @DisplayName("Должен проверить пустые поля не обязательные для заполнения")
    public void shouldCorrectCheckEmptyNotRequiredFields() throws JsonProcessingException {
        //переменной client передается результат десериализации константного поля NOT_REQUIRED_JSON_EMPTY_FIELDS в обьекте класса client
        Client client = mapper.readValue(NOT_REQUIRED_JSON_EMPTY_FIELDS, Client.class);
        //переменной root передается результат десериализации константного поля CONTENT в обьекте класса Root
        Root root = mapper.readValue(CONTENT, Root.class);
        //вызов метода проверки корректности клиентской JSON переменной
        checkCorrectClientJsonValue(client, root);
    }

    @Test
    @DisplayName("Должен проверить пустые поля обязательные для заполнения")
    public void shouldCheckEmptyRequiredFields() throws JsonProcessingException {
        //переменной client передается результат десериализации константного поля EMPTY_REQUIRED_JSON_FIELDS в обьекте класса client
        Client client = mapper.readValue(EMPTY_REQUIRED_JSON_FIELDS, Client.class);
        //переменной root передается результат десериализации константного поля CONTENT в обьекте класса Root
        Root root = mapper.readValue(CONTENT, Root.class);
        //вызов метода проверки пустых обязательных для заполнения полей клиентского JSON
        checkEmptyRequiredFields(client, root);
    }

    @Test
    @DisplayName("Должен проверить выход полей за допустимый диапозон")
    public void shouldCheckOutOfTheRangeJsonFields() throws JsonProcessingException {
        //переменной client передается результат десериализации константного поля OUT_OF_RANGE_JSON_FIELD в обьекте класса client
        Client client = mapper.readValue(OUT_OF_RANGE_JSON_FIELD, Client.class);
        //переменной root передается результат десериализации константного поля CONTENT в обьекте класса Root
        Root root = mapper.readValue(CONTENT, Root.class);
        //вызов метод проверки полей клиентского JSON, вышедших за допустимый диапозон
        checkOutOfTheRangeJsonFields(client, root);
    }


    /**
     * Метод проверки корректности клиентского JSON переменной
     *
     * @param client - аргумент, содержащий ссылку на объект класса Client и содержащий данные из клиентского JSON
     * @param root   - аргумент, содержащий ссылку на объект класса Root и содержащий JSON с требованиями к полям при регистрации клиента
     */
    public void checkCorrectClientJsonValue(Client client, Root root) {
        //последовательный вызов assertTrue();, проверяющих на истинность предоставленных методами логических условий
        assertTrue(checkFirstName(root.getProperties().getFirstName(), client.getFirstName(), root.getRequired()));
        assertTrue(checkLastName(root.getProperties().getLastName(), client.getLastName(), root.getRequired()));
        assertTrue(checkMiddleName(root.getProperties().getMiddleName(), client.getMiddleName(), root.getRequired()));
        assertTrue(checkPhoneNumber(root.getProperties().getPhoneNumber(), client.getPhoneNumber(), root.getRequired()));
        assertTrue(checkGender(root.getProperties().getGender(), client.getGender(), root.getRequired()));
        assertTrue(checkBirthPlace(root.getProperties().getBirthPlace(), client.getBirthPlace(), root.getRequired()));
        assertTrue(checkCountryOfResidence(root.getProperties().getCountryOfResidence(), client.getCountryOfResidence(), root.getRequired()));
        assertTrue(checkCountryCode(root.getProperties().getAddress().getProperties().getCountryCode(),
                client.getAddress().getCountryCode(), root.getProperties().getAddress().getRequired()));
        assertTrue(checkPostCode(root.getProperties().getAddress().getProperties().getPostCode(), client.getAddress().getPostCode(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkState(root.getProperties().getAddress().getProperties().getState(), client.getAddress().getState(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkCity(root.getProperties().getAddress().getProperties().getCity(), client.getAddress().getCity(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkDistrict(root.getProperties().getAddress().getProperties().getDistrict(), client.getAddress().getDistrict(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkStreet(root.getProperties().getAddress().getProperties().getStreet(), client.getAddress().getStreet(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkHouse(root.getProperties().getAddress().getProperties().getHouse(), client.getAddress().getHouse(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkBuilding(root.getProperties().getAddress().getProperties().getBuilding(), client.getAddress().getBuilding(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkApartment(root.getProperties().getAddress().getProperties().getApartment(), client.getAddress().getApartment(),
                root.getProperties().getAddress().getRequired()));
    }

    /**
     * Метод проверки пустых обязательных для заполнения полей клиентского JSON
     *
     * @param client - аргумент, содержащий ссылку на объект класса Client и содержащий данные из клиентского JSON
     * @param root   - аргумент, содержащий ссылку на объект класса Root и содержащий JSON с требованиями к полям при регистрации клиента
     */
    public void checkEmptyRequiredFields(Client client, Root root) {
        /*последовательный вызов методов assertTrue(); и assertFalse();,
        проверяющих на истинность и ложность предоставленных методами логических условий
         */
        assertFalse(checkFirstName(root.getProperties().getFirstName(), client.getFirstName(), root.getRequired()));
        assertFalse(checkLastName(root.getProperties().getLastName(), client.getLastName(), root.getRequired()));
        assertTrue(checkMiddleName(root.getProperties().getMiddleName(), client.getMiddleName(), root.getRequired()));
        assertFalse(checkPhoneNumber(root.getProperties().getPhoneNumber(), client.getPhoneNumber(), root.getRequired()));
        assertFalse(checkGender(root.getProperties().getGender(), client.getGender(), root.getRequired()));
        assertFalse(checkBirthPlace(root.getProperties().getBirthPlace(), client.getBirthPlace(), root.getRequired()));
        assertFalse(checkCountryOfResidence(root.getProperties().getCountryOfResidence(), client.getCountryOfResidence(), root.getRequired()));
        assertFalse(checkCountryCode(root.getProperties().getAddress().getProperties().getCountryCode(),
                client.getAddress().getCountryCode(), root.getProperties().getAddress().getRequired()));
        assertTrue(checkPostCode(root.getProperties().getAddress().getProperties().getPostCode(), client.getAddress().getPostCode(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkState(root.getProperties().getAddress().getProperties().getState(), client.getAddress().getState(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkCity(root.getProperties().getAddress().getProperties().getCity(), client.getAddress().getCity(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkDistrict(root.getProperties().getAddress().getProperties().getDistrict(), client.getAddress().getDistrict(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkStreet(root.getProperties().getAddress().getProperties().getStreet(), client.getAddress().getStreet(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkHouse(root.getProperties().getAddress().getProperties().getHouse(), client.getAddress().getHouse(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkBuilding(root.getProperties().getAddress().getProperties().getBuilding(), client.getAddress().getBuilding(),
                root.getProperties().getAddress().getRequired()));
        assertTrue(checkApartment(root.getProperties().getAddress().getProperties().getApartment(), client.getAddress().getApartment(),
                root.getProperties().getAddress().getRequired()));
    }

    /**
     * Метод проверки полей клиентского JSON, вышедших за допустимый диапозон
     *
     * @param client - аргумент, содержащий ссылку на объект класса Client и содержащий данные из клиентского JSON
     * @param root   - аргумент, содержащий ссылку на объект класса Root и содержащий JSON с требованиями к полям при регистрации клиента
     */
    public void checkOutOfTheRangeJsonFields(Client client, Root root) {
        //последовательный вызов методов assertFalse();, проверяющих на ложность предоставленных методами логических условий
        assertFalse(checkFirstName(root.getProperties().getFirstName(), client.getFirstName(), root.getRequired()));
        assertFalse(checkLastName(root.getProperties().getLastName(), client.getLastName(), root.getRequired()));
        assertFalse(checkMiddleName(root.getProperties().getMiddleName(), client.getMiddleName(), root.getRequired()));
        assertFalse(checkPhoneNumber(root.getProperties().getPhoneNumber(), client.getPhoneNumber(), root.getRequired()));
        assertFalse(checkGender(root.getProperties().getGender(), client.getGender(), root.getRequired()));
        assertFalse(checkBirthPlace(root.getProperties().getBirthPlace(), client.getBirthPlace(), root.getRequired()));
        assertFalse(checkCountryOfResidence(root.getProperties().getCountryOfResidence(), client.getCountryOfResidence(), root.getRequired()));
        assertFalse(checkCountryCode(root.getProperties().getAddress().getProperties().getCountryCode(),
                client.getAddress().getCountryCode(), root.getProperties().getAddress().getRequired()));
        assertFalse(checkPostCode(root.getProperties().getAddress().getProperties().getPostCode(), client.getAddress().getPostCode(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkState(root.getProperties().getAddress().getProperties().getState(), client.getAddress().getState(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkCity(root.getProperties().getAddress().getProperties().getCity(), client.getAddress().getCity(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkDistrict(root.getProperties().getAddress().getProperties().getDistrict(), client.getAddress().getDistrict(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkStreet(root.getProperties().getAddress().getProperties().getStreet(), client.getAddress().getStreet(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkHouse(root.getProperties().getAddress().getProperties().getHouse(), client.getAddress().getHouse(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkBuilding(root.getProperties().getAddress().getProperties().getBuilding(), client.getAddress().getBuilding(),
                root.getProperties().getAddress().getRequired()));
        assertFalse(checkApartment(root.getProperties().getAddress().getProperties().getApartment(), client.getAddress().getApartment(),
                root.getProperties().getAddress().getRequired()));
    }

    /**
     * Метод проверки FirstName
     *
     * @param firstName - аргумент, содержащий ссылку на объект класса FirstName
     * @param value     - аргумент, содержащий переменную типа String значения из поля FirstName
     * @param required  - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkFirstName(FirstName firstName, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> FirstName,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("FirstName") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра firstName
            */
            return value.length() <= firstName.getMaxLength() && value.length() >= firstName.getMinLength();
            //если
        } else if (!required.contains("FirstName") && value != null) {
            /* если List<String>  не содержит FirstName, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра firstName
             */
            return value.length() <= firstName.getMaxLength() && value.length() >= firstName.getMinLength();
        } else if (required.contains("FirstName") && value == null) {
            //если List<String> содержит FirstName, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("FirstName") && value == null) {
            //если List<String> не содержит FirstName, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки LastName
     *
     * @param lastName - аргумент, содержащий ссылку на объект класса LastName
     * @param value    - аргумент, содержащий переменную типа String значения из поля LastName
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkLastName(LastName lastName, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> LastName,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("LastName") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра lastName
            */
            return value.length() <= lastName.getMaxLength() && value.length() >= lastName.getMinLength();
        } else if (!required.contains("LastName") && value != null) {
              /* если List<String>  не содержит LastName, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра lastName
             */
            return value.length() <= lastName.getMaxLength() && value.length() >= lastName.getMinLength();
        } else if (required.contains("LastName") && value == null) {
            //если List<String> содержит LastName, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("LastName") && value == null) {
            //если List<String> не содержит LastName, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки MiddleName
     *
     * @param middleName - аргумент, содержащий ссылку на объект класса MiddleName
     * @param value      - аргумент, содержащий переменную типа String значения из поля MiddleName
     * @param required   - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkMiddleName(MiddleName middleName, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> MiddleName,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("MiddleName") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра middleName
            */
            return value.length() <= middleName.getMaxLength() && value.length() >= middleName.getMinLength();
        } else if (!required.contains("MiddleName") && value != null) {
             /* если List<String>  не содержит MiddleName, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра lastName
             */
            return value.length() <= middleName.getMaxLength() && value.length() >= middleName.getMinLength();
        } else if (required.contains("MiddleName") && value == null) {
            //если List<String> содержит MiddleName, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("MiddleName") && value == null) {
            //если List<String> не содержит MiddleName, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки PhoneNumber
     *
     * @param phoneNumber - аргумент, содержащий ссылку на объект класса PhoneNumber
     * @param value       - аргумент, содержащий переменную типа String значения из поля PhoneNumber
     * @param required    - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */

    public boolean checkPhoneNumber(PhoneNumber phoneNumber, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> PhoneNumber,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("PhoneNumber") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра phoneNumber, и ищем в value совпадение с заданным шаблоном
            */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(phoneNumber.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= phoneNumber.getMaxLength() && value.length() >= phoneNumber.getMinLength()
                    && matcher.find();
        } else if (!required.contains("PhoneNumber") && value != null) {
             /* если List<String>  не содержит PhoneNumber, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра phoneNumber
            и ищем в value совпадение с заданным шаблоном
             */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(phoneNumber.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= phoneNumber.getMaxLength() && value.length() >= phoneNumber.getMinLength()
                    && matcher.find();
        } else if (required.contains("PhoneNumber") && value == null) {
            //если List<String> содержит PhoneNumber, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("PhoneNumber") && value == null) {
            //если List<String> не содержит PhoneNumber, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки Gender
     *
     * @param gender   - аргумент, содержащий ссылку на объект класса Gender
     * @param value    - аргумент, содержащий переменную типа String значения из поля Gender
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkGender(Gender gender, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> Gender,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("Gender") && value != null) {
            /* если указанное условие верно, то проверяем содержит ли Gender value
             */
            return gender.getMyEnum().contains(value);
        } else if (!required.contains("Gender") && value != null) {
            /* если List<String>  не содержит Gender, как поля обязательного для заполнения и value не равно null,
            проверяем содержит ли Gender value
             */
            return gender.getMyEnum().contains(value);
        } else if (required.contains("Gender") && value == null) {
            //если List<String> содержит Gender, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("Gender") && value == null) {
            //если List<String> не содержит Gender, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки BirthPlace
     *
     * @param birthPlace - аргумент, содержащий ссылку на объект класса BirthPlace
     * @param value      - аргумент, содержащий переменную типа String значения из поля BirthPlace
     * @param required   - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkBirthPlace(BirthPlace birthPlace, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> BirthPlace,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("BirthPlace") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра birthPlace
            */
            return value.length() <= birthPlace.getMaxLength() && value.length() >= birthPlace.getMinLength();
        } else if (!required.contains("BirthPlace") && value != null) {
           /* если List<String>  не содержит BirthPlace, как поля обязательного для заполнения и value не равно null,
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра birthPlace
             */
            return value.length() <= birthPlace.getMaxLength() && value.length() >= birthPlace.getMinLength();
        } else if (required.contains("BirthPlace") && value == null) {
            //если List<String> содержит BirthPlace, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("BirthPlace") && value == null) {
            //если List<String> не содержит BirthPlace, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки CountryOfResidence
     *
     * @param residence - аргумент, содержащий ссылку на объект класса CountryOfResidence
     * @param value     - аргумент, содержащий переменную типа String значения из поля CountryOfResidence
     * @param required  - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkCountryOfResidence(CountryOfResidence residence, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> CountryOfResidence,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("CountryOfResidence") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра residence, и ищем в value совпадение с заданным шаблоном
            */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(residence.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= residence.getMaxLength() && value.length() >= residence.getMinLength() && matcher.find();
        } else if (!required.contains("CountryOfResidence") && value != null) {
          /* если List<String>  не содержит CountryOfResidence, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра residence
            и ищем в value совпадение с заданным шаблоном
             */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(residence.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= residence.getMaxLength() && value.length() >= residence.getMinLength()
                    && matcher.find();
        } else if (required.contains("CountryOfResidence") && value == null) {
            //если List<String> содержит CountryOfResidence, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("CountryOfResidence") && value == null) {
            //если List<String> не содержит CountryOfResidence, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки CountryCode
     *
     * @param countryCode - аргумент, содержащий ссылку на объект класса CountryCode
     * @param value       - аргумент, содержащий переменную типа String значения из поля CountryCode
     * @param required    - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkCountryCode(CountryCode countryCode, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> CountryCode,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("CountryCode") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра countryCode, и ищем в value совпадение с заданным шаблоном
            */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(countryCode.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= countryCode.getMaxLength() && value.length() >= countryCode.getMinLength() && matcher.find();
        } else if (!required.contains("CountryCode") && value != null) {
             /* если List<String>  не содержит CountryCode, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра countryCode
            и ищем в value совпадение с заданным шаблоном
             */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(countryCode.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= countryCode.getMaxLength() && value.length() >= countryCode.getMinLength()
                    && matcher.find();
        } else if (required.contains("CountryCode") && value == null) {
            //если List<String> содержит CountryCode, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("CountryCode") && value == null) {
            //если List<String> не содержит CountryCode, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки PostCode
     *
     * @param code     - аргумент, содержащий ссылку на объект класса PostCode
     * @param value    - аргумент, содержащий переменную типа String значения из поля PostCode
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkPostCode(PostCode code, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> PostCode,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("PostCode") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра code, и ищем в value совпадение с заданным шаблоном
            */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(code.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= code.getMaxLength() && value.length() >= code.getMinLength() && matcher.find();
        } else if (!required.contains("PostCode") && value != null) {
            /* если List<String>  не содержит PostCode, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра code
            и ищем в value совпадение с заданным шаблоном
             */
            //создание объекта класса Pattern для работы с регулярными выражениями с заданным шаблоном
            Pattern pattern = Pattern.compile(code.getPattern());
            //создание объекта класса Matcher для работы с регулярными выражениями и поиск совпадений с шаблоном
            Matcher matcher = pattern.matcher(value);

            return value.length() <= code.getMaxLength() && value.length() >= code.getMinLength()
                    && matcher.find();
        } else if (required.contains("PostCode") && value == null) {
            //если List<String> содержит PostCode, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("PostCode") && value == null) {
            //если List<String> не содержит PostCode, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки State
     *
     * @param state    - аргумент, содержащий ссылку на объект класса State
     * @param value    - аргумент, содержащий переменную типа String значения из поля State
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkState(State state, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> State,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("State") && value != null) {
            // если указанное условие верно, то проверяем что длина value меньше либо равна заданному параметру state
            return value.length() <= state.getMaxLength();
        } else if (!required.contains("State") && value != null) {
             /* если List<String>  не содержит State, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру state
             */
            return value.length() <= state.getMaxLength();
        } else if (required.contains("State") && value == null) {
            //если List<String> содержит State, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("State") && value == null) {
            //если List<String> не содержит State, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки City
     *
     * @param city     - аргумент, содержащий ссылку на объект класса City
     * @param value    - аргумент, содержащий переменную типа String значения из поля City
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkCity(City city, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> City,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("City") && value != null) {
            /* если указанное условие верно, то проверяем входит ли длина value в заданный диапозон максимального
            и минимального параметра city
            */
            return value.length() <= city.getMaxLength() && value.length() >= city.getMinLength();
        } else if (!required.contains("City") && value != null) {
        /* если List<String>  не содержит City, как поля обязательного для заполнения и value не равно null
            проверяем входит ли длина value в заданный диапозон максимального и минимального параметра city
             */
            return value.length() <= city.getMaxLength() && value.length() >= city.getMinLength();
        } else if (required.contains("City") && value == null) {
            //если List<String> содержит City, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("City") && value == null) {
            //если List<String> не содержит City, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки District
     *
     * @param district - аргумент, содержащий ссылку на объект класса District
     * @param value    - аргумент, содержащий переменную типа String значения из поля District
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkDistrict(District district, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> District,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("District") && value != null) {
            // если указанное условие верно, то проверяем что длина value  меньше либо равна задданному параметру district
            return value.length() <= district.getMaxLength();
        } else if (!required.contains("District") && value != null) {
               /* если List<String>  не содержит District, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру district
             */
            return value.length() <= district.getMaxLength();
        } else if (required.contains("District") && value == null) {
            //если List<String> содержит District, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("District") && value == null) {
            //если List<String> не содержит District, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки Street
     *
     * @param street   - аргумент, содержащий ссылку на объект класса Street
     * @param value    - аргумент, содержащий переменную типа String значения из поля Street
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkStreet(Street street, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> Street,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("Street") && value != null) {
            // если указанное условие верно, то проверяем что длина value  меньше либо равна задданному параметру street
            return value.length() <= street.getMaxLength();
        } else if (!required.contains("Street") && value != null) {
              /* если List<String>  не содержит Street, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру street
             */
            return value.length() <= street.getMaxLength();
        } else if (required.contains("Street") && value == null) {
            //если List<String> содержит Street, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("Street") && value == null) {
            //если List<String> не содержит Street, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки House
     *
     * @param house    - аргумент, содержащий ссылку на объект класса House
     * @param value    - аргумент, содержащий переменную типа String значения из поля House
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkHouse(House house, String value, List<String> required) {
        /* в условном операторе проверяем содержит ли List<String> House,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("House") && value != null) {
            // если указанное условие верно, то проверяем что длина value  меньше либо равна задданному параметру house
            return value.length() <= house.getMaxLength();
        } else if (!required.contains("House") && value != null) {
              /* если List<String>  не содержит House, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру house
             */
            return value.length() <= house.getMaxLength();
        } else if (required.contains("House") && value == null) {
            //если List<String> содержит House, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("House") && value == null) {
            //если List<String> не содержит House, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки Building
     *
     * @param building - аргумент, содержащий ссылку на объект класса Building
     * @param value    - аргумент, содержащий переменную типа String значения из поля Building
     * @param required - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkBuilding(Building building, String value, List<String> required) {
         /* в условном операторе проверяем содержит ли List<String> Building,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("Building") && value != null) {
            // если указанное условие верно, то проверяем что длина value  меньше либо равна задданному параметру building
            return value.length() <= building.getMaxLength();
        } else if (!required.contains("Building") && value != null) {
            /* если List<String>  не содержит Building, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру building
             */
            return value.length() <= building.getMaxLength();
        } else if (required.contains("Building") && value == null) {
            //если List<String> содержит Building, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("Building") && value == null) {
            //если List<String> не содержит Building, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод проверки Apartment
     *
     * @param apartment - аргумент, содержащий ссылку на объект класса Apartment
     * @param value     - аргумент, содержащий переменную типа String значения из поля Apartment
     * @param required  - аргумент, содержащий List типа String обязательных для заполенеия полей
     * @return - возвращает логическое значение по результату проверки
     */
    public boolean checkApartment(Apartment apartment, String value, List<String> required) {
         /* в условном операторе проверяем содержит ли List<String> Apartment,
         как поля обязательного для заполнения,  и не равно ли value null
        */
        if (required.contains("Apartment") && value != null) {
            // если указанное условие верно, то проверяем что длина value  меньше либо равна задданному параметру apartment
            return value.length() <= apartment.getMaxLength();
        } else if (!required.contains("Apartment") && value != null) {
            /* если List<String>  не содержит Apartment, как поля обязательного для заполнения и value не равно null
            проверяем что длина value меньше либо равна заданному параметру apartment
             */
            return value.length() <= apartment.getMaxLength();
        } else if (required.contains("Apartment") && value == null) {
            //если List<String> содержит Apartment, как поля обязательного для заполнения и value равно null возвращаем false
            return false;
        } else if (!required.contains("Apartment") && value == null) {
            //если List<String> не содержит Apartment, как поля обязательного для заполнения и value равно null возвращаем true
            return true;
        } else {
            return false;
        }
    }
}
