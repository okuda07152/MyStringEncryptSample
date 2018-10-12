package com.example.okuda0715.myaesencryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {

    private static final String KEY = "YKo83n14SWf7o8G5";
    private static final String ALGORITHM = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String source1 = "This is a cipher test source.";
            String source2 = "これは暗号化テストです。";

            String encrypt1 = CipherManager.encrypt(source1, KEY);
            String encrypt2 = CipherManager.encrypt(source2, KEY);
            String decrypt1 = CipherManager.decrypt(encrypt1, KEY);
            String decrypt2 = CipherManager.decrypt(encrypt2, KEY);

            Log.v("log★","Source1: " + source1);
            Log.v("log★","Encrypt1: " + encrypt1);
            Log.v("log★","Decrypt1: " + decrypt1);
            Log.v("log★","");
            Log.v("log★","Source2: " + source2);
            Log.v("log★","Encrypt2: " + encrypt2);
            Log.v("log★","Decrypt2: " + decrypt2);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Log.e("MyAESEncrypt",ex.toString());
        }
    }
}
