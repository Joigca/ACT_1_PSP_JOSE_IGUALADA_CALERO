package dam2.act1_psp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    static Button BtnEnviar;
    static RadioGroup RGroupSex;
    static EditText TxtNombre;
    static TextView datosRecibidos;

    Toast toast;

    final int SUBACTIVITY_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnEnviar = (Button) findViewById(R.id.BtnEnviar);
        RGroupSex = (RadioGroup) findViewById(R.id.rGrp);
        TxtNombre = (EditText) findViewById(R.id.txtNombre);
        datosRecibidos = (TextView) findViewById(R.id.textView2);

        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DatosCorrectos() == false){
                    toast = toast.makeText(getApplicationContext(), "Inserte correctamente los datos.", Toast.LENGTH_LONG);
                }else{
                    /*
                    Intent AC_segundaVentana = new Intent(MainActivity.this, SegundaVentana.class);
                    startActivity(AC_segundaVentana);
                    */

                    Intent i = new Intent (getApplicationContext(), SegundaVentana.class);
                    startActivityForResult(i, SUBACTIVITY_1);
                }
            }
        });
    }

    public boolean DatosCorrectos(){

        if(TxtNombre.getText().toString() == null || RGroupSex.getCheckedRadioButtonId() == -1){
            return false;
        }else{
            return true;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SUBACTIVITY_1:
                gestionaSubActivity1(resultCode, data);
                break;
        }
    }

    public void gestionaSubActivity1(int resultCode, Intent data){

        if (resultCode == RESULT_OK){
            String mensaje = "";
            Bundle b = data.getExtras();
            int edad = b.getInt("Edad");
            if(edad < 18) mensaje = new String("Estás hecho un chaval");
            if((edad >= 18) && (edad < 25)) mensaje = new String("Como un toro");
            if(edad > 25) mensaje = new String("ai ai ai..");
            datosRecibidos.setText(mensaje);
        }else{
            toast = toast.makeText(getApplicationContext(), "Algo ha fallado", Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
