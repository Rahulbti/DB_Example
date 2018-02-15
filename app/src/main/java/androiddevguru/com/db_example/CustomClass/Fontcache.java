package androiddevguru.com.db_example.CustomClass;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by John wick on 6/10/2016.
 */
public class Fontcache {

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeface(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), fontname);
            } catch (Exception e) {
                return null;
            }

            fontCache.put(fontname, typeface);
        }

        return typeface;
    }
}
