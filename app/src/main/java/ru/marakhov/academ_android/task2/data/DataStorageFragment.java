package ru.marakhov.academ_android.task2.data;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class DataStorageFragment extends Fragment
{
	public static final String TAG = "DataStorageFragment";
	private ArrayList<ContentDataProvider> mContentArray = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}


	public ArrayList<ContentDataProvider> getContentArray(Context context)
	{
		if (mContentArray == null)
		{
			mContentArray = ContentDataProvider.fillContentArray(context);
		}
		return mContentArray;
	}
}
