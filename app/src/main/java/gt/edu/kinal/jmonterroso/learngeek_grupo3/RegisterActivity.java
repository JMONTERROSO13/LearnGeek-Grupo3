package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


public class RegisterActivity extends ActionBarActivity {

    private Button btnlog;
    private Button btnregit;
    private EditText userName;
    private EditText password;
    private EditText password2;
    private EditText email;
    private EditText nameUsers;

    private SQLiteDatabase db;
    private SQLite sqlite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnlog = (Button)findViewById(R.id.logins);
        btnregit = (Button)findViewById(R.id.regit);
        userName = (EditText)findViewById(R.id.userText);
        password = (EditText)findViewById(R.id.passText);
        password2 = (EditText)findViewById(R.id.passText2);
        email = (EditText)findViewById(R.id.emailText);
        nameUsers= (EditText)findViewById(R.id.nameText);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Regresar al Login", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnregit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().length() > 0){
                    if (password.getText().length() > 0){
                        if (password2.getText().length() > 0){
                            if (email.getText().length() > 0){
                                    if(nameUsers.getText().length()>0){
                                        if (password.getText().toString().equals(password2.getText().toString())){

                                            sqlite = new SQLite(getBaseContext());
                                            db = sqlite.getReadableDatabase();

                                            String Sql = "SELECT * FROM User WHERE userName='"+userName.getText().toString()+"'";

                                            Cursor cc = db.rawQuery(Sql, null);
                                            int exists = 0;
                                            if (cc.moveToFirst())
                                            {
                                                do {
                                                    exists++;
                                                } while (cc.moveToNext());
                                            }
                                            db.close();
                                            if (exists <= 0 ){
                                                ContentValues users = new ContentValues();
                                                sqlite = new SQLite(getBaseContext());
                                                db = sqlite.getWritableDatabase();
                                                try{
                                                    sqlite = new SQLite(getBaseContext());
                                                    db = sqlite.getWritableDatabase();


                                                    //categorias
                                                    db.execSQL("INSERT INTO Category VALUES (null, 'APLICACIONES WEB' ,  'PROGRAMACION ORIENTADA EN INTERNET Y NAVEGACION');");
                                                    db.execSQL("INSERT INTO Category VALUES (null, 'APLICACIONES MOVILES' ,  'PROGRAMACION ORIENTADA EN DISPOSITIVOS MOVILES')");
                                                    db.execSQL("INSERT INTO Category VALUES (null, 'APLICACIONES DE ESCRITORIO' , 'PROGRAMACION ORIENTADA EN OS NATIVOS')");

                                                    //Cursos
                                                    db.execSQL("INSERT INTO Course VALUES (null, 'JAVA' , 'PROGRAMACION ORIENTADA EN OBJETOS INICIADA POR SUN Y ACTUALMENTE DE ORACLE',3);");
                                                    db.execSQL("INSERT INTO Course VALUES (null, 'PHP' , 'PROGRAMACION EN SERVIDORES Y ACCECIBILIDAD',1)");
                                                    db.execSQL("INSERT INTO Course VALUES (null, 'VB.NET' , 'LENGUAJE DE MICROSOFT QUE IMPLEMENTA OTRAS EXTENSIONES', 2)");
                                                    db.execSQL("INSERT INTO Course VALUES (null, 'HTML5' , 'DISEÑO Y PRGORMACION DE INTERNET',1)");
                                                    db.execSQL("INSERT INTO Course VALUES (null, 'C#' , 'PROGRAMACION NATIVA ',2)");

                                                    db.execSQL("INSERT INTO Lesson VALUES (null, 'JAVA INTRO' , 'Introducción a Java', 'Referencia: Oracle.com', 'https://www.youtube.com/watch?v=QgSeDCmB-qQ&list=PLw8RQJQ8K1yQDqPyDRzt-h8YlBj96OwMP&index=1', 1)");
                                                    users.put("userName", userName.getText().toString());
                                                    users.put("password", password.getText().toString());
                                                    users.put("email", email.getText().toString());
                                                    users.put("name", nameUsers.getText().toString());

                                                    db.insert("User", null, users);
                                                    db.close();

                                                    Intent intent =  new Intent(RegisterActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                    RegisterActivity.this.finish();
                                                } catch (Exception e) {
                                                    db.close();
                                                    Toast toast = Toast.makeText(getApplicationContext(),"3)" + e.toString(), Toast.LENGTH_SHORT);
                                                    toast.show();
                                                }
                                            }else
                                            {
                                                Toast.makeText(getApplicationContext(),"El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Las contraseñas no son inguales", Toast.LENGTH_SHORT).show();
                                        }
                                }else{
                                    Toast.makeText(getApplicationContext(),"El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Ingrese Email", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(),"Confirme su contraseña", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Ingrese un nombre de usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
