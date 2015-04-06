package com.example.next.stickytitlelistview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    RecyclerView rvMain;
    LinearLayout llMenu;
    TextView tvComment;
    TextView tvRepost;

    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llMenu = (LinearLayout) findViewById(R.id.ll_menu);
        tvComment = (TextView) findViewById(R.id.tv_comment);
        tvRepost = (TextView) findViewById(R.id.tv_repost);
        tvComment.setOnClickListener(this);
        tvRepost.setOnClickListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(this);
        rvMain = (RecyclerView) findViewById(R.id.rv_main);
        rvMain.setLayoutManager(manager);

        /*
        View weiboDetailView = LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.view_top, null);
        View menuView = LayoutInflater.from(
                getApplicationContext()).inflate(R.layout.view_header_menu, null);
        rvMain.addView(weiboDetailView, 0);
        rvMain.addView(menuView, 1);
        */

        adapter = new ListAdapter(this);
        rvMain.setAdapter(adapter);
        rvMain.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                System.out.println("firstVisibleItemPosition : " + manager.findFirstVisibleItemPosition());
                System.out.println("firstCompletelyVisibleItemPosition : " + manager.findFirstCompletelyVisibleItemPosition());
                if(manager.findFirstVisibleItemPosition() >= 1) {
                    llMenu.setVisibility(View.VISIBLE);
                } else {
                    llMenu.setVisibility(View.GONE);
                }
                /*
                int[] locationOnScreen = new int[2];
                manager.getChildAt(1).getLocationOnScreen(locationOnScreen);
                System.out.println("manager获取的 Y 坐标：" + locationOnScreen[1]);

                try {
                    recyclerView.findViewWithTag("tag").getLocationOnScreen(locationOnScreen);
                    System.out.println("tag的 Y 坐标：" + locationOnScreen[1]);
                } catch (NullPointerException e){
                }


                recyclerView.getChildAt(1).getLocationOnScreen(locationOnScreen);
                System.out.println("第一个视图的 Y 坐标：" + locationOnScreen[1]);
                */
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment:
                int mPosition = ((LinearLayoutManager) rvMain.getLayoutManager()).
                        findFirstVisibleItemPosition();
                if(mPosition == 0){
                    adapter.resetItems();
                } else {
                    adapter.resetItems();
                    rvMain.getLayoutManager().scrollToPosition(1);
                }
                break;
            case R.id.tv_repost:
                int position = ((LinearLayoutManager) rvMain.getLayoutManager()).
                        findFirstVisibleItemPosition();
                if(position == 0){
                    adapter.resetItems();
                } else {
                    adapter.resetItems();
                    rvMain.getLayoutManager().scrollToPosition(1);
                }
                break;
        }
    }
}
