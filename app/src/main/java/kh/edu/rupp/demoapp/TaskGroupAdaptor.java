package kh.edu.rupp.demoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kh.edu.rupp.demoapp.model.TaskGroup;

public class TaskGroupAdaptor extends RecyclerView.Adapter<TaskGroupAdaptor.TaskGroupViewHolder> {

    private final List<TaskGroup> taskGroupList;
    private final Context context;

    public TaskGroupAdaptor(List<TaskGroup> taskGroupList) {
        this.taskGroupList = taskGroupList;
        // We need the context to resolve colors
        this.context = null; 
    }
    
    // Constructor that takes a context
    public TaskGroupAdaptor(Context context, List<TaskGroup> taskGroupList) {
        this.context = context;
        this.taskGroupList = taskGroupList;
    }

    @NonNull
    @Override
    public TaskGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task_group, parent, false);
        return new TaskGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskGroupViewHolder holder, int position) {
        TaskGroup taskGroup = taskGroupList.get(position);

        holder.titleTextView.setText(taskGroup.getTitle());
        holder.countTextView.setText(taskGroup.getTaskCount() + " Tasks");
        holder.progressBar.setProgress(taskGroup.getPercent());
        holder.percentTextView.setText(taskGroup.getPercent() + "%");

        // Set the background color based on position
        int[] colors = new int[]{
                R.color.task_color_1,
                R.color.task_color_2,
                R.color.task_color_3,
                R.color.task_color_4
        };
        int colorRes = colors[position % colors.length];
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), colorRes));
    }

    @Override
    public int getItemCount() {
        return taskGroupList != null ? taskGroupList.size() : 0;
    }

    public static class TaskGroupViewHolder extends RecyclerView.ViewHolder {
        CardView cardView; // Reference to the CardView
        TextView titleTextView;
        TextView countTextView;
        ProgressBar progressBar;
        TextView percentTextView;

        public TaskGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView; // The root view is the CardView
            titleTextView = itemView.findViewById(R.id.taskGroupTitle);
            countTextView = itemView.findViewById(R.id.taskGroupCount);
            progressBar = itemView.findViewById(R.id.taskGroupProgress);
            percentTextView = itemView.findViewById(R.id.taskGroupPercentText);
        }
    }
}
