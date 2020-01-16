package com.example.c195pa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.c195pa.Entities.Course;
import com.example.c195pa.Entities.Term;
import com.example.c195pa.R;
import com.example.c195pa.RoomAdapter;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    private final LayoutInflater mInflater;
    private List<Course> mCourses; //Cached copy of courses
    private CourseAdapter.OnCourseListener mOnCourseListener;

    public CourseAdapter(Context context, CourseAdapter.OnCourseListener onCourseListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnCourseListener = onCourseListener;
    }

    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CourseAdapter.CourseViewHolder(itemView, mOnCourseListener);
    }

    @Override
    public void onBindViewHolder(CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.CourseItemView.setText(current.getName());

        } else {
            // Covers the case of data not being ready yet.
            holder.CourseItemView.setText("No Course");
        }
    }

    public void setCourses(List<Course> Courses){
        mCourses = Courses;
        notifyDataSetChanged();
    }

    public List<Course> getCourses() {
        return mCourses;
    }


    // getItemCount() is called many times, and when it is first called,
    // mCourse has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView CourseItemView;
        CourseAdapter.OnCourseListener onCourseListener;

        private CourseViewHolder(View itemView, CourseAdapter.OnCourseListener onCourseListener) {
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.textView);
            this.onCourseListener = onCourseListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCourseListener.onCourseClick(getAdapterPosition());

        }
    }

    public interface OnCourseListener {
        void onCourseClick(int position);
    }

    public Course getItem(int position) {
        return mCourses.get(position);
    }

    public void removeAt(int position) {
        mCourses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCourses.size());
    }
}

