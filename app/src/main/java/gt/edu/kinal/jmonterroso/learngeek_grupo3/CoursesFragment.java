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
import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Course;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {


    public CoursesFragment() {
        // Required empty public constructor
    }


    private ListView ListCourses;
    private SQLite sqlite;
    private SQLiteDatabase db;
    ArrayList<Course> listCourse= new ArrayList<Course>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        ListCourses = (ListView)view.findViewById(R.id.listCourse);

        listaAdapter();
        ListCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentTitular = new Intent(getActivity(), CourseActivity.class);
                Course slecet = (Course)parent.getItemAtPosition(position);
                Bundle extras = new Bundle();
                extras.putString("Nombre", slecet.getNameCourse());
                extras.putString("Descripcion", slecet.getDescriptionCourse());
                intentTitular.putExtras(extras);
                startActivity(intentTitular);
            }
        });

        registerForContextMenu(ListCourses);

        return view;
    }


    public void listaAdapter() {
        listCourse.clear();

        sqlite = new SQLite(getActivity().getBaseContext());
        db = sqlite.getReadableDatabase();

        String Sql = "SELECT * FROM Course";

        Cursor cc = db.rawQuery(Sql, null);
        Course obj;
        if (cc.moveToFirst()) {
            do {

                obj = new Course();
                obj.setNameCourse(cc.getString(1));
                obj.setDescriptionCourse(cc.getString(2));
                listCourse.add(obj);

            } while (cc.moveToNext());
        }

        db.close();

        AdaptadorCourse adapter = new AdaptadorCourse(getActivity(), listCourse);
        ListCourses.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class AdaptadorCourse extends ArrayAdapter<Course> {
        public AdaptadorCourse(Context context,  ArrayList<Course> datos){
            super(context, R.layout.listitem_course, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_course, null);

            TextView nameCategory = (TextView)item.findViewById(R.id.nameCourse);
            TextView descriptionCategory = (TextView)item.findViewById(R.id.descriptionCourse);

            String nC= listCourse.get(position).getNameCourse();
            String dC= listCourse.get(position).getDescriptionCourse();

            nameCategory.setText(nC);
            descriptionCategory.setText(dC);

            return item;
        }
    }

}
