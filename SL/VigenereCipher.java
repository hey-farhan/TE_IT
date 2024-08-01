import java.util.Scanner;

public class VigenereCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            if (Character.isUpperCase(plainChar)) {
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = keyChar - 'A';
                char encryptedChar = (char) ((plainChar - 'A' + shift) % 26 + 'A');
                ciphertext.append(encryptedChar);
                keyIndex++;
            } else {
                ciphertext.append(plainChar); // Preserve spaces and non-alphabetic characters
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            if (Character.isUpperCase(cipherChar)) {
                char keyChar = key.charAt(keyIndex % keyLength);
                int shift = keyChar - 'A';
                char decryptedChar = (char) ((cipherChar - 'A' - shift + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
                keyIndex++;
            } else {
                plaintext.append(cipherChar); // Preserve spaces and non-alphabetic characters
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext (uppercase letters and spaces only): ");
        String plaintext = scanner.nextLine().toUpperCase(); // Convert to uppercase

        System.out.print("Enter key (uppercase letters only): ");
        String key = scanner.nextLine().toUpperCase(); // Convert to uppercase

        // Remove any non-alphabetic characters from the key
        key = key.replaceAll("[^A-Z]", "");

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);

        scanner.close();
    }
}
