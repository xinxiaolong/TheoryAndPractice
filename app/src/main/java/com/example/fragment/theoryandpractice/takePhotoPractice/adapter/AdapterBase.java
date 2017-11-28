package com.example.fragment.theoryandpractice.takePhotoPractice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 具体的用法可以参照
 * @author xiaolong
 *
 * @param <T>
 */
public abstract class AdapterBase<T> extends BaseAdapter {

	protected LayoutInflater inflater = null;
	protected List<T> mList = new LinkedList<T>();
	protected Context context;

	public AdapterBase(Context context) {
		inflater = LayoutInflater.from(context);
		this.context = context;
	}

	public void appendOneToTopList(T c) {
		if (mList == null) {
			return;
		}
		mList.add(0, c);
		notifyDataSetChanged();
	}

	public void appendOneList(T c) {
		if (mList == null) {
			return;
		}
		mList.add(c);
		notifyDataSetChanged();
	}
	
	public List<T> getList() {
		return mList;
	}

	public void appendToList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(list);
		notifyDataSetChanged();
	}

	public void appendToTopList(List<T> list) {
		if (list == null) {
			return;
		}
		mList.addAll(0, list);
		notifyDataSetChanged();
	}

	public void setList(List<T> list) {
		if (list == null) {
			return;
		}
		mList = list;
		notifyDataSetChanged();
	}

	public void clear() {
		mList.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		if (position > mList.size() - 1) {
			return null;
		}
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (position == getCount() - 1) {
			onReachBottom();
		}
		return getExView(position, convertView, parent);
	}

	protected View getExView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = getConvertView();
			Object customView = getCustomView();
			ButterKnife.inject(customView,convertView);
			convertView.setTag(customView);
		} else {
			setCustomView(convertView.getTag());
		}
		setViewData(position);
		return convertView;
	}

	protected void onReachBottom() {

	}

	protected abstract int getConvertViewId();

	public View getConvertView() {
		return inflater.inflate(getConvertViewId(), null);
	}

	protected abstract Object getCustomView();

	protected abstract void setCustomView(Object customView);

	protected abstract void setViewData(int position);

	public void showToast(String str) {
		Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
	}
}
