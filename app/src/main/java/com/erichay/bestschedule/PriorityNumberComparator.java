package com.erichay.bestschedule;

import java.util.Comparator;

/**
 * Created by Jeffrey on 2017-01-21.
 */

public class PriorityNumberComparator implements Comparator<Task>
{
    public int compare(Task task1, Task task2)
    {
        //Compare the values
        return task1.compareTo(task2);
    }
}
