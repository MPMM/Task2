package ru.marakhov.academ_android.task2.data;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;

import ru.marakhov.academ_android.task2.R;

public class ContentDataProvider
{
	private int nameID;
	private int descriptionID;
	private int logoUrlID;
	private int[] pictures;
	private int[] pictureDescriptions;
	private int[] paragraphs;


	public ContentDataProvider(String prefix, Context context)
	{
		Resources resources = context.getResources();

		String contentPrefix = context.getPackageName() + ":string/" + prefix;
		nameID = resources.getIdentifier(contentPrefix + "name", null, null);
		descriptionID = resources.getIdentifier(contentPrefix + "description", null, null);
		logoUrlID = resources.getIdentifier(contentPrefix + "logo", null, null);
		pictures = new int[3];
		pictureDescriptions = new int[3];
		paragraphs = new int[3];

		for (int i = 0; i < 3; i++)
		{
			pictures[i]=resources.getIdentifier(contentPrefix + "picture"+Integer.toString(i+1), null, null);
			pictureDescriptions[i]=resources.getIdentifier(contentPrefix + "picdescript"+Integer.toString(i+1), null, null);
			paragraphs[i]=resources.getIdentifier(contentPrefix + "paragraph"+Integer.toString(i+1), null, null);
		}
	}

	public int[] getPictures()
	{
		return pictures;
	}

	public int[] getPictureDescriptions()
	{
		return pictureDescriptions;
	}

	public int[] getParagraphs()
	{
		return paragraphs;
	}

	public int getNameID()
	{
		return nameID;
	}

	public int getDescriptionID()
	{
		return descriptionID;
	}

	public int getLogoUrlID()
	{
		return logoUrlID;
	}

	public static ArrayList<ContentDataProvider> fillContentArray(Context context)
	{
		ArrayList<ContentDataProvider> resultArray = new ArrayList<>();
		Resources resources = context.getResources();
		String[] prefs = resources.getStringArray(R.array.content_prefix_array);
		for (String pref : prefs) resultArray.add(new ContentDataProvider(pref, context));
		return resultArray;
	}

	public interface ContentInterface
	{
		ArrayList<ContentDataProvider> getContentArray();
	}
}
