package com.example.rezakala.More_Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.Convert_px;
import com.example.rezakala.Action.Farsinumber;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Cart.Cart_activity;
import com.example.rezakala.Cart.Db_DataBase;
import com.example.rezakala.Comments.Activity_Comments;
import com.example.rezakala.Config;
import com.example.rezakala.More_Product.Compare.Activity_Compare;
import com.example.rezakala.Pack_timer.Api_timer;
import com.example.rezakala.Packslider.Slider_pageradapter;
import com.example.rezakala.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class More_Product extends AppCompatActivity implements Adapter_color.Get_color_change {
    CardView card_info,card_comments, Cardview_service;;
    Db_DataBase db_dataBase;
    Intent getintent;
    RatingBar ra_bar_score;
    ImageView Im_back,Addfav, Im_share,Im_shop;
    LinearLayout Liner_Cart,linertimer,linerlayout_slider;
    TextView Tv_sec,Tv_min,Tv_hour;
    TextView Tv_title_product_fa, Tv_title_product_en,Tv_des,Tv_price,Tv_g;
    TextView Tv_number_service_shop,Tv_toolbar_title;
    private static final String TAG = "aaa";
    Datamodel_product datamodel_product;
    String idstore,service_shop,price_sale;
    String titletoolbar,titleproduct;
    int idview[];
    Slider_pageradapter slider_pageradapter;
    ViewPager viewpager;

    TextView Tv_number_color;
    RecyclerView recyclerview_color;
    public int idcolorMore;

    RecyclerView recyclerview_score;

    RelativeLayout Rel_more_Text;

    ScrollView scrollview;
    Toolbar toolbar;
    int checkscroll=0,idcolor;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_product);
        Log.d(TAG, "onCreate: more_product");
        card_comments=findViewById(R.id.card_comments);
        db_dataBase=new Db_DataBase(this);
        getintent=getIntent();
        Cast();
        checktimer();
        Getproduct();

        ///// animation on toolbarwhen scroll
        scrollview.getViewTreeObserver().
                addOnScrollChangedListener(
                        new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged()
            {
                int scrolltoolbarchange=scrollview.getScrollY();
                int dpsize=Convert_px.convertpx(scrolltoolbarchange,
                        getApplicationContext());
                int colorblack= Color.argb(scrolltoolbarchange,250,66,66);
                int colorwhite=Color.argb(255,250,66,66);

                if(scrolltoolbarchange > 220)
                {
                    toolbar.setBackgroundColor(colorwhite);
                    Im_back.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_arrow_back_white_24dp,null));
                    Im_shop.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_shop_white,null));
                    Drawable drawable= ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_optionmenu_white);
                    toolbar.setOverflowIcon(drawable);

                }
                else
                {
                    Im_back.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.icon_product_black_toolbar,null));
                    Im_shop.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_shop_black_toolbar,null));
                    Drawable drawable= ContextCompat.getDrawable
                            (getApplicationContext(),R.drawable.ic_optionmenu_black);
                    toolbar.setOverflowIcon(drawable);

                    toolbar.setBackgroundColor(colorblack);
                    Tv_toolbar_title.setText("");
                }

                if(dpsize > 240)
                {
                    if(checkscroll==0)
                    {
                        Animation animation = AnimationUtils.loadAnimation
                                ((More_Product.this), R.anim.anim_toolbar_title);
                        Tv_toolbar_title.startAnimation(animation);
                        checkscroll=1;
                    }
                    Tv_toolbar_title.setText(titletoolbar);
                }
                else
                {
                    checkscroll=0;
                    Tv_toolbar_title.setText("");
                }

            }
        });
        Getcheckinfo();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_product_menuoption,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
