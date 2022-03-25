package steps;

import dataModels.Response;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ValidationSteps {

    @Step("Шаг - проверить список ответов, что следующее U больше предыдущего")
    public void validateUNextGreaterThenPrevious(ArrayList<Response> responses) {
        Response cache = null;
        for (Response response : responses) {
            if (cache != null) {
                Assertions.assertTrue(response.getData().getu() > cache.getData().getu(),
                        String.format("Current U: %s is lower then previous: %s!",
                                response.getData().getu(), cache));
            }
            cache = response;
        }
    }

    @Step("Шаг - проверить ответ, что первое В меньше первого А")
    public void firstBLessFirstA(Response response) {
        System.out.println();
        double firstB = Double.parseDouble(response.getData().getB().get(0).get(0));
        double firstA = Double.parseDouble(response.getData().getA().get(0).get(0));
        Assertions.assertTrue(firstB < firstA,
                String.format("First B: %s bigger then first min A: %s", firstB, firstA));
    }

    @Step("Шаг - проверить ответ, что первое максимальное В больше первого минимального А")
    public void firstMaxBLessFirstMinA(Response response) {
        ArrayList<Double> firstAValues = new ArrayList<>();
        ArrayList<Double> firstBValues = new ArrayList<>();

        response.getData().getB().forEach(n -> firstBValues.add(Double.valueOf(n.get(0))));
        response.getData().getA().forEach(n -> firstAValues.add(Double.valueOf(n.get(0))));

        Double minFirstA = firstAValues.stream().reduce(Double::min).get();
        Double maxFirstB = firstBValues.stream().reduce(Double::max).get();
        //System.out.printf("Max first B - %s Min first A - %s", maxFirstB, minFirstA);
        // можно прикрутить логгер, для более качественного логгирования
        Assertions.assertTrue(maxFirstB < minFirstA,
                String.format("First max B: %s bigger then first min A: %s", maxFirstB, minFirstA));
    }
}
