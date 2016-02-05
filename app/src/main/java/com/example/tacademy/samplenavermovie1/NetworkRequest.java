package com.example.tacademy.samplenavermovie1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

/**
 * Created by Tacademy on 2016-02-05.
 */
public abstract class NetworkRequest<T> implements Runnable {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";

    public static final int ERROR_CODE_NETWORK = -1;
    public static final int ERROR_CODE_PARSE = -2;
    public static final int ERROR_CODE_HTTP = -3;
    public static final int ERROR_UNKNOWN = 0;


    NetworkManager.OnResultListener<T> mResultListener;
    NetworkManager manager;

    void setManager(NetworkManager manager){
        this.manager = manager;
    }

    public void setOnResultListener(NetworkManager.OnResultListener<T> listener){
        mResultListener = listener;
    }

    void sendSuccess(){
        if(mResultListener !=null){
            mResultListener.onSuccess(this, result);
        }
    }

    void sendFailure(){
        if(mResultListener!=null){
            mResultListener.onFailure(this, errorCode, responseCode, responseMessage, errorThrowable);
        }
    }

    T result;
    int errorCode;
    int responseCode;
    String responseMessage;
    Throwable errorThrowable;

    public abstract URL getURL() throws MalformedURLException;
    public String getRequestMethod(){
        return METHOD_GET;
    }

    public int getTimeout(){
        return 30000;
    }

    public void setOutput(OutputStream out){

    }

    public void setRequestHeader(HttpURLConnection conn){

    }

    public void setConfiguration(HttpURLConnection conn){

    }

    abstract protected T parse(InputStream is) throws ParseException;

    @Override
    public void run() {

        try {
            URL url = getURL();
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            String method = getRequestMethod();
            if(method == METHOD_POST || method == METHOD_PUT){
                conn.setDoOutput(true);
            }
            conn.setRequestMethod(method);
            setRequestHeader(conn);
            setConfiguration(conn); // 부가적인 설정을 하는 역할, 이 예제에서는 사용 안함
            conn.setConnectTimeout(getTimeout());
            conn.setReadTimeout(getTimeout());
            if(conn.getDoOutput()){
                OutputStream out = conn.getOutputStream();
                setOutput(out);
            }

            int code = conn.getResponseCode();
            if(code >= HttpURLConnection.HTTP_OK && code < HttpURLConnection.HTTP_MULT_CHOICE){
                InputStream is = conn.getInputStream();
                result = parse(is);
                manager.sendSuccess(this);
                return;
            }
            responseCode = code;
            responseMessage = conn.getResponseMessage();
            errorCode = ERROR_CODE_HTTP;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            errorThrowable = e;
            errorCode = ERROR_CODE_NETWORK;
        } catch (IOException e) {
            e.printStackTrace();
            errorThrowable = e;
            errorCode = ERROR_CODE_NETWORK;
        } catch (ParseException e) {
            e.printStackTrace();
            errorThrowable = e;
            errorCode = ERROR_CODE_PARSE;
        }
        manager.sendFailure(this);
    }
}
