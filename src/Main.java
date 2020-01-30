import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Main {
    private static Main Crypto;

    static void fileProcessor(int cipherMode, String key, File inputFile, File outputFile){
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(cipherMode, secretKey);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Desktop desktop=Desktop.getDesktop();
        String key = "This is a secret";
        //String key = "This is a key";
        File inputFile = new File("D:\\java project\\AES\\src\\hello.txt");
        File encryptedFile = new File("D:\\java project\\AES\\src\\encry.txt");
        File decryptedFile = new File("D:\\java project\\AES\\src\\decry.txt");

        try {
            Crypto.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
            Crypto.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
            System.out.println(" File Encrypted ! Success");
            System.out.println("TO decry press 1");
            System.out.println("TO see original file press 2 ");
            System.out.println("TO see encrypted file press 3 ");
            int choice =in.nextInt();

            switch (choice){
                case 1:

                    File decry=new File("D:\\java project\\AES\\src\\decry.txt");
                    desktop.open(decry);
                    break;

                case 2:
                    File ogfile=new File("D:\\java project\\AES\\src\\hello.txt");
                    desktop.open(ogfile);
                    break;
                case 3:
                    File encryfile=new File("D:\\java project\\AES\\src\\encry.txt");
                    desktop.open(encryfile);
                    break;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}

