package cn.com.lv.library.emoji;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import cn.com.lv.library.R;

/**
 * Created by lvlinqing on 2014/12/25.
 */
public class EmojiFragment extends LvBaseFragment implements AdapterView.OnItemClickListener{

    private GridView emojiGridView;
    private EmojiAdapter emojiAdapter;
    private EmojiCallBackListener emojiCallBackListener;


    public static  EmojiFragment newIntance(){

        return  new EmojiFragment();

    }

    @Override
    public void onAttach(Activity activity) {

        try {

            emojiCallBackListener = (EmojiCallBackListener) activity;

        }catch (Exception e){

        }


        super.onAttach(activity);
    }

    @Override
    protected int getResourseid() {

        return R.layout.emoji_fragment;
    }

    @Override
    protected void initView(View view) {

        emojiGridView = (GridView) view.findViewById(R.id.emoji_grid);
        emojiGridView.setOnItemClickListener(this);
        emojiGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        setDataToView();

    }

    private void setDataToView(){

       String[] resIds =getArguments().getStringArray("emojis");
       emojiAdapter = new EmojiAdapter(resIds,getActivity());
       emojiGridView.setAdapter(emojiAdapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment parentFragment =getParentFragment();

        if (parentFragment instanceof EmojiViewPagerFragment){

            if (position == emojiAdapter.getCount()-1){

                emojiCallBackListener.delete();

            }else{

                EmojiViewPagerFragment emojiViewPagerFragment = (EmojiViewPagerFragment) parentFragment;

                emojiCallBackListener.emojiText(EmojiUtil.getEmojiKey(getActivity()).get(position+emojiViewPagerFragment.getCurrentItem()*20));
            }
        }

    }
}
