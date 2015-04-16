package com.perfani.ddg.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService
{
    public String readAllLines(String filePath) throws IOException
    {
        StringBuilder strBuilder = new StringBuilder();
        
        for (String line : Files.readAllLines(Paths.get(filePath)))
        {
            strBuilder.append(line);
        }
        
        return strBuilder.toString();
    }

}
