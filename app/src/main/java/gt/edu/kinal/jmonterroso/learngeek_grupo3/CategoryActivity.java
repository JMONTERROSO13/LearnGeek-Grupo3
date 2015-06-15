package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Category;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Course;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


public class CategoryActivity extends ActionBarActivity {

    private TextView nombre;
    private TextView description;

    private ListView listCourse;
    private SQLite sqlite;
    private SQLiteDatabase db;

    ArrayList<Course> listCourses= new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        nombre = (TextView)findViewById(R.id.nameDetailCategory);
        description = (TextView)findViewById(R.id.descriptionDetailCategory);
        listCourse = (ListView)findViewById(R.id.listCourseCategory);

        Bundle extras = getIntent().getExtras();
        listaAdapter();
        nombre.setText(extras.getString("Nombre"));
        description.setText(extras.getString("Descripcion"));

        registerForContextMenu(listCourse);
    }


    public void listaAdapter() {
        listCourses.clear();

        sqlite = new SQLite(CategoryActivity.this.getBaseContext());
        db = sqlite.getReadableDatabase();

        Bundle extrass = getIntent().getExtras();
        String Cats = extrass.getString("Nombre");
        String Sql = "SELECT idCategory FROM Category Where nameCategory='"+Cats+"' ;";

        Cursor cc = db.rawQuery(Sql, null);
        int idCategory = 0 ;

        if (cc.moveToFirst()) {
            do {
                idCategory = cc.getInt(0);
            } while (cc.moveToNext());
        }
        db.close();
        sqlite = new SQLite(CategoryActivity.this.getBaseContext());
        db = sqlite.getReadableDatabase();

        Sql = "SELECT * FROM Course WHERE idCategory="+idCategory+";";

        Cursor ccu = db.rawQuery(Sql, null);
        Course obj;
        if (ccu.moveToFirst()) {
            do {

                obj = new Course();
                obj.setNameCourse(ccu.getString(1));
                obj.setDescriptionCourse(ccu.getString(2));
                listCourses.add(obj);

            } while (cc.moveToNext());
        }

        db.close();

        AdaptadorCategoryCourses adapter = new AdaptadorCategoryCourses(CategoryActivity.this, listCourses);
        listCourse.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class AdaptadorCategoryCourses extends ArrayAdapter<Course> {
        public AdaptadorCategoryCourses(Context context,  ArrayList<Course> datos){
            super(context, R.layout.listitem_category_courses, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_category_courses, null);

            TextView nameCategory = (TextView)item.findViewById(R.id.nameCourseCat);
            TextView descriptionCategory = (TextView)item.findViewById(R.id.descriptionCourseCat);


            String namCourseCat= listCourses.get(position).getNameCourse();
            String descriptionCourseCat= listCourses.get(position).getDescriptionCourse();

            nameCategory.setText(namCourseCat);
            descriptionCategory.setText(descriptionCourseCat);

            return item;
        }
    }
}
