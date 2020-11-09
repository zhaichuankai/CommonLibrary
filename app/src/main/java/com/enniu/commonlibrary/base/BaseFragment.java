package com.enniu.commonlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.boniu.paizhaoshiwu.R;
import com.boniu.paizhaoshiwu.manager.RequestManager;

import butterknife.Unbinder;


/**
 * Created by zac on 2017\7\22 0022.
 * all fragment must be extends this class
 */
public abstract class BaseFragment extends Fragment{
    public Unbinder mUnbinder;
    public RequestManager mRequestManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRequestManager = new RequestManager();
        setData(view);
        setListener(view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public abstract void setData(View view);

    public abstract void setListener(View view);

    public void openActivity(Class<?> targetActivityClass) {
        openActivity(targetActivityClass, null);
    }

    public void openActivity(Class<?> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(getActivity(), targetActivityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        startActivity(intent);
        ((Activity) getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void openActivityAndCloseThis(Class<?> targetActivityClass) {
        openActivity(targetActivityClass);
        closeSelf();
    }

    public void closeSelf() {
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
    }
}