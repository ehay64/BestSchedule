package com.erichay.bestschedule;

import java.io.Serializable;
import java.util.Date;

/**
 * Task keeps tracks of the hours, due date, and priority.
 *
 * @date January 21, 2017
 * @author Jeffrey Yamasaki
 */

public class Task implements Comparable, Serializable
{
    //Constants
    final private double ratingConversion[] = {0, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6};
    final private int MINS_IN_HOURS = 60;
    final private int SECONDS_IN_MINS = 60;
    final private int NUMBER_POSSIBLE_STARS = 6;

    //Instance Variables
    private int hours = 0;
    private Date dueDate;
    private String name = "";
    private long uniqueID = 0;
    private double priority = 1;
    private double priorityNumber = 0;

    //Constructors

    /**
     * Creates a task that dictates what the user has to do, default behaviour
     */
    public Task()
    {
        dueDate = new Date();
        uniqueID = System.currentTimeMillis();
    }

    /**
     * Creates a task with a specific hour set, due date, name, and priority
     * @param taskHours The estimated number of hours needed to complete the task
     * @param taskDueDate The due date of the task
     * @param taskName The name of the task
     * @param taskPriority The priority of the task as a rating of five stars, zero is least important, 5 is the msot important
     */
    public Task(int taskHours, Date taskDueDate, String taskName, double taskPriority)
    {
        hours = taskHours;
        dueDate = taskDueDate;
        name = taskName;
        uniqueID = System.currentTimeMillis();
        priority = (int) taskPriority;
        //Calculate the priority number
        priorityNumber = this.calculatePriorityNumber(taskDueDate, calculateAdjustedHours(convertPriority((int)taskPriority), taskHours));
    }

    //Methods
    //Getters

    /**
     * Gets the hours of the task
     * @return Hours of the task
     */
    public int getHours()
    {
        return hours;
    }

    /**
     * Gets the due date of the app
     * @return Due date of the app
     */
    public Date getDueDate()
    {
        return dueDate;
    }

    /**
     * Gets the name of the task
     * @return Name of the task
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the priority of the task
     * @return Priority of the task
     */
    public double getPriority()
    {
        return priority;
    }

    /**
     * Gets the priority number of the app
     * @return Priority number of the app
     */
    public double getPriorityNumber()
    {
        return priorityNumber;
    }

    /**
     * Gets the unique ID of the task
     * @return Unique ID of the task
     */
    public long getUniqueID()
    {
        return uniqueID;
    }

    //Setters

    /**
     * Sets the number of hours left on the task and recalculates the priority number based on the change in hours
     * @param hours The number of hours left to work on the task
     */
    public void setHours(int hours)
    {
        this.hours = hours;
        //Changed hours after work was done
        this.priorityNumber = calculatePriorityNumber(this.dueDate, this.calculateAdjustedHours(this.convertPriority((int) this.priority), this.hours));
    }

    /**
     * Sets the due date
     * @param dueDate The due date of the task
     */
    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
        //Changed hours to reflect new due date
        this.priorityNumber = calculatePriorityNumber(this.dueDate, this.calculateAdjustedHours(this.convertPriority((int) this.priority), this.hours));
    }

    /**
     * Sets the name
     * @param name The name of the task
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the priority
     * @param priority The priority of the task
     */
    public void setPriority(double priority)
    {
        this.priority = priority;
        //Changed hours to reflect new priority
        this.priorityNumber = calculatePriorityNumber(this.dueDate, this.calculateAdjustedHours(this.convertPriority((int) this.priority), this.hours));
    }

    //Calculation Methods

    /**
     * Calculates the adjusted number of hours based on priority and the hours of the task
     * @param convertedPriority The priority multiplier after being converted
     * @param hoursBeforeCalc
     * @return The hours of hours adjusted after priority
     */
    public double calculateAdjustedHours(double convertedPriority, int hoursBeforeCalc)
    {
        return (convertedPriority * hoursBeforeCalc);
    }

    /**
     * Converts priority to a multiplication factor. The higher the priority, the higher the multiplication factor
     * @param initialPriority The priority chosen by the user with the star rating bar
     * @return The converted priority as a multiplication factor
     */
    public double convertPriority(int initialPriority)
    {
            return ratingConversion[initialPriority + NUMBER_POSSIBLE_STARS];
    }

    /**
     * Calculates the priority number using the due date, current date and adjusted hours
     * @param taskDueDate The due date of the task
     * @param taskAdjustedPriority The adjusted hours of the task based on priority
     * @return An integer that represents the task as a function of its due date, current date, and adjusted hours
     */
    public long calculatePriorityNumber(Date taskDueDate, double taskAdjustedPriority)
    {
        long taskPriorityNumber = ((taskDueDate.getTime()/1000) - ((System.currentTimeMillis()/1000))) - ((int)taskAdjustedPriority * (MINS_IN_HOURS) * (SECONDS_IN_MINS));
        //Convert priority number back to hours
        taskPriorityNumber = taskPriorityNumber / ((MINS_IN_HOURS) * (SECONDS_IN_MINS));
        return taskPriorityNumber;
    }

    @Override
    /**
     * Used to compare a pair of tasks. This method is used by the PriorityNumberComparator to sort all the tasks
     */
    public int compareTo(Object object)
    {
        Task task1 = (Task) object;
        //Compare the priority numbers
        if(this.priorityNumber < task1.priorityNumber)
        {
            return -1;
        }
        else if(this.priorityNumber > task1.priorityNumber)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}