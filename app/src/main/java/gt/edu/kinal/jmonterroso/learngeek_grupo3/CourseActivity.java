package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Course;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.beans.Lesson;
import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


public class CourseActivity extends ActionBarActivity {


    private TextView nombre;
    private TextView description;

    private ListView listLesson;
    private SQLite sqlite;
    private SQLiteDatabase db;

    ArrayList<Lesson> listLessons= new ArrayList<Lesson>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        nombre = (TextView)findViewById(R.id.nameCourseDetail);
        description = (TextView)findViewById(R.id.descriptionDetailCourse);
        listLesson= (ListView)findViewById(R.id.listCourseDetail);

        Bundle extras = getIntent().getExtras();
        listaAdapter();
        nombre.setText(extras.getString("Nombre"));
        description.setText(extras.getString("Descripcion"));

        registerForContextMenu(listLesson);
    }



    public void listaAdapter() {
        listLessons.clear();

        sqlite = new SQLite(CourseActivity.this.getBaseContext());
        db = sqlite.getReadableDatabase();

        Bundle extrass = getIntent().getExtras();
        String Cats = extrass.getString("Nombre");
        String Sql = "SELECT idCourse FROM Course Where nameCourse='"+Cats+"' ;";

        Cursor cc = db.rawQuery(Sql, null);
        int idC= 0 ;

        if (cc.moveToFirst()) {
            do {
                idC= cc.getInt(0);
            } while (cc.moveToNext());
        }
        db.close();
        sqlite = new SQLite(CourseActivity.this.getBaseContext());
        db = sqlite.getReadableDatabase();

        Sql = "SELECT * FROM Lesson WHERE idCourse="+idC+";";

        Cursor ccu = db.rawQuery(Sql, null);
        Lesson obj;
        if (ccu.moveToFirst()) {
            do {

                obj = new Lesson();
                obj.setName(ccu.getString(1));
                obj.setDescription(ccu.getString(2));
                obj.setReferencesUrl(ccu.getString(3));
                obj.setUrlVideo(ccu.getString(4));
                listLessons.add(obj);

            } while (cc.moveToNext());
        }

        db.close();

        AdaptadorCourses adapter = new AdaptadorCourses(CourseActivity.this, listLessons);
        listLesson.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class AdaptadorCourses extends ArrayAdapter<Lesson> {
        public AdaptadorCourses(Context context,  ArrayList<Lesson> datos){
            super(context, R.layout.listitem_course_lesson, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.listitem_course_lesson, null);

            TextView name = (TextView)item.findViewById(R.id.nameCourseCat);
            TextView desc = (TextView)item.findViewById(R.id.descriptionCourseCat);
            TextView ref = (TextView)item.findViewById(R.id.descriptionCourseCat);
            VideoView urlVideo = (VideoView)item.findViewById(R.id.viedoL);


            String names = listLessons.get(position).getName();
            String description = listLessons.get(position).getDescription();
            String url = listLessons.get(position).getUrlVideo();
            String urlR = listLessons.get(position).getReferencesUrl();

            Uri uri=Uri.parse(url);

            name.setText(names);
            desc.setText(description);
            ref.setText(urlR);
            urlVideo.setVideoURI(uri);
            urlVideo.start();

            return item;
        }
    }
}
