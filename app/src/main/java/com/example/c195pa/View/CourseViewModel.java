package com.example.c195pa.View;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.c195pa.Entities.Course;
import com.example.c195pa.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CourseViewModel extends AndroidViewModel {

    private RoomRepository mRepository;
    private com.example.c195pa.DAO.CourseDao courseDao;
    private LiveData<List<Course>> mAllCourses;


    public CourseViewModel(Application application) {
        super(application);
        mRepository = new RoomRepository(application);
        mAllCourses = mRepository.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() { return mAllCourses; }

    public void insert(Course course) { mRepository.insertCourse(course); }

    public LiveData<Course> getCourseByCourseId(int mId) {return mRepository.getCourseByCourseId(mId);}

    public LiveData<List<Course>> getCoursesByTermId(int mTermId) {return mRepository.getCoursesByTermId(mTermId);}

    public LiveData<Course> getCourseByCourseName(String name) {return mRepository.getCourseByCourseName(name);}

    public void updateCourse(Course course) {mRepository.upDateCourse(course);}

    public void deleteCourse(int id) {mRepository.deleteCourse(id);
    }

}
