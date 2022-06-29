package com.example.rezakala.More_Product.Compare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rezakala.Product.Datamodel_listproduct;
import com.example.rezakala.R;

import java.util.List;

public class Activity_Add_product extends AppCompatActivity {
    private static final String TAG = "aaa";
    RecyclerView recyclerview;
    ImageView Im_close;
    EditText Et_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Cast();

        Im_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api_Add_Search.Get_search(Activity_Add_product.this,
                        Et_search.getText().toString(),
                        new Api_Add_Search.Get_info() {
                    @Override
                    public void Listinfo(List<Datamodel_listproduct> list) {
                        recyclerview.setLayoutManager(new LinearLayoutManager(Activity_Add_product.this));
                        Adapter_List_Compare_search adapter=new Adapter_List_Compare_search(Activity_Add_product.this, list, new Adapter_List_Compare_search.Get_More() {
                            @Override
                            public void getid(String id) {
                                Log.e(TAG, "getid: "+id );
                                Intent intent=new Intent();
                                intent.putExtra("idproduct2",id);
                                setResult(1,intent);
                                finish();
                            }
                        });
                        recyclerview.setAdapter(adapter);
                    }
                });
            }
        });
    }

    void Cast()
    {
        recyclerview=findViewById(R.id.recyclerview);
        Im_close=findViewById(R.id.Im_close);
        Et_search=findViewById(R.id.Et_search);
    }
}