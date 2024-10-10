package AuthenticationModel.PasswordManagement;

import org.mindrot.jbcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoManager {

    public static String encryptUsingBase64(String key)
    {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(key.getBytes(StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptUsingBase64(String key) throws IllegalArgumentException
    {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(key)); // after decoding we get byte value so we convert it to string
    }

    public static String encryptPasswordUsingHash(String password) throws IllegalArgumentException
    {
       return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword)
    {
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }
}
