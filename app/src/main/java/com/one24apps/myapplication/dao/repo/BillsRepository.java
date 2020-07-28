package com.one24apps.myapplication.dao.repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.one24apps.myapplication.dao.BillsDao;
import com.one24apps.myapplication.db.AppDB;
import com.one24apps.myapplication.model.Bills;

import java.util.List;

public class BillsRepository {
    private BillsDao billsDao;
    private LiveData<List<Bills>> billsList;
    private Bills bill;

    public BillsRepository(Application application) {
        AppDB appDB = AppDB.getInstance(application);

        billsDao = appDB.billsDao();

        billsList = billsDao.billsList();
    }

    public void insertBill(Bills bill) {
        new InsertAsyncTask(billsDao).execute(bill);
    }

    public void updateBill(Bills bill) {
        new UpdateAsyncTask(billsDao).execute(bill);
    }

    public void deleteBill(Bills bill) {
        new DeleteAsyncTask(billsDao).execute(bill);
    }

    public void deleteAllBills() {
        new UpdateAllAsyncTask(billsDao).execute();
    }

    public LiveData<List<Bills>> billsList() {
        return billsList;
    }

    // Async task doesnot perform by room, we need to do manually
    private static class InsertAsyncTask extends AsyncTask<Bills, Void, Void> {
        BillsDao billsDao;

        private InsertAsyncTask(BillsDao billsDao){
            this.billsDao = billsDao;
        }

        @Override
        protected Void doInBackground (Bills... bills) {
            billsDao.saveBill(bills[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Bills, Void, Void> {
        BillsDao billsDao;

        private UpdateAsyncTask(BillsDao billsDao){
            this.billsDao = billsDao;
        }

        @Override
        protected Void doInBackground (Bills... bills) {
            billsDao.updateBill(bills[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Bills, Void, Void> {
        BillsDao billsDao;

        private DeleteAsyncTask(BillsDao billsDao){
            this.billsDao = billsDao;
        }

        @Override
        protected Void doInBackground (Bills... bills) {
            billsDao.deleteBill(bills[0]);
            return null;
        }
    }

    private static class UpdateAllAsyncTask extends AsyncTask<Void, Void, Void> {
        BillsDao billsDao;

        private UpdateAllAsyncTask(BillsDao billsDao){
            this.billsDao = billsDao;
        }

        @Override
        protected Void doInBackground (Void... bills) {
            billsDao.deleteAllBill();
            return null;
        }
    }
}
