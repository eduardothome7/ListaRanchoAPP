package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bd.OpenHelperDB;
import model.Product;

/**
 * Created by eduardo on 25/05/2017.
 */

public class ProductDAO {

    private OpenHelperDB openHelperDB;

    public ProductDAO(Context context){
      openHelperDB = new OpenHelperDB(context);
    }

    public boolean create(Product product){
        SQLiteDatabase db = openHelperDB.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put("name", product.getName());
        params.put("description", product.getDescription());
        params.put("price", product.getPrice());
        params.put("quantity", product.getQuantity());
        params.put("checked", 0);

        try {
            db.insert("products", null, params);
            db.close();
            return true;
        } catch(SQLiteAbortException error) {
            db.close();
            return false;
        }
    }

    public void destroy(Product product){
        SQLiteDatabase db = openHelperDB.getWritableDatabase();
        db.delete("products", "id=?", new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public void getCart(Product product) {
        SQLiteDatabase db = openHelperDB.getWritableDatabase();
        ContentValues params = new ContentValues();
        params.put("checked",1);
        db.update("products",params,"id=?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }

    public List<Product> all(){
        SQLiteDatabase db = openHelperDB.getReadableDatabase();
        Cursor cursor = db.query("products",
                new String[]{"id","name","description", "price","quantity","checked"},
                "checked=0",null,null,null,"name");

        List<Product> listProducts = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            int quantitity = cursor.getInt(cursor.getColumnIndex("quantity"));
            Product product = new Product(id, name,description, price, quantitity);
            listProducts.add(product);
        }

        return listProducts;
    }

}