//        if(id==R.id.menu_b){
//            Intent intent=new Intent(getApplicationContext(),Product_check_optionmenu.class);
//            intent.putExtra("title",titleproduct);
//            startActivity(intent);
//
//        }
        if (id==R.id.menu_m)
        {
            Intent intent=new Intent(getApplicationContext(), Activity_Compare.class);
            intent.putExtra("idproduct",getintent.getStringExtra("idproduct"));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void Getcheckinfo()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        if (toekn==null){
            //Log.d(TAG, "Getcheckinfo: token = null");
        }
        else
        {
            String url= Config.urlhome+"addfav.php?token="+ GetToken.gettoken(getApplicationContext())+"&idproduct="+getintent.getStringExtra("idproduct");
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String check=response.getString("status");
                        if(check.equals("error")){
                            Addfav.setImageDrawable
                                    (ResourcesCompat.getDrawable(getResources(),
                                            R.drawable.ic_baseline_favorite_24,null));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Requst_Volley.Getinstanc(getApplicationContext()).add(jsonObjectRequest);
        }
    }

    void Cast()
    {

        ra_bar_score = findViewById(R.id.ra_bar_score);
        Im_back = findViewById(R.id.Im_back);
        Liner_Cart = findViewById(R.id.Liner_Cart);
        linertimer = findViewById(R.id.linertimer);

        Tv_sec = findViewById(R.id.Tv_sec);
        Tv_min = findViewById(R.id.Tv_min);
        Tv_hour = findViewById(R.id.Tv_hour);

        Tv_title_product_fa = findViewById(R.id.Tv_title_product_fa);
        Tv_title_product_en = findViewById(R.id.Tv_title_product_en);
        Tv_des = findViewById(R.id.Tv_des);
        Tv_price = findViewById(R.id.Tv_price);
        Tv_g = findViewById(R.id.Tv_g);

        linerlayout_slider = findViewById(R.id.linerlayout_slider);
        viewpager = findViewById(R.id.viewpager);

        Tv_number_color = findViewById(R.id.Tv_number_color);
        recyclerview_color =findViewById(R.id.recyclerview_color_more);

        Addfav=findViewById(R.id.Addfav);

        recyclerview_score = findViewById(R.id.recyclerview_score);

        Im_share=findViewById(R.id.Im_share);

        Cardview_service=findViewById(R.id.Cardview_service);
        Tv_number_service_shop = findViewById(R.id.Tv_number_service_shop);

        Rel_more_Text = findViewById(R.id.Rel_more_Text);
        scrollview = findViewById(R.id.Scrollview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Im_shop = findViewById(R.id.Im_shop);
        Tv_toolbar_title = findViewById(R.id.Tv_toolbar_title);

        TextView Tv_1 = findViewById(R.id.Tv_1);
        TextView Tv_2 = findViewById(R.id.Tv_2);
        Tv_1.setText("از تعداد 1490 رای ثبت شده");
        Tv_2.setText("4.3 از 5");
        float t=Float.parseFloat("3");
        ra_bar_score.setRating(t);

        Im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Liner_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: Liner_Cart");
                Add_Cart();
            }
        });


    }

    void Add_Cart()
    {
        db_dataBase.Get_Storeid(idstore); /// is the same set_Storeid
//        if(db_dataBase.IdPostexists(idstore)){
//            Log.d(TAG, "Add_Cart: exist before");
//            db_dataBase.Update_price(idstore); /// update number of this product
//        }
//        else
//        {
            //Log.d(TAG, "Add_Cart: insert");
            db_dataBase.Insert_Post(price_sale,idcolor,service_shop);
            Intent intent=new Intent(getApplicationContext(),
                    Cart_activity.class);
            startActivity(intent);
//        }
    }

    void checktimer(){
        String check=getintent.getStringExtra("offer");
        if(check!=null && check.equals("0"))
        {
            //Log.d(TAG, "check!=null && check.equals(0) ");
            linertimer.setVisibility(View.GONE);
        }
        else
        {
            Log.d(TAG, "check==null || check != 0 ");
            linertimer.setVisibility(View.VISIBLE);
            Gettimer();
        }
    }

    void  Gettimer()
    {
        Api_timer.Getmethod_timer(getApplicationContext(),Tv_sec,Tv_min,Tv_hour);
    }
    void Getproduct()
    {
        Api_product.Getpost(More_Product.this,
                Integer.parseInt(getintent.getStringExtra("idproduct")),
                new Api_product.interface_poduct() {
            @Override
            public void list(final List<Datamodel_product> datamodel_products) {
                //Log.d(TAG, "list in Getproduct() : ");
                db_dataBase.Get_Datamodel(datamodel_products);
                for (int i = 0; i <datamodel_products.size() ; i++) {
                    datamodel_product=datamodel_products.get(i);
                    idstore=datamodel_product.getIdstore();
                    price_sale=datamodel_product.getPrice();
                    service_shop=datamodel_product.getG();

                    card_comments.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(getApplicationContext(),
                                    Activity_Comments.class);
                            intent.putExtra("title",datamodel_product.getName());
                            intent.putExtra("id",datamodel_product.getIdproduct());
                            startActivity(intent);
                        }
                    });

                    Im_share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT,datamodel_product.getName()+"\n\n"+datamodel_product.getNameen()+"\n"+datamodel_product.getPic()+"\n"+"www.rezaeianengineer.com");
                            startActivity(Intent.createChooser(intent,
                                    "اشتراک گذاری به وسیله..."));
                        }
                    });


                    if(datamodel_product.isCheck_service())
                    {
                        Cardview_service.setVisibility(View.VISIBLE);

                        Tv_number_service_shop.setText(datamodel_product.getLen()+" فروشنده و گارانتی برای این کالا وجود دارد  ");
                    }
                    else
                    {
                        Cardview_service.setVisibility(View.GONE);
                    }

                    titletoolbar=datamodel_product.getName();
                    titleproduct=datamodel_product.getName();
                    Tv_title_product_fa.setText(datamodel_product.getName());
                    Tv_title_product_en.setText(datamodel_product.getNameen());
                    Get_Color(datamodel_product.getColors());
                    Tv_price.setText(getintent.getStringExtra("price")+" تومان ");

                    Tv_g.setText(getintent.getStringExtra("g"));
                    Get_fav(datamodel_product.getCheck_fav());
                }

            }
        }, new Api_product.interface_images() {
            @Override
            public void listimages(List<String> Images)
            {

                idview=new int[Images.size()];

                Sliderindactor(Images.size());
                slider_pageradapter=new Slider_pageradapter
                        (More_Product.this,
                        More_Product.this,Images,idview);
                viewpager.setAdapter(slider_pageradapter);
                ///// Assign animation to slider
                viewpager.setPageTransformer
                        (false,
                                new ViewPager.PageTransformer() {
                    @Override
                    public void transformPage(@NonNull View page, float position) {
                        page.setTranslationX(-position*page.getWidth());
                        if (position<-1){
                            page.setAlpha(0);

                        }
                        else if (position<=0){
                            page.setAlpha(1);
                            page.setPivotX(0);
                            page.setRotationY(90*Math.abs(position));

                        }
                        else if (position <=1){
                            page.setAlpha(1);
                            page.setPivotX(page.getWidth());
                            page.setRotationY(-90*Math.abs(position));

                        }
                        else {
                            page.setAlpha(0);

                        }
                    }
                });

            }
        });

        Setup_Recyclerview_Score_Product();

    }

    void Setup_Recyclerview_Score_Product()
    {
        int px=getResources().getDisplayMetrics().widthPixels;
        int t=Convert_px.Convert_px(200,More_Product.this);
        int count=(px-t)/5;
        //Log.d(TAG, "Setup_Recyclerview_Score_Product: ");
        recyclerview_score.setLayoutManager
                (new LinearLayoutManager(More_Product.this));
        Adapter_score adapter_score=new Adapter_score
                (count,More_Product.this,Get_api_score());
        recyclerview_score.setAdapter(adapter_score);
    }

    List<Datamodel_score> Get_api_score()
    {
        List<Datamodel_score> datamodel_scores=new ArrayList<>();
        for (int i = 0; i <6; i++) {
            Datamodel_score datamodel_score=new Datamodel_score();
            switch (i){
                case 0 :
                    datamodel_score.setScore("4,9");
                    datamodel_score.setTitle("کیفیت ساخت");

                    break;
                case 1 :
                    datamodel_score.setScore("2,6");
                    datamodel_score.setTitle("ارزش خرید نسبت به قیمت");
                    break;
                case 2 :
                    datamodel_score.setScore("2,7");
                    datamodel_score.setTitle("نو آوری");
                    break;

                case 3 :
                    datamodel_score.setScore("4,2");
                    datamodel_score.setTitle("امکانات و قابلیت ها");
                    break;
                case 4 :
                    datamodel_score.setScore("2,1");
                    datamodel_score.setTitle("سهولت استفاده");
                    break;

                case 5 :
                    datamodel_score.setScore("1,5");
                    datamodel_score.setTitle("طراحی و ظاهر");
                    break;
            }
            datamodel_scores.add(datamodel_score);
        }

        return datamodel_scores;
    }
    void Get_fav(String fav)
    {

        SharedPreferences sharedPreferences=getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        //Log.d(TAG, "Get_fav: before check token");
        if (toekn!=null) {
            Log.d(TAG, "Get_fav: !=null");
            if (fav.equals("true")) {
                Addfav.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.redheart, null));

            } else {
                Addfav.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_favorite_border_black_24dp, null));

            }
        }
    }

    void Get_Color(String color)
    {
        List<Datamodel_color> datamodel_colors=new ArrayList<>();
        String[] colors=color.split("!");
        Tv_number_color.setText(Farsinumber.farsinumber(String.valueOf(colors.length))+" رنگ ");
        for (int i = 0; i <colors.length ; i++) {
            Datamodel_color datamodel_color=new Datamodel_color();
            //Log.d(TAG, "Get_Color: "+colors[i]);
            switch (Integer.parseInt(colors[i]))
            {
                case 1 :
                    datamodel_color.setIdcolor(String.valueOf(1));
                    datamodel_color.setName_color("زرد");
                    datamodel_color.setColor("#ffff00");
                    break;
                case 2 :
                    datamodel_color.setIdcolor(String.valueOf(2));
                    datamodel_color.setName_color("نارنجی");
                    datamodel_color.setColor("#ff5722");
                    break;
                case 3 :
                    datamodel_color.setIdcolor(String.valueOf(3));
                    datamodel_color.setName_color("آبی");
                    datamodel_color.setColor("#3498DB");
                    break;
                case 4 :
                    datamodel_color.setIdcolor(String.valueOf(4));
                    datamodel_color.setName_color("مشکی");
                    datamodel_color.setColor("#000000");
                    break;
                case 5 :
                    datamodel_color.setIdcolor(String.valueOf(5));
                    datamodel_color.setName_color("سبز");
                    datamodel_color.setColor("#27AE60");
                    break;

            }

            datamodel_colors.add(datamodel_color);
        }
        Log.d(TAG, "datamodel_colors: "+datamodel_colors.size());
        recyclerview_color.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        Adapter_color adapter_color=new Adapter_color(More_Product.this,
                datamodel_colors, this);
        recyclerview_color.setAdapter(adapter_color);
//        Log.d(TAG, "Get_Color: ");
    }

    void Sliderindactor(final int len)
    {
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(Convert_px.convertpx(30,getApplicationContext()),Convert_px.convertpx(30,getApplicationContext()));
        layoutParams.setMargins(0,0,13,0);
        for (int i = 0; i <len ; i++) {
            int id=View.generateViewId();
            idview[i]=id;
            View view=new View(More_Product.this);
            view.setBackgroundResource(R.drawable.shape_slider_noactive);
            view.setLayoutParams(layoutParams);
            view.setId(id);
            linerlayout_slider.addView(view);
        }


    }

    @Override
    public void Changecolor(String idcolor, int possion) {
        this.idcolor=Integer.parseInt(idcolor);
    }


}