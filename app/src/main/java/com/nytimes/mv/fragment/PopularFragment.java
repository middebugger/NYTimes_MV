package com.nytimes.mv.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.nytimes.mv.R;
import com.nytimes.mv.activity.PopularDetailActivity;
import com.nytimes.mv.adapter.PopularListAdapter;
import com.nytimes.mv.base.BaseFragment;
import com.nytimes.mv.model.PopularResponse;
import com.nytimes.mv.utilities.DataRequest;
import com.nytimes.mv.utilities.RecyclerviewItemDecoration;
import com.nytimes.mv.utilities.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class PopularFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @BindView(R.id.recylerView)
    RecyclerView recyclerView;

    @Nullable
    @BindView(R.id.errorLayout)
    View errorLayout;

    @Nullable
    private PopularListAdapter popularListAdapter;

    @Nullable
    private Disposable disposable;

    @Nullable
    private DataRequest dataRequest;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.cntx = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popularlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        setViews();
        onRefresh();
    }

    private void setViews() {

        popularListAdapter = new PopularListAdapter(cntx, newsItem -> PopularDetailActivity.start(cntx, newsItem));
        recyclerView.setAdapter(popularListAdapter);

        recyclerView.addItemDecoration(new RecyclerviewItemDecoration(getResources().getDimensionPixelSize(R.dimen.space_list)));
        recyclerView.setLayoutManager(new LinearLayoutManager(cntx));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        swipeRefreshLayout.setOnRefreshListener(this);

        initRequest();
    }

    private void initRequest() {
        if (dataRequest == null) {
            dataRequest = new DataRequest();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        popularListAdapter = null;
        swipeRefreshLayout = null;
        recyclerView = null;
    }

    @Override
    @OnClick(R.id.buttonRetry)
    public void onRefresh() {

        showProgress(true);

        initRequest();

        disposable = dataRequest.getMostPopularArticles()
                .subscribe(this::updateItems,
                        this::handleError);
    }

    private void showProgress(boolean shouldShow) {
        swipeRefreshLayout.setRefreshing(shouldShow);
        Utils.setVisible(recyclerView, !shouldShow);
        Utils.setVisible(errorLayout, !shouldShow);
    }

    private void updateItems(@Nullable PopularResponse response) {
        if (popularListAdapter != null)
            popularListAdapter.replaceItems(response.getResults());

        Utils.setVisible(recyclerView, true);
        swipeRefreshLayout.setRefreshing(false);
        Utils.setVisible(errorLayout, false);
    }

    private void handleError(Throwable th) {
        Utils.setVisible(errorLayout, true);
        swipeRefreshLayout.setRefreshing(false);
        Utils.setVisible(recyclerView, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        showProgress(false);
        Utils.disposeSafe(disposable);
        disposable = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (popularListAdapter != null && popularListAdapter.getItemCount() > 0) {
            Utils.setVisible(errorLayout, false);
        }
    }
}