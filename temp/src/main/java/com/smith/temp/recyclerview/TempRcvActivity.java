package com.smith.temp.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.smith.temp.R;

import java.lang.ref.WeakReference;
import java.util.List;

public class TempRcvActivity extends AppCompatActivity {
    private static final int MSG_REFRESH_SUCESS = 1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TempViewModel viewModel;
    private TempAdapter adapter;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_activity_rcv);
        viewModel = ViewModelProviders.of(this).get(TempViewModel.class);
        handler = new TempHandler(this);
        initViews();
        viewModel.list.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (null != adapter) {
                    adapter.setList(strings);
                    if (strings == null) {
                        adapter.setFooter_state(TempAdapter.LOADING_NO_MORE);
                    } else if (strings.size() < 10) {
                        adapter.setFooter_state(TempAdapter.LOADING_NO_MORE);
                    } else {
                        adapter.setFooter_state(TempAdapter.LOADING_MORE);
                    }
                }

            }
        });
        viewModel.getList();
    }

    private void initViews() {
        swipeRefreshLayout = findViewById(R.id.temp_swiperefreshlayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("smith","-----------onRefresh-------");
                Message msg = Message.obtain();
                msg.arg1 = MSG_REFRESH_SUCESS;
                handler.sendMessageDelayed(msg,2000);

            }
        });
        recyclerView = findViewById(R.id.temp_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TempAdapter(null,this);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new LoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                viewModel.loadList();
            }
        });
    }

    private static class TempHandler extends Handler {
        WeakReference<TempRcvActivity> wrf;

        public TempHandler(TempRcvActivity activity) {
            this.wrf = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == MSG_REFRESH_SUCESS) {
                if (null != wrf && null != wrf.get()) {
                    wrf.get().swipeRefreshLayout.setRefreshing(false);
                }

            }
        }
    }
}
