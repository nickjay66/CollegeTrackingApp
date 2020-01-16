package com.example.c195pa.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.c195pa.DateConverter;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "course_table" /*/foreignKeys =
        {@ForeignKey(
                entity = Term.class,
                parentColumns = "term_id",
                childColumns = "term_id",
                onUpdate = ForeignKey.NO_ACTION,
                onDelete = ForeignKey.CASCADE)},
         indices = @Index(value = "term_id")/*/
)
public class Course implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "course")
    private String mName;

    @TypeConverters(DateConverter.class)
    @NonNull
    @ColumnInfo(name = "start_date")
    private Date mStartDate;

    @TypeConverters(DateConverter.class)
    @NonNull
    @ColumnInfo(name = "end_date")
    private Date mEndDate;

    @NonNull
    @ColumnInfo(name = "mentor")
    private String mMentor;

    @NonNull
    @ColumnInfo(name = "mentor_email")
    private String mMentorEmail;

    @NonNull
    @ColumnInfo(name = "mentor_phone")
    private String mMentorPhone;

    @NonNull
    @ColumnInfo(name = "status")
    private String mStatus;

    @NonNull
    @ColumnInfo(name = "term_id")
    private int mTermId;

    @ColumnInfo(name = "notes")
    private String mNotes = null;

    public Course(@NonNull String mName, @NonNull Date mStartDate, @NonNull Date mEndDate, @NonNull String mStatus, @NonNull String mMentor, @NonNull String mMentorEmail, @NonNull String mMentorPhone, int mTermId, String mNotes) {
        this.mName = mName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mStatus = mStatus;
        this.mMentor = mMentor;
        this.mMentorEmail = mMentorEmail;
        this.mMentorPhone = mMentorPhone;
        this.mTermId = mTermId;
        this.mNotes = mNotes;
    }

    public Course(@NonNull int courseId,@NonNull String mName, @NonNull Date mStartDate, @NonNull Date mEndDate, @NonNull String mStatus, @NonNull String mMentor, @NonNull String mMentorEmail, @NonNull String mMentorPhone, int mTermId, String mNotes) {
        this.mId = courseId;
        this.mName = mName;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mStatus = mStatus;
        this.mMentor = mMentor;
        this.mMentorEmail = mMentorEmail;
        this.mMentorPhone = mMentorPhone;
        this.mTermId = mTermId;
        this.mNotes = mNotes;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public Date getStartDate() {
        return mStartDate;
    }

    @NonNull
    public Date getEndDate() {
        return mEndDate;
    }

    @NonNull
    public String getMentor() {
        return mMentor;
    }

    @NonNull
    public String getMentorEmail() {
        return mMentorEmail;
    }

    @NonNull
    public String getMentorPhone() {
        return mMentorPhone;
    }

    public String getStatus() {
        return mStatus;
    }

    public int getTermId() {
        return mTermId;
    }

    public String getNotes() { return mNotes; }

}
