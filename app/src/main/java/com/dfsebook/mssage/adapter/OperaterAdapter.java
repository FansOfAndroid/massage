package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.OperateItemBinding;
import com.dfsebook.mssage.entity.OperaterInfo;

import java.util.List;

/**
 * Created by Administrator on 15-11-6.
 */
public class OperaterAdapter extends RecyclerView.Adapter<OperaterAdapter.OperaterViewHolder>{

    private List<OperaterInfo> operaterInfos;

    private CustomerAdapter.OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(CustomerAdapter.OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }

    public OperaterAdapter(List<OperaterInfo> operaterInfos) {
        this.operaterInfos = operaterInfos;
    }

    @Override
    public OperaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        OperateItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.operate_item, parent, false);
        OperaterViewHolder operaterViewHolder = new OperaterViewHolder(binding.getRoot());
        operaterViewHolder.setBinding(binding);
        return operaterViewHolder;
    }

    @Override
    public void onBindViewHolder(final OperaterViewHolder holder, int position) {
        OperaterInfo operaterInfo = operaterInfos.get(position);
        if(null != onItemClickLitener){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int adapterPositon = holder.getAdapterPosition();
                    onItemClickLitener.onItemClick(view,adapterPositon);
                }
            });
        }
        holder.getBinding().setVariable(BR.operater,operaterInfo);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return operaterInfos == null ? 0 :operaterInfos.size();
    }

    public class OperaterViewHolder extends RecyclerView.ViewHolder{

        private OperateItemBinding binding;

        public OperateItemBinding getBinding() {
            return binding;
        }

        public void setBinding(OperateItemBinding binding) {
            this.binding = binding;
        }

        public OperaterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener{
        public void OnItemClick(View view,int position);
    }
}
