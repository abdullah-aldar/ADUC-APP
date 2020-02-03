package com.aldar.studentportal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aldar.studentportal.R;
import com.aldar.studentportal.models.faqModels.FAQModel;

import java.util.ArrayList;
import java.util.List;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.MyViewHolder> {

    private Context context;
    private final LayoutInflater inflater;
    private List<FAQModel> faqModelList;
    private ArrayList<String> alIndexPosition = new ArrayList<>();

    public FAQAdapter(Context context, List<FAQModel> faqModelList) {

        inflater = LayoutInflater.from(context);
        this.context = context;
        this.faqModelList = faqModelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_faq_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
     FAQModel model = faqModelList.get(position);

     holder.tvQuestion.setText(model.getQuestion());
     holder.tvAnswer.setText(model.getAnswer());

     holder.layoutQuestion.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (alIndexPosition.contains(String.valueOf(position))) {
                 holder.ivArrow.setImageResource(R.drawable.down);
                 holder.tvAnswer.setVisibility(View.GONE);
                 alIndexPosition.remove(String.valueOf(position));
             } else {
                 alIndexPosition.add(String.valueOf(position));
                 holder.ivArrow.setImageResource(R.drawable.up);
                 holder.tvAnswer.setVisibility(View.VISIBLE);

             }
         }
     });

    }

    @Override
    public int getItemCount() {
        return faqModelList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layoutQuestion;
        TextView tvQuestion;
        TextView tvAnswer;
        ImageView ivArrow;
        private MyViewHolder(View itemView) {
            super(itemView);
            layoutQuestion = itemView.findViewById(R.id.question_layout);
            tvQuestion = itemView.findViewById(R.id.tv_faq_question);
            tvAnswer = itemView.findViewById(R.id.tv_faq_answer);
            ivArrow = itemView.findViewById(R.id.iv_arrow);
        }
    }
}