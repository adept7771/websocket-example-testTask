package tests;

import dataModels.Response;
import etc.Settings;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import steps.GetDataSteps;
import steps.ValidationSteps;

import java.util.ArrayList;

@Feature("Крутая фича")
@Story("Сторя с функционалом")
@Owner("Dmitry Potapov")
@Link(url = "https://ya.ru", name = "Линк на доку")
@Execution(ExecutionMode.CONCURRENT) // можно запускать многопоточно)
public class ExampleTests {

    // Settings.connectAddress можно так же куда-то под капот занести, чтоб постоянно не вызывать.

    @Test
    @DisplayName("Значение U больше предыдущего")
    @Severity(SeverityLevel.BLOCKER)
    public void uNextGreaterThenPrevious() throws InterruptedException {
        ArrayList<Response> responses =
                getDataSteps.getResponsesByNum(20, Settings.connectAddress);
        validationSteps.validateUNextGreaterThenPrevious(responses);
    }

    // у вас во втором тесте ошибка. Имеется требование по максимальности для b первого числа. В примере
    // говорится про числа 0	:	0.07093900 и 1	:	7.66120000 при этом максимальным указано число
    // 0.07093900. Поэтому этот тест я сделал просто на проверку первых числе в A / B. Следующий уже
    // с требованием максимальности к первому числу.

    @Test
    @DisplayName("Первое значение B меньше первого значения А")
    @Severity(SeverityLevel.BLOCKER)
    public void firstBLessFirstA() throws InterruptedException {
        Response response =
                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
        validationSteps.firstBLessFirstA(response);
    }

    @Test
    @DisplayName("Первое МАКС значение B меньше первого МИНИМАЛЬНОГО значения А")
    @Severity(SeverityLevel.BLOCKER)
    public void firstMaxBLessFirstMinA() throws InterruptedException {
        Response response =
                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
        validationSteps.firstMaxBLessFirstMinA(response);
    }

    GetDataSteps getDataSteps = new GetDataSteps();
    ValidationSteps validationSteps = new ValidationSteps();
}
