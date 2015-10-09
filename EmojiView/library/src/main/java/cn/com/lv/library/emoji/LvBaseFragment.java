package cn.com.lv.library.emoji;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author lvlinqing
 *         BaseFragment
 */
public abstract class LvBaseFragment extends Fragment {

  private View rootView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (rootView == null) {
      rootView = inflater.inflate(getResourseid(), null);
      initView(rootView);
    } else {
      ViewGroup viewGroup = (ViewGroup) rootView.getParent();
      if (viewGroup != null) {
        viewGroup.removeView(rootView);
      }
    }

    return rootView;
  }

  protected abstract int getResourseid();

  protected abstract void initView(View view);

}
