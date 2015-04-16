package com.perfani.ddg.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexService
{
    public static List<String> getMatches(String input, String regexPattern)
    {
        ArrayList<String> outputs = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find())
        {
            outputs.add(matcher.group());
        }
        
        return outputs;
    }
}
