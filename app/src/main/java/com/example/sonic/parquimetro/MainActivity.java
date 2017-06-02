package com.example.sonic.parquimetro;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Button inicio;
    EditText costoInicio, costoFraccion;
    TextView horaInicio, horaFin, costo, tiempoTotal;
    Date tiempoInicial, tiempoFinal;
    boolean activo = false;
    long millisRestantes;
    int costoTotal, horasTotal, minutosTotal, costoInicial, costoFracc;
    Context a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        inicio = (Button) findViewById(R.id.inicio);
        costoInicio = (EditText)findViewById(R.id.costoInicial);
        costoFraccion = (EditText)findViewById(R.id.costoFraccion);
        horaInicio = (TextView)findViewById(R.id.horaInicio);
        horaFin = (TextView)findViewById(R.id.horaFinal);
        costo =(TextView)findViewById(R.id.costo);
        tiempoTotal = (TextView)findViewById(R.id.tiempoTotal);
        a = this;
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activo){
                    tiempoFinal = new Date();
                    horaFin.setText("SALIDA: "+tiempoFinal.getHours()+":"+tiempoFinal.getMinutes());
                    inicio.setText("FIJAR ENTRADA");
                    activo = false;
                    millisRestantes = tiempoFinal.getTime() - tiempoInicial.getTime();
                    horasTotal=(int)TimeUnit.MILLISECONDS.toHours(millisRestantes);
                    minutosTotal=(int)TimeUnit.MILLISECONDS.toMinutes(millisRestantes);
                    tiempoTotal.setText("TRANSCURRIDO: "+horasTotal+":"+(minutosTotal - TimeUnit.HOURS.toMinutes(horasTotal)));
                    costoTotal=costoInicial;
                    if(horasTotal>0){
                        costoTotal+=((minutosTotal-60)/15)*costoFracc;
                        if(((minutosTotal-60)%15)!=0){
                            costoTotal+=costoFracc;
                        }
                    }
                    costo.setText("COSTO: "+costoTotal);
                }else{
                    try{
                        costoInicial = Integer.parseInt(String.valueOf(costoInicio.getText()));
                        costoFracc = Integer.parseInt(String.valueOf(costoFraccion.getText()));
                    tiempoInicial = new Date();
                    horaFin.setText("SALIDA: ");
                    costo.setText("COSTO: ");
                    tiempoTotal.setText("TIEMPO TRANSCURRIDO");
                    horaInicio.setText("ENTRADA: "+tiempoInicial.getHours()+":"+tiempoInicial.getMinutes());
                    inicio.setText("MARCAR SALIDA");
                    activo = true;


                    }catch (Exception e){
                        Toast.makeText(a,"SIN DATOS", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
