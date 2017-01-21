package com.erichay.bestschedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import android.content.Context;

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

    public static void save()
    {
        try
        {
            FileOutputStream writeStream = new FileOutputStream("taskList");
            ObjectOutputStream writeObjectStream = new ObjectOutputStream(writeStream);
            writeObjectStream.writeObject(tasks);
            //Close streams
            writeObjectStream.close();
            writeStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void load()
    {
        try
        {
            FileInputStream readStream = new FileInputStream("taskList");
            ObjectInputStream readObjectStream = new ObjectInputStream(readStream);
            tasks = (ArrayList<Task>) readObjectStream.readObject();
            //Close streams
            readObjectStream.close();
            readStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
