package android.example.com.googleandroid;

import android.example.com.googleandroid.pulltorefresh.PulltorefreshFragment;
import android.example.com.googleandroid.swiperefresh.SwiperefreshFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Neo on 15/9/19.
 */
public class MainFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        /*获取参数处理不同的fragment*/
        int frIndex = getIntent().getIntExtra(Constants.Fragment.FRAGMENT_INDEX, 0);
        String tag;
        Fragment fr;
        FragmentManager fm = getSupportFragmentManager();
        switch (frIndex){
            default:
            case SwiperefreshFragment.INDEX:
                tag = SwiperefreshFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new SwiperefreshFragment();
                }
                break;
            case PulltorefreshFragment.INDEX:
                tag = PulltorefreshFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new PulltorefreshFragment();
                }
                break;
        }
        fm.beginTransaction().replace(R.id.main_fragment, fr, tag).commit();
    }
}
