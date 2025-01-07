package com.ktech.java_practice;


import java.util.Arrays;
import java.util.Random;

public class PracticeChildClass extends Practice {

    public PracticeChildClass(int i, int j)
    {
        super(i,j);

    }
    public  PracticeChildClass(int a)
    {

    }

    @Override
    public void testMethod()
    {
        super.testMethod();

    }

    public static int testd(int n)
    {
        if(n==1)
        {
            return 1;
        }
        return n*testd(n-1);
    }

    public static void main(String... args) {

        System.out.println(testd(5));
        Random randon = new Random();
        String[] arr = {"a"};
        Arrays.sort(arr);
        Arrays.binarySearch(arr,"a");

//        StringBuilder stringBuilder = new StringBuilder("test");
//        stringBuilder.insert(2,"t");
//        stringBuilder.setLength(2);
//        System.out.println(stringBuilder.toString());
//        String s = "aest";
//        String t = "best";
//        String[] arr = {"t","e","j"};
//
////       boolean isMatches = s.regionMatches(true,1,t,2,3);
//       System.out.println( s.compareTo(t));
//       s.length();
//       s.isEmpty();
//       s.isBlank();
//       s.charAt(0);
//       s.indexOf("t");
//       s.lastIndexOf("t");
//       String.join(",",arr);



    }
}
