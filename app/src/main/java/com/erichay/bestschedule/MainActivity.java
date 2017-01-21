package com.erichay.bestschedule;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.Date;

public class MainActivity extends Activity
{

    ListView list;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setActionBar(toolbar);

        //Add 20 new tasks to the list
        for (int i = 0; i < 20; i++)
        {
            Resources.tasks.add(new Task(10, new Date(),"Test Task: " + i, 3));
        }

        //Get the list view
        list = (ListView)findViewById(R.id.content);
        //Set the adapter
        list.setAdapter(new TaskAdapter(this));
        //Set the action that occurs when an item is long clicked
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("Deleting Task", "task id = " + id);
                deleteTask(id);
                return true;
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.newTask:
            {
                //Launch the new activity here
                Log.d("MainActivity", "New Task Activity Launched");
                break;
            }

            default:
            {
                super.onOptionsItemSelected(item);
                break;
            }
        }

        return true;
    }

    public void deleteTask(final long taskId)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete " + Resources.getTaskById(taskId).getName() + "?").setTitle("Delete Task");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                Resources.deleteTask(taskId);
                list.invalidateViews();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                //Do nothing
            }
        });

        builder.show();
    }

}
