package com.erichay.bestschedule;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

public class MainActivity extends Activity
{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setActionBar(toolbar);

        //Add 20 new tasks to the list
        for (int i = 0; i < 20; i++)
        {
            Resources.tasks.add(new Task(10, "Test Task", 3, 4));
        }

        ListView list = (ListView)findViewById(R.id.content);

        list.setAdapter(new TaskAdapter(this));

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

}
