/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shachecker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author root
 */
public class SHAChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: SHAChecker filename [compare-sha]");
        } else {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                File file = new File(args[0]);

                byte[] data = new byte[(int) file.length()];
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    fileInputStream.read(data);
                }

                byte[] digest = md.digest(data);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    sb.append(String.format("%02x", digest[i]));
                }
                
                String result = sb.toString().toUpperCase();

                System.out.println(result + " (File)");
                
                if(args.length == 2) {
                    String desiredSHA = args[1].toUpperCase();
                    System.out.println(desiredSHA + " (Desired)");
                    if(result.equals(desiredSHA)) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Wrong!");
                    }
                }
            } catch (NoSuchAlgorithmException | IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }

}
