package com.ktech.selenium;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
    public static void main(String[] args) {
        String s= "aabcbbcddeyy";
        String firstNonRepetitive ="";

        for(int i=0; i<s.length();i++)
        {

            int firstIndex = s.indexOf(s.charAt(i));
            int lastIndex = s.lastIndexOf(s.charAt(i));
            if(firstIndex==lastIndex)
            {
                firstNonRepetitive = ""+s.charAt(i);
                break;
            }
        }

        System.out.println(firstNonRepetitive);










//        String s = "test@gmail.com"; // test@gmail.co.uk;
//        Pattern pattern = Pattern.compile("\\w+@\\w+\\.(\\w){2,3}\\.*");
//        Matcher matcher = pattern.matcher(s);
//        while (matcher.find())
//        {
//            System.out.println(matcher.group()+ " is a valid email");
//        }





    }


}
