package AuthenticationModel.PasswordManagement;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static Random random = new SecureRandom();
    private static String specialChars = "!@#$%^&*()_+<>=-?";
    private final static int passwordLength = 10;

    public static String generateRandomPassword()
    {
        try {
            StringBuilder passwordBuilder = new StringBuilder();
            buildRandomPassword(passwordBuilder);
            for (int i = 4; i < passwordLength; i++) {
                int randomPicker = random.nextInt(4);
                switch (randomPicker) {
                    case 0 ->
                            passwordBuilder.append(getRandomCharacterFromRange(65, 90));  // here lambda is used to avoid break
                    case 1 -> passwordBuilder.append(getRandomCharacterFromRange(97, 122));
                    case 2 -> passwordBuilder.append(getRandomCharacterFromRange(48, 57));
                    case 3 -> passwordBuilder.append(specialChars.charAt(random.nextInt(specialChars.length())));
                }
            }
            shufflePassword(passwordBuilder);
            return passwordBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Character getRandomCharacterFromRange(int start , int end)
    {
        return (char)random.nextInt((end-start + 1) + start);
    }

    public static void buildRandomPassword(StringBuilder passwordBuilder)
    {
        passwordBuilder.append(getRandomCharacterFromRange(65,90)); //For Uppercase using Ascii values
        passwordBuilder.append(getRandomCharacterFromRange(97,122)); //For Lowercase using Ascii values
        passwordBuilder.append(getRandomCharacterFromRange(48,57)); //For digits using Ascii values
        passwordBuilder.append(specialChars.charAt(random.nextInt(specialChars.length())));
    }

    public static void shufflePassword(StringBuilder password)
    {
        try {
            for (int i = password.length() - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                char temp = password.charAt(i);
                password.setCharAt(i, password.charAt(j));
                password.setCharAt(j, temp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
