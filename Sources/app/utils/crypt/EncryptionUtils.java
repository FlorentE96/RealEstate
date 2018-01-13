package app.utils.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import java.nio.charset.StandardCharsets;

/**
 * A utility class that encrypts or decrypts a file.
 * @author www.codejava.net
 *
 */
public class EncryptionUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public static String encrypt(String key, String inputString)
            throws EncryptionException {
            byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
            byte[] outputBytes = doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes);
            return new String(outputBytes, StandardCharsets.UTF_8);
    }

    public static String encrypt(String key, char[] inputString)
            throws EncryptionException {
        byte[] inputBytes = new String(inputString).getBytes(StandardCharsets.UTF_8);
        byte[] outputBytes = doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes);
        return new String(outputBytes, StandardCharsets.UTF_8);
    }

    public static void encrypt(String key, File inputFile, File outputFile)
            throws EncryptionException {
        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        }
        catch(IOException ex)
        {
            throw new EncryptionException("Error opening/writing file", ex);
        }
    }

    public static void encrypt(String key, String inputString, File outputFile, boolean append)
            throws EncryptionException {
        try {
            byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
            byte[] outputBytes = doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile, append);
            outputStream.write(outputBytes);
            outputStream.write("\r\n".getBytes(StandardCharsets.UTF_8));

            outputStream.close();
        }
        catch(IOException ex)
        {
            throw new EncryptionException("Error opening/writing file", ex);
        }
    }

    public static void decrypt(String key, File inputFile, File outputFile)
            throws EncryptionException {
        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            byte[] outputBytes = doCrypto(Cipher.DECRYPT_MODE, key, inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
        }
        catch(IOException ex)
        {
            throw new EncryptionException("Error opening/writing file", ex);
        }
    }

    public static String decrypt(String key, String inputString)
            throws EncryptionException {
            byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
            byte[] outputBytes = doCrypto(Cipher.DECRYPT_MODE, key, inputBytes);
            return new String(outputBytes, StandardCharsets.UTF_8);
    }

//    public static char[] decrypt(String key, String inputString)
//            throws app.utils.crypt.EncryptionException {
//        byte[] inputBytes = inputString.getBytes(StandardCharsets.UTF_8);
//        byte[] outputBytes = doCrypto(Cipher.DECRYPT_MODE, key, inputBytes);
//        return new String(outputBytes, StandardCharsets.UTF_8).toCharArray();
//    }


//    public static void decryptFile2Stream(String key, File inputFile, byte[] outputBytes)
//            throws app.utils.crypt.EncryptionException {
//        try {
//            File file = new File("test.txt");
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuffer.append(line);
//                stringBuffer.append("\n");
//            }
//            fileReader.close();
//            System.out.println("Contents of file:");
//            System.out.println(stringBuffer.toString());
//
//            byte[] outputBytes = doCrypto(Cipher.DECRYPT_MODE, key, inputBytes);
//
//            FileOutputStream outputStream = new FileOutputStream(outputFile, true);
//            outputStream.write(outputBytes);
//
//            outputStream.close();
//        }
//        catch(IOException ex)
//        {
//            throw new app.utils.crypt.EncryptionException("Error opening/writing file", ex);
//        }
//    }

    private static byte[] doCrypto(int cipherMode, String key, byte[] inputBytes) throws EncryptionException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(cipherMode, secretKey, ivspec);

            return cipher.doFinal(inputBytes);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException |InvalidAlgorithmParameterException ex) {
            throw new EncryptionException("Error encrypting/decrypting word", ex);
        }
    }
}