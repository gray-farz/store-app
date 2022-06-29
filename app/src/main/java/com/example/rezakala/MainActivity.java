package com.example.rezakala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.rezakala.Action.GetToken;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Cart.Db_DataBase;
import com.example.rezakala.Category_Main.Adapter_Recyclerview_category_home;
import com.example.rezakala.Category_Main.Api_Category_home;
import com.example.rezakala.Category_Main.Datamodel_cagegory_home;
import com.example.rezakala.Custom_font.CustomTypefaceSpan;
import com.example.rezakala.Pack_sand_count.Adapter_sandcount;
import com.example.rezakala.Pack_sand_count.Api_sandcount;
import com.example.rezakala.Pack_sand_count.Datamodel_sandcount;
import com.example.rezakala.Pack_timer.Api_timer;
import com.example.rezakala.Packslider.Slider_config;
import com.example.rezakala.Panel.Login_Activity;
import com.example.rezakala.Panel.Panel.Main_profile_panel;
import com.example.rezakala.Product.Api_product;
import com.example.rezakala.Product.Custom_product;
import com.example.rezakala.Product.Datamodel_listproduct;
import com.example.rezakala.Product_offer.Api_product_offer;
import com.example.rezakala.Product_offer.Custom_product_offer;
import com.example.rezakala.Viewpager_Tablayout_Category.Viewpager_Category;
import com.google.android.material.navigation.NavigationView;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Api_Category_home.Get_category, NavigationView.OnNavigationItemSelectedListener {
    ImageView Im_panel,Im_search;
    Slider_config slider_config;
    ProgressWheel progress_wheel;
    ViewPager viewpager;
    LinearLayout linerlayout_slider;
    RecyclerView recyclerview_category_home,recyclerview_span_count;
    Adapter_Recyclerview_category_home adapter_recyclerview_category_home;
    TextView Tv_sec,Tv_min,Tv_hour;
    Api_sandcount api_sandcount;
    Adapter_sandcount adapter_sandcount;
    Custom_product custom_product,Custom_proruct_new;
    Custom_product_offer custom_product_offer;
    private static final String TAG = "aaa";
    Db_DataBase db_dataBase;
    NavigationView navigationView;
    Menu m;
    static Typeface iranianSansFont;
    TextView Tv_number,Tv_title_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Im_search=findViewById(R.id.Im_search);
//        Im_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), Search_Product.class);
//                startActivity(intent);
//            }
//        });

        db_dataBase=new Db_DataBase(this);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }

        Cast();
        Setupslider();
        setup_category_home();
        Gettimer();
        Setup_Spandcount();
        Getlist_Custom_proruct_pourforsh();
        Getlist_Custom_proruct_offer();
        FontGetment_headrviewitmes();
        Tv_title_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });

        Im_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Main_profile_panel.class);
                startActivity(intent);
            }
        });


    }
    void FontGetment_headrviewitmes(){
        View view=navigationView.getHeaderView(0);
        long count=db_dataBase.Get_count_record();
        TextView tv_view=(TextView) navigationView.getMenu().findItem(R.id.nav_slideshow).getActionView();
        tv_view.setText(" "+count+" ");
        //Log.d(TAG, "FontGetment_headrviewitmes: "+count);
        if(count !=0)
            Tv_number.setText(String.valueOf(count));
        Tv_title_header=view.findViewById(R.id.textView);
        Tv_title_header.setTypeface(getIranianSansFont(getApplicationContext()));
        //Im_panel.setVisibility(View.GONE);
        SharedPreferences sharedPreferences=getSharedPreferences("info",0);
        String toekn=sharedPreferences.getString("token",null);
        if (toekn==null){
            //Im_panel.setVisibility(View.GONE);
        }
        else
        {
            Im_panel.setVisibility(View.VISIBLE);
            String url= Config.urlhome+"userinfo.php?token="+ GetToken.gettoken(getApplicationContext());

            JsonObjectRequest js = new JsonObjectRequest(0,
                    url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject jsonObject=response.getJSONObject("userinfo");
                                String checkname=jsonObject.getString("name")+"  "+jsonObject.getString("family");
                                Tv_title_header.setText(checkname);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "خطا در دریافت اطلاعات از سمت سرور", Toast.LENGTH_SHORT).show();
                }
            });

            js.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Requst_Volley.Getinstanc(getApplicationContext()).add(js);
        }

    }
    private void applyFontToMenuItem(MenuItem mi) {
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan(getApplicationContext(), "", getIranianSansFont(getApplicationContext())), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
    public static Typeface getIranianSansFont(Context context) {

        if (iranianSansFont == null) {
            iranianSansFont = Typeface.createFromAsset(context.getAssets(), "fonts/zahra_roosta.ttf");
        }
        return iranianSansFont;
    }

    private void Cast()
    {
        progress_wheel=findViewById(R.id.progress_wheel);
        viewpager=findViewById(R.id.viewpager);
        linerlayout_slider=findViewById(R.id.linerlayout_slider);
        linerlayout_slider.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        recyclerview_category_home=findViewById(R.id.recyclerview_category_home);

        Tv_sec=findViewById(R.id.Tv_sec);
        Tv_min=findViewById(R.id.Tv_min);
        Tv_hour=findViewById(R.id.Tv_hour);

        recyclerview_span_count=findViewById(R.id.recyclerview_span_count);

        Tv_number=findViewById(R.id.Tv_number);
        Im_panel=findViewById(R.id.Im_panel);
    }

    void Setupslider()
    {
        slider_config=new Slider_config(MainActivity.this,MainActivity.this,progress_wheel,viewpager,linerlayout_slider);
    }
    void setup_category_home()
    {
        Api_Category_home.Category(MainActivity.this,
                MainActivity.this);
    }

    @Override
    public void getcategory(List<Datamodel_cagegory_home> list) {
        recyclerview_category_home.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        adapter_recyclerview_category_home=new Adapter_Recyclerview_category_home(MainActivity.this,list);
        recyclerview_category_home.setAdapter(adapter_recyclerview_category_home);

    }

    @Override
    public void Error(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    void  Gettimer()
    {
        Api_timer.Getmethod_timer(getApplicationContext(),Tv_sec,Tv_min,Tv_hour);
    }
    void Setup_Spandcount(){
        api_sandcount=new Api_sandcount(MainActivity.this);
        api_sandcount.Setup_Sapndcountmethod(new Api_sandcount.Getspandcount() {
            @Override
            public void Listsandcount(List<Datamodel_sandcount> datamodel_sandcounts) {
                final GridLayoutManager gridLayoutManager=new GridLayoutManager
                        (MainActivity.this,2);
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        switch (adapter_sandcount.getItemViewType(position)){
                            case Adapter_sandcount.view_type_header :
                                return gridLayoutManager.getSpanCount();
                            case Adapter_sandcount.view_type_spandcount :
                                return 1;
                            default:
                                return -1;
                        }
                    }
                });
                recyclerview_span_count.setLayoutManager(gridLayoutManager);
                adapter_sandcount =new Adapter_sandcount(MainActivity.this,datamodel_sandcounts);
                recyclerview_span_count.setAdapter(adapter_sandcount);
            }
        });
    }

    void Getlist_Custom_proruct_pourforsh()
    {
        Api_product.Getlist_product("product_pourforsh"
                ,getApplicationContext(), new Api_product.Listproduct() {
            @Override
            public void Listpost(List<Datamodel_listproduct> datamodel_listproducts) {
                custom_product=findViewById(R.id.Custom_proruct_pourforsh);
                custom_product.Getlistproduct();
                custom_product.getlist(datamodel_listproducts);
                custom_product.Settitle("محصولات پروفروش");
            }
        });
        //Getlist_Custom_proruct_new();

    }

    void Getlist_Custom_proruct_new()
    {
        Api_product.Getlist_product("product_new",getApplicationContext(), new Api_product.Listproduct() {
            @Override
            public void Listpost(List<Datamodel_listproduct> datamodel_listproducts) {
                Custom_proruct_new=findViewById(R.id.Custom_proruct_new);
                Custom_proruct_new.Getlistproduct();
                Custom_proruct_new.getlist(datamodel_listproducts);
                Custom_proruct_new.Settitle("محصولات جدید");
            }
        });

    }

    void Getlist_Custom_proruct_offer()
    {
        Api_product_offer.Getlist_product("product_offer", getApplicationContext(), new Api_product_offer.Listproduct() {
            @Override
            public void Listpost(List<Datamodel_listproduct> datamodel_listproducts) {
                //Log.d(TAG, "Listpost in offer: ");
                custom_product_offer=findViewById(R.id.Custom_product_offer);
                custom_product_offer.Getlistproduct();
                custom_product_offer.getlist(datamodel_listproducts);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {

        }
        else if (id == R.id.nav_gallery) {

            Intent intent=new Intent(getApplicationContext(), Viewpager_Category.class);
            startActivity(intent);

        }
//        else if (id == R.id.nav_slideshow) {
//            Intent intentcart=new Intent(getApplicationContext(), Cart_activity.class);
//            startActivity(intentcart);
//
//        }
//        else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }else if (id == R.id.nav_about) {
//            Intent intent=new Intent(getApplicationContext(),About_Digikala.class);
//            startActivity(intent);
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}