package ab3.impl.MaroltLiebhart;

/****************************
 * Created by Michael Marolt *
 *****************************/

public class LZW_Decoder {
    byte[] compressedData;
    private int codeWordLength;
    private String binaryData;
    private String[] dictionary;

    public LZW_Decoder(byte[] compressedData) {
        codeWordLength = compressedData[0];
        dictionary = new String[1<<codeWordLength];
        System.out.println(dictionary.length);
        for (int i = 0; i < 255; i++) {
            dictionary[i] = Character.toString(((char)i));
        }
        System.out.println(dictionary[97]);
        codeWordLength = compressedData[0];
        binaryData = "";
        for (int i = 1; i < compressedData.length; i++) {
            binaryData += toBinaryString(compressedData[i]);
        }
        System.out.println(binaryData);
    }

    private static String toBinaryString(byte n) {
        StringBuilder sb = new StringBuilder("00000000");
        for (int bit = 0; bit < 8; bit++) {
            if (((n >> bit) & 1) > 0) {
                sb.setCharAt(7 - bit, '1');
            }
        }
        return sb.toString();
    }

}
