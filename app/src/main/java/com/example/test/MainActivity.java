package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText user ,pass;
    FirebaseAuth auth;
    ImageView imageView;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser!=null){Intent intent = new Intent(MainActivity.this, ChatMenu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);}

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        Picasso.get().load(R.drawable.logocolor).into(imageView);
        auth = FirebaseAuth.getInstance();
        button=(Button)findViewById(R.id.main_login_btn);
        user=(EditText)findViewById(R.id.main_user_et) ;
        pass=(EditText)findViewById(R.id.main_pass_et);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("جاري التحميل ...");
                pd.show();
                String userString=user.getText().toString();
                String passString=pass.getText().toString();
                if (TextUtils.isEmpty(userString) || TextUtils.isEmpty(passString)){
                    Toast.makeText(MainActivity.this, "يرجي ادخال اسم المستخدم و كلمة المرور ", Toast.LENGTH_SHORT).show();
                }
                else{
                auth.signInWithEmailAndPassword(userString,passString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, ChatMenu.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            pd.cancel();
                        } else {
                            Toast.makeText(MainActivity.this, "البيانات خاطئة ", Toast.LENGTH_SHORT).show();
                            pd.cancel();

                        }


                    }
                });



            }
            }
        });
    }


}
