package com.dfsebook.mssage.operation;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.adapter.ReplyAdapter;
import com.dfsebook.mssage.dialog.ErrorDialog;
import com.dfsebook.mssage.entity.Reply;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.util.DataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 15-11-21.
 */
public class InserReply extends BasicOperate {

    private Context context;

    private String replyContent;

    private Reply reply;

    private ReplyAdapter adapter;

    private int questionId;

    public InserReply(Context context, String replyContent, int questionId,ReplyAdapter adapter) {
        this.context = context;
        this.replyContent = replyContent;
        this.adapter = adapter;
        this.questionId = questionId;
        this.reply = formReply(replyContent);
    }

    @Override
    protected void OperateDataSource() {
        DataSource.getData().getReplies().add(reply);
        super.OperateDataSource();
    }

    @Override
    public void OperateWebService() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.SERVICE_URL + "insertMsReply/" + reply.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s.equals(Config.OPERATE_FAIL))
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
        adapter.addReply(reply);
        super.NotifyDataSetChange();
    }

    private Reply formReply(String replycontent){
        replycontent = DataSource.transURLDecoderString(replycontent);
        Reply reply = new Reply();
        reply.setCustomerId(DataSource.currentCustomer.getCustomerId());
        reply.setCustomerPhoto(DataSource.currentCustomer.getCustomerPhoto());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        reply.setCommentDate(simpleDateFormat.format(new Date()));
        reply.setQuestionId(questionId);
        reply.setCustomerName(DataSource.currentCustomer.getCustomerName());
        reply.setReplyContent(replycontent);
        reply.setReplyTitle(0);
        return reply;
    }
}
