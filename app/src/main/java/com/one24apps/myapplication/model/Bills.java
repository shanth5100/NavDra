package com.one24apps.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bills_tbl")
public class Bills {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double amount;
    private boolean cleared;
    private String payee;
    private String note;

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount (double amount) {
        this.amount = amount;
    }

    public boolean isCleared () {
        return cleared;
    }

    public void setCleared (boolean cleared) {
        this.cleared = cleared;
    }

    public String getPayee () {
        return payee;
    }

    public void setPayee (String payee) {
        this.payee = payee;
    }

    public String getNote () {
        return note;
    }

    public void setNote (String note) {
        this.note = note;
    }
}
