package com.example.rezakala.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.rezakala.More_Product.Datamodel_product;

import java.util.ArrayList;
import java.util.List;


public class Db_DataBase extends SQLiteOpenHelper {
    private static final String TAG = "Db_DataBase";
    static final int version=1;
    static final String Db_name="digikala.db";
    static final String Tbl_cart="Tbl_Cart";
    static final String Tbl_Store="Tbl_Store";

    List<Datamodel_product> datamodel_products;
    List<Integer> list_Idstore=new ArrayList<>();
    SQLiteDatabase sqlw=this.getWritableDatabase();
   public SQLiteDatabase sqlr=this.getReadableDatabase();
    String idsotre;
    public Db_DataBase(Context context) {
        super(context, Db_name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Tbl_cart +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                " idstore TEXT,idproduct TEXT,titlefa TEXT,titleen TEXT,Image TEXT,color TEXT,service TEXT,shop TEXT,number INTEGER," +
                "price INTEGER,Total_price INTEGER,final_price INTEGER,discount INTEGER )");
        db.execSQL("create table " + Tbl_Store +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "idstore INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+Tbl_cart);
        db.execSQL(" DROP TABLE IF EXISTS "+Tbl_Store);
        onCreate(db);
    }
    public void Get_Datamodel(List<Datamodel_product> datamodel_products)
    {
        this.datamodel_products=datamodel_products;

    }

    public void Get_Storeid(String idsotre)
    {
        this.idsotre=idsotre;
    }


    public void Insert_Post(String price,int color_id,String service)
    {
        if(datamodel_products.size()>0){

            for (int i = 0; i <datamodel_products.size() ; i++) {
                Datamodel_product datamodel_product=datamodel_products.get(i);
                ContentValues contentValues=new ContentValues();
                contentValues.put("idstore",idsotre);
                contentValues.put("idproduct",datamodel_product.getIdproduct());
                contentValues.put("titlefa",datamodel_product.getName());
                contentValues.put("titleen",datamodel_product.getNameen());
                contentValues.put("Image",datamodel_product.getPic());
                contentValues.put("color",color_id);
                contentValues.put("service",service);
                contentValues.put("shop",service);
                contentValues.put("number",1);
                contentValues.put("price",Integer.parseInt(price));
                contentValues.put("Total_price",Integer.parseInt(price));
                contentValues.put("final_price",Integer.parseInt(price));
                contentValues.put("discount",Integer.parseInt(price));
                sqlw.insert(Tbl_cart,null,contentValues);
            }

            Set_Idsote(idsotre);

        }
    }
    public boolean IdStoreexists(String idsotre) {
        SQLiteDatabase sqlcheck = this.getReadableDatabase();
        Cursor cursor = sqlcheck.rawQuery("SELECT * FROM " + Tbl_Store + " WHERE idstore = '" + idsotre + "'", null);
        if (cursor.getCount() > 0)
            return true;
        return false;
    }

    void Set_Idsote(String idsotre)
    {
        if(!IdStoreexists(idsotre)){
            ContentValues contentValues=new ContentValues();
            contentValues.put("idstore",idsotre);
            sqlw.insert(Tbl_Store,null,contentValues);
        }
    }

    public Cursor Get_Cursor(){
        Cursor cursor=sqlr.rawQuery("select * from "+Tbl_cart,null);
        return cursor;
    }

    public List<Datamodel_Cart> Get_info(){
        List<Datamodel_Cart> datamodel_carts=new ArrayList<>();
        Cursor cursor=sqlr.rawQuery("select * from "+Tbl_cart,null);
        if(cursor.getCount()>0){
            while (cursor.moveToNext()){
                Datamodel_Cart datamodel_cart=new Datamodel_Cart();
                datamodel_cart.setIdstore(cursor.getString(1));
                datamodel_cart.setIdproduct(cursor.getString(2));
                datamodel_cart.setTitlefa(cursor.getString(3));
                datamodel_cart.setTitleen(cursor.getString(4));
                datamodel_cart.setImage(cursor.getString(5));
                datamodel_cart.setColor(cursor.getString(6));
                datamodel_cart.setService(cursor.getString(7));
                datamodel_cart.setShop(cursor.getString(8));
                datamodel_cart.setNumber(cursor.getString(9));
                datamodel_cart.setPrice(cursor.getString(10));
                datamodel_cart.setTotal_price(cursor.getString(11));
                datamodel_cart.setFinal_price(cursor.getString(12));
                datamodel_cart.setDiscount(cursor.getString(13));
                datamodel_carts.add(datamodel_cart);
            }
            cursor.close();

        }
        return datamodel_carts;
    }

    public long Final_price_product()
    {
        long price=0;
        SQLiteStatement sqLiteStatement=sqlr.compileStatement("select sum(final_price) from "+Tbl_cart);
        price=sqLiteStatement.simpleQueryForLong();
        return price;
    }

    public long Get_count_record()
    {
        long price=0;
        SQLiteStatement sqLiteStatement=sqlr.compileStatement("select count(idstore) from "+Tbl_Store);
        price=sqLiteStatement.simpleQueryForLong();
        return price;
    }


    public boolean IdPostexists(String id_post) {
        SQLiteDatabase sqlcheck = this.getReadableDatabase();
        Cursor cursor = sqlcheck.rawQuery("SELECT * FROM " + Tbl_cart + " WHERE idstore = '" + id_post + "'", null);
        if (cursor.getCount() > 0)
            return true;
        return false;
    }


    public long Number_count(String id)
    {
        long price=0;
        SQLiteStatement sqLiteStatement=sqlr.compileStatement("select number from "+Tbl_cart + " where idstore="+id);
        price=sqLiteStatement.simpleQueryForLong();
        return price;
    }


    public void Update_price(String id)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("number",Number_count(id)+1); /// number of this product that the user has ordered
        sqlw.update(Tbl_cart,contentValues,"idstore=?",new String[]{id});
    }

    public void Update_price_product(String idproduct,int count,int number)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("number",number);
        contentValues.put("Total_price",count);
        contentValues.put("final_price",count);
        sqlw.update(Tbl_cart,contentValues,"idstore=?",new String[]{idproduct});
    }

    public void Get_remove_idstore(String idsotre)
    {
        sqlw.delete(Tbl_Store,"idstore=?",new String[]{idsotre});
    }

    public void Delete_product(String idproduct)
    {
        sqlw.delete(Tbl_cart,"idstore=?",new String[]{idproduct});
    }

    public void Delete_product()
    {
        sqlw.delete(Tbl_cart,null,null);
        sqlw.delete(Tbl_Store,null,null);
    }

}
