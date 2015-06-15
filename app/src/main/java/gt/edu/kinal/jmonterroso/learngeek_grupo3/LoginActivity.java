package gt.edu.kinal.jmonterroso.learngeek_grupo3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import gt.edu.kinal.jmonterroso.learngeek_grupo3.helpers.SQLite;


public class LoginActivity extends ActionBarActivity {

    private Button btnLogin;
    private Button btnRegis;
    private EditText userName;
    private EditText pass_text;
    private CheckBox cRemember;
    private String Password;
    private SQLiteDatabase db;
    private SQLite userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button)findViewById(R.id.logins);
        btnRegis = (Button)findViewById(R.id.registers);
        userName = (EditText)findViewById(R.id.userText);
        pass_text = (EditText)findViewById(R.id.passText);
        cRemember = (CheckBox)findViewById(R.id.remember);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userDB = new SQLite(getBaseContext());
                db = userDB.getReadableDatabase();
                String sql;
                sql = "Select password from User Where userName='"+userName.getText().toString()+"'";
                Cursor c = db.rawQuery(sql,null);

                int valida = 0;
                if (c.moveToFirst()){
                    do {
                        Password = c.getString(0);
                        valida++;
                    }while(c.moveToNext());
                }
                db.close();
                if (userName.getText().length() > 0) {
                    if (pass_text.getText().length() > 0) {
                        if (valida > 0) {
                            if (Password.equals(pass_text.getText().toString())) {

                                SharedPreferences preferences = getSharedPreferences( getString(R.string.sharedClass), Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(getString(R.string.userRemembered), userName.getText().toString());
                                Toast.makeText(getApplicationContext(), "Bienvenido " + userName.getText().toString(), Toast.LENGTH_SHORT).show();
                                editor.putBoolean(getString(R.string.isRemembered), cRemember.isChecked());
                                editor.apply();
                                Intent intentLogin = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentLogin);
                                LoginActivity.this.finish();

                            } else {
                                Toast.makeText(LoginActivity.this, "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuario No Registrado", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "Ingrese su Contraseña", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Ingrese su Nombre de Usuario", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
