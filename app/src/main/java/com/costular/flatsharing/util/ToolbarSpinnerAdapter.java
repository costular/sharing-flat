package com.costular.flatsharing.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.costular.flatsharing.R;
import com.costular.flatsharing.data.User;

import java.util.ArrayList;
import java.util.List;

public class ToolbarSpinnerAdapter extends BaseAdapter {

	private List<User> mItems = new ArrayList<>();

	public void clear() {
		mItems.clear();
	}

	public void addItem(User user) {
		mItems.add(user);
	}

	public void addItems(List<User> yourObjectList) {
		mItems.addAll(yourObjectList);
	}

	@Override
	public int getCount() {
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		return mItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getDropDownView(int position, View view, ViewGroup parent) {
		if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
			view.setTag("DROPDOWN");
		}

		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(getTitle(position));

		return view;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
					toolbar_spinner_item_actionbar, parent, false);
			view.setTag("NON_DROPDOWN");
		}
		TextView textView = (TextView) view.findViewById(android.R.id.text1);
		textView.setText(getTitle(position));
		return view;
	}

	private String getTitle(int position) {
		return position >= 0 && position < mItems.size() ? mItems.get(position).getName() : "";
	}
}