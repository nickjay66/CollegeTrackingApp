package com.example.c195pa.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c195pa.Entities.Assessment;
import com.example.c195pa.Entities.Course;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAssessment(Assessment assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAll();

    @Query("DELETE FROM assessment_table WHERE assessment_id = :mId")
    void deleteAssessment(int mId);

    @Query("SELECT * FROM assessment_table ORDER BY name ASC")
    LiveData<List<Assessment>> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE courseId = :mId")
    LiveData<List<Assessment>> getAssessmentsByCourseId(int mId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAssessment(Assessment assessment);


}
