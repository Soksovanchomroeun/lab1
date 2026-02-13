package kh.edu.rupp.demoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

import kh.edu.rupp.demoapp.model.TaskGroup;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "DashboardActivity";
    private RecyclerView recyclerView;
    private TaskGroupAdaptor taskGroupAdaptor;
    private TextView summaryPercentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.taskGroupsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        summaryPercentTextView = findViewById(R.id.summaryPercentText);

        loadTasksFromJson();
    }

    private void loadTasksFromJson() {
        String jsonString;
        try {
            InputStream is = getAssets().open("tasks.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            Type listTaskGroupType = new TypeToken<List<TaskGroup>>() {}.getType();
            List<TaskGroup> taskGroups = gson.fromJson(jsonString, listTaskGroupType);

            taskGroupAdaptor = new TaskGroupAdaptor(taskGroups);
            recyclerView.setAdapter(taskGroupAdaptor);

            if (taskGroups != null && !taskGroups.isEmpty()) {
                int totalPercent = 0;
                for (TaskGroup group : taskGroups) {
                    totalPercent += group.getPercent();
                }
                int averagePercent = totalPercent / taskGroups.size();
                
                // Update the percentage in the top card
                summaryPercentTextView.setText(averagePercent + "%");
            }

            Log.d(TAG, "Successfully loaded " + taskGroups.size() + " task groups from JSON.");

        } catch (IOException ex) {
            ex.printStackTrace();
            Log.e(TAG, "Error reading tasks.json from assets", ex);
        }
    }
}
