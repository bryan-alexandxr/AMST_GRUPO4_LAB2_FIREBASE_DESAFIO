package com.xander.asmt_grupo4_lab2_cloud_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PerfilUsuario extends AppCompatActivity {
    ImageView img;
    TextView txt_id, txt_nombre, txt_correo, txt_num, txt_FBID, txt_ver;
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        img = findViewById(R.id.imv_foto);
        txt_id = findViewById(R.id.txt_userId);
        txt_nombre = findViewById(R.id.txt_nombre);
        txt_correo = findViewById(R.id.txt_correo);
        txt_num = findViewById(R.id.txt_num);
        txt_FBID = findViewById(R.id.txt_FBID);
        txt_ver = findViewById(R.id.txt_ver);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (user != null) {
            String id = acct.getId();
            String name = user.getDisplayName();
            String gmail = user.getEmail();
            String numero = user.getPhoneNumber();
            String fbid = user.getUid();
            String ver = "No";
            if (user.isEmailVerified()) {
                ver = "Sí";
            }

            txt_id.setText("ID: " + id);
            txt_nombre.setText("NOMBRE: " + name);
            txt_correo.setText("CORREO: " + gmail);
            txt_num.setText("NÚMERO: " + numero);
            txt_FBID.setText("FIREBASE ID: " + fbid);
            txt_ver.setText("¿ESTÁ VERIFICADO?: " + ver);

            Picasso.get().load(user.getPhotoUrl()).placeholder(R.drawable.google).into(img);
        } else {
            getApplicationContext();

        }
    }
    public void serra(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}