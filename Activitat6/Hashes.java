
import java.security.MessageDigest;
import java.util.HexFormat;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public int npass = 0;
    private final char[] CHARSET = "abcdefABCDEF1234567890!".toCharArray();

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] digest = md.digest(pw.getBytes());
        return HexFormat.of().formatHex(digest);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iterations = 10000; // luego lo subimos a 10K ahora esto es tmp en tiempos de ejecucion mas rapidas.
        int keyLength = 512;
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        SecretKey key = skf.generateSecret(spec);
        byte[] encoded = key.getEncoded();
        return HexFormat.of().formatHex(encoded);
    }

    public String forcaBruta(String alg, String has, String alt) throws Exception {
        this.npass = 0;
        final int maxLen = 6;
        final int base = CHARSET.length;

        for (int len = maxLen; len >= 1; len--) {
            int[] idx = new int[len];
            boolean done = false;

            while (!done) {

                StringBuilder sb = new StringBuilder(len);
                for (int i = 0; i < len; i++)
                    sb.append(CHARSET[idx[i]]);
                String candidate = sb.toString();

                npass++;
                String hash;
                if ("SHA-512".equalsIgnoreCase(alg)) {
                    hash = getSHA512AmbSalt(candidate, alt);
                } else {
                    hash = getPBKDF2AmbSalt(candidate, alt);
                }

                if (hash.equalsIgnoreCase(has)) {
                    return candidate;
                }

                int pos = len - 1;
                while (pos >= 0) {
                    idx[pos]++;
                    if (idx[pos] < base)
                        break;
                    idx[pos] = 0;
                    pos--;
                }
                if (pos < 0)
                    done = true;
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2) {
        long millis = Math.abs(t2 - t1);
        long days = millis / (24L * 3600L * 1000L);
        millis %= (24L * 3600L * 1000L);
        long hours = millis / (3600L * 1000L);
        millis %= (3600L * 1000L);
        long minutes = millis / (60L * 1000L);
        millis %= (60L * 1000L);
        long seconds = millis / 1000L;
        millis %= 1000L;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "bbbbb";
        Hashes h = new Hashes();

        String[] aHashes = {
                h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algoritmes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.print("==============================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.print("------------------------------\n");
            System.out.print("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.print("------------------------------\n\n");
        }
    }

}
