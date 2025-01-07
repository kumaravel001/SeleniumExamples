package com.ktech.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointersApproach {


    public static void main(String[] args) {
        int[] integerArray = {1,2,3,4,5,6};
       System.out.println(Arrays.toString(findingPair(integerArray,8)));

    }

    public static Object[] findingPair(int[] integerArray,  int target) {
        Arrays.sort(integerArray);
        List<Integer> result = new ArrayList<>();
        for (int l = 0; l < integerArray.length - 2; l++)
        {
        for (int r = l + 1; r < integerArray.length; r++) {
            int sum = integerArray[l] + integerArray[r];
            if (sum == target) {
                result.add(integerArray[l]);
                result.add(integerArray[r]);
            }
        }
    }
        if(result.size()==0)
        {
            result.add(-1);
            result.add(-1);
        }

        return result.toArray();

    }
}
