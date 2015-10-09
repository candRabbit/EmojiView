package cn.com.lv.library.emoji;

import android.support.v4.view.ViewPager;
import android.view.View;

import cn.com.lv.library.R;
import cn.com.lv.library.widget.CirclePageIndicator;

/**
 * Created by lvlinqing on 2014/12/26.
 */
public class EmojiViewPagerFragment extends LvBaseFragment {

    private ViewPager viewPager;
    private CirclePageIndicator circlePageIndicator;


    @Override
    protected int getResourseid() {

        return R.layout.emoji_viewpage_fragment;
    }

    @Override
    protected void initView(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.emoji_view_pager);
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.viewpager_indicator);
        viewPager.setAdapter(new EmojiViewPagerAdapter(getChildFragmentManager(),getActivity()));
        circlePageIndicator.setViewPager(viewPager);

    }

    public int  getCurrentItem(){

        return  viewPager.getCurrentItem();
    }
}
