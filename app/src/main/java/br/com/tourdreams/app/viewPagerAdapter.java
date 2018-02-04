package br.com.tourdreams.app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by Ellen on 27/09/2017.
 */

public class viewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    DadosQuarto [] imagensStr;

    public viewPagerAdapter(Context context, DadosQuarto[] imagensStr) {
        this.context = context;
        this.imagensStr = imagensStr;
    }

    @Override
    public int getCount() {
        if(imagensStr != null ){
        return imagensStr.length;
        }return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.custom_layout,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_custom);

        String imagem = context.getString(R.string.caminhoImagem) +imagensStr[position].getNome_imagem();

        Log.d("instantiateItem", imagem);
        DadosQuarto d = new DadosQuarto();
        d.getNome_imagem();
        Picasso.with(context).load(imagem).into(imageView);

        //ViewPager vp = (ViewPager) container;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout)object);
    }
}
