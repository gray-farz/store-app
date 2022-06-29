package com.example.rezakala.More_Product.Compare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.rezakala.Action.Requst_Volley;
import com.example.rezakala.Config;
import com.example.rezakala.More_Product.Compare.stickyheader.StickyHeaderItemDecorator;
import com.example.rezakala.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Compare extends AppCompatActivity implements Config {
    private static final String TAG = "aaa";
    ImageView Image_post,Im_add,Im_close,Im_screen_add,Image_remove;
    TextView Tv_title,Tv_add,Tv_screen_add;
    RecyclerView recyclerview;
    ArrayList<Section> sectionArrayList;
    int Headerdone=0,childdone=0;
    SectionAdapter adapter;
    String idproduct_1;
    Intent getientget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Log.d(TAG, "onCreate Activity_Compare : ");
        getientget=getIntent();
        Cast();

        idproduct_1=getientget.getStringExtra("idproduct");
        Setup_recyclerview(0,"");

        Im_screen_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick Im_screen_add: ");
                Intent intent=new Intent(getApplicationContext(),Activity_Add_product.class);
                startActivityForResult(intent,1);
                //startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1 && data!=null){
            Setup_recyclerview(1,data.getStringExtra("idproduct2"));
        }
    }

    void Cast()
    {
        Tv_add=findViewById(R.id.Tv_add);
        Im_close=findViewById(R.id.Im_close);
        Image_post=findViewById(R.id.Image_post);
        Im_add=findViewById(R.id.Im_add);
        Tv_title=findViewById(R.id.Tv_title);
        recyclerview=findViewById(R.id.recyclerview);
        Im_screen_add=findViewById(R.id.Im_screen_add);
        Tv_screen_add=findViewById(R.id.Tv_screen_add);
        Image_remove=findViewById(R.id.Image_remove);
    }

    void Setup_recyclerview(int check,String idproduct_2)
    {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        sectionArrayList=new ArrayList<>();
        GetData(check,idproduct_2);
        adapter =new SectionAdapter(sectionArrayList);
        recyclerview.setAdapter(adapter);
        StickyHeaderItemDecorator decorator =new StickyHeaderItemDecorator(adapter);
        decorator.attachToRecyclerView(recyclerview);
    }

    void GetData(final int check, final String idproduct_2)
    {
        Log.e(TAG, "GetData: "+idproduct_2 );
        String url=null;
        if(check==1){
            url =urlhome+"api/compare.php?idproduct="+idproduct_1+"!"+idproduct_2;
        }else
        {
            url =urlhome+"api/compare.php?idproduct="+idproduct_1;
        }
        Log.d(TAG, "GetData in compare url: "+url);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(0, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject json_product=response.getJSONObject("product");
                    JSONObject json_product_1=json_product.getJSONObject(idproduct_1);
                    Tv_title.setText(json_product_1.getString("name"));
                    Log.d(TAG, "onResponse pic: "+Url_image+json_product_1.getString("pic"));
                    Glide.with(getApplicationContext()).load(Url_image+json_product_1.getString("pic")).into(Image_post);
                    if(check==1) {
                        Image_remove.setVisibility(View.VISIBLE);
                        JSONObject json_product_2 = json_product.getJSONObject(idproduct_2);
                        Tv_add.setText(json_product_2.getString("name"));
                        Glide.with(getApplicationContext()).load(Url_image + json_product_2.getString("pic")).into(Im_add);
                        Im_screen_add.setVisibility(View.GONE);
                        Tv_screen_add.setVisibility(View.GONE);

                    }
                    else
                    {
                        Tv_screen_add.setText("افزودن کالا");
                        Tv_add.setText("");
                        Image_remove.setVisibility(View.GONE);
                        Im_screen_add.setVisibility(View.VISIBLE);
                        Tv_screen_add.setVisibility(View.VISIBLE);
                    }

                    JSONArray jsonArray=response.getJSONArray("propertis");
                    for (int i = 0; i <jsonArray.length() ; i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        HeaderModel headerModel=new HeaderModel(i);
                        headerModel.setheader(jsonObject.getString("name"));
                        sectionArrayList.add(headerModel);
                        Headerdone+=Headerdone+1;
                        JSONObject json_value=jsonObject.getJSONObject("value");
                        JSONArray array_product_1 = json_value.getJSONArray(idproduct_1);
                        JSONArray array_product_2=null;
                        JSONObject jsonObject2=null;
                        if(check==1) {

                            array_product_2= json_value.getJSONArray(idproduct_2);
                        }

                        for (int j = 0; j < array_product_1.length(); j++)
                        {
                            JSONObject jsonObject1 =
                                    array_product_1.getJSONObject(j);
                            if(check==1){
                                jsonObject2 = array_product_2.getJSONObject(j);
                            }

                            ChildModel childModel = new ChildModel(0);
                            childModel.setHeader_child(
                                    jsonObject1.getString("name"));
                            childModel.setInfo(jsonObject1.getString("value"));
                            if(check==1){
                                childModel.setValues_2(jsonObject2.
                                        getString("value"));
                            }
                            sectionArrayList.add(childModel);
                            childdone += childdone + 1;

                        }
                    }
                    adapter.notifyDataSetChanged();
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Requst_Volley.Getinstanc(getApplicationContext()).add(jsonObjectRequest);
    }
}