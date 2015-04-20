/***
 *    Copyright 2015 Douglas Bellon Rocha
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
