# 聊天表情控件

![](https://github.com/candRabbit/EmojiView/blob/master/EmojiView/screenshot/screenshot.png)

怎么使用


<cn.com.lv.library.widget.AppCommentView
        android:layout_alignParentBottom="true"
        app:comment_layout="@layout/group_radio_comment_view"
        android:id="@+id/app_commentview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        ></cn.com.lv.library.widget.AppCommentView>
        
public class MainActivity extends AppCompatActivity implements EmojiCallBackListener {

  private AppCommentView appCommentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCommentView = (AppCommentView) findViewById(R.id.app_commentview);
        appCommentView.setHint("说点什么...");
        appCommentView.setAppCommentViewListener(new AppCommentView.AppCommentViewListener() {
            @Override
            public void sendText(String text) {

            //edittext 内容回调
            }
        });

    }

    //表情选择的回调
    @Override
    public void emojiText(String emojiText) {
     
     
    }
    //表情删除的回调
    @Override
    public void delete() {
  
    }
        
        }
