package com.erichay.bestschedule;

import java.util.Date;

public class Task
{
    //Constants
    final private double ratingConversion[] = {1, 2, 3, 4, 5, 1, 1.5, 2, 2.5, 3};
    final private int MINS_IN_HOURS = 60;
    final private int SECONDS_IN_MINS = 60;


    //Instance Variables
    private int hours = 0;
    private Date dueDate;
    private String name = "";
    private long uniqueID = 0;
    private double priority = 1;
    private double priorityNumber = 0;

    //Static variables
    private static int newID = 0;

    //Constructors

    /**
     * Creates a task that dictates what the user has to do
     */
    public Task()
    {
        dueDate = new Date();
        uniqueID = newID;
        newID++;
    }

    public Task(int taskHours, Date taskDueDate, String taskName, int taskPriority)
    {
        hours = taskHours;
        dueDate = taskDueDate;
        name = taskName;
        uniqueID = newID;
        priority = taskPriority;
        //Calculate the priority number
        priorityNumber = this.calculatePriorityNumber(taskDueDate, calculateAdjustedHours(convertPriority(taskPriority), taskHours));
        //Move to next ID
        newID++;
    }

    //Methods
    //Getters
    public int getHours()
    {
        return hours;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public String getName()
    {
        return name;
    }

    public double getPriority()
    {
        return priority;
    }

    public double getPriorityNumber()
    {
        return priorityNumber;
    }

    public long getUniqueID()
    {
        return uniqueID;
    }

    //Setters
    public void setHours(int hours)
    {
        this.hours = hours;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    //Calculation Methods
    public double calculateAdjustedHours(double priorityBeforeCalc, int hoursBeforeCalc)
    {
        double adjustedHours = priorityBeforeCalc * hoursBeforeCalc;
        return adjustedHours;
    }

    public double convertPriority(int initialPriority)
    {
        double convertedPriority;
        //If the priority is zero
        if(initialPriority == 0)
        {
            convertedPriority = 1;
        }
        else
        {
            convertedPriority = ratingConversion[initialPriority + 5];
        }
        return convertedPriority;
    }

    public long calculatePriorityNumber(Date taskDueDate, double taskAdjustedPriority)
    {
        long taskPriorityNumber = (taskDueDate.getTime()/1000) - (System.currentTimeMillis()/1000) - ((int)taskAdjustedPriority * (MINS_IN_HOURS) * (SECONDS_IN_MINS));
        //Convert priority number back to hours
        taskPriorityNumber = taskPriorityNumber / ((MINS_IN_HOURS) * (SECONDS_IN_MINS));
        return taskPriorityNumber;
    }
}