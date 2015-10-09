package cn.com.lv.library.emoji;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lvlinqing on 2014/12/26.
 * 带表情的TEXTVIEW
 */
public class EmojiTextView extends TextView {

	private final static String regx = "\\[(\\S+?)\\]";
	private Pattern pattern;

	public EmojiTextView(Context context) {
		super(context);
		pattern = Pattern.compile(regx);
	}

	public EmojiTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		pattern = Pattern.compile(regx);

	}

	public void setText(String text) {

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
		super.setText(spannableString);

	}

	public void setApendText(String text) {

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
		super.append(spannableString);

	}
}
