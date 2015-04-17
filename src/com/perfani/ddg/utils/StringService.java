package com.perfani.ddg.utils;

public class StringService
{
    public static String getFirstCharToUpper(String input)
    {
        char first = Character.toUpperCase(input.charAt(0));
        return first + input.substring(1);
    }

}
