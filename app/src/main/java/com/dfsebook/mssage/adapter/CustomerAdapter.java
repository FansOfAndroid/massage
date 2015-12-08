package com.dfsebook.mssage.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.BR;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.databinding.CustomerItemBinding;
import com.dfsebook.mssage.entity.Customer;

import java.util.List;

/**
 * Created by Administrator on 15-11-7.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerView>{

    private List<Customer> customers ;

    public CustomerAdapter(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public CustomerView onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomerItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.customer_item, parent, false);
        CustomerView customerView = new CustomerView(binding.getRoot());
        customerView.setBinding(binding);
        return customerView;
    }

    @Override
    public void onBindViewHolder(final CustomerView holder, int position) {
        Customer customer = customers.get(position);
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
        holder.getBinding().setVariable(BR.customer,customer);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return customers == null ? 0 : customers.size();
    }

    public class CustomerView extends RecyclerView.ViewHolder{

        public CustomerItemBinding getBinding() {
            return binding;
        }

        public void setBinding(CustomerItemBinding binding) {
            this.binding = binding;
        }

        private CustomerItemBinding binding;

        public CustomerView(View itemView) {
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
