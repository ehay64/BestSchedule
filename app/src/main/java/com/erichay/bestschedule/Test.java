package com.erichay.bestschedule;

import java.util.Date;

public class Test
{
    public static void main(String[] args)
    {
        // TTask(int taskHours, Date taskDueDate, String taskName, int taskPriority)
        // t2 parameters
        int t2Hours = 40;
        Date t2DueDate = new Date();
        String t2Name = "Walk the dog";
        double t2Priority = 5.9;


        Task t1 = new Task();
        Task t2 = new Task(t2Hours, t2DueDate, t2Name, t2Priority);

        //print t2 attributes
        System.out.println("Name: "+t2.getName());
        System.out.println("ID: "+t2.getUniqueID());
        System.out.println("DueDate: "+t2.getDueDate());
        System.out.println("Hours: "+t2.getHours());
        System.out.println("Priority: "+t2.getPriority());

        System.out.println("Priority Number: "+t2.getPriorityNumber());


    }
}

