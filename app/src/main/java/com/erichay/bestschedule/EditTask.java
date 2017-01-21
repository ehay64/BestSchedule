package com.erichay.bestschedule;

import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toolbar;

import java.util.Date;

public class EditTask extends Activity
{
    private long taskId;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    private Date date;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.edit_toolbar);
        setActionBar(toolbar);

        //Get the task id
        taskId = getIntent().getLongExtra("TASK_ID", -1);

        //Set the text for the toolbar based on what we're doing
        if (taskId == -1)
        {
            toolbar.setTitle("New Task");
        }
        else
        {
            Task task = Resources.getTaskById(taskId);
            toolbar.setTitle(task.getName());
            ((EditText)findViewById(R.id.edit_name)).setText(task.getName());
            ((EditText)findViewById(R.id.edit_hours)).setText(Integer.toString(task.getHours()));
            ((EditText)findViewById(R.id.edit_date)).setText(task.getDueDate().toString());
            ((RatingBar)findViewById(R.id.edit_rating)).setRating((float)task.getPriority());
            date = task.getDueDate();
        }
    }

    public void getDate(View v)
    {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getFragmentManager(), "datePicker");
    }

    public void setDate(int y, int m, int d)
    {
        year = y;
        month = m;
        day = d;

        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getFragmentManager(), "timePicker");
    }

    public void setTime(int h, int m)
    {
        hour = h;
        minute = m;

        date = new Date(year - 1900, month, day, hour, minute);
        ((EditText)findViewById(R.id.edit_date)).setText(date.toString());
    }

    public void save(View v)
    {
        String name = ((EditText)findViewById(R.id.edit_name)).getText().toString();
        int hours = Integer.parseInt(((EditText)findViewById(R.id.edit_hours)).getText().toString());
        double rating = ((RatingBar)findViewById(R.id.edit_rating)).getRating();

        if (taskId == -1)
        {
            Task task = new Task(hours, date, name, rating);
            Resources.tasks.add(task);
            //Resort tasks
        }
        else
        {
            Task task = Resources.getTaskById(taskId);
            task.setName(name);
            task.setDueDate(date);
            task.setHours(hours);
            task.setPriority((int)rating);
        }

        finish();
    }

}
