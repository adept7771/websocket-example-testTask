package steps;

import com.google.gson.Gson;
import dataModels.Response;
import helpers.ClientWebSocket;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class GetDataSteps {

    // модельки генерил через плагин к идее json to pojo, его отлично скушал Gson и не поперхнулся.
    // единственно плагин оказывается не регистрозависим, и путал значения U / u например. Чуть-чуть
    // модельки подправил. Вероятно есть более элегантное решение для авто-генерации моделек.

    Gson gson = new Gson();

    @Step("Шаг - получить количество ответов {0} по адресу {1}")
    public ArrayList<Response> getResponsesByNum(int numberOfResponses, String connectionAddress) throws InterruptedException {
        ArrayList<String> responseStrings = new ClientWebSocket(connectionAddress)
                .getResponses(numberOfResponses);

        ArrayList<Response> responseObjects = new ArrayList<>();
        responseStrings.forEach(n ->
                responseObjects.add(gson.fromJson(n, Response.class)));

        return responseObjects;
    }


    @Step("Шаг - получить гарантированный ответ с значениями A и B по адресу {10}")
    public Response getResponseWithAAndBGuaranteed(String connectionAddress) throws InterruptedException {
        int attempts = 0;
        while (attempts < 20) {
            ArrayList<Response> responses = getResponsesByNum(5, connectionAddress);
            for (Response response : responses) {
                if (response.getData().getA() != null && response.getData().getB() != null &&
                        !response.getData().getA().isEmpty() && !response.getData().getB().isEmpty()) {
                    return response;
                }
            }
            attempts += 1;
        }
        Assertions.fail("No response with A and B guaranteed for 20 attempts");
        return null;
    }
}
