package com.example.uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    RequestQueue requestQueue;
    EditText textusername;
    EditText textPassword;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue = Volley.newRequestQueue(this);
        textusername =findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        btn = findViewById(R.id.btnlogin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String usename = textusername.getText().toString();
        String password = textPassword.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST,
                "http://192.168.5.202/UAS/loginmahasantri.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("ok")){
                    startActivity(new Intent(login.this,MainActivity.class));
                    Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Response"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> data = new HashMap<>();
                data.put("nama",usename);
                data.put("password",password);
                return data;
            }
        };
        requestQueue.add(request);

    }

}