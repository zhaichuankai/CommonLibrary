package com.enniu.commonlibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.boniu.paizhaoshiwu.LoginActivity;
import com.boniu.paizhaoshiwu.R;
import com.boniu.paizhaoshiwu.entity.event.GoLoginEvent;
import com.boniu.paizhaoshiwu.entity.event.NetEvent;
import com.boniu.paizhaoshiwu.manager.AppManager;
import com.boniu.paizhaoshiwu.manager.RequestManager;
import com.boniu.paizhaoshiwu.utils.DisplayUtils;
import com.boniu.paizhaoshiwu.widget.dialog.CustomerDialog;
import com.gyf.immersionbar.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * all activity must be extends this base activity
 **/
public abstract class BaseActivity extends AppCompatActivity {

    public View mVStatus, mVLine;
    public RelativeLayout mRlRoot, mRlTab;
    public View mContainer;
    public View mNetContainer;
    public View mLoadingContainer;
    public ImageView mImgBack;
    public TextView mTvLeft, mTvTitle, mTvRight;
    public RequestManager mRequestManager;
    public static final int RC_CHOOSE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        initView();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mContainer = getLayoutInflater().inflate(layoutResID, null);
        mNetContainer = getLayoutInflater().inflate(R.layout.include_net, null);
        mLoadingContainer = getLayoutInflater().inflate(R.layout.include_loading, null);
        RelativeLayout.LayoutParams lpRoot = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        lpRoot.addRule(RelativeLayout.BELOW, R.id.v_line);

        if (null != mRlRoot) {
            mRlRoot.addView(mLoadingContainer, lpRoot);
            mRlRoot.addView(mNetContainer, lpRoot);
            mRlRoot.addView(mContainer, lpRoot);

            mLoadingContainer.setVisibility(View.GONE);
            mNetContainer.setVisibility(View.GONE);
            mContainer.setVisibility(View.VISIBLE);
        }

        ButterKnife.bind(this);

        mLoadingView = new CustomerDialog.Builder().buildLoading(this);
        //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，
        // 如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();

        AppManager.getAppManager().addActivity(this);
        AppManager.getAppManager().addMemory(this);

        mRequestManager = new RequestManager();

        EventBus.getDefault().register(this);
        setData();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void showLoding() {
        mLoadingContainer.setVisibility(View.VISIBLE);
        mNetContainer.setVisibility(View.GONE);
        mContainer.setVisibility(View.GONE);
    }

    public void dismissLoding() {
        mContainer.setVisibility(View.VISIBLE);
        mNetContainer.setVisibility(View.GONE);
        mLoadingContainer.setVisibility(View.GONE);
    }

    public void netUnconnected() {
        mNetContainer.setVisibility(View.VISIBLE);
        mContainer.setVisibility(View.GONE);
        mLoadingContainer.setVisibility(View.GONE);
    }

    public void netConnected() {
        mContainer.setVisibility(View.VISIBLE);
        mNetContainer.setVisibility(View.GONE);
        mLoadingContainer.setVisibility(View.GONE);
    }

    private void initView() {
        mVStatus = findViewById(R.id.v_status);
        mVLine = findViewById(R.id.v_line);
        ViewGroup.LayoutParams params = mVStatus.getLayoutParams();
        params.height = DisplayUtils.getStatusHeight(this);
        mVStatus.setLayoutParams(params);

        mRlRoot = findViewById(R.id.rl_root);
        mRlTab = findViewById(R.id.rl_tab);
        mImgBack = findViewById(R.id.img_back);
        mTvLeft = findViewById(R.id.tv_left);
        mTvTitle = findViewById(R.id.tv_title);
        mTvRight = findViewById(R.id.tv_right);

        mImgBack.setOnClickListener(v -> closeSelf());
    }

    public void hideStatusBar() {
        if (null != mVStatus) {
            mVStatus.setVisibility(View.GONE);
        }
    }

    public void hideTabBar() {
        if (null != mRlTab) {
            mRlTab.setVisibility(View.GONE);
        }
    }

    public void hideLine() {
        if (null != mVLine) {
            mVLine.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title) {
        if (null != mTvTitle) {
            mTvTitle.setText(title);
        }
    }

    public void setTabTheme(int color) {
        if (null != mVStatus) {
            mVStatus.setBackgroundColor(color);
        }

        if (null != mRlTab) {
            mRlTab.setBackgroundColor(color);
        }
    }

    public void setLeftText(String leftText) {
        if (null != mTvLeft) {
            mTvLeft.setText(leftText);
            mImgBack.setVisibility(View.GONE);
            mTvLeft.setVisibility(View.VISIBLE);
        }
    }

    public void setRightText(String rightText) {
        if (null != mTvRight) {
            mTvRight.setText(rightText);
            mImgBack.setVisibility(View.GONE);
            mTvRight.setVisibility(View.VISIBLE);
        }
    }

    private CustomerDialog mLoadingView;

    public void showLoading() {
        mLoadingView.show();
    }

    public void dismissLoading() {
        mLoadingView.dismiss();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        AppManager.getAppManager().finalize(this);
    }

    protected abstract void setData();

    protected abstract void setListener();

    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        this.finish();
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass, Bundle bundle) {
        openActivity(targetActivityClass, bundle);
        this.finish();
    }

    public void closeSelf() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeSelf();
    }

    @Subscribe
    public void eventBus(NetEvent event) {
//        if (event.getStatus()==NetEvent.NET_CONNECTED){
//            netConnected();
//        }else {
//            netUnconnected();
//        }
    }

    @Subscribe
    public void eventBus(GoLoginEvent event) {
        Bundle bundle = new Bundle();
        bundle.putString("goto_result", "close");
        openActivity(LoginActivity.class, bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
    }
}