package ru.marakhov.academ_android.task2.ui.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.marakhov.academ_android.task2.data.ContentDataProvider;
import ru.marakhov.academ_android.task2.R;
import ru.marakhov.academ_android.task2.ui.adapters.TitleListAdapter;

public class TitleListFragment extends Fragment implements AdapterView.OnItemClickListener
{
	public static final String TAG = "TitleListFragment";
	private ArrayList<ContentDataProvider> mContentArray;

	public interface SelectInterface{
		void selectItem(int position);
	}

	public static TitleListFragment newInstance()
	{
		return new TitleListFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View fragmentView=inflater.inflate(R.layout.fragment_title_list, container, false);

		ListView titlesList=(ListView) fragmentView.findViewById(R.id.title_list_fragment_list_view_menu);
		TitleListAdapter adapter=new TitleListAdapter(getActivity().getApplicationContext(), mContentArray);
		titlesList.setAdapter(adapter);
		titlesList.setOnItemClickListener(this);
		return fragmentView;
	}

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);
		ContentDataProvider.ContentInterface contentInterface =(ContentDataProvider.ContentInterface)getActivity();
		mContentArray = contentInterface.getContentArray();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		SelectInterface selectInterface= (SelectInterface) getActivity();
		selectInterface.selectItem(position);
	}
}
