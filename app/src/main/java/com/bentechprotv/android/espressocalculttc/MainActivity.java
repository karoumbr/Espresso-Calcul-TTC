package com.bentechprotv.android.espressocalculttc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    Button btnTTC, btnVid;
    TextView txtTVA, txtTTC;
    EditText edtHT,edtTVA,edtQTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTTC = (Button) findViewById(R.id.btnTTC);
        btnVid = (Button) findViewById(R.id.btnVid);
        txtTVA = (TextView) findViewById(R.id.txtTVA);
        txtTTC = (TextView) findViewById(R.id.txtTTC);
        edtHT = (EditText) findViewById(R.id.edtHT);
        edtTVA = (EditText) findViewById(R.id.edtTVA);
        edtQTE = (EditText) findViewById(R.id.edtQTE);
        btnTTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtHT.getText().toString().equals(""))
                { Toast.makeText(getApplicationContext(),"il faut saisir le prix hors taxes",Toast.LENGTH_LONG).show();
                   // return;
                    edtHT.setText("1");
                }
                    else if (Float.valueOf(edtHT.getText().toString()) < 0.0){
                    edtHT.setText("0");
                }
                if (Float.valueOf(edtTVA.getText().toString()) < 0.0){
                    edtTVA.setText("0");
                }

                if (edtQTE.getText().toString().equals("")){
                    edtQTE.setText("1");
                } else if (Integer.valueOf(edtQTE.getText().toString()) <= 0 ){
                    edtQTE.setText("1");
                }

                // calcul du montant TTC et du total de TVA
                //total ttc = (montant HT + (montant HT * (taux tva/100)))* quantité
                Float mTVA,mTTC;
              mTTC = ((Float.parseFloat(edtHT.getText().toString()) + Float.parseFloat(edtHT.getText().toString()) * (Float.parseFloat(edtTVA.getText().toString())/100)) * Integer.parseInt(edtQTE.getText().toString()));

                //arrondir le résultat : 3 chiffres après la virgule
                DecimalFormat df = new DecimalFormat("#.000");
                txtTTC.setText(df.format(mTTC));
                //total tva =  (montant HT * (taux tva/100))* quantité
                mTVA = (( Float.parseFloat(edtHT.getText().toString()) * (Float.parseFloat(edtTVA.getText().toString())/100)) * Integer.parseInt(edtQTE.getText().toString()));
                //version erronée:
               // mTVA = (( Float.parseFloat(edtHT.getText().toString()) * (Float.parseFloat(edtTVA.getText().toString())/100)) );
                txtTVA.setText(df.format(mTVA));
            }
        });

        btnVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtTTC.setText("");
                txtTVA.setText("");
                edtHT.setText("");
                edtTVA.setText("");
                edtQTE.setText("");
            }
        });

    }
}