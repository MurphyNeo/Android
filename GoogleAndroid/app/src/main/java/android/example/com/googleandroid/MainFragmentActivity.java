package android.example.com.googleandroid;

import android.example.com.googleandroid.actionbar.ActionBarFragment;
import android.example.com.googleandroid.dynamicimage.DynamicImageFragment;
import android.example.com.googleandroid.pulltorefresh.PulltorefreshFragment;
import android.example.com.googleandroid.swiperefresh.SwiperefreshFragment;
import android.example.com.googleandroid.tabone.TabOneFragment;
import android.example.com.googleandroid.viewpagerindicator.IndicatorFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

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
            case Constants.Index.SWIPEREFRESH_INDEX:
                tag = SwiperefreshFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new SwiperefreshFragment();
                }
                break;
            case Constants.Index.PULLTOREFRESH_INDEX:
                tag = PulltorefreshFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new PulltorefreshFragment();
                }
                break;
            case Constants.Index.DYNAMICIMAGE_INDEX:
                tag = DynamicImageFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new DynamicImageFragment();
                }
                break;
            case Constants.Index.ACTIONBAR_INDEX:
                tag = ActionBarFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new ActionBarFragment();
                }
                break;
            case Constants.Index.TABONE_INDEX:
                tag = TabOneFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new TabOneFragment();
                }
                break;
            case Constants.Index.INDICATOR_INDEX:
                tag = IndicatorFragment.class.getSimpleName();
                fr = fm.findFragmentByTag(tag);
                if(fr == null){
                    fr = new IndicatorFragment();
                }
                break;
        }
        fm.beginTransaction().replace(R.id.main_fragment, fr, tag).commit();
    }


}
