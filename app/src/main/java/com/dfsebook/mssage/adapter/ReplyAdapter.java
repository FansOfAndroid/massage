package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.ReplyItemBinding;
import com.dfsebook.mssage.entity.Reply;

import java.util.List;

/**
 * Created by Administrator on 15-11-7.
 */
public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder>{

    private List<Reply> replies;

    public ReplyAdapter(List<Reply> replies) {
        this.replies = replies;
    }

    @Override
    public ReplyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ReplyItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.reply_item, parent, false);
        ReplyViewHolder replyViewHolder = new ReplyViewHolder(binding.getRoot());
        replyViewHolder.setBinding(binding);
        return replyViewHolder;
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder{

        private ReplyItemBinding binding;

        public ReplyItemBinding getBinding() {
            return binding;
        }

        public void setBinding(ReplyItemBinding binding) {
            this.binding = binding;
        }

        public ReplyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(ReplyViewHolder holder, int position) {
        Reply reply = replies.get(position);
        holder.getBinding().setVariable(BR.reply,reply);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return replies == null ? 0 : replies.size();
    }

    public void addReply(Reply reply){
        replies.add(reply);
        notifyDataSetChanged();
    }
}
