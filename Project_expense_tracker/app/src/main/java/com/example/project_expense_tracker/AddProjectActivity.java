package com.example.project_expense_tracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProjectActivity extends AppCompatActivity {
    private EditText projectId, projectName, projectDescription, startDate, endDate, manager, status, budget;
    private EditText specialRequirements, clientInfo;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        // Initialize views
        projectId = findViewById(R.id.projectId);
        projectName = findViewById(R.id.projectName);
        projectDescription = findViewById(R.id.projectDescription);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        manager = findViewById(R.id.manager);
        status = findViewById(R.id.status);
        budget = findViewById(R.id.budget);
        specialRequirements = findViewById(R.id.specialRequirements);
        clientInfo = findViewById(R.id.clientInfo);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(view -> {
            // Validate fields
            if (isInputValid()) {
                saveProject();
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isInputValid() {
        return !projectId.getText().toString().isEmpty() &&
                !projectName.getText().toString().isEmpty() &&
                !projectDescription.getText().toString().isEmpty() &&
                !startDate.getText().toString().isEmpty() &&
                !endDate.getText().toString().isEmpty() &&
                !manager.getText().toString().isEmpty() &&
                !status.getText().toString().isEmpty() &&
                !budget.getText().toString().isEmpty();
    }

    private void saveProject() {
        // Logic to save project data in SQLite Database
        Project project = new Project(
                projectId.getText().toString(),
                projectName.getText().toString(),
                projectDescription.getText().toString(),
                startDate.getText().toString(),
                endDate.getText().toString(),
                manager.getText().toString(),
                status.getText().toString(),
                budget.getText().toString(),
                specialRequirements.getText().toString(),
                clientInfo.getText().toString()
        );

        DatabaseHelper db = new DatabaseHelper(this);
        db.addProject(project);
        Toast.makeText(this, "Project saved!", Toast.LENGTH_SHORT).show();
    }
}

