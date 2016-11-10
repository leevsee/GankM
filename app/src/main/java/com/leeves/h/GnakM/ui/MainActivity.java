package com.leeves.h.GnakM.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.leeves.h.GnakM.R;
import com.leeves.h.GnakM.model.entity.FengMian;
import com.leeves.h.GnakM.presenter.FengMianPresenter;
import com.leeves.h.GnakM.ui.adapter.RecyclerFengMianAdapter;
import com.leeves.h.GnakM.ui.base.NavigationViewActivity;
import com.leeves.h.GnakM.ui.view.FenMianView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;

public class MainActivity extends NavigationViewActivity implements FenMianView {


    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.test_bn)
    Button mTestBn;
    @BindView(R.id.rv_fengmian)
    RecyclerView mRvFengmian;
    @BindView(R.id.swipe_fresh_layout)
    SwipeRefreshLayout mSwipeFreshLayout;

    private int lastVisibleItem;
    private RecyclerFengMianAdapter mRecyclerAdapter;
    private List<FengMian> mFengMianList;
    private GridLayoutManager mGridLayoutManager;
    private FengMianPresenter mMianPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mMianPresenter = new FengMianPresenter(this);
        mMianPresenter.loadData();
        initRecycler();
        initSwipeRefresh();
        mTestBn.setVisibility(View.GONE);
    }

    @Override
    public void showFenMianData(int pages, List list) {
        Log.i(TAG, "showFenMianData: ====="+pages);
        if (list == null) {

        } else if (pages == 1) {
            mRecyclerAdapter.refreshData(list);
        } else {
            mRecyclerAdapter.addFengMianData(list);
        }
        mSwipeFreshLayout.setRefreshing(false);
    }

    private void initRecycler() {
        mRecyclerAdapter = new RecyclerFengMianAdapter(this, mFengMianList);
        //设置固定RecyclerView大小
        mRvFengmian.setHasFixedSize(true);
        //设置一列
        mGridLayoutManager = new GridLayoutManager(this, 1);
//        mLayoutManager = new LinearLayoutManager(this);
        mRvFengmian.setLayoutManager(mGridLayoutManager);
        //设置RecyclerView动画
        mRvFengmian.setItemAnimator(new SlideInOutLeftItemAnimator(mRvFengmian));
        AlphaAnimatorAdapter alphaAnimatorAdapter = new AlphaAnimatorAdapter(mRecyclerAdapter, mRvFengmian);
        mRvFengmian.setAdapter(alphaAnimatorAdapter);
    }

    private void initSwipeRefresh() {
        mSwipeFreshLayout.setRefreshing(true);
        mSwipeFreshLayout.setColorSchemeResources(R.color.color1, R.color.color2, R.color.color3, R.color.color4);
        //下拉刷新
        mSwipeFreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMianPresenter.loadData();
                Log.i(TAG, "onRefresh: ====");
            }
        });

        //上拉刷新
        RecyclerView.OnScrollListener rvListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mRecyclerAdapter.getItemCount()
                        && !mSwipeFreshLayout.isRefreshing()) {
                    mSwipeFreshLayout.setRefreshing(true);
                    mMianPresenter.loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
            }
        };

        mRvFengmian.addOnScrollListener(rvListener);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected String setToolbarTitle() {
        return "干货精选";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean isMaterialMenu() {
        return true;
    }


}
