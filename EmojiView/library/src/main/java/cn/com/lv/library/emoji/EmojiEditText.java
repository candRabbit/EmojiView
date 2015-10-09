package cn.com.lv.library.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lvlinqing on 2014/12/26.
 * 带表情的 edittext
 */
public class EmojiEditText extends EditText {

    private final static String regx = "\\[(\\S+?)\\]";
    private Pattern pattern;

    public EmojiEditText(Context context) {
        super(context);
        pattern = Pattern.compile(regx);
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        pattern = Pattern.compile(regx);
    }

    public void insertEmoji(String emojiText){

        int textSize = (int) getTextSize();
        SpannableString spannableString = new SpannableString(emojiText);
        Drawable drawable = getResources().getDrawable(getResources().getIdentifier(EmojiUtil.getEmojis(getContext()).get(emojiText),"drawable",getContext().getPackageName()));
        drawable.setBounds(0,0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan,0,emojiText.length(), SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE);
        getText().insert(getSelectionStart(),spannableString);


    }

    public void addText(String text){



        Matcher matcher = pattern.matcher(text);

        SpannableString spannableString = new SpannableString(text);

        while (matcher.find()) {

            String emojiStr = EmojiUtil.getEmojis(getContext()).get(
                    text.substring(matcher.start(), matcher.end()));

            if (null != emojiStr) {

                Drawable drawable = getResources().getDrawable(
                        getResources().getIdentifier(emojiStr, "drawable",
                                getContext().getPackageName()));
                // Drawable drawable =
                // context.getResources().getDrawable(emojis[position]);

                int size = (int) getTextSize();
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                ImageSpan imageSpan = new ImageSpan(drawable);
                spannableString.setSpan(imageSpan, matcher.start(),
                        matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }

        getText().insert(getSelectionStart(),spannableString);
    }

    public void delete(){

        int selection = getSelectionStart();
        String text =getText().toString().substring(0,selection);
        if (selection > 0) {
            String text2 = text.substring(selection - 1);
            if ("]".equals(text2)) {
                int start = text.lastIndexOf("[");
                int end = selection;
                if (EmojiUtil.getEmojiKey(getContext()).contains(text.substring(start,end))){
                    getText().delete(start, end);
                    return;
                }

            }
            getText().delete(selection - 1, selection);
        }

    }
}
