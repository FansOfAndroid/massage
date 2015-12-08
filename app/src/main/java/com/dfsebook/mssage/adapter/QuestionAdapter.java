package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.QuestionItemBinding;
import com.dfsebook.mssage.entity.Question;
import com.dfsebook.mssage.util.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-7.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHoder>{

    private List<Question> questions ;

    public QuestionAdapter() {
        questions = getSignedQuestion(DataSource.getData().getQuestions());
    }

    private List<Question> getSignedQuestion(List<Question> questions){
        List<Question> result = new ArrayList<>();
        for(Question question : questions){
            if(DataSource.currentCustomer != null) {
                if (question.getQuestionTitle() == 1 ||
                        question.getCustomerId() == DataSource.currentCustomer.getCustomerId())
                    result.add(question);
            }
            else {
                if (question.getQuestionTitle() == 1)
                    result.add(question);
            }
        }
        return result;
    }

    @Override
    public QuestionViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.question_item, parent, false);
        QuestionViewHoder questionViewHoder = new QuestionViewHoder(binding.getRoot());
        questionViewHoder.setBinding(binding);
        return questionViewHoder;
    }

    @Override
    public int getItemCount() {
        return questions == null ? 0 : questions.size();
    }

    @Override
    public void onBindViewHolder(final QuestionViewHoder holder, int position) {
        final Question question = questions.get(position);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickLitener != null) {
                        mOnItemClickLitener.onItemClick(v,question);
                    }

                }
            });
        }
        holder.getBinding().setVariable(BR.question,question);
        holder.getBinding().executePendingBindings();
    }

    public class QuestionViewHoder extends RecyclerView.ViewHolder{

        private QuestionItemBinding binding;

        public QuestionItemBinding getBinding() {
            return binding;
        }

        public void setBinding(QuestionItemBinding binding) {
            this.binding = binding;
        }

        public QuestionViewHoder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickLitener{
        void onItemClick(View view,Question question);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void addQuestion(Question question){
        questions.add(0,question);
        notifyDataSetChanged();
    }
}
