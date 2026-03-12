package com.example.project_expense_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Expense {
    private String expenseId, projectId, date, amount, currency, type, paymentMethod, claimant, paymentStatus, description, location;
    // Add getters and setters
}

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "projects.db";
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating projects table
        String CREATE_PROJECTS_TABLE = "CREATE TABLE projects (id INTEGER PRIMARY KEY AUTOINCREMENT, ...)";
        db.execSQL(CREATE_PROJECTS_TABLE);

        // Creating expenses table
        String CREATE_EXPENSES_TABLE = "CREATE TABLE expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "expenseId TEXT, projectId TEXT, date TEXT, amount TEXT, currency TEXT, type TEXT, " +
                "paymentMethod TEXT, claimant TEXT, paymentStatus TEXT, description TEXT, location TEXT)";
        db.execSQL(CREATE_EXPENSES_TABLE);
    }

    public void addExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("expenseId", expense.getExpenseId());
        values.put("projectId", expense.getProjectId());
        values.put("date", expense.getDate());
        values.put("amount", expense.getAmount());
        values.put("currency", expense.getCurrency());
        values.put("type", expense.getType());
        values.put("paymentMethod", expense.getPaymentMethod());
        values.put("claimant", expense.getClaimant());
        values.put("paymentStatus", expense.getPaymentStatus());
        values.put("description", expense.getDescription());
        values.put("location", expense.getLocation());

        db.insert("expenses", null, values);
        db.close();
    }
}

