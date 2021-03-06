package adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sep4_android.R;

import view.fragment.HomeFrag;
import view.fragment.SaunasFrag;


public class SectionsPageAdapterEm extends FragmentPagerAdapter {

    @StringRes

    private int [] imageId = {R.drawable.icon_home_white_large,R.drawable.icon_sauna_filled_white};
    private String [] titleID ={"Home","Saunas"};
    private final Context mContext;


    public SectionsPageAdapterEm(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch(position){
            case 0:
                frag = new HomeFrag() ;
                break;
            case 1:
                frag = new SaunasFrag();
                break;

        }
        return frag;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getDrawable(imageId[position]);

        image.setBounds(0, 0, 80, 80);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    @Override
    public int getCount() {

        int count = 0;
        for (int i = 0; i< imageId.length; i++){
            if (imageId[i] !=0)
                count++;
        }
        return count;


    }
}