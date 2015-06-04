package cn.spade.android.circlestateview.sample.tool;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by zhaosc on 15/6/4.
 */
public class AssetsTool {

  private static final String TAG = AssetsTool.class.getSimpleName();

  /**
   * 获得自定义字体
   * @param context
   * @return
   */
  public static Typeface getCustomizeTypeface(Context context){
	Typeface typeface = null;
	try {
	  typeface = Typeface.createFromAsset(context.getAssets(), "font/icomoon.ttf");
	} catch (Exception e) {
	  Log.e(TAG, e.getMessage());
	  typeface = null;
	}
	return typeface;
  }
}
