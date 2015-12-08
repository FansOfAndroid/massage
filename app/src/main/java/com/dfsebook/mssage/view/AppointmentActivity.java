package com.dfsebook.mssage.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.ArrangeAdapter;
import com.dfsebook.mssage.entity.Appointment;
import com.dfsebook.mssage.util.DataSource;

import java.util.List;

public class AppointmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Appointment> appointmentList;

    private ArrangeAdapter adapter;

    private Appointment appointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrange_listview);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        appointmentList = DataSource.getAppointments();
        init();
        adapter.setOnItemClickLitener(new ItemClick());
    }

    private void init(){
        recyclerView = (RecyclerView)findViewById(R.id.arrnge_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ArrangeAdapter(appointmentList);
        recyclerView.setAdapter(adapter);
    }

    private class ItemClick implements ArrangeAdapter.OnItemClickLitener{

        @Override
        public void onItemClick(View view, int position) {
            appointment = appointmentList.get(position);
            if (appointment.getLimitNum() == appointment.getOcupied()) {
                Toast.makeText(AppointmentActivity.this,
                        getResources().getString(R.string.appointment_full),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(AppointmentActivity.this, AppointmentInfoActivity.class);
            intent.putExtra("appointment", appointment);
            AppointmentActivity.this.startActivity(intent);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        appointmentList = DataSource.reGetAppointments();
        adapter.fresh(appointmentList);
    }

}
