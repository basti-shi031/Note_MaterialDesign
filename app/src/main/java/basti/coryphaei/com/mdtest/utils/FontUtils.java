package basti.coryphaei.com.mdtest.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * 字体工具类
 * Created by Bowen on 2015/8/14.
 */
public class FontUtils {

    private static Typeface blackThinTTF;
    private static Typeface blackMiddleTTF;
    private static Typeface blackTTF;

    //细黒
    public static Typeface getBlackThin(Context context){
        if (blackThinTTF == null){
            blackThinTTF = Typeface.createFromAsset(context.getAssets(),"fontcn/blackthin.TTF");
        }
        return  blackThinTTF;
    }

    //中黒
    public static Typeface getBlackMiddle(Context context){
        if (blackMiddleTTF == null){
            blackMiddleTTF = Typeface.createFromAsset(context.getAssets(),"fontcn/blackmiddle.TTF");
        }
        return  blackMiddleTTF;
    }

    //黒
    public static Typeface getBlack(Context context){
        if (blackTTF == null){
            blackTTF = Typeface.createFromAsset(context.getAssets(),"fontcn/black.TTF");
        }
        return  blackTTF;
    }

}
