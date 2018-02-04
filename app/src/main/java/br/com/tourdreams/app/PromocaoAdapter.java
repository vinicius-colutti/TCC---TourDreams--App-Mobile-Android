package br.com.tourdreams.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Ellen on 27/09/2017.
 */

public class PromocaoAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    Promocao [] imagensStr;

    public PromocaoAdapter(Context context,Promocao [] imagensStr) {
        this.context = context;
        this.imagensStr = imagensStr;
    }
    @Override
    public int getCount() {
        return imagensStr.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

   @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_custom);

       String imagem = context.getString(R.string.imagens) +imagensStr[position].getBanner_promocao();
       Log.d("instantiateItem", imagem);

       Picasso.with(context).load(R.drawable.bannerpromocao2).into(imageView);

       //imageView.setImageDrawable( context.getDrawable( R.drawable.bannerpromocao2));

        ViewPager vp = (ViewPager) container;
        vp.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

}

