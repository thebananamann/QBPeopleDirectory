package com.android.qbpeopledirectory.activities;
import java.util.List;

import android.graphics.BitmapFactory;
import com.android.qbpeopledirectory.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

public class CustomUserAdapter extends BaseAdapter {
    ViewHolder holder;
    Context context;
    List<RowItem> rowItems;
    ParseFile imageFile;

    /* Constructor assigns context and List of Row items to be displayed
    */

    CustomUserAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView FirstName;
        TextView Designation;
        TextView LastName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.search_row_layout, null);
            holder = new ViewHolder();

            holder.FirstName = (TextView) convertView
                    .findViewById(R.id.firstTV);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_picIV);
            holder.Designation = (TextView) convertView.findViewById(R.id.desigTV);
            holder.LastName = (TextView) convertView
                    .findViewById(R.id.lastTV);

            RowItem row_pos = rowItems.get(position);
            ImageView image = (ImageView) convertView.findViewById(R.id.profile_picIV);

            holder.FirstName.setText(row_pos.getFirstName());
            holder.Designation.setText(row_pos.getDesignation());
            holder.LastName.setText(row_pos.getLastName());

            imageFile=(ParseFile)row_pos.getProfile_pic_id();

            imageFile.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] bytes, ParseException e) {

                    if(e==null){


                        holder.profile_pic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length) );
                    }

                }
            });



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}