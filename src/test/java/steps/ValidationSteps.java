package steps;

import dataModels.Response;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class ValidationSteps {

    public boolean validateUNextGreaterThenPrevious(ArrayList<Response> responses) {
        Response cache = null;
        for (Response response : responses) {
            if (cache != null) {
                Assertions.assertTrue(response.getData().getu() > cache.getData().getu());
            }
            cache = response;
        }
        return true;
    }

    public boolean firstBLessFirstA(Response response) {
        return Double.parseDouble(response.getData().getB().get(0).get(0)) <
                Double.parseDouble(response.getData().getA().get(0).get(0));
    }

    public boolean firstMaxBLessFirstMinA(Response response) {
        double firstNumB = Double.parseDouble(response.getData().getB().get(0).get(0));
        double secondNumB = Double.parseDouble(response.getData().getB().get(0).get(1));
        double firstNumA = Double.parseDouble(response.getData().getA().get(0).get(0));
        double secondNumA = Double.parseDouble(response.getData().getA().get(0).get(1));
        double maxFirstB = Math.max(firstNumB, secondNumB);
        double minFirstA = Math.min(firstNumA, secondNumA);
        System.out.printf("Max first B - %s Min first A - %s", maxFirstB, minFirstA);
        // можно прикрутить логгер, для более качественного логгирования
        return maxFirstB < minFirstA;
    }
}
