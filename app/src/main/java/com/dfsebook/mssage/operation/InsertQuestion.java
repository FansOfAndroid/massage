package com.dfsebook.mssage.operation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.adapter.QuestionAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Question;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-20.
 */
public class InsertQuestion extends BasicOperate {

    private Context context;

    private String questionContent;

    private Question question;

    private QuestionAdapter adapter;

    public InsertQuestion(Context context, String questionContent, QuestionAdapter adapter) {
        this.context = context;
        this.questionContent = questionContent;
        this.adapter = adapter;
        question = formQuestion(questionContent);
    }


    @Override
    protected void OperateDataSource() {
        DataSource.getData().getQuestions().add(question);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        final StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "insertMsQuestion/" + question.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(Config.OPERATE_FAIL.equals(s))
                            ErrorDialog.show(context);
                        else {
                            OperateDataSource();
                            NotifyDataSetChange();
                        }
                    }
                },
                new AppErrorListener(context)
        );
        MyApp.mRequestQueue.add(request);
        super.OperateWebService();
    }

    @Override
    protected void NotifyDataSetChange() {
        adapter.addQuestion(question);
        super.NotifyDataSetChange();
    }

    private Question formQuestion(String questionContent){
        questionContent = DataSource.transURLDecoderString(questionContent);
        Question question = new Question();
        question.setCustomerId(DataSource.currentCustomer.getCustomerId());
        question.setCustomerName(DataSource.currentCustomer.getCustomerName());
        question.setCustomerPhoto(DataSource.currentCustomer.getCustomerPhoto());
        question.setNewQuestion(true);
        question.setQuestionContent(questionContent);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        question.setQuestionDate(simpleDateFormat.format(new Date()));
        question.setQuestionTitle(Config.OPERATER_WAIT_IN);
        return question;
    }
}
