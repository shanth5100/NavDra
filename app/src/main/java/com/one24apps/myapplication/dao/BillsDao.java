package com.one24apps.myapplication.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.one24apps.myapplication.model.Bills;

import java.util.List;

@Dao
public interface BillsDao {
    @Insert
    void saveBill(Bills bill);

    @Update
    void updateBill(Bills bill);

    @Delete
    void deleteBill(Bills bill);

    @Query("delete from bills_tbl")
    void deleteAllBill();

    @Query("select * from bills_tbl")
    LiveData<List<Bills>> billsList();

}
