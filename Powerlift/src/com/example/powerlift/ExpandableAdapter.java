package com.example.powerlift;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter {

	private List<ExerciseName> catList;
	private int itemLayoutId;
	private int groupLayoutId;
	private Context ctx;

	TextView li_exercise_name;
	TextView li_exercise_weight;
	Button li_button;
	CheckBox li_checkbox;
	LinearLayout ll;

	public ExpandableAdapter(List<ExerciseName> catList, Context ctx) {

		this.itemLayoutId = R.layout.workout_list_child;
		this.groupLayoutId = R.layout.workout_list;
		this.catList = catList;
		this.ctx = ctx;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return catList.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return catList.get(groupPosition).getChildren().get(childPosition)
				.hashCode();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.workout_list_child, parent, false);
		}


		TextView groupName = (TextView) v.findViewById(R.id.li_date_in);
		TextView groupDescr = (TextView) v.findViewById(R.id.li_increment_in);

		ExerciseNameChild det = catList.get(groupPosition).getChildren()
				.get(childPosition);



		groupName.setText(det.getDate());
		groupDescr.setText(""+det.getIncrement());

		return v;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		int size = catList.get(groupPosition).getChildren().size();
		System.out.println("Child for group [" + groupPosition + "] is ["
				+ size + "]");
		return size;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return catList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return catList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return catList.get(groupPosition).hashCode();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.workout_list, parent, false);
		}
		
		
		li_exercise_name = (TextView) convertView
				.findViewById(R.id.li_exercise_name);

		li_exercise_weight = (TextView) convertView
				.findViewById(R.id.li_exercise_weight);

		
		li_button = (Button) convertView.findViewById(R.id.li_button);

		ll = (LinearLayout) convertView.findViewById(R.id.li_ll_header);



		ExerciseName cat = catList.get(groupPosition);

		li_exercise_name.setText(cat.getName());
		li_exercise_weight.setText(""+cat.getWeight());

		return v;

	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
