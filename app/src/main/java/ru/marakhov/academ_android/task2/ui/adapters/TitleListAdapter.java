package ru.marakhov.academ_android.task2.ui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.marakhov.academ_android.task2.data.ContentDataProvider;
import ru.marakhov.academ_android.task2.R;


public class TitleListAdapter extends BaseAdapter
{
	private Context mContext;
	private ArrayList<ContentDataProvider> contentArray;

	static class ViewHolder
	{
		@Bind(R.id.title_list_item_text_view_title)
		protected TextView title;
		@Bind(R.id.title_list_item_text_view_descr)
		protected TextView description;
		@Bind(R.id.title_list_item_image_view_logo)
		protected ImageView logo;

		private Context mContext;

		public ViewHolder(View convertView, Context context)
		{
			mContext = context;
			ButterKnife.bind(this,convertView);
		}

		public void setTitleText(int textID)
		{
			title.setText(textID);
		}

		public void setDescription(int descriptionID)
		{
			description.setText(descriptionID);
		}

		public void setLogo(String logoURL)
		{
			Glide.with(mContext).load(logoURL).placeholder(R.drawable.replace_m).into(logo);
		}
	}


	public TitleListAdapter(Context context, ArrayList<ContentDataProvider> contentArray)
	{
		mContext = context;
		this.contentArray = contentArray;
	}


	@Override
	public int getCount()
	{
		return contentArray.size();
	}

	@Override
	public Object getItem(int position)
	{
		return contentArray.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;

		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.title_list_item, parent, false);
			viewHolder = new ViewHolder(convertView, mContext);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.setTitleText(contentArray.get(position).getNameID());
		viewHolder.setDescription(contentArray.get(position).getDescriptionID());
		viewHolder.setLogo(mContext.getString(contentArray.get(position).getLogoUrlID()));
		return convertView;
	}
}
