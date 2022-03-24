package steps;

import com.google.gson.Gson;
import dataObj.Response;
import helpers.ClientWebSocket;

import java.util.ArrayList;

public class GetDataSteps {

    Gson gson = new Gson();

    public ArrayList<Response> getResponsesByNum(int numberOfResponses, String connectionAddress) throws InterruptedException {
        ArrayList<String> responseStrings = new ClientWebSocket(connectionAddress)
                .getResponses(numberOfResponses);

        ArrayList<Response> responseObjects = new ArrayList<>();
        responseStrings.forEach(n ->
                responseObjects.add(gson.fromJson(n, Response.class)));

        return responseObjects;
    }


}
