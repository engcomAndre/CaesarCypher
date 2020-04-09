package encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class Encrypt {
    public static final List<Character> noMapChars = Arrays.asList('.',' ','0','1','2','3','4','5','6','7','8','9');

    public static String CaesarDecrypt(String cipherText, Integer role) {
        cipherText = cipherText.toLowerCase();
        StringBuilder res = new StringBuilder();

        for(char ch : cipherText.toLowerCase().toCharArray()){

            int chInt = ((int)ch  - role);

            if(chInt < 97) chInt += 26;
            if(chInt > 122) chInt -= 26;

            res.append(noMapChars.contains(ch) ? ch : (char)(chInt));
        }
        return res.toString();
    }

    public static String SHA1Encrypt(String txt) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(txt.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
