package com.dfsebook.mssage.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.ArrangeItemBinding;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.util.PieChart;

import java.util.List;

/**
 * Created by Administrator on 15-11-6.
 */
public class ArrangeAdapter extends RecyclerView.Adapter<ArrangeAdapter.ArrageViewHolder>{

    private List<Appointment> appointments;

    private Context context;

    public ArrangeAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public ArrageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ArrangeItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.arrange_item, parent, false);
        ArrageViewHolder arrageViewHolder = new ArrageViewHolder(binding.getRoot());
        arrageViewHolder.setBinding(binding);
        return  arrageViewHolder;
    }

    @Override
    public void onBindViewHolder(final ArrageViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
        holder.getBinding().setVariable(BR.arrapp,appointment);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return appointments == null ? 0: appointments.size();
    }

    public class ArrageViewHolder extends RecyclerView.ViewHolder{

        private ArrangeItemBinding binding;

        public ArrangeItemBinding getBinding() {
            return binding;
        }

        public void setBinding(ArrangeItemBinding binding) {
            this.binding = binding;
        }

        public ArrageViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickLitener{
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void fresh(List<Appointment> appointmentList){
        this.appointments = appointmentList;
        notifyDataSetChanged();
    }
}
