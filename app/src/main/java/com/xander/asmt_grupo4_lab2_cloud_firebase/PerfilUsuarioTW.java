package com.xander.asmt_grupo4_lab2_cloud_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

public class PerfilUsuarioTW extends MainActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");

        provider.addCustomParameter("lang", "es");

        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(PerfilUsuarioTW.this, "Inicio de Sesión correcto.", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(PerfilUsuarioTW.this, Twitter.class));
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PerfilUsuarioTW.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
        } else {
            firebaseAuth
                    .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(PerfilUsuarioTW.this, "Inicio de Sesión correcto.", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(PerfilUsuarioTW.this, Twitter.class));


                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PerfilUsuarioTW.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(PerfilUsuarioTW.this, Twitter.class));

                                }
                            });
        }


    }
}

