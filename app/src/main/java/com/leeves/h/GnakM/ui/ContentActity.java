package com.leeves.h.GnakM.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.leeves.h.GnakM.R;
import com.leeves.h.GnakM.model.entity.BaseData;
import com.leeves.h.GnakM.presenter.ContentPresenter;
import com.leeves.h.GnakM.ui.adapter.RecyclerContentAdapter;
import com.leeves.h.GnakM.ui.base.ToolbarActivity;
import com.leeves.h.GnakM.ui.view.ContentView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

/**
 * Function：
 * Created by h on 2016/11/10.
 *
 * @author Leeves
 */

public class ContentActity extends ToolbarActivity implements ContentView {

    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.swipe_fresh_layout)
    SwipeRefreshLayout mSwipeFreshLayout;

    private static final String TAG = ContentActity.class.getSimpleName();
    private ContentPresenter mContentPresenter;
    private RecyclerContentAdapter mRecyclerContentAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private String mDate;
    private String mMeiZhiUrl;
    private List<BaseData> mDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initRecycler();
        recyclerListener();
        mContentPresenter = new ContentPresenter(this,mDate,mMeiZhiUrl);
        mContentPresenter.loadContentData();
    }

    @Override
    public void showContentData(List list) {
        mDataList = list;
        mRecyclerContentAdapter.refreshData(mDataList);
        mSwipeFreshLayout.setRefreshing(false);
    }

    private void initRecycler() {
        mRecyclerContentAdapter = new RecyclerContentAdapter(this, mDataList,mMeiZhiUrl);
        //设置固定RecyclerView大小
        mRvContent.setHasFixedSize(true);
        //设置LinearLayout
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRvContent.setLayoutManager(mLinearLayoutManager);
        //设置RecyclerView动画
        mRvContent.setItemAnimator(new SlideInOutLeftItemAnimator(mRvContent));
        AlphaAnimatorAdapter alphaAnimatorAdapter = new AlphaAnimatorAdapter(mRecyclerContentAdapter, mRvContent);
        mRvContent.setAdapter(alphaAnimatorAdapter);
        //获取数据
        mSwipeFreshLayout.setRefreshing(true);
    }

    /**
     * 拉下刷新
     */
    private void recyclerListener() {
        mSwipeFreshLayout.setColorSchemeResources(R.color.color1, R.color.color2, R.color.color3,R.color.color4);
        mSwipeFreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "onRefresh: =====");
                if (mDataList ==null){
                    mContentPresenter.loadContentData();
                }else {
                    mSwipeFreshLayout.setRefreshing(false);
                }
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank_data;
    }

    @Override
    protected String setToolbarTitle() {
        Intent intent = getIntent();
        if (intent != null) {
            mDate = intent.getStringExtra("date");
            mMeiZhiUrl = intent.getStringExtra("meiZhiUrl");
        }
        return mDate;
    }

    @Override
    public boolean isCanBack() {
        return true;
    }
}
