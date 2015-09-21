package android.example.com.googleandroid;

import android.app.Activity;
import android.content.Intent;
import android.example.com.googleandroid.pulltorefresh.PulltorefreshFragment;
import android.example.com.googleandroid.swiperefresh.SwiperefreshFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void publicIntent(int index){
        Intent intent = new Intent(this, MainFragmentActivity.class);
        intent.putExtra(Constants.Fragment.FRAGMENT_INDEX, index);
        startActivity(intent);
    }

    public void swipeRefreshLayout(View view){
        publicIntent(Constants.Index.SWIPEREFRESH_INDEX);
    }

    public void pullToRefresh(View view){
        publicIntent(Constants.Index.PULLTOREFRESH_INDEX);
    }

    public void dynamicImage(View view){
        //publicIntent(Constants.Index.DYNAMICIMAGE_INDEX);
        Toast.makeText(getApplicationContext(), "敬请期待!", Toast.LENGTH_LONG).show();
    }

    public void actionBar(View view){
        publicIntent(Constants.Index.ACTIONBAR_INDEX);
    }

    public void tabOne(View view){
        publicIntent(Constants.Index.TABONE_INDEX);
    }

    public void indicator(View view){
        publicIntent(Constants.Index.INDICATOR_INDEX);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_fragment_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.swiperefresh_bar:
                publicIntent(Constants.Index.SWIPEREFRESH_INDEX);
                return true;
            case R.id.pull_to_refresh_bar:
                publicIntent(Constants.Index.PULLTOREFRESH_INDEX);
                return true;
            case R.id.actionbar_bar:
                publicIntent(Constants.Index.ACTIONBAR_INDEX);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
