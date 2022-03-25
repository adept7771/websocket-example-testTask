package tests;

import dataModels.Response;
import etc.Settings;
import io.qameta.allure.*;
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
    @Severity(SeverityLevel.NORMAL)
    public void uNextGreaterThenPreviousTest() throws InterruptedException {
        ArrayList<Response> responses =
                getDataSteps.getResponsesByNum(20, Settings.connectAddress);
        validationSteps.validateUNextGreaterThenPrevious(responses);
    }

    @Test
    @DisplayName("Максимальное первое знач. B меньше минимального первого знач. A")
    @Severity(SeverityLevel.BLOCKER)
    public void maxFirstBLessMinFirstA() throws InterruptedException {
        Response response =
                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
        validationSteps.firstMaxBLessFirstMinA(response);
    }

//    @Test
//    @Disabled
//    @DisplayName("Первое значение B меньше первого значения А")
//    @Severity(SeverityLevel.BLOCKER)
//    public void firstBLessFirstA() throws InterruptedException { // не верно понял условие, игнорим
//        Response response =
//                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
//        validationSteps.firstBLessFirstA(response);
//    }

//    @Test
//    @Disabled
//    @DisplayName("Первое МАКС значение B меньше первого МИНИМАЛЬНОГО значения А")
//    @Severity(SeverityLevel.BLOCKER)
//    public void firstMaxBLessFirstMinA() throws InterruptedException { // не верно понял условие, игнорим
//        Response response =
//                getDataSteps.getResponseWithAAndBGuaranteed(Settings.connectAddress);
//        validationSteps.firstMaxBLessFirstMinA(response);
//    }

    GetDataSteps getDataSteps = new GetDataSteps();
    ValidationSteps validationSteps = new ValidationSteps();
}
