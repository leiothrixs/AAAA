package com.ezworking.wechatunlock.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.ezworking.my_android.base.utils.CommonActionBar;
import com.ezworking.wechatunlock.R;
import com.ezworking.wechatunlock.application.AppCache;
import com.ezworking.wechatunlock.domain.UserInfoBean;
import com.ezworking.wechatunlock.ui.AppBaseActivity;

import java.util.List;

public class MainActivity extends AppBaseActivity{

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tv_tile = null;
    RelativeLayout rl_title = null;

    private List<Fragment> fragmentList = null;
    private long exitTime = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);

        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"EiFpiWImzUijXEXKmWGiFCcI");
    }



    @Override
    public void initData() {
        //PermisionController.getInstance().requestPermission(this);
        initCustomActionBar();
  //      checkVersion();


    }

    @Override
    public void initListener() {

    }

    private void initCustomActionBar() {
        CommonActionBar commonActionBar =
                new CommonActionBar(aty,
                        new CommonActionBar.IActionBarListener() {
                            @Override
                            public void onBtnRight(View v) {
                              //  PageJumps.PageJumps(aty, "", null);
                            }

                            @Override
                            public void onBtnLeft(View v) {
                             //   PageJumps.PageJumps(aty, MyDetailActivity.class, null);
                            }

                            @Override
                            public void setTitle(TextView v) {
                                // TODO Auto-generated method stub
                                v.setText("首页");
                                v.setTextColor(getResources().getColor(R.color.black_text3));
                            }
                        });
        tv_tile = commonActionBar.getTitleView();
        commonActionBar.setImgLeftViewVisibility(View.VISIBLE);
        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
        UserInfoBean mUserInfo = AppCache.getInstance().getUserInfo();
        //2017.5.10  avatarSdv.setImageURI(Uri.parse(mUserInfo.getuAvatar()));
    }
}
