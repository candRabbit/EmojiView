package cn.com.lv.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import cn.com.lv.library.R;
import cn.com.lv.library.emoji.EmojiEditText;
import cn.com.lv.library.emoji.EmojiViewPagerFragment;

/**
 * Created by lvlinqing on 2015/5/14.
 * 用户评论VIEW
 */
public class AppCommentView extends FrameLayout implements View.OnClickListener, TextWatcher {

  private Button sendBtn, emojiBtn;
  private EmojiEditText emojiEditText;
  private View emojiView;
  private InputMethodManager methodManager;
  private AppCommentViewListener appCommentViewListener;

  public AppCommentView(Context context) {
    super(context);
    initView();
  }

  public AppCommentView(Context context, AttributeSet attrs) {
    super(context, attrs);
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AppCommentView);
    int commentLayout = typedArray.getResourceId(R.styleable.AppCommentView_comment_layout,-1);
    inflate(context,commentLayout,this);
    typedArray.recycle();
    initView();
  }

  public AppCommentView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

  }

  public void setAppCommentViewListener(AppCommentViewListener appCommentViewListener) {
    this.appCommentViewListener = appCommentViewListener;
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    EmojiViewPagerFragment emojiViewPagerFragment = new EmojiViewPagerFragment();
    FragmentActivity fragmentActivity = (FragmentActivity) getContext();
    fragmentActivity.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.chat_emoji_view, emojiViewPagerFragment)
        .commit();
  }

  private void initView() {
    methodManager =
        (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    //inflate(getContext(), R.layout.app_edit_comment_view, this);
    emojiView = findViewById(R.id.emoji_view);
    sendBtn = (Button) findViewById(R.id.send_btn);
    emojiBtn = (Button) findViewById(R.id.select_emoji_btn);
    emojiEditText = (EmojiEditText) findViewById(R.id.comment_edit);
    sendBtn.setOnClickListener(this);
    emojiBtn.setOnClickListener(this);
    emojiEditText.addTextChangedListener(this);
    emojiEditText.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {

    int i = v.getId();
    if (i == R.id.send_btn) {
      if (null != appCommentViewListener) {
        appCommentViewListener.sendText(emojiEditText.getText().toString());
        hideEmojiView();
        hideInput();
        emojiEditText.setText("");
      }

    } else if (i == R.id.select_emoji_btn) {
      if (emojiView.getVisibility() == VISIBLE) {
        emojiView.setVisibility(GONE);
      } else {
        emojiView.setVisibility(VISIBLE);
        hideInput();
      }

    } else if (i == R.id.comment_edit) {
      if (emojiView.getVisibility() == VISIBLE) {
        emojiView.setVisibility(GONE);
      }

    }
  }

  public void keyBack(){
    if (emojiView.getVisibility() == VISIBLE){
      emojiView.setVisibility(GONE);
    }
  }

  protected void hideInput() {
    FragmentActivity fragmentActivity = (FragmentActivity) getContext();
    methodManager.hideSoftInputFromWindow(
            fragmentActivity.getWindow().peekDecorView().getWindowToken(),
            InputMethodManager.HIDE_NOT_ALWAYS);
  }

  public void setHint(String hint) {
    emojiEditText.setHint(hint);
  }

  public void insertEmoji(String emojiText) {
    emojiEditText.insertEmoji(emojiText);
  }

  public void deleteEmoji() {
    emojiEditText.delete();
  }
  public void hideEmojiView(){
    if (VISIBLE == emojiView.getVisibility()){
      emojiView.setVisibility(GONE);
    }
  }
  /**
   * 弹出软键盘
   */
  public void showInput() {
    if (VISIBLE == emojiView.getVisibility()){
     return;
    }
    InputMethodManager inputManager = (InputMethodManager) emojiEditText.getContext()
        .getSystemService(Context.INPUT_METHOD_SERVICE);
    inputManager.showSoftInput(emojiEditText, 0);
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

    if (TextUtils.isEmpty(emojiEditText.getText())) {
      sendBtn.setEnabled(false);
    } else {
      sendBtn.setEnabled(true);
    }
  }

  @Override
  public void afterTextChanged(Editable s) {

  }

  public void setSendBtnBg(int resId){
    sendBtn.setBackgroundResource(resId);
  }

  public interface AppCommentViewListener{
    public void sendText(String text);
  }
}
