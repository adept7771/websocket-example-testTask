package tests;

import dataModels.Response;
import etc.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GetDataSteps;
import steps.ValidationSteps;

import java.util.ArrayList;

public class ExampleTests {

    // Settings.connectAddress можно так же куда-то под капот занести, чтоб постоянно не вызывать.

    @Test
    public void uNextGreaterThenPrevious() throws InterruptedException {
        ArrayList<Response> responses =
                getDataSteps.getResponsesByNum(20, Settings.connectAddress);
        Assertions.assertTrue(validationSteps.validateUNextGreaterThenPrevious(responses));
    }

    // у вас во втором тесте ошибка. Имеется требование по максимальности для b первого числа. В примере
    // говорится про числа 0	:	0.07093900 и 1	:	7.66120000 при этом максимальным указано число
    // 0.07093900. Поэтому этот тест я сделал просто на проверку первых числе в A / B. Следующий уже
    // с требованием максимальности к первому числу.

    @Test
    public void firstBLessFirstA() throws InterruptedException {
        Response response =
                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
        Assertions.assertTrue(validationSteps.firstBLessFirstA(response),
                "First B bigger then first A value");
    }

    @Test
    public void firstMaxBLessFirstMinA() throws InterruptedException {
        Response response =
                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
        Assertions.assertTrue(validationSteps.firstMaxBLessFirstMinA(response),
                "First max B bigger then first min A");
        // можно вынести значения еще в какие-то под методы, чтобы ошибка была более наглядна
    }

    GetDataSteps getDataSteps = new GetDataSteps();
    ValidationSteps validationSteps = new ValidationSteps();
}
