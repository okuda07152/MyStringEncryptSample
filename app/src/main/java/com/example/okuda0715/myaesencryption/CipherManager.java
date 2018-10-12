package com.example.okuda0715.myaesencryption;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 暗号化・復号化クラス
 */
public class CipherManager {

    // AES,DES等の暗号化方式
    private static String algorithm = "AES";
    // 暗号化・復号化を行うためのCipherクラス
    private static Cipher cipher;

    /**
     * 文字列を暗号化する。
     *
     * @param source    暗号化したい文字列
     * @param key       暗号化キー
     * @return 暗号化後の文字列
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String source, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 暗号化・復号化を行うためのCipherインスタンスを取得する
        cipher = Cipher.getInstance(algorithm);
        // 第一引数：暗号化（ENCRYPT_MODE）、復号化（DECRYPT_MODE）
        // 第二引数：暗号化キー
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), algorithm));
        // 暗号化後の文字列
        byte[] encryptedString = cipher.doFinal(source.getBytes());
        // Base64でエンコード
        // Base64にすることでマルチバイト文字が扱えない環境でも暗号化文字列を受け渡しできる。
        // 第二引数：URL_SAFE（URLでも使用可能な文字列でエンコードする）
        byte[] base64EncStr = Base64.encode(encryptedString, Base64.URL_SAFE);
        // byte→String変換
        return new String(base64EncStr);
    }

    /**
     * 暗号化された文字列を復号化する
     *
     * @param encryptSource 復号化したい文字列
     * @param key           復号化キー
     * @return 復号化後の文字列
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String encryptSource, String key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        // 暗号化・復号化を行うためのCipherインスタンスを取得する
        cipher = Cipher.getInstance(algorithm);
        // 第一引数：暗号化（ENCRYPT_MODE）、復号化（DECRYPT_MODE）
        // 第二引数：暗号化キー
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), algorithm));
        // Base64でエンコードされた暗号文字列をString→byte変換して取得
        byte[] base64EncByte = encryptSource.getBytes();
        // Base64エンコードをデコードする
        byte[] encryptedByte = Base64.decode(base64EncByte, Base64.URL_SAFE);
        // 暗号文字列を復号する
        byte[] decryptedByte = cipher.doFinal(encryptedByte);
        // byte→String変換
        return new String(decryptedByte);
    }
}
