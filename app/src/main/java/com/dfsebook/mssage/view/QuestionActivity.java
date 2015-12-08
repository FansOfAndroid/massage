package com.dfsebook.mssage.view;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dfsebook.mssage.R;
import com.dfsebook.mssage.entity.Question;
import com.dfsebook.mssage.entity.Reply;
import com.dfsebook.mssage.fold.FoldLayout;
import com.dfsebook.mssage.fragments.QuestionFragment;
import com.dfsebook.mssage.fragments.ReplyFragment;
import com.dfsebook.mssage.util.DataSource;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements QuestionFragment.OnFragmentInteractionListener{

    private FoldLayout foldLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        foldLayout = (FoldLayout) this.findViewById(R.id.main_view_container);
        foldLayout.setFragmentManager(this.getSupportFragmentManager());
        QuestionFragment feedFragment = new QuestionFragment();
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment detailFrag = getSupportFragmentManager().findFragmentByTag(FoldLayout.FRAGMENT_DETAIL_VIEW_TAG);
        fragmentTransaction.replace(R.id.main_view_container, feedFragment, "feed");
        if (detailFrag != null)
            fragmentTransaction.remove(detailFrag);
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentInteraction(View view,Question question) {
        int[] containerLocation = new int[2];
        this.foldLayout.getLocationInWindow(containerLocation);
        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);
        int location = viewLocation[1] + getResources().getDimensionPixelSize(R.dimen.image_height) - containerLocation[1];
        List<Reply> replies = new ArrayList<>();
        for (Reply reply : DataSource.getData().getReplies()){
            if(reply.getQuestionId() == question.getQuestionId()) {
                if(DataSource.currentCustomer != null) {
                    if (reply.getCustomerId() == DataSource.currentCustomer.getCustomerId() ||
                            reply.getReplyTitle() == 1) {
                        replies.add(reply);
                    }
                }
                else {
                    if(reply.getReplyTitle() == 1){
                        replies.add(reply);
                    }
                }
            }
        }
        openItemDetailView(replies, question.getQuestionId(), location);
    }

    private void openItemDetailView(List<Reply> replies,int questionId, int location) {
        ReplyFragment detail = ReplyFragment.newInstance(replies, questionId);
        this.foldLayout.setFoldCenter(location);
        getSupportFragmentManager().beginTransaction().add(R.id.main_view_container, detail, FoldLayout.FRAGMENT_DETAIL_VIEW_TAG)
                .commitAllowingStateLoss();
    }

}
