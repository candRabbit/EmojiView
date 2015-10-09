package cn.com.lv.library.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.com.lv.library.R;

/**
 * Created by lvlinqing on 2014/12/25.\
 * ps 每行显示7*4-1 一个 27
 */
public class EmojiAdapter extends BaseAdapter implements View.OnClickListener{

   private  String[] emojis;
   private Context context;
   private LayoutInflater layoutInflater;
   private AbsListView.LayoutParams layoutParams;

    public EmojiAdapter(String[] emojis,Context context) {

        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.emojis = emojis ;

        layoutParams = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,context.getResources().getDisplayMetrics().widthPixels/7);

    }

    public String[] getDataSources(){

        return  emojis;
    }

    @Override
    public int getCount() {
        return emojis.length+1;
    }

    @Override
    public Object getItem(int position) {
        return emojis[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (null == convertView){

            convertView =  layoutInflater.inflate(R.layout.emoji_item,null);
            convertView.setLayoutParams(layoutParams);
            viewHolder = new ViewHolder();
            viewHolder.iconImageView = (ImageView) convertView.findViewById(R.id.emoji_icon_image);
            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == emojis.length){

            viewHolder.iconImageView.setImageResource(R.drawable.compose_emotion_delete);

        }else{

            Drawable drawable = context.getResources().getDrawable(context.getResources().getIdentifier(emojis[position],"drawable",context.getPackageName()));
            //Drawable drawable = context.getResources().getDrawable(emojis[position]);
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            viewHolder.iconImageView.setImageDrawable(drawable);


        }


        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    private class  ViewHolder{

        public View iconView;
        public ImageView iconImageView;

    }


}
