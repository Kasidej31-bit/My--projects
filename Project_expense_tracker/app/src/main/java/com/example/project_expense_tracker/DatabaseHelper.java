package com.example.project_expense_tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Map;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "projects.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROJECTS_TABLE = "CREATE TABLE projects (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "projectId TEXT, projectName TEXT, projectDescription TEXT, startDate TEXT, endDate TEXT, " +
                "manager TEXT, status TEXT, budget TEXT, specialRequirements TEXT, clientInfo TEXT)";
        db.execSQL(CREATE_PROJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS projects");
        onCreate(db);
    }

    public void addProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("projectId", project.getProjectId());
        values.put("projectName", project.getProjectName());
        values.put("projectDescription", project.getProjectDescription());
        values.put("startDate", project.getStartDate());
        values.put("endDate", project.getEndDate());
        values.put("manager", project.getManager());
        values.put("status", project.getStatus());
        values.put("budget", project.getBudget());
        values.put("specialRequirements", project.getSpecialRequirements());
        values.put("clientInfo", project.getClientInfo());

        db.insert("projects", null, values);
        db.close();
    }

    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM projects", null);
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project(
                        cursor.getString(1), // projectId
                        cursor.getString(2), // projectName
                        cursor.getString(3), // projectDescription
                        cursor.getString(4), // startDate
                        cursor.getString(5), // endDate
                        cursor.getString(6), // manager
                        cursor.getString(7), // status
                        cursor.getString(8), // budget
                        cursor.getString(9), // specialRequirements
                        cursor.getString(10) // clientInfo
                );
                projectList.add(project);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return projectList;
    }

    public void deleteProject(int projectId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("projects", "id = ?", new String[]{String.valueOf(projectId)});
        db.close();
    }

    public void uploadToCloud(List<Project> projectList) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (Project project : projectList) {
            Map<String, Object> projectMap = new HashMap<>();
            projectMap.put("projectId", project.getProjectId());
            projectMap.put("projectName", project.getProjectName());
            projectMap.put("projectDescription", project.getProjectDescription());
            projectMap.put("startDate", project.getStartDate());
            projectMap.put("endDate", project.getEndDate());
            projectMap.put("manager", project.getManager());
            projectMap.put("status", project.getStatus());
            projectMap.put("budget", project.getBudget());
            projectMap.put("specialRequirements", project.getSpecialRequirements());
            projectMap.put("clientInfo", project.getClientInfo());

            db.collection("projects").add(projectMap);
        }
    }

}



