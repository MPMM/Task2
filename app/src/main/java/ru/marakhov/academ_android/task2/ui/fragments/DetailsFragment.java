package ru.marakhov.academ_android.task2.ui.fragments;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.marakhov.academ_android.task2.R;
import ru.marakhov.academ_android.task2.data.ContentDataProvider;

public class DetailsFragment extends Fragment
{
	public static final String TAG = "DetailsFragment";

	private ContentDataProvider mDataProvider;
	private boolean mDelayedFillContent;
	private boolean mIsViewCreated;

	@Bind(R.id.details_fragment_text_view_title)
	public TextView mTitleTextView;

	@Bind({R.id.details_fragment_image_view_picture1, R.id.details_fragment_image_view_picture2, R.id.details_fragment_image_view_picture3})
	public List<ImageView> mPictureImageViews;
	@Bind({R.id.details_fragment_text_view_pictureDescription1, R.id.details_fragment_text_view_pictureDescription2, R.id.details_fragment_text_view_pictureDescription3})
	public List<TextView> mPictureDesciptTextViews;
	@Bind({R.id.details_fragment_text_view_paragraph1, R.id.details_fragment_text_view_paragraph2, R.id.details_fragment_text_view_paragraph3})
	public List<TextView> mParagraphTextViews;


	public static DetailsFragment newInstance()
	{
		return new DetailsFragment();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.fragment_details, container, false);
		ButterKnife.bind(this, fragmentView);
		if (mDelayedFillContent)
		{
			mDelayedFillContent = false;
			fillContentViews();
		}
		mIsViewCreated = true;
		return fragmentView;
	}


	public void setContent(ContentDataProvider provider)
	{
		mDataProvider = provider;

		if (mIsViewCreated)
		{
			fillContentViews();
		}
		else
		{
			mDelayedFillContent = true;
		}
	}

	private void fillContentViews()
	{
		mTitleTextView.setText(mDataProvider.getNameID());
		Context context = getActivity();
		for (int i = 0; i < 3; i++)
		{
			Glide.with(context)
					.load(context.getString(mDataProvider.getPictures()[i]))
					.placeholder(R.drawable.replace_m)
					.into(mPictureImageViews.get(i));

			mPictureDesciptTextViews.get(i).setText(mDataProvider.getPictureDescriptions()[i]);
			mParagraphTextViews.get(i).setText(mDataProvider.getParagraphs()[i]);
		}

	}
}
