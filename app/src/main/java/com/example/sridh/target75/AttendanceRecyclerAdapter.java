package com.example.sridh.target75;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sridh.target75.database.AttendanceContract;

/**
 * Created by sridh on 24-12-2016.
 */

public class AttendanceRecyclerAdapter extends RecyclerView.Adapter<AttendanceRecyclerAdapter.AttendanceViewHolder> {


    private Context mContext;
    private Cursor mCursor;
    final private ListItemClickListener mItemClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickeditemindex);
    }

    public AttendanceRecyclerAdapter(Context context, Cursor cursor,ListItemClickListener listItemClickListener) {
        this.mContext = context;
        this.mCursor = cursor;
        this.mItemClickListener=listItemClickListener;

    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mSubjectView;

        public AttendanceViewHolder(View itemView) {

            super(itemView);
            mSubjectView = (TextView) itemView.findViewById(R.id.subject_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedposition = getAdapterPosition();
            mItemClickListener.onListItemClick(clickedposition);
        }
    }


    @Override
    public AttendanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        int id=R.layout.list_itemview;
        View view = inflater.inflate(id, parent, false);
        Log.v("AttendanceAdapter", "onCreateViewHolder");

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AttendanceViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {return;}

            String subject = mCursor.getString(mCursor.getColumnIndex(AttendanceContract.AttendanceEntry.COLUMN_SUBJECT));
            Log.v("AttendanceAdapter", "onBindBViewHolder " + subject + " " + position);
            holder.mSubjectView.setText(subject);
        }

    @Override
    public int getItemCount() {
        if (mCursor != null) {
            return mCursor.getCount();
        }
         return 1;
    }
}
