package com.perfani.ddg.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOService
{
    public static String readAllLines(String filePath) throws IOException
    {
        StringBuilder strBuilder = new StringBuilder();
        
        for (String line : Files.readAllLines(Paths.get(filePath)))
        {
            strBuilder.append(line);
        }
        
        return strBuilder.toString();
    }

    public static boolean createFolder(String path)
    {
        return new File(path).mkdir();
    }
    
    public static void writeFile(String path, String content) 
           throws IOException
    {
        File file = new File(path);
        if (!file.exists())
        {
            file.createNewFile();
        }
        
        PrintWriter writer = new PrintWriter(file);
        writer.println(content);
        writer.close(); 
    }
}
