package com.example.c195pa.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.c195pa.Entities.Course;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Query("DELETE FROM course_table")
    void deleteAll();

    @Query("DELETE FROM course_table WHERE course_id = :id")
    void deleteCourse(int id);

    @Query("SELECT * FROM course_table ORDER BY course ASC")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE course_id = :mId")
    LiveData<Course> getCourseByCourseId(int mId);

    @Query("SELECT * FROM course_table WHERE term_id = :mTermId")
    LiveData<List<Course>> getCoursesByTermId(int mTermId);


    @Query("SELECT * FROM course_table WHERE course = :name")
    LiveData<Course> getCourseByCourseName(String name);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCourse(Course course);
}
