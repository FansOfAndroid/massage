package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.SharingItemBinding;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.util.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-10-13.
 */
public class SharingAdapter extends RecyclerView.Adapter<SharingAdapter.ViewHolder>{

    private List<Sharing> sharings ;


    public SharingAdapter(List<Sharing> sharings) {
        this.sharings = getSignedSharing(sharings);
    }

    private List<Sharing> getSignedSharing(List<Sharing> sharings){
        List<Sharing> result = new ArrayList<>();
        if(DataSource.currentCustomer != null) {
            for (Sharing sharing : sharings) {
                if (sharing.getSharingTitle() == 1 ||
                        sharing.getCustomerId() == DataSource.currentCustomer.getCustomerId())
                    result.add(sharing);
            }
        }
        else {
            for (Sharing sharing : sharings) {
                if (sharing.getSharingTitle() == 1 )
                    result.add(sharing);
            }
        }
        return result;

    }

    @Override
    public int getItemCount() {
        return sharings == null ? 0 :sharings.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Sharing sharing = sharings.get(position);
        sharing.setSelfShow(true);
        holder.getBinding().setVariable(BR.sharing,sharing);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SharingItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.sharing_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private SharingItemBinding binding;

        public SharingItemBinding getBinding() {
            return binding;
        }

        public void setBinding(SharingItemBinding binding) {
            this.binding = binding;
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addfresh(Sharing sharing){
        sharings.add(0,sharing);
        notifyDataSetChanged();
        notifyItemChanged(0);
    }

}
