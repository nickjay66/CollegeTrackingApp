package com.example.c195pa.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.c195pa.Entities.Term;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Query("DELETE FROM term_table")
    void deleteAll();

    @Query("DELETE FROM term_table WHERE term = :mName")
    void deleteByTermName(String mName);

    @Query("SELECT * from term_table ORDER BY term ASC")
    LiveData<List<Term>> getAllTerms();

    @Query("SELECT * FROM term_table WHERE term_id = :mId")
    LiveData<Term> getTermByTermId(int mId);

    //@Query("SELECT * FROM term_table WHERE t")

}
