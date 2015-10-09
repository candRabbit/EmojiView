package cn.com.lv.library.emoji;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lvlinqing on 2014/12/26.
 */
public class EmojiViewPagerAdapter extends FragmentPagerAdapter {

    public final static int MAX_PAGE= 5;
    public final static int PAGEITEMCOUNT= 20;
    public ArrayList<String> emojiReId =null;


    public EmojiViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        emojiReId = EmojiUtil.getEmojiValue(context);


    }

    @Override
    public Fragment getItem(int i) {

        EmojiFragment emojiFragment = EmojiFragment.newIntance();
        Bundle bundle = new Bundle();

        List<String> emojis = null;

        if ((i+1)*PAGEITEMCOUNT>emojiReId.size()){
           emojis =  emojiReId.subList(i * PAGEITEMCOUNT, emojiReId.size());
        }else{
          emojis =  emojiReId.subList(i * PAGEITEMCOUNT, (i + 1) * PAGEITEMCOUNT);
        }

        String[] resId  = new String[emojis.size()];

        for (int j = 0; j<emojis.size();j++){

            resId[j] = emojis.get(j);
        }
        bundle.putStringArray("emojis", resId);
        emojiFragment.setArguments(bundle);

        return emojiFragment;
    }

    @Override
    public int getCount() {

        return MAX_PAGE;
    }
}
