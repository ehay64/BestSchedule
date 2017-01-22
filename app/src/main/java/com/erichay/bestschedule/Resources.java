package com.erichay.bestschedule;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

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

    public static void save(Context context)
    {
        File file = new File(context.getFilesDir(), "taskList");

        Log.d("File", file.toString());

        try
        {
            FileOutputStream writeStream = new FileOutputStream(file);
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

    public static void load(Context context)
    {
        File file = new File(context.getFilesDir(), "taskList");

        try
        {
            FileInputStream readStream = new FileInputStream(file);
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

    public static void sortTasks()
    {
        Collections.sort(tasks, new PriorityNumberComparator());
    }
}
