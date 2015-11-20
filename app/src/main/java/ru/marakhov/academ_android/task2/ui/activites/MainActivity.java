package ru.marakhov.academ_android.task2.ui.activites;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import ru.marakhov.academ_android.task2.R;
import ru.marakhov.academ_android.task2.data.ContentDataProvider;
import ru.marakhov.academ_android.task2.data.DataStorageFragment;
import ru.marakhov.academ_android.task2.ui.fragments.DetailsFragment;
import ru.marakhov.academ_android.task2.ui.fragments.TitleListFragment;

public class MainActivity extends FragmentActivity implements ContentDataProvider.ContentInterface, TitleListFragment.SelectInterface
{
	private boolean mIsWideScreen;
	private FragmentManager mFragmentManager;
	private TitleListFragment mTitleListFragment;
	private DetailsFragment mDetailsFragment;
	private int mPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mIsWideScreen = (findViewById(R.id.activity_main_listfragment) != null);

		mFragmentManager = getSupportFragmentManager();
		mTitleListFragment = (TitleListFragment) mFragmentManager.findFragmentByTag(TitleListFragment.TAG);
		mDetailsFragment = (DetailsFragment) mFragmentManager.findFragmentByTag(DetailsFragment.TAG);

		if (savedInstanceState != null)
		{
			mPosition = savedInstanceState.getInt("Position");
			if (mDetailsFragment != null)
			{
				mDetailsFragment.setContent(getContentArray().get(mPosition));
			}
		}

		if (mIsWideScreen)
		{
			if (mTitleListFragment != null)
			{
				mFragmentManager.beginTransaction().remove(mTitleListFragment).commit();
				mTitleListFragment = null;
			}
		}
		else
		{
			if ((mTitleListFragment == null) && (mDetailsFragment == null))
			{
				FragmentTransaction ft = mFragmentManager.beginTransaction();
				mTitleListFragment = TitleListFragment.newInstance();
				ft.add(R.id.activity_main_frame_layout_main, mTitleListFragment, TitleListFragment.TAG);
				ft.commit();
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt("Position", mPosition);
	}

	@Override
	public void onBackPressed()
	{
		if (mIsWideScreen)
		{
			finish();
		}
		else
		{
			if (mDetailsFragment == null)
			{
				finish();
			}
			else
			{
				mTitleListFragment = TitleListFragment.newInstance();
				FragmentTransaction ft = mFragmentManager.beginTransaction();
				ft.replace(R.id.activity_main_frame_layout_main, mTitleListFragment, TitleListFragment.TAG);
				ft.commit();
				mDetailsFragment = null;
			}
		}
	}

	@Override
	public ArrayList<ContentDataProvider> getContentArray()
	{
		FragmentManager fragmentManager=getSupportFragmentManager();
		DataStorageFragment dataStorageFragment = (DataStorageFragment) fragmentManager.findFragmentByTag(DataStorageFragment.TAG);
		if (dataStorageFragment == null)
		{
			dataStorageFragment = new DataStorageFragment();
			fragmentManager.beginTransaction().add(dataStorageFragment, DataStorageFragment.TAG).commit();
		}
		return dataStorageFragment.getContentArray(getApplicationContext());
	}

	@Override
	public void selectItem(int position)
	{
		mPosition = position;

		mDetailsFragment = DetailsFragment.newInstance();
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.replace(R.id.activity_main_frame_layout_main, mDetailsFragment, DetailsFragment.TAG);
		transaction.commit();
		mTitleListFragment = null;
		mDetailsFragment.setContent(getContentArray().get(mPosition));
	}
}
