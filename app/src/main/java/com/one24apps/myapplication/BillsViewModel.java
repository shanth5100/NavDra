package com.one24apps.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.one24apps.myapplication.dao.BillsRepository;
import com.one24apps.myapplication.model.Bills;

import java.util.List;

public class BillsViewModel extends AndroidViewModel {
    private BillsRepository billsRepository;
    private LiveData<List<Bills>> billsList;
    public BillsViewModel (@NonNull Application application) {
        super(application);

        billsRepository = new BillsRepository(application);
        billsList = billsRepository.billsList();
    }

    public void saveBill(Bills bill){
        billsRepository.insertBill(bill);
    }
    public void updateBill(Bills bill){
        billsRepository.updateBill(bill);
    }
    public void deleteBill(Bills bill){
        billsRepository.deleteBill(bill);
    }

    public void deleteAllBills(){
        billsRepository.deleteAllBills();
    }
    public LiveData<List<Bills>> getBillsList(){
        return billsList;
    }
}
