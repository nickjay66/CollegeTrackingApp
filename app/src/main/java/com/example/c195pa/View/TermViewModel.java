package com.example.c195pa.View;

import android.app.Application;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.c195pa.DAO.TermDao;
import com.example.c195pa.Entities.Term;
import com.example.c195pa.RoomRepository;

import java.util.List;

public class TermViewModel extends AndroidViewModel {

    private RoomRepository mRepository;
    private LiveData<List<Term>> mAllTerms;

    public TermViewModel(Application application) {
        super(application);
        mRepository = new RoomRepository(application);
        mAllTerms = mRepository.getAllTerms();
    }

    public LiveData<List<Term>> getAllTerms() { return mAllTerms; }

    public void insert(Term term) { mRepository.insert(term); }

    public LiveData<Term> getTermByTermId(int mId) {return mRepository.getTermByTermId(mId);}

    public void deleteByTermName(String mName) { mRepository.deleteByTermName(mName);}
}
