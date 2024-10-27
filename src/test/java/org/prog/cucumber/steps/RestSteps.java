package org.prog.cucumber.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import org.prog.dto.ResultsDto;
import org.prog.util.DataHolder;

import java.util.List;
import java.util.stream.Collectors;

public class RestSteps {

    @Given("Request {int} users from randomuser.me as {string}")
    public void getUsers(int amount, String alias) {
        List<String> names = RestAssured.given()
                .baseUri("https://randomuser.me/")
                .basePath("/api/")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResultsDto.class)
                .getResults()
                .stream()
                .map(u -> u.getName().getFirst() + " " + u.getName().getLast())
                .collect(Collectors.toList());

        DataHolder.HOLDER.put(alias, names);
    }
}
