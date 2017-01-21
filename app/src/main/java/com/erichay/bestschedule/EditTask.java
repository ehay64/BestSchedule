package com.erichay.bestschedule;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

public class EditTask extends Activity
{
    private long taskId;

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

        }
    }

    public void getDate(View v)
    {


    }

}
