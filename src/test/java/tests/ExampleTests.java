package tests;

import dataObj.Response;
import etc.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import steps.GetDataSteps;
import steps.ValidationSteps;

import java.util.ArrayList;

public class ExampleTests {

    @Test
    public void uNextGreaterThenPrevious() throws InterruptedException {
        ArrayList<Response> responses =
                getDataSteps.getResponsesByNum(20, Settings.connectAddress);
        Assertions.assertTrue(validationSteps.validateUNextGreaterThenPrevious(responses));
    }

    @Test
    public void maxFirstBLessMinFirstA() throws InterruptedException {
        ArrayList<Response> responses =
                getDataSteps.getResponsesByNum(20, Settings.connectAddress);
        Assertions.assertTrue(validationSteps.validateUNextGreaterThenPrevious(responses));
    }

    GetDataSteps getDataSteps = new GetDataSteps();
    ValidationSteps validationSteps = new ValidationSteps();
}
