package com.dfsebook.mssage.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 15-11-14.
 */
public class FileHelper {

    private Context context;
    private boolean hasSD = false;
    private String SDPATH;
    private String FILESPATH;

    public FileHelper(Context context) {
        this.context = context;
        hasSD = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        SDPATH = Environment.getExternalStorageDirectory().getPath();
        FILESPATH = this.context.getFilesDir().getPath();
    }

    public boolean isExistsed(String fileName){
        File file = new File(SDPATH + "//" + fileName);
        if(file == null) return false;
        return file.exists();
    }

    public File createSDFile(String fileName) throws IOException {
        File file = new File(SDPATH + "//" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public boolean deleteSDFile(String fileName) {
        File file = new File(SDPATH + "//" + fileName);
        if (file == null || !file.exists() || file.isDirectory())
            return false;
        return file.delete();
    }

    public void writeSDFile(String content,String fileName) {

           try {
            FileOutputStream fout = new FileOutputStream(SDPATH + "//" + fileName);
            byte[] bytes = content.getBytes();
            fout.write(bytes);
            fout.close();
        }
        catch (Exception e){
        }
    }

    public String readSDFile(String fileName) {
        String result = "";
        try {
            FileInputStream fin = new FileInputStream(SDPATH + "//" + fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            result = new String(buffer) ;
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getFILESPATH() {
        return FILESPATH;
    }
    public String getSDPATH() {
        return SDPATH;
    }
    public boolean hasSD() {
        return hasSD;
    }
}
