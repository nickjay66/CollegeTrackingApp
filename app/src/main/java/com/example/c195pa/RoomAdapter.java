package com.example.c195pa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.c195pa.Entities.Course;
import com.example.c195pa.Entities.Term;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.TermViewHolder> {

    private final LayoutInflater mInflater;
    private List<Term> mTerms; // Cached copy of Terms
    private OnTermListener mOnTermListener;

    RoomAdapter(Context context, OnTermListener onTermListener) {
        mInflater = LayoutInflater.from(context);
        this.mOnTermListener = onTermListener;
    }

    @Override
    public TermViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TermViewHolder(itemView, mOnTermListener);
    }

    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if (mTerms != null) {
            Term current = mTerms.get(position);
            holder.TermItemView.setText(current.getTerm());

        } else {
            // Covers the case of data not being ready yet.
            holder.TermItemView.setText("No Term");
        }
    }

    void setTerms(List<Term> Terms){
        mTerms = Terms;
        notifyDataSetChanged();
    }


    // getItemCount() is called many times, and when it is first called,
    // mTerms has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mTerms != null)
            return mTerms.size();
        else return 0;
    }

    class TermViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView TermItemView;
        OnTermListener onTermListener;

        private TermViewHolder(View itemView, OnTermListener onTermListener) {
            super(itemView);
            TermItemView = itemView.findViewById(R.id.textView);
            this.onTermListener = onTermListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onTermListener.onTermClick(getAdapterPosition());

        }
    }

    public interface OnTermListener {
        void onTermClick(int position);
    }

    public Term getItem(int position) {
        return mTerms.get(position);
    }
}
