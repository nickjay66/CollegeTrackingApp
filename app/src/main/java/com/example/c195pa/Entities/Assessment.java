package com.example.c195pa.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "assessment_table")
public class Assessment implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "assessment_id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "date")
    private Date mDueDate;

    @NonNull
    @ColumnInfo(name = "type")
    private String mType;

    @NonNull
    @ColumnInfo(name= "courseId")
    private int mCourseId;

    public Assessment(@NonNull String mName, @NonNull Date mDueDate, @NonNull String mType, int mCourseId) {
        this.mName = mName;
        this.mDueDate = mDueDate;
        this.mType = mType;
        this.mCourseId = mCourseId;
    }

    public Assessment(@NonNull int assessmentId, @NonNull String mName, @NonNull Date mDueDate, @NonNull String mType, int mCourseId) {
        this.mId = assessmentId;
        this.mName = mName;
        this.mDueDate = mDueDate;
        this.mType = mType;
        this.mCourseId = mCourseId;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public Date getDueDate() {
        return mDueDate;
    }

    @NonNull
    public String getType() {
        return mType;
    }

    public int getCourseId() {
        return mCourseId;
    }
}
