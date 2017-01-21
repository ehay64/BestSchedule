package com.erichay.bestschedule;


import java.util.ArrayList;

public class Resources
{
    public static ArrayList<Task> tasks = new ArrayList<Task>();

    public static boolean deleteTask(long id)
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks.get(i).getUniqueID() == id)
            {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public static Task getTaskById(long id)
    {
        for (int i = 0; i < tasks.size(); i++)
        {
            if (tasks.get(i).getUniqueID() == id)
            {
                return tasks.get(i);
            }
        }

        return null;
    }
}
