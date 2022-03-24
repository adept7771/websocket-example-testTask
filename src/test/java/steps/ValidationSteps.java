package steps;

import dataObj.Response;
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
}
