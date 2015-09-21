package android.example.com.googleandroid.tabone;

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
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by Neo on 15/9/21.
 */
public class TabOneFragment extends Fragment implements View.OnClickListener{

    private ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSetting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().hide();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabone_main, container, false);

        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);

        mImgWeixin = (ImageButton)view.findViewById(R.id.tab_weixin);
        mImgFrd = (ImageButton)view.findViewById(R.id.tab_frd);
        mImgAddress = (ImageButton)view.findViewById(R.id.tab_address);
        mImgSetting = (ImageButton)view.findViewById(R.id.tab_settings);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetImg();
                switch (position){
                    case 0:
                        mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        mImgSetting.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mImgWeixin.setOnClickListener(this);
        mImgFrd.setOnClickListener(this);
        mImgAddress.setOnClickListener(this);
        mImgSetting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.tab_weixin:
                mViewPager.setCurrentItem(0);
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case R.id.tab_frd:
                mViewPager.setCurrentItem(1);
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case R.id.tab_address:
                mViewPager.setCurrentItem(2);
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case R.id.tab_settings:
                mViewPager.setCurrentItem(3);
                mImgSetting.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }
    }

    private void resetImg(){
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgSetting.setImageResource(R.drawable.tab_settings_normal);
    }

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
            return 4;
        }

    }
}
