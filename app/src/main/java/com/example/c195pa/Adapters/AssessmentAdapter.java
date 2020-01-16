package com.example.c195pa.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.c195pa.Entities.Assessment;
import com.example.c195pa.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    private final LayoutInflater mInflater;
    private List<Assessment> mAssessments; //Cached copy of courses
    private AssessmentAdapter.OnAssessmentListener mOnAssessmentListener;

    public AssessmentAdapter(Context context, AssessmentAdapter.OnAssessmentListener onAssessmentListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnAssessmentListener = onAssessmentListener;
    }

    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView, mOnAssessmentListener);
    }

    @Override
    public void onBindViewHolder(AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            Assessment current = mAssessments.get(position);
            holder.AssessmentItemView.setText(current.getName());

        } else {
            // Covers the case of data not being ready yet.
            holder.AssessmentItemView.setText("No Course");
        }
    }

    public void setAssessments(List<Assessment> Assessments){
        mAssessments = Assessments;
        notifyDataSetChanged();
    }

    public List<Assessment> getAssessments() {
        return mAssessments;
    }


    // getItemCount() is called many times, and when it is first called,
    // mCourse has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }

    class AssessmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView AssessmentItemView;
        AssessmentAdapter.OnAssessmentListener onAssessmentListener;

        private AssessmentViewHolder(View itemView, AssessmentAdapter.OnAssessmentListener onAssessmentListener) {
            super(itemView);
            AssessmentItemView = itemView.findViewById(R.id.textView);
            this.onAssessmentListener = onAssessmentListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAssessmentListener.onAssessmentClick(getAdapterPosition());

        }
    }

    public interface OnAssessmentListener {
        void onAssessmentClick(int position);
    }

    public Assessment getItem(int position) {
        return mAssessments.get(position);
    }

    /*/public void removeAt(int position) {
        mCourses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mCourses.size());
    }/*/
}
