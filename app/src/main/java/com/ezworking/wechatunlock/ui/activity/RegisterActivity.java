package com.ezworking.wechatunlock.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ezworking.my_android.base.utils.AsyncHttpClientUtil;
import com.ezworking.my_android.base.utils.CommonActionBar;
import com.ezworking.my_android.base.utils.LogUtil;
import com.ezworking.my_android.base.utils.MD5;
import com.ezworking.my_android.base.utils.PageJumps;
import com.ezworking.my_android.base.utils.SzSign;
import com.ezworking.my_android.base.utils.ToastUtil;
import com.ezworking.my_android.base.view.LoadingDialog;
import com.ezworking.wechatunlock.R;
import com.ezworking.wechatunlock.api.ConstantNetUrl;
import com.ezworking.wechatunlock.api.RequestApi;
import com.ezworking.wechatunlock.application.AppCache;
import com.ezworking.wechatunlock.domain.RegsiterInfoBean;
import com.ezworking.wechatunlock.domain.ResultBean;
import com.ezworking.wechatunlock.ui.AppBaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppBaseActivity {
    @Bind(R.id.resiger)
    Button mRegiser;

    @Bind(R.id.lLayout)
    LinearLayout lLayout;

    @Bind(R.id.iv_avatar)
    public SimpleDraweeView simpleDraweeView;

    @Bind(R.id.phone_num)
    EditText mPhoneNum;

    @Bind(R.id.code)
    EditText mCode;

    @Bind(R.id.get_code)
    Button mGetCode;

    @Bind(R.id.nick_name)
    EditText mNickName;

    @Bind(R.id.qq)
    EditText mQQ;

    @Bind(R.id.wechat)
    EditText mWeChat;

    @Bind(R.id.referrer_id)
    EditText mReferrerId;

    @Bind(R.id.pwd)
    EditText mPwd;

    @Bind(R.id.show_code_ib)
    ImageButton mShowCodeIb;

    private LoadingDialog mLoadDialog;
    private Timer timer;
    private int time = 30;
    private TimerTask timerTask;
    private String phoneNum;
    private String Code;
    private String Nick;
    private String QQ;
    private String weChat;
    private String referrerID;
    private String pwd;

    private Boolean isShow = false;

    private ResultBean resultbean;
    private RegsiterInfoBean regsiterInfoBean;
    private ResultBean result;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void initData() {
        initCustomActionBar();
        mRegiser.setAlpha(0.4f);
        mRegiser.setEnabled(false);
        checkEditText(mPhoneNum);
        checkEditText(mCode);
        checkEditText(mNickName);
        checkEditText(mQQ);
        checkEditText(mWeChat);
        checkEditText(mReferrerId);
        checkEditText(mPwd);
       /* WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams layoutParams = lLayout.getLayoutParams();
        layoutParams.height = height /4;
        lLayout.setLayoutParams(layoutParams);*/
        simpleDraweeView.setImageResource(R.drawable.icon_avatar);

        mRegiser.setEnabled(false);
        mRegiser.setAlpha(0.4f);
        mGetCode.setEnabled(false);
        mGetCode.setAlpha(0.4f);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mGetCode.setEnabled(false);
                    mGetCode.setBackgroundResource(R.drawable.shape_gray);
                    mGetCode.setText(time + "");
                    time--;
                    if (time < 0) {
                        mGetCode.setEnabled(true);
                        mGetCode.setBackgroundResource(R.drawable.shape_orage);
                        mGetCode.setText(R.string.get_code);
                        timer.cancel();
                        timer = null;
                        timerTask = null;
                        time = 60;
                    }
                    break;
            }

        }
    };
    @Override
    public void initListener() {
        mRegiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


        mGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerifyCode();
            }
        });

        mShowCodeIb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow){
                    mPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isShow = true;
                    mShowCodeIb.setImageResource(R.drawable.icon_pwd_open);
                }else{
                    mPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isShow = false;
                    mShowCodeIb.setImageResource(R.drawable.icon_pwd_close);
                }
                mPwd.setSelection(mPwd.getText().length());
            }
        });
    }


    public void checkEditText(EditText editText) {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                phoneNum = mPhoneNum.getText().toString().trim();
                Code = mCode.getText().toString().trim();
                Code = mCode.getText().toString().trim();
                Nick = mNickName.getText().toString().trim();
                QQ = mQQ.getText().toString().trim();
                weChat = mWeChat.getText().toString().trim();
                referrerID = mReferrerId.getText().toString().trim();
                pwd = mPwd.getText().toString().trim();

                if (time==30&&phoneNum.length()==11){
                    mGetCode.setEnabled(true);
                    mGetCode.setAlpha(1.0f);
                    mGetCode.setBackgroundResource(R.drawable.shape_orage);
                }else if(time==30&&phoneNum.length()!=11){
                    mGetCode.setEnabled(false);
                    mGetCode.setAlpha(0.4f);
                }

                checkInputContent();
            }
        };
        editText.addTextChangedListener(tw);
    }


    private void checkInputContent() {
        if (!TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(Code) && !TextUtils.isEmpty(Nick) && !TextUtils.isEmpty(QQ)
                && !TextUtils.isEmpty(QQ)&& !TextUtils.isEmpty(weChat)&& !TextUtils.isEmpty(referrerID)
                && !TextUtils.isEmpty(pwd)&& pwd.length()>=6) {
            mRegiser.setEnabled(true);
            mRegiser.setAlpha(1.0f);
        } else {
            mRegiser.setEnabled(false);
            mRegiser.setAlpha(0.4f);
        }
    }
    private void showLoading(String msg) {
        mLoadDialog = new LoadingDialog(msg);
        mLoadDialog.show(getSupportFragmentManager(), LoadingDialog.TAG);
    }

    private void dismissLoading() {
        if (mLoadDialog != null) {
            mLoadDialog.dismiss();
        }
    }

    private void getVerifyCode() {
        try {
            JSONObject jsonObject = new JSONObject();
            String signStr = SzSign.createSign(jsonObject);
            String token = MD5.getMessageDigest(signStr.getBytes());
            jsonObject.put("key", signStr);
            jsonObject.put("token", token);
            jsonObject.put("phone", phoneNum);
            RequestApi.jsonPost(aty, ConstantNetUrl.SENDCODE, jsonObject, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    showLoading("");
                }

                @Override
                public void onFinish() {
                    dismissLoading();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String response = new String(responseBody, "utf-8");
                        resultbean = new Gson().fromJson(response, ResultBean.class);
                        if (!RequestApi.isSuccess(resultbean, aty)) {
                            ToastUtil.showToast(aty, resultbean.getErrorMsg());
                            LogUtil.e("getVerifyCodeByPhoneNumber---------"+resultbean.getErrorMsg());
                            return;
                        }

                        if (timer == null) {
                            timer = new Timer();
                            timerTask = new TimerTask() {
                                @Override
                                public void run() {
                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                            };
                            timer.schedule(timerTask, 0, 1000);
                        }


                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    AsyncHttpClientUtil.onFailure(aty, statusCode);
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void getData() {



        try {
            String md5Pwd = MD5.getMessageDigest(pwd.getBytes());
            LogUtil.e("md5Pwd--------" + md5Pwd);
            AppCache.getInstance().setPassword(md5Pwd);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", Nick);
            jsonObject.put("pwd", md5Pwd);
            jsonObject.put("phone", phoneNum);
            jsonObject.put("verifyCode", Code);
            jsonObject.put("wechat", weChat);
            jsonObject.put("qq", QQ);
            jsonObject.put("referrerID", referrerID);
            RequestApi.jsonPost(aty, ConstantNetUrl.REGISTER, jsonObject, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    showLoading("");
                }

                @Override
                public void onFinish() {
                    dismissLoading();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String response = null;
                    try {
                        response = new String(responseBody, "utf-8");
                        LogUtil.d(response);
                        try{
                            RegsiterInfoBean regsiterInfoBean = new Gson().fromJson(response, RegsiterInfoBean.class);
                        }catch (Exception e){
                            result =  new Gson().fromJson(response,ResultBean.class);
                            //deal wrong
                            ToastUtil.showToast(aty, result.getErrorMsg());
                            return;
                        }

                        if (!RequestApi.isSuccess(regsiterInfoBean, aty)) {
                            ToastUtil.showToast(aty, regsiterInfoBean.getErrorMsg());
                            return;
                        }

                        String userToken = regsiterInfoBean.getUserToken();
                        if (userToken == "") {
                            ToastUtil.showToast(aty, result.getErrorMsg());
                            return;
                        }
                        //注册成功
                      //  ToastUtil.showToast(aty, R.string.reminder3);
                      //  AppCache.getInstance().saveUserInfo(userInfoBean);
                        AppCache.getInstance().setUserLogin(true);
                        finish();
                        PageJumps.PageJumps(aty,MainActivity.class,null);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    AsyncHttpClientUtil.onFailure(aty, statusCode);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initCustomActionBar() {
        CommonActionBar commonActionBar =
                new CommonActionBar(aty,
                        new CommonActionBar.IActionBarListener() {
                            @Override
                            public void onBtnRight(View v) {
                            }

                            @Override
                            public void onBtnLeft(View v) {
                                finish();
                            }

                            @Override
                            public void setTitle(TextView v) {
                                v.setText("注册");
                                v.setTextColor(getResources().getColor(R.color.black_text3));
                            }
                        });
        TextView tvTitle
                = commonActionBar.getTitleView();
        commonActionBar.setImgLeftViewVisibility(View.VISIBLE);
        commonActionBar.setimgRightViewVisibility(View.GONE);
    }
}
