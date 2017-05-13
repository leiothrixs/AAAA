package com.ezworking.wechatunlock.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.ezworking.my_android.base.utils.DeviceUtils;
import com.ezworking.my_android.base.utils.NetWorkUtil;
import com.ezworking.my_android.base.utils.ToastUtil;
import com.ezworking.wechatunlock.application.AppCache;
import com.ezworking.wechatunlock.application.AppConfig;
import com.ezworking.wechatunlock.domain.ResultBase;

import org.json.JSONObject;

import java.util.UUID;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by dujiande on 2016/8/11.
 */
public class RequestApi {

    public static AsyncHttpClient client = null;
    public static String CLIENT = "android";
    private static String FORMAT = "json";
    private static String APP_TYPE = "trainingSys";

    private static boolean isDebug = true;


    static {
        client = new AsyncHttpClient();
        client.setConnectTimeout(20000);
        client.setResponseTimeout(20000);

    }

    public static void jsonPost(Context context,String url,JSONObject jsonObject,AsyncHttpResponseHandler asyncHttpResponseHandler){
        Log.d("api",url);
        jsonObject = appendJsonObject(context,jsonObject);
      //2017.05.10  String signStr = SzSign.createSign(jsonObject);
        try{
            //2017.05.10      jsonObject.put("sign",signStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!NetWorkUtil.isNetworkConnected(context)){
            ToastUtil.showToast(context,"网络不可用!");
            return;
        }
       String jsonStr = jsonObject.toString();
        //jsonStr = DES.encrypt(jsonStr, Constant.DES_KEY);
        StringEntity stringEntity = new StringEntity(jsonStr,"UTF-8");
        Log.d("api",jsonStr);
        client.addHeader("Content-Type","application/json; charset=UTF-8");
        client.post(context,url, stringEntity, "application/json", asyncHttpResponseHandler);

    }

    public static JSONObject appendJsonObject(Context context,JSONObject jsonObject){
        if(jsonObject == null){
            jsonObject = new JSONObject();
        }
        String pkName;
        String versionName;
        try {
            DeviceUtils.init(context);
        }catch (Exception e){
            e.printStackTrace();
        }

        String deviceid;
        try {
            pkName = context.getPackageName();
            versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
            deviceid = DeviceUtils.getDeviceUniqueCode();
            if(TextUtils.isEmpty(deviceid)){
                deviceid = getAppId(context);
            }
            jsonObject.put("appType", APP_TYPE);
            jsonObject.put("appversion",versionName);
            jsonObject.put("deviceId", deviceid);
            jsonObject.put("hig", "");
            jsonObject.put("lat", "");
            jsonObject.put("lng", "");
            jsonObject.put("platform", CLIENT);
            if(AppCache.getInstance().isUserLogin() && !TextUtils.isEmpty(AppCache.getInstance().getToken())){
                jsonObject.put("token", AppCache.getInstance().getToken());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }



    public static String getAppId(Context context) {
        String uniqueID = AppConfig.getAppConfig(context).get(AppConfig.CONF_APP_UNIQUEID);
        if (TextUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            AppConfig.getAppConfig(context).set(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 登录
     * @param context
     * @param account
     * @param pwd
     * @param asyncHttpResponseHandler
     */
    public static void login(Context context, String account, String pwd, AsyncHttpResponseHandler asyncHttpResponseHandler){

        try {
            String url = ConstantNetUrl.LOGIN;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("account",account);
            jsonObject.put("pwd",pwd );
            jsonPost(context,url,jsonObject,asyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean isSuccess(ResultBase resultBase, Context context){
        if(resultBase == null){
            ToastUtil.showToast(context,"请求失败，请稍后重试!");
            return false;
        }
        if(resultBase.isSuccess){
            //Log.d("api",resultBase.toString());
            return true;
        }
        return false;
    }

    /**
     * 取消请求
     * @param context
     * @param mayInterruptIfRunning
     */
    public static void cancelRequest(Context context,boolean mayInterruptIfRunning){
        client.cancelRequests(context,mayInterruptIfRunning);
    }



}
