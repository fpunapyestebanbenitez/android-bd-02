package bd2.electiva1.fpuna.py.basededatosenandroid2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtNombre;

    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Obtenemos las referencias a los controles
        txtCodigo = (EditText) findViewById(R.id.txtReg);
        txtNombre = (EditText) findViewById(R.id.txtVal);

        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        Log.d("DB: ","DBUsuarios creada.");

        db = usdbh.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();

                //Alternativa 1: m�todo sqlExec()
                //String sql = "INSERT INTO Usuarios (codigo,nombre) VALUES ('" + cod + "','" + nom + "') ";
                //db.execSQL(sql);

                //Alternativa 2: m�todo insert()
                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("codigo", cod);
                nuevoRegistro.put("nombre", nom);
                db.insert("Usuarios", null, nuevoRegistro);
                Log.d("usuario registrado: ", nom);
                Toast.makeText(MainActivity.this, "Usuario: " + nom + " registrado.", Toast.LENGTH_SHORT).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();
                String nom = txtNombre.getText().toString();

                //Alternativa 1: m�todo sqlExec()
                //String sql = "UPDATE Usuarios SET nombre='" + nom + "' WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: m�todo update()
                ContentValues valores = new ContentValues();
                valores.put("nombre", nom);
                db.update("Usuarios", valores, "codigo=" + cod, null);

                Toast.makeText(MainActivity.this, "Usuario: " + nom + " actualizado.", Toast.LENGTH_SHORT).show();
                Log.d("usuario actualizado: ", nom);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Recuperamos los valores de los campos de texto
                String cod = txtCodigo.getText().toString();

                //Alternativa 1: m�todo sqlExec()
                //String sql = "DELETE FROM Usuarios WHERE codigo=" + cod;
                //db.execSQL(sql);

                //Alternativa 2: m�todo delete()
                db.delete("Usuarios", "codigo=" + cod, null);

                Log.d("usuario eliminado: ", cod);

                Toast.makeText(MainActivity.this, "Usuario: " + cod + " eliminaedo.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
