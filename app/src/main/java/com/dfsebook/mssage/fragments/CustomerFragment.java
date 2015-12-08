package com.dfsebook.mssage.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.CustomerAdapter;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.entity.Sharing;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.InitRecyclerView;
import com.dfsebook.mssage.view.SharingActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-11-7.
 */
public class CustomerFragment extends Fragment{

    private List<Customer> customers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, null);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.showList);
        InitRecyclerView.initLinearLayoutVERTICAL(getContext(), recyclerView);
        customers = DataSource.getData().getCustomers();
        CustomerAdapter customerAdapter = new CustomerAdapter(customers);
        recyclerView.setAdapter(customerAdapter);
        customerAdapter.setOnItemClickLitener(new CustomerItemClick());
        return  view;
    }

    private class CustomerItemClick implements CustomerAdapter.OnItemClickLitener{
        @Override
        public void onItemClick(View view, int position) {
            Customer customer = DataSource.getData().getCustomers().get(position);
            List<Sharing> sharings = new ArrayList<>();
            for( Sharing sharing : DataSource.getData().getSharings()){
                if(sharing.getCustomerId() == customer.getCustomerId()){
                    sharings.add(sharing);
                }
            }
            Intent intent = new Intent(getActivity(), SharingActivity.class);
            intent.putExtra("sharings", (Serializable)sharings);
            intent.putExtra("customer",customer);
            getActivity().startActivity(intent);
        }
    }
}
