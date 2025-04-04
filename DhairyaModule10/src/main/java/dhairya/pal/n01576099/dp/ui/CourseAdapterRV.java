package dhairya.pal.n01576099.dp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dhairya.pal.n01576099.dp.R;

public class CourseAdapterRV extends RecyclerView.Adapter<CourseAdapterRV.ViewHolder> {
    private ArrayList<CourseItemRV> courseItemRVArrayList;
    private Context context;

    public CourseAdapterRV(ArrayList<CourseItemRV> courseItemRVArrayList, Context context) {
        this.courseItemRVArrayList = courseItemRVArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapterRV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapterRV.ViewHolder holder, int position) {
        CourseItemRV courseItemRV = courseItemRVArrayList.get(position);
        holder.courseNameTV.setText(courseItemRV.getCourseName());
        holder.courseDescriptionTV.setText(courseItemRV.getCourseDescription());
    }

    @Override
    public int getItemCount() {
        return courseItemRVArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTV, courseDescriptionTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.dhaCourseName);
            courseDescriptionTV = itemView.findViewById(R.id.dhaCourseDescription);
        }
    }
}
