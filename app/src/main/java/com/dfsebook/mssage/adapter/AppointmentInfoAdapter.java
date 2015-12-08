package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.AppointmentInfoBinding;
import com.dfsebook.mssage.entity.AppointmentInfo;

import java.util.List;

/**
 * Created by Administrator on 15-10-18.
 */
public class AppointmentInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private List<AppointmentInfo> appointmentInfos;

    public AppointmentInfoAdapter(List<AppointmentInfo> appointmentInfos) {
        this.appointmentInfos = appointmentInfos;
    }

    @Override
    public int getItemCount() {
        return appointmentInfos == null ? 0 : appointmentInfos.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        AppointmentInfo appointmentInfo = appointmentInfos.get(position);
        if(getItemViewType(position) == 1) {
            if(onItemClickListener != null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
            }
            OwnerViewHolder ownerViewHolder = (OwnerViewHolder) holder;
            ownerViewHolder.getBinding().setVariable(BR.info, appointmentInfo);
            ownerViewHolder.getBinding().executePendingBindings();
        }
        else {
            OtherViewHolder otherViewHolder = (OtherViewHolder)holder;
            otherViewHolder.getBinding().setVariable(BR.info,appointmentInfo);
            otherViewHolder.getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemViewType(int position) {
        AppointmentInfo appinfo = appointmentInfos.get(position);
        if(appinfo.isOwner()){
            if(appinfo.getAppointmentStation() == 1 ||appinfo.getAppointmentStation() == 0)
                return 1;
            else return 0;
        } else return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AppointmentInfoBinding binding = DataBindingUtil.inflate(inflater, R.layout.appointment_info, parent, false);
        if(viewType == 1) {
            OwnerViewHolder viewHolder = new OwnerViewHolder(binding.getRoot());
            viewHolder.setBinding(binding);
            return viewHolder;
        }
        else {
            OtherViewHolder viewHolder = new OtherViewHolder(binding.getRoot());
            viewHolder.setBinding(binding);
            return viewHolder;
        }
    }

    public class OtherViewHolder extends RecyclerView.ViewHolder{

        private AppointmentInfoBinding binding;

        public AppointmentInfoBinding getBinding() {
            return binding;
        }

        public void setBinding(AppointmentInfoBinding binding) {
            this.binding = binding;
        }

        public OtherViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class OwnerViewHolder extends RecyclerView.ViewHolder{

        private AppointmentInfoBinding binding;

        public AppointmentInfoBinding getBinding() {
            return binding;
        }

        public void setBinding(AppointmentInfoBinding binding) {
            this.binding = binding;
        }

        public OwnerViewHolder(View itemView) {
            super(itemView);
        }
    }


    public void add(AppointmentInfo appointmentInfo) {
        appointmentInfos.add(appointmentInfo);
        notifyDataSetChanged();
    }

    public void delete(AppointmentInfo appointmentInfo){
        appointmentInfos.remove(appointmentInfo);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void  setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
