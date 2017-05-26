package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bd.OpenHelperDB;
import model.Item;

/**
 * Created by eduardo on 25/05/2017.
 */

public class ItemDAO {

    private OpenHelperDB openHelperDB;

    public ItemDAO(Context context){
      openHelperDB = new OpenHelperDB(context);
    }

    public void create(Item item){
        SQLiteDatabase db = openHelperDB.getWritableDatabase();

        ContentValues params = new ContentValues();
        params.put("name", item.getName());
        params.put("description", item.getDescription());
        params.put("price", item.getPrice());
        params.put("quantity", item.getQuantity());

        db.insert("itens",null,params);

        db.close();
    }

    public void destroy(Item item){
        SQLiteDatabase db = openHelperDB.getWritableDatabase();
        db.delete("itens", "id=?", new String[]{String.valueOf(item.getId())});
        db.close();
    }

    public List<Item> all(){
        SQLiteDatabase db = openHelperDB.getWritableDatabase();
        Cursor cursor = db.query("itens",
                new String[]{"id","name","quantitity", "description", "price"},
                null,null,null,null,"name");

        List<Item> listItens = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int quantitity = cursor.getInt(cursor.getColumnIndex("quantitity"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            double price = cursor.getDouble(cursor.getColumnIndex("price"));
            Item item = new Item(id, name,description, price, quantitity);
            listItens.add(item);
        }

        return listItens;
    }

}
