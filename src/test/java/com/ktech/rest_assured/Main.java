package com.ktech.rest_assured;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Main {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String addResponse = given().header("Content-Type", "application/json")
                .queryParam("key", "qaclick123")
                .body(Payload.addPayload())
                .when().post("maps/api/place/add/json")
                .then().extract().response().asString();
       String placeId = fetchJsonValue(addResponse,"place_id");
       System.out.println(placeId);
       String newAddress = "110,Rue Prud";

       String updateResponse = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
               .body("{\r\n" +
                        "\"place_id\":\""+placeId+"\",\r\n" +
                        "\"address\":\""+newAddress+"\",\r\n" +
                        "\"key\":\"qaclick123\"\r\n" +
                        "}")
               .when().put("maps/api/place/update/json").then().extract().response().asString();
       System.out.println(updateResponse);

        String getResponse = given().header("Content-Type","application/json").queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("/maps/api/place/get/json").then().extract().response().asString();
        System.out.println(getResponse);


    }



    public static String fetchJsonValue(String response, String path) {
        JsonPath js = new JsonPath(response);
        String jsonValue = js.getString(path);
        return jsonValue;
    }
}
