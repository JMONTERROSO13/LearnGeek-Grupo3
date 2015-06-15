package gt.edu.kinal.jmonterroso.learngeek_grupo3;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Category;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private ListView listCategories;
    private SQLite sqlite;
    private SQLiteDatabase db;
    ArrayList<Category> listCategory= new ArrayList<Category>();

    public CategoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        listCategories = (ListView)view.findViewById(R.id.listCategory);

        listaAdapter();
        listCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTitular = new Intent(getActivity(), CategoryActivity.class);
                Category slecet = (Category)parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("Nombre", slecet.getNameCategory());
                extras.putString("Descripcion", slecet.getDescriptionCategory());
                intentTitular.putExtras(extras);
                startActivity(intentTitular);
            }
        });

        registerForContextMenu(listCategories);

        return view;
    }


    public void listaAdapter() {
        listCategory.clear();

        sqlite = new SQLite(getActivity().getBaseContext());
        db = sqlite.getReadableDatabase();

        String Sql = "SELECT * FROM Category";

        Cursor cc = db.rawQuery(Sql, null);
        Category obj;
        if (cc.moveToFirst()) {
            do {

                obj = new Category();
                obj.setNameCategory(cc.getString(1));
                obj.setDescriptionCategory(cc.getString(2));
                listCategory.add(obj);

            } while (cc.moveToNext());
        }

        db.close();

        AdaptadorCategory adapter = new AdaptadorCategory(getActivity(),listCategory);
        listCategories.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class AdaptadorCategory extends ArrayAdapter<Category> {
        public AdaptadorCategory(Context context,  ArrayList<Category> datos){
            super(context, R.layout.listitem_category, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_category, null);

            TextView nameCategory = (TextView)item.findViewById(R.id.nameCategory);
            TextView descriptionCategory = (TextView)item.findViewById(R.id.descriptionCategory);


            String tituloCategories= listCategory.get(position).getNameCategory();
            String descriptionCategories= listCategory.get(position).getDescriptionCategory();

            nameCategory.setText(tituloCategories);
            descriptionCategory.setText(descriptionCategories);

            return item;
        }
    }
}