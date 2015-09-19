package android.example.com.googleandroid.pulltorefresh;

import android.example.com.googleandroid.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Neo on 15/9/19.
 */
public class PulltorefreshFragment extends Fragment {

    public static final int INDEX = 1;

    private ArrayAdapter<String> mAdapter;
    private PullToRefreshListView mListView;
    private List<String> mDataList = new ArrayList<String>(Arrays.asList("下拉刷新"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pulltorefresh, container, false);
        mListView = (PullToRefreshListView)view.findViewById(R.id.pull_refresh_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDataList);
        mListView.setAdapter(mAdapter);

        /*
        Mode.BOTH:同时支持上拉下拉
        Mode.PULL_FROM_START:只支持下拉
        Mode.PULL_FROM_END:只支持上拉
        如果设置为BOTH,需要设置刷新Listener为OnRefreshListener2,实现下拉方法onPullDownToRefresh()
        和上拉方法onPullUpToRefresh()。
        如果为非BOTH,则可以实现OnRefreshListener2或者OnRefreshListener.
         */
        mListView.setMode(Mode.BOTH);

        /*
        设置刷新时显示的文字
        注意：上拉和下拉时getLoadingLayoutProxy()中的参数反过来了
        一个是(true, false)一个是(false, true)
         */
        ILoadingLayout downPull = mListView.getLoadingLayoutProxy(true, false);
        downPull.setPullLabel("下拉刷新");
        downPull.setReleaseLabel("松开刷新");
        downPull.setRefreshingLabel("正在刷新");
        ILoadingLayout upPull = mListView.getLoadingLayoutProxy(false, true);
        upPull.setPullLabel("上拉刷新");
        upPull.setReleaseLabel("松开刷新");
        upPull.setRefreshingLabel("正在刷新");

        /*
        setOnRefreshListener()设置刷新监听
        setOnLastItemVisibleListener()设置到底部监听
        setOnPullEventListener()设置事件监听
        其中还有很多其它的监听,这里就不一一列出了
         */
        mListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                new GetDataTask().execute();
            }
        });

        /*
        设置自动刷新
        注意：这个一定要写在setOnRefreshListener后面,不然的话是不会监听到的,看到的界面就是一直在加载中
         */
        mListView.setRefreshing(true);
    }

    private class GetDataTask extends AsyncTask<Void, Void, List<String>> {
        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Arrays.asList("java Web", "Android App", "Ios App");
        }

        @Override
        protected void onPostExecute(List<String> list) {
            super.onPostExecute(list);
            mDataList.addAll(list);
            mAdapter.notifyDataSetChanged();
            //设置刷新完成
            mListView.onRefreshComplete();
        }
    }
}
