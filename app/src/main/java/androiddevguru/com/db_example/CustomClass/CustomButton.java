package androiddevguru.com.db_example.CustomClass;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by John wick on 6/6/2016.
 */
public class CustomButton extends android.support.v7.widget.AppCompatButton {


    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }

    private void init(Context context, AttributeSet attrs){

        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA,"textStyle",Typeface.NORMAL);
        Typeface customFont = selectTypeFace(context,textStyle);
        setTypeface(customFont);
    }
    private Typeface selectTypeFace(Context context, int textStyle){


        switch (textStyle){
            case Typeface.NORMAL:
                return Fontcache.getTypeface("roboto.ttf",context);
            case Typeface.BOLD:
                return Fontcache.getTypeface("roboto_bold.ttf",context);
            default:
                return Fontcache.getTypeface("roboto.ttf",context);
        }

    }
}
