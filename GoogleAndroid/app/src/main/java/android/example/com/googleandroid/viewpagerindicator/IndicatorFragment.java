package android.example.com.googleandroid.viewpagerindicator;

import android.example.com.googleandroid.R;
import android.example.com.googleandroid.pulltorefresh.PulltorefreshFragment;
import android.example.com.googleandroid.swiperefresh.SwiperefreshFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by Neo on 15/9/21.
 */
public class IndicatorFragment extends Fragment {


    private ViewPager mViewPager;
    private TabPageIndicator mTabPageIndicator;
    SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.indicator_main, container, false);
        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);
        mTabPageIndicator = (TabPageIndicator)view.findViewById(R.id.indicator);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabPageIndicator.setViewPager(mViewPager);

    }

    private String[] titles = new String[]{"头条","娱乐","体育","财经","热点","科技","汽车","彩票","财经","热点","科技","汽车","彩票"};
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if (position == 0){
                fragment = new SwiperefreshFragment();
//                Bundle args = new Bundle();
//                args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
//                fragment.setArguments(args);
            }else if(position == 1) {
                fragment = new PulltorefreshFragment();
            }else{
                fragment = new PulltorefreshFragment();
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
