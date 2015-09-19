package android.example.com.googleandroid;

import android.app.Activity;
import android.content.Intent;
import android.example.com.googleandroid.pulltorefresh.PulltorefreshFragment;
import android.example.com.googleandroid.swiperefresh.SwiperefreshFragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void swipeRefreshLayout(View view){
        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.putExtra(Constants.Fragment.FRAGMENT_INDEX, SwiperefreshFragment.INDEX);
        startActivity(intent);
    }

    public void pullToRefresh(View view){
        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.putExtra(Constants.Fragment.FRAGMENT_INDEX, PulltorefreshFragment.INDEX);
        startActivity(intent);
    }

}
