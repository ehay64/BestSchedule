package com.erichay.bestschedule;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class TaskAdapter implements ListAdapter
{

    private Context contex;

    public TaskAdapter(Context c)
    {
        contex = c;
    }

    public void registerDataSetObserver(DataSetObserver observer)
    {

    }

    public void unregisterDataSetObserver(DataSetObserver observer)
    {

    }

    public int getCount()
    {
        return Resources.tasks.size();
    }

    public Object getItem(int position)
    {
        return Resources.tasks.get(position);
    }

    public long getItemId(int position)
    {
        return Resources.tasks.get(position).getUniqueID();
    }

    public boolean hasStableIds()
    {
        return true;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)contex.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v;
        Task t = Resources.tasks.get(position);

        if (convertView == null)
        {
            v = inflater.inflate(R.layout.task_list_item, null);
        }
        else
        {
            v = convertView;
        }

        //Set the title of the task
        TextView title = (TextView)v.findViewById(R.id.task_title);
        title.setText(t.getName());

        //Set the hours of the task
        TextView hours = (TextView)v.findViewById(R.id.task_hours);
        hours.setText("Hours: " + Integer.toString(t.getHours()));

        //Set the due date of the task
        TextView date = (TextView)v.findViewById(R.id.due_date);
        date.setText("Due Date: " + t.getDueDate().toString());

        //Set the number of stars
        RatingBar bar = (RatingBar)v.findViewById(R.id.rating);
        bar.setRating((float)t.getPriority());

        return v;
    }

    public int getItemViewType(int position)
    {
        return 1;
    }

    public int getViewTypeCount()
    {
        return 1;
    }

    public boolean isEmpty()
    {
        if (Resources.tasks.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean areAllItemsEnabled()
    {
        return true;
    }

    public boolean isEnabled(int position)
    {
        return true;
    }
}
