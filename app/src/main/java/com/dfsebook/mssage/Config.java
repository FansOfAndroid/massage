package com.dfsebook.mssage;

import android.graphics.Color;

import javax.ws.rs.PUT;

/**
 * Created by Administrator on 15-10-8.
 */
public class Config {

    public static final String HOST_NAME = "120.27.41.151";

    public static final int HOST_PORT = 2121;

    public static final String USER_NAME = "xuejian";

    public static final String USER_CODE = "123456";

    public static final String HOST_ROOT = "/picturesS/";

    public static final String UPDATE_PATH = "/picturesS/";

    public static final String SERVICE_URL = "http://120.27.41.151:8080/MassageWeb/msage/";

    public static final String SERVER_APK_PATH = "http://120.27.41.151/MassageWeb.apk";

    public static final String OPERATE_SUCCESS  = "success to operate ";

    public static final String OPERATE_FAIL = "fail to operate";

    public static final String SERVICE_BPIC = "http://120.27.41.151/picturesL/";

    public static final String SERVICE_SPIC = "http://120.27.41.151/picturesS/";

    public static final int APPOINTMENT_SHOW_DAYS = 7;

    public static final int LIMIT_NUM = 20;

    public static final int ADMINISTRATORID = 3;

    public static final float PROGRESSBAR_TEXTSIZE = 20f;

    public static final int PROGRESSBAR_TEXTCOLOR = Color.RED;

    public static final int OPERATER_WAIT_IN = 0;

    public static final int OPERATER_APPOINTMENTED = 1;

    public static final int OPERATER_WAIT_DELETE = 2;

    public static final int OPERATER_WAIT_CHANGE = 3;

    public static final int OPERATER_QUESTION = 4;

    public static final int OPERATER_REPLY = 5;

    public static final int OPERATER_SHARING = 6;

    public static final int APPOINTMENTINFO_SELF = 1;

    public static final int APPOINTMENTINFO_OTHER = 0;


}
