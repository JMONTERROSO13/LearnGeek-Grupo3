package gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JMONTERROSO on 14/06/2015.
 */
public class SQLite extends SQLiteOpenHelper {

    private static SQLiteDatabase.CursorFactory factory = null;

    private String sqlUser = "CREATE TABLE User (idUser INTEGER PRIMARY KEY, userName TEXT, email TEXT, password TEXT, name TEXT)";
    private String sqlCategory = "CREATE TABLE Category(idCategory INTEGER PRIMARY KEY, nameCategory TEXT,descriptionCategory TEXT)";
    private String sqlCourse = "CREATE TABLE Course (idCourse INTEGER PRIMARY KEY, nameCourse TEXT, descriptionCourse TEXT, idCategory INTEGER, FOREIGN KEY (idCategory) REFERENCES Category(idCategory));";
   private String sqlLesson = "CREATE TABLE Lesson (idLesson INTEGER PRIMARY KEY, name TEXT, descriptionLesson TEXT, referencesLesson TEXT, urlVideo TEXT, idCourse INTEGER, FOREIGN KEY(idCourse) REFERENCES Course(idCourse));";
    private String sqlCourseAssignment = "CREATE TABLE CourseAssignment(idCourseAssignment INTEGER PRIMARY KEY, idCourse INTEGER, idUser INTEGER,FOREIGN KEY(idCourse) REFERENCES Course(idCourse),FOREIGN KEY(idUser) REFERENCES User(idUser));";

    public SQLite(Context context) {
        super(context, "LearnGeekKinal", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlUser);
        db.execSQL(sqlCategory);
        db.execSQL(sqlCourse);
        db.execSQL(sqlLesson);
        db.execSQL(sqlCourseAssignment);
            }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Category");
        db.execSQL("DROP TABLE IF EXISTS Course");
        db.execSQL("DROP TABLE IF EXISTS Lesson");
        db.execSQL("DROP TABLE IF EXISTS CourseAssignment");

        db.execSQL(sqlUser);
        db.execSQL(sqlCategory);
        db.execSQL(sqlCourse);
        db.execSQL(sqlLesson);
        db.execSQL(sqlCourseAssignment);
    }
}
