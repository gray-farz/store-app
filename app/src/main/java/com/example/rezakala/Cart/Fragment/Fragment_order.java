package com.example.rezakala.Cart.Fragment;


import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.rezakala.Cart.Datamodel_Cart;
import com.example.rezakala.Panel.Panel.Datamodel.Datmodel_address;
import com.example.rezakala.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_order extends Fragment
{
    Api_address_frgament api_address_frgament;
    RecyclerView recyclerview,recyclerview_cart;
    Adapter_address_fragment adapter;
    ImageView Send_save;
    String name_f,address_f,phone_f,Idaddress_f;
    private static final String TAG = "aaa";
    public Fragment_order() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_fragment_order, container, false);
        api_address_frgament=new Api_address_frgament(getActivity());
        api_address_frgament.Getaddress(new Api_address_frgament.in_address() {
            @Override
            public void Address(List<Datmodel_address> datmodel_addresses) {
                //Log.d(TAG, "Address in onCreateView: ");
                recyclerview=view.findViewById(R.id.recyclerview);
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                adapter=new Adapter_address_fragment(getActivity(),
                        datmodel_addresses,
                        new Adapter_address_fragment.Get_id_address_select() {
                    @Override
                    public void Get_id_address_select(String idaddress,String name, String address, String phone) {
                        name_f=name;
                        address_f=address;
                        phone_f=phone;
                        Idaddress_f=idaddress;
                    }
                });
                recyclerview.setAdapter(adapter);
                //Log.d(TAG, "Address in onCreateView: ");
            }
        });


        Api_ListCart.Get_list_Cart(getActivity(),
                new Api_ListCart.Get_List_Cart() {
            @Override
            public void Get_list(List<Datamodel_Cart> list) {
                //Log.d(TAG, "Get_list: in Get_list_Cart");
                recyclerview_cart=view.findViewById(R.id.recyclerview_cart);
                recyclerview_cart.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                Adapter_List_Cart adapter_list_cart=new Adapter_List_Cart(getActivity(),list);
                recyclerview_cart.setAdapter(adapter_list_cart);
            }
        });

        Send_save=view.findViewById(R.id.Send_save);
        Send_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_send_check fragment_send_check=new Fragment_send_check();
                fragment_send_check.Get_Send_user(Idaddress_f,name_f,address_f,phone_f);
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.Rel_root,fragment_send_check).addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}
