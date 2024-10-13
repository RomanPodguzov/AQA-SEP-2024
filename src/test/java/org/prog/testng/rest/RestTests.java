package org.prog.testng.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.ResultsDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestTests {

    @Test
    public void randomUserTest() {
        RequestSpecification specification = RestAssured.given();
        specification.baseUri("https://randomuser.me/");
        specification.basePath("/api/");
        specification.queryParam("inc", "gender,name,nat");
        specification.queryParam("noinfo");
        specification.queryParam("results", 10);

        Response response = specification.get();
        ValidatableResponse validatableResponse = response.then();

        validatableResponse.statusCode(200);
        validatableResponse.contentType(ContentType.JSON);
        response.body().prettyPrint();
//        validatableResponse.body("results.findAll { it.gender == 'female' }.size()", greaterThan(0));
//        List<String> genders = response.jsonPath().getList("results.gender");
//
//        for (String g : genders) {
//            System.out.println(g);
//        }

        // SERIALIZATION   : Java >> JSON
        // DESERIALIZATION : JSON >> Java

        ResultsDto dto = response.body().as(ResultsDto.class);
//        List<String> ladiesNames = new ArrayList<>();
//        for (UserDto user : dto.getResults()) {
//            if ("female".equals(user.getGender())) {
//                ladiesNames.add(user.getName().getFirst() + " " + user.getName().getLast());
//            }
//        }
//
//        for (String un : ladiesNames) {
//            System.out.println(un);
//        }

        Assert.assertTrue(dto.getResults()
                .stream()
                .anyMatch(user -> "female".equals(user.getGender()))
        );

        dto.getResults().stream()
                .filter(u -> "female".equals(u.getGender()))
                .map(u -> u.getName().getFirst() + " " + u.getName().getLast())
                .forEach(un -> System.out.println(un));
    }

    @Test
    public void streamDemo() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        strings.add("f");
        strings.add("g");
        strings.add("j");
        strings.add("h");
        strings.add("k");
        String results = "";

    }
}
