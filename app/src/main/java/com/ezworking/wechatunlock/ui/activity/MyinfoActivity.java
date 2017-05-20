package com.ezworking.wechatunlock.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ezworking.my_android.base.utils.AsyncHttpClientUtil;
import com.ezworking.my_android.base.utils.CommonActionBar;
import com.ezworking.my_android.base.utils.PageJumps;
import com.ezworking.my_android.base.utils.SharePreferenceUtils;
import com.ezworking.my_android.base.utils.ToastUtil;
import com.ezworking.my_android.base.view.LoadingDialog;
import com.ezworking.wechatunlock.R;
import com.ezworking.wechatunlock.api.ConstantNetUrl;
import com.ezworking.wechatunlock.api.RequestApi;
import com.ezworking.wechatunlock.domain.ExchangeRataBean;
import com.ezworking.wechatunlock.domain.MyinfoBean;
import com.ezworking.wechatunlock.ui.AppBaseActivity;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import cz.msebera.android.httpclient.Header;

public class MyinfoActivity extends AppBaseActivity {
    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.wechat)
    TextView wechat;
    @Bind(R.id.qq)
    TextView qq;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.points)
    TextView points;
    @Bind(R.id.bt_phone_rate)
    Button phoneRate;
    @Bind(R.id.bt_money_rate)
    Button moneyRate;
    private LoadingDialog mLoadDialog;
    private ExchangeRataBean exchangeRataBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_myinfo);
    }

    @Override
    public void initData() {
        initCustomActionBar();
        getData();

        getRate();
    }

    @Override
    public void initListener() {
        phoneRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageJumps.PageJumps(aty,ExchangeLikeActivity.class,null);
            }
        });

        moneyRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageJumps.PageJumps(aty,ExchangeMoneyActivity.class,null);
            }
        });


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
                                v.setText("个人资料");
                                v.setTextColor(getResources().getColor(R.color.black_text3));
                            }
                        });
        TextView tvTitle
                = commonActionBar.getTitleView();
        commonActionBar.setImgLeftViewVisibility(View.VISIBLE);
        commonActionBar.setimgRightViewVisibility(View.VISIBLE);
    }

         void getData(){
              JSONObject jsonObject = new JSONObject();
              RequestApi.jsonPost(aty, ConstantNetUrl.MYINFOS, jsonObject, new AsyncHttpResponseHandler() {

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
                          MyinfoBean myinfoBean = new Gson().fromJson(response, MyinfoBean.class);
                            if (!myinfoBean.isSuccess()){
                                ToastUtil.showToast(aty, myinfoBean.getErrorMsg());
                                return;
                            }

                          if (myinfoBean!=null){
                              username.setText(myinfoBean.getData().getName());
                              wechat.setText(myinfoBean.getData().getWechat());
                              qq.setText(myinfoBean.getData().getQq());
                              phone.setText(myinfoBean.getData().getPhone());
                              points.setText(myinfoBean.getData().getPoints());
                          }

                      } catch (Exception e) {
                          e.printStackTrace();
                      }

                  }

                  @Override
                  public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                      AsyncHttpClientUtil.onFailure(aty, statusCode);
                  }
              });

          };

         void getRate(){
            JSONObject jsonObject = new JSONObject();
            RequestApi.jsonPost(aty, ConstantNetUrl.GETEXCHANGERATE, jsonObject, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    try {
                        String response = new String(responseBody, "utf-8");
                        exchangeRataBean =  new Gson().fromJson(response,ExchangeRataBean.class);
                        if (exchangeRataBean!=null){
                            String qqRate = exchangeRataBean.getQqRate();
                            String cashRate = exchangeRataBean.getCashRate();
                            String qqNum = exchangeRataBean.getQqNum();
                            String cashNum = exchangeRataBean.getCashNum();
                            SharePreferenceUtils.putValueToSP(aty,qqNum,"qqNum");
                            SharePreferenceUtils.putValueToSP(aty,qqRate,"qqRate");
                            SharePreferenceUtils.putValueToSP(aty,cashNum,"cashNum");
                            SharePreferenceUtils.putValueToSP(aty,cashRate,"cashRate");

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

}
