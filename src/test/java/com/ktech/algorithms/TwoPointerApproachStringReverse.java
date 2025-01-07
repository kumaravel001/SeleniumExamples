package com.ktech.algorithms;

public class TwoPointerApproachStringReverse {

    public static void main(String[] args) {

        //System.out.println(reverseAString("hello"));
        //System.out.println(longestSubString("pwwkew"));
        // System.out.println(vowels("leetcode",3));
        System.out.println(caesarCipher("1X7T4VrCs23k4vv08D6yQ3S19G4rVP188M9ahuxB6j1tMGZs1m10ey7eUj62WV2exLT4C83zl7Q80M",27));


    }

    public static String caesarCipher(String s, int k)
    {

        char[] characterArray = s.trim().toCharArray();
        char[] encryptedCharArray = new char[characterArray.length];

        for(int i =0; i <characterArray.length;i++)
        {
            if(characterArray[i]>=65 && characterArray[i]<=90)
            {
                encryptedCharArray[i]=  ((characterArray[i]+k)>90)? (char) (64+((characterArray[i]+k)-90)): (char) (characterArray[i]+k);
            }
            else if(characterArray[i]>=97 && characterArray[i]<=122)
            {
                encryptedCharArray[i]=  ((characterArray[i]+k)>122)? (char) (96+((characterArray[i]+k)-122)): (char) (characterArray[i]+k);
            }
            else
            {
                encryptedCharArray[i] = characterArray[i];
            }
        }


        return new String(encryptedCharArray);
    }

    public static int vowels(String s, int k)
    {
        char[] characterArray = s.trim().toCharArray();
        int l = 0;
        int r = k;
        int maxVowel = 0;

        while ((l+r)<s.length())
        {
            int vowelCount = 0;
            for(int i = l; i<r;i++)
            {

                switch (characterArray[i])
                {
                    case 'a': case 'e': case'i': case 'o': case 'u':
                        vowelCount++;
                        break;
                    default:
                        break;
                }

            }
            maxVowel = Math.max(maxVowel,vowelCount);
            l++;
            r= l+k;

        }
        return maxVowel;

    }


    public static int longestSubString(String s)
    {

        char[] characterArray = s.trim().toCharArray();
        String longestSubString = "";
        int max = 0;

        for(int l=0; l <s.length()-1;l++)
        {
            longestSubString = "";
            longestSubString = longestSubString+characterArray[l];

            for(int r =l+1; r<s.length();r++ )
            {
                if(!longestSubString.contains(""+characterArray[r]))
                {
                    longestSubString = longestSubString+ characterArray[r];

                }
                else
                {
                    System.out.println(longestSubString);
                    max = Math.max(max,longestSubString.length());
                    break;
                }
            }
        }

        return max;
    }



    public static String reverseAString(String s)
    {
        if(!s.trim().isEmpty())
        {
            char[] characterArray = s.toCharArray();
            int l = 0;
            int r = characterArray.length-1;
            while (l<r)
            {
                char temp = characterArray[l];
                characterArray[l] = characterArray[r];
                characterArray[r] = temp;
                l++;
                r--;
            }
          return new String(characterArray);
        }

        return "";
    }


}
