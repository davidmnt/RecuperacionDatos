package com.example.recuperaciondatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         et1 = findViewById(R.id.NombreArchivo);
         et2 = findViewById(R.id.texto);
        Button btn_guardar = findViewById(R.id.BotonGuardar);
        Button btn_recuperar = findViewById(R.id.BotonRecuperar);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                grabar(view);

            }
        });

        btn_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuperar(view);

            }
        });

    }

    public void grabar(View v){
        String nombreArchivo=et1.getText().toString();
        String contenido=et2.getText().toString();
        try{
            //Tenemos que crear la clase OutputStreamWriter y le pasamos como método lo que nos devuelve openFileOutput
            //A este le pasamos el nombre de los archivos y una constante modo private.
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(nombreArchivo, Context.MODE_PRIVATE));
            archivo.write(contenido);
            archivo.flush();
            archivo.close();
            et1.setText("");
            et2.setText("");
            Toast.makeText(getApplicationContext(),"Los datos fueron grabados", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"No se pudo crear el archivo", Toast.LENGTH_LONG).show();
        }
    }

    public void recuperar(View v)
    {
        String nombreArchivo=et1.getText().toString();
        try {
            InputStreamReader archivo=new InputStreamReader(openFileInput(nombreArchivo));
            BufferedReader br= new BufferedReader(archivo);
            String linea=br.readLine();
            String contenido="";
            while (linea!=null){
                contenido=contenido+linea+"\n";
                linea=br.readLine();
            }
            br.close();
            archivo.close();
            et2.setText(contenido);
        } catch (IOException e) {
            Toast.makeText(this,"No existe el archivo",Toast.LENGTH_LONG).show();
        }
    }


}