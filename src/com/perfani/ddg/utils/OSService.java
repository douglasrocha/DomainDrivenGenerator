package com.perfani.ddg.utils;

public class OSService
{
    public static boolean isWindows()
    {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
}
