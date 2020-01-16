package com.example.c195pa.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.c195pa.DateConverter;

import java.util.Date;

@Entity(tableName = "term_table")
public class Term {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "term_id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "term")
    private String mTerm;

    @TypeConverters(DateConverter.class)
    @NonNull
    private Date startDate;

    @TypeConverters(DateConverter.class)
    @NonNull
    private Date endDate;

    public Term(@NonNull String term, Date startDate, Date endDate) {
        this.mTerm = term;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public int getId() {return this.mId;}

    public String getTerm(){return this.mTerm;}

    public Date getStartDate() {return this.startDate;}

    public Date getEndDate() {return endDate;}

}
