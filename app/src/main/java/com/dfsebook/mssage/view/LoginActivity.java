package com.dfsebook.mssage.view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dfsebook.mssage.Config;
import com.dfsebook.mssage.MyApp;
import com.dfsebook.mssage.R;
import com.dfsebook.mssage.adapter.ArrangeAdapter;
import com.dfsebook.mssage.entity.Customer;
import com.dfsebook.mssage.listener.AppErrorListener;
import com.dfsebook.mssage.listener.InsertCustomerListener;
import com.dfsebook.mssage.operation.InsertCustomer;
import com.dfsebook.mssage.operation.InsertTouxiang;
import com.dfsebook.mssage.util.BitmapUtil;
import com.dfsebook.mssage.util.DataSource;
import com.dfsebook.mssage.util.FtpUtil;
import com.dfsebook.mssage.util.ImageUtil;
import com.dfsebook.mssage.util.OperateImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 729;
    private static int UPLOAD_SUCCESS = 1729;
    private EditText cname;
    private EditText recode;
    private EditText code;
    private Button register;
    private Button login ;
    private Button cancel;
    private Button selectTx;
    private ImageView imageView;
    private int selectIndex;
    private List<String> txs;
    private String tx;
    private Bitmap bitmap;
    private boolean newTouxiang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();

        register.setOnClickListener(new RegisterClickListener());
        login.setOnClickListener(new LoginClickListener());
        cancel.setOnClickListener(new CancelClickListener());
        imageView.setOnClickListener(new ImageClickListener());
        selectTx.setOnClickListener(new SelectTxClickListener());
    }

    private void init(){
        cname = (EditText)this.findViewById(R.id.customerName);
        recode = (EditText)this.findViewById(R.id.reCode);
        code = (EditText)this.findViewById(R.id.customerCode);
        register = (Button)this.findViewById(R.id.register);
        login = (Button)this.findViewById(R.id.login);
        cancel = (Button)this.findViewById(R.id.cancel);
        selectTx = (Button)this.findViewById(R.id.selectTx);
        imageView = (ImageView)findViewById(R.id.customerpic);
        login.setFocusable(true);
        login.setFocusableInTouchMode(true);
        selectIndex = 0;
        newTouxiang = false;
        tx = "tx1.jpg";

        txs = DataSource.getData().getTouxiangs();
        OperateImageView.loadImageVolley(Config.SERVICE_SPIC + txs.get(0), imageView);
    }

    private boolean nameIsExisted(){
        for(Customer customer : DataSource.getData().getCustomers()){
            if(customer.getCustomerName().equals(cname.getText().toString())) {
                return true;
            }
        }
        return false;
    }

    private boolean codesIsSame(){
        if (recode.getText().toString().equals(code.getText().toString()) == false) {
            return true;
        }
        return false;
    }

    private  boolean nameOrCodesIsNull(){
        if(TextUtils.isEmpty(cname.getText().toString())
                ||TextUtils.isEmpty(recode.getText().toString())
                || TextUtils.isEmpty(code.getText().toString())) {
            return true;
        }
        return false;
    }

    public static boolean isMobileNumber(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean iswrong(String s){
        Pattern pattern = Pattern.compile("^(([a-z]*|[a-z]*|/d*|[-_/~!@#/$%/^&/*/./(/)/[/]/{/}<>/?////'/]*)|.{0,5})$|/s ");
        Matcher m = pattern.matcher(s);
        return m.matches();
    }

    private void whichTxSelected(){
        if(newTouxiang)
            tx = MyApp.PHONE + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            bitmap = ImageUtil.getSmallBitmap(picturePath,200,200);
            imageView.setImageBitmap(bitmap);
            newTouxiang = true;
        }
    }

    private class RegisterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            if (nameOrCodesIsNull()) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.inppputnull), Toast.LENGTH_SHORT).show();
                return;
            }
            if (nameIsExisted()) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.name_exist), Toast.LENGTH_SHORT).show();
                return;
            }
            if (codesIsSame()) {
                Toast.makeText(LoginActivity.this, getResources().getString(R.string.recodeerror), Toast.LENGTH_SHORT).show();
                return;
            }
            hideKeyboard();
            whichTxSelected();
            if(newTouxiang)
                uploadTouxiang();
            else {
                InsertCustomer insertCustomer = new InsertCustomer(LoginActivity.this,
                        cname.getText().toString(),
                        code.getText().toString(),tx);
                insertCustomer.OperateWebService();
            }
        }
    }

    private class LoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    private class CancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            if(DataSource.reActivity == null){
                intent.setClass(LoginActivity.this,MainActivity.class);
            }
            else {
                intent.setClass(LoginActivity.this,DataSource.reActivity.getClass());
            }
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    private class ImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            selectIndex ++;
            OperateImageView.loadImageVolley(Config.SERVICE_SPIC + txs.get(selectIndex % txs.size()), imageView);
            tx = txs.get(selectIndex % txs.size());
        }
    }

    private class SelectTxClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent i = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    private void uploadTouxiang(){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(UPLOAD_SUCCESS == (int)msg.obj){
                    InsertCustomer insertCustomer = new InsertCustomer(LoginActivity.this,
                            cname.getText().toString(),
                            code.getText().toString(),tx);
                    insertCustomer.OperateWebService();
                }
            }
        };
        new Thread(){
            public void run() {
                InputStream iinputStream = ImageUtil.Bitmap2InputStream(bitmap, 1);
                FtpUtil ftpUtil = new FtpUtil(Config.HOST_NAME,Config.HOST_PORT,
                        Config.USER_NAME,Config.USER_CODE,Config.HOST_ROOT );
                if(ftpUtil.ftpLogin()) {
                    ftpUtil.uploadFile(iinputStream, Config.UPDATE_PATH, tx);
                }
                ftpUtil.ftpLogOut();
                Message message = handler.obtainMessage(0,UPLOAD_SUCCESS);
                handler.sendMessage(message);
            }
        }.start();
    }

}

