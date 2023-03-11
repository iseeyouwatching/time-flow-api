package ru.hits.timeflowapi.service;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hits.timeflowapi.exception.InternalException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Сервис для шифрования и дешифрования строк.
 */
@Service
@Slf4j
public class StringCryptographer {

    @Value("${secret-key}")
    private String strKey;


    /**
     * Метод для шифрования строки.
     *
     * @param value незашифрованная строка.
     * @return зашифрованная строка.
     */
    public String encryptString(String value) {
        try {
            byte[] encryptedBytes = encrypt(value);
            byte[] encodedAndEncryptedBytes = encode(encryptedBytes);
            return bytesToString(encodedAndEncryptedBytes);
        } catch (
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException exception
        ) {
            log.error(exception.getMessage(), exception);
            throw new InternalException("Ошибка при шифровании строки.");
        }

    }

    /**
     * Метод для дешифрования строки.
     *
     * @param value зашифрованная строка.
     * @return расшифрованная строка.
     */
    public String decryptString(String value) {
        try {
            byte[] decodedBytes = decode(value.getBytes(StandardCharsets.UTF_8));
            byte[] decryptedBytes = decrypt(decodedBytes);
            return bytesToString(decryptedBytes);
        } catch (
                NoSuchPaddingException |
                IllegalBlockSizeException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException exception
        ) {
            log.error(exception.getMessage(), exception);
            throw new InternalException("Ошибка при дешифровании строки.");
        }
    }

    /**
     * Метод для шифрования строки.
     *
     * @param value строка, которую нужно зашифровать.
     * @return зашифрованный массив байт, полученный из строки.
     */
    private byte[] encrypt(String value) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Метод для дешифрования массива байт.
     *
     * @param bytes зашифрованный массив байт.
     * @return дешифрованный массив байт.
     */
    private byte[] decrypt(byte[] bytes) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(bytes);
    }

    /**
     * Метод для кодирования массива байт.
     *
     * @param bytes незакодированный массив байт.
     * @return закодированный массив байт.
     */
    private byte[] encode(byte[] bytes) {
        return Base64.encode(bytes);
    }

    /**
     * Метод для декодирования массива байт.
     *
     * @param bytes закодированный массив байт.
     * @return декодированный массив байт.
     */
    private byte[] decode(byte[] bytes) {
        return Base64.decode(bytes);
    }

    /**
     * Метод для перевода массива байт в строку.
     *
     * @param bytes массив байт.
     * @return строка, построенная из символов, полученных из байтов массива.
     */
    private String bytesToString(byte[] bytes) {
        StringBuilder resultString = new StringBuilder();

        for (byte currentByte : bytes) {
            resultString.append((char) currentByte);
        }

        return resultString.toString();
    }

}
