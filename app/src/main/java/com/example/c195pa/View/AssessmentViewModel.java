package com.example.c195pa.View;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.c195pa.Entities.Assessment;
import com.example.c195pa.Entities.Course;
import com.example.c195pa.RoomRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AssessmentViewModel extends AndroidViewModel {

    private RoomRepository mRepository;
    private com.example.c195pa.DAO.CourseDao courseDao;
    private LiveData<List<Assessment>> mAllAssessments;



    public AssessmentViewModel(Application application) {
        super(application);
        mRepository = new RoomRepository(application);
        mAllAssessments = mRepository.getAllAssessments();
    }

    public LiveData<List<Assessment>> getAllAssessments() { return mAllAssessments; }

    public LiveData<List<Assessment>> getAssessmentsByCourseId(int mId) { return mRepository.getAssessmentsByCourseId(mId); }

    public void insertAssessment(Assessment assessment) { mRepository.insertAssessment(assessment); }

    public void deleteAssessment(int id) {mRepository.deleteAssessment(id); }

    public void updateAssessment(Assessment assessment) {mRepository.updateAssessment(assessment);}

}
