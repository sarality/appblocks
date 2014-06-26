package com.sarality.app.view.nav;

import android.view.View;

import com.sarality.app.view.list.BaseListRowRenderer;
import com.sarality.app.view.list.ListRowViewCache;

public abstract class NavigationListRowRenderer extends BaseListRowRenderer<String> {

	private final int rowLayout;
	
	public NavigationListRowRenderer(int rowLayout) {
		super();
		this.rowLayout = rowLayout;
	}

	@Override
	public int getRowLayout(String value) {
		return rowLayout;
	}

	@Override
	public void populateViewCache(View rowView, ListRowViewCache rowViewCache,
			String value) {
		// TODO Auto-generated method stub
		
	}

	public abstract void onSelection(View rowView, String value);

	public abstract void render(View rowView, String value);
	
	@Override
	public void render(View rowView, ListRowViewCache rowViewCache, String value) {
		render(rowView, value);
	}

}
