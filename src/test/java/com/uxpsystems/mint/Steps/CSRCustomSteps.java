package com.uxpsystems.mint.Steps;

/**
 * Created by sumadragos on 22/03/17.
 */

import com.google.inject.Inject;
import com.jayway.restassured.response.ResponseBody;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import lv.ctco.cukesrest.internal.RequestSpecificationFacade;
import lv.ctco.cukesrest.internal.ResponseFacade;
import lv.ctco.cukesrest.internal.context.GlobalWorldFacade;
import org.json.JSONObject;

/**
 * Created by sumadragos on 21/03/17.
 */



public class CSRCustomSteps {

    @Inject
    RequestSpecificationFacade requestSpecfacade;



    @Inject
    ResponseFacade responseFacade;

    @Inject
    GlobalWorldFacade world;


    private static String mintCSRSession = "";


    @And("^I pass the MINTsession to the next request$")
    public void iPassTheMINTsessionToTheNextRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        String headervalue = this.responseFacade.response().getHeader("Set-Cookie");
        System.out.println("header is " + headervalue);
        String sessionID = headervalue.substring(headervalue.indexOf("=")+1,headervalue.indexOf(";"));
        System.out.println("mint session is :" + sessionID);
        mintCSRSession = sessionID;
        this.requestSpecfacade.sessionId("JSESSIONID",sessionID);

        //throw new PendingException();
    }



    @Given("^I provide the following user details$")
    public void iProvideTheFollowingUserDetails(DataTable data) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ResponseBody responseBody = this.responseFacade.response().body();

        JSONObject response = new JSONObject(this.responseFacade.response().getBody().asString());
        JSONObject parameters = new JSONObject( response.get("parameters").toString());
        parameters.put(data.getGherkinRows().get(0).getCells().get(0).toString(),data.getGherkinRows().get(0).getCells().get(1).toString());
        parameters.put(data.getGherkinRows().get(1).getCells().get(0).toString(),data.getGherkinRows().get(1).getCells().get(1).toString());
        parameters.put(data.getGherkinRows().get(2).getCells().get(0).toString(),data.getGherkinRows().get(2).getCells().get(1).toString());
        response.put("parameters",parameters);
        response.remove("customerSystems");
        response.remove("displayMessage");
        response.remove("lastStep");


        System.out.println(response.toString());

        JSONObject request = new JSONObject();

        this.requestSpecfacade.body(response.toString());

        //throw new PendingException();
    }

    @And("^I use the session from the previous request$")
    public void iUseTheSessionFromThePreviousRequest() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        this.requestSpecfacade.sessionId("JSESSIONID",mintCSRSession);
        //throw new PendingException();
    }
}

