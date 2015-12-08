package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.PictureItemBinding;
import com.dfsebook.mssage.entity.ActivePicture;

import java.util.List;

/**
 * Created by Administrator on 15-10-9.
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private List<ActivePicture> activePictures;

    public PictureAdapter(List<ActivePicture> activePictures) {
        this.activePictures = activePictures;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PictureItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.picture_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding.getRoot());
        viewHolder.setBinding(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
        ActivePicture activePicture = activePictures.get(position);
        holder.getBinding().setVariable(BR.picture,activePicture);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return activePictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private PictureItemBinding binding;

        public PictureItemBinding getBinding() {
            return binding;
        }

        public void setBinding(PictureItemBinding binding) {
            this.binding = binding;
        }

        public ViewHolder(View itemView) {
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



}
