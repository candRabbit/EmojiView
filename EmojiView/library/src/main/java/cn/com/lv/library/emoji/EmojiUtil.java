package cn.com.lv.library.emoji;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lvlinqing on 2014/12/25.
 */
public class EmojiUtil {

	public static HashMap<String, String> emojis = null;
	public static ArrayList<String> emojiKeys, emojiValues;

	public static HashMap<String, String> getEmojis(Context context) {

		if (null == emojis) {

			emojis = paserEmjoy(context);

		}

		return emojis;

	}

	/**
	 * 解析表情
	 * 
	 * @param contextl
	 * @return
	 */
	public static HashMap<String, String> paserEmjoy(Context contextl) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		XmlPullParser xmlPullParser = Xml.newPullParser();
		try {

			xmlPullParser.setInput(contextl.getAssets().open("emoji.xml"),
					"utf-8");
			int type = xmlPullParser.getEventType();

			while (type != XmlPullParser.END_DOCUMENT) {

				String tagName = xmlPullParser.getName();

				switch (type) {

				case XmlPullParser.START_TAG:

					if (tagName.equals("e")) {

						String key = xmlPullParser.getAttributeValue("", "key");
						String value = xmlPullParser.nextText();
						hashMap.put(key, value);
					}

					break;

				}
				type = xmlPullParser.next();

			}

		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hashMap;
	}

	public static ArrayList<String> getEmojiKey(Context context) {

		if (null == emojiKeys) {

			HashMap<String, String> team = getEmojis(context);

			emojiKeys = new ArrayList<String>();

			Iterator<Map.Entry<String, String>> iterator = team.entrySet()
					.iterator();

			while (iterator.hasNext()) {

				emojiKeys.add(iterator.next().getKey());
			}

		}

		return emojiKeys;
	}

	public static ArrayList<String> getEmojiValue(Context context) {

		if (null == emojiValues) {

			HashMap<String, String> team = getEmojis(context);

			emojiValues = new ArrayList<String>();

			Iterator<Map.Entry<String, String>> iterator = team.entrySet()
					.iterator();

			while (iterator.hasNext()) {

				emojiValues.add(iterator.next().getValue());
			}

		}

		return emojiValues;
	}

}
