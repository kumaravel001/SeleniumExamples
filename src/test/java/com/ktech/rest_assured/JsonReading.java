package com.ktech.rest_assured;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JsonReading {

    public static void main(String[] args) throws IOException {
      String response = JsonMock.mockResponse();
        JsonPath js = new JsonPath(response);
        int numberOfCourses = js.getInt("courses.size()");
        System.out.println("Number Of Courses = "+numberOfCourses);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount = "+purchaseAmount);
        System.out.println("First Course Title = "+js.getString("courses[0].title"));
        int coursesPriceSum = 0;
        for(int i =0; i <numberOfCourses; i++ )
        {
            System.out.println("Course Title ="+js.getString("courses["+i+"].title"));
            int price = js.getInt("courses["+i+"].price");
            System.out.println("Course price ="+ price);
            coursesPriceSum +=price;

            if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
            {
                System.out.println("Number Of Copies Cold by RPA =" + js.getInt("courses["+i+"].copies"));
            }
        }

        if(coursesPriceSum==purchaseAmount)
        {
            System.out.println("Course Price and Purchase Amount is equal");
        }


    }

}
