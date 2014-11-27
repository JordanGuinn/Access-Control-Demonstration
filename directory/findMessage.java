import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Jordan Guinn
 * Takes an HTML file with a covert message as its sole argument 
 * and extracts the covert message.  It then writes the message
 * to a new file.
 */
public class findMessage {

    public static void main(String[] args){

    	FileInputStream fileInputStream=null;
        File file = new File(args[0]);

        byte[] htmlbytes = new byte[(int) file.length()];
        String htmlBin[] = new String[(int) file.length()];

        char[] alphabet = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        try {
            // Read in both files to use
	    	fileInputStream = new FileInputStream(file);
	    	fileInputStream.read(htmlbytes);
            fileInputStream.close();
 
            int ascii_chars[] = new int[htmlbytes.length];
            Character message[] = new Character[(int) file.length()];
            int count = htmlbytes.length - 1;
            int char_pos = 0;

            // Convert HTML file to binary data
	    	for (int i = 0; i < htmlbytes.length; i++) {
                htmlBin[i] = String.format("%8s", Integer.toBinaryString(htmlbytes[i] & 0xFF)).replace(' ', '0');       	
            }

            // Convert binary data to ASCII characters
            for (int i = 0; i < htmlbytes.length; i++) {
                ascii_chars[i] = Integer.parseInt(htmlBin[i], 2);;
            }

            // Get letters of messageby adding up numbers of consecutive 
            // blank spaces and matching number to alphabet array
            if(ascii_chars[count] == 160){
                while(ascii_chars[count] != 10){
                    count--;
                    int ascii_val = 0;
                    while(ascii_chars[count] != 160 && ascii_chars[count] != 10)
                    {
                        if(ascii_chars[count] == 32 || ascii_chars[count] == 194)
                        {
                            ascii_val++;
                        }
                        count--;
                    }
                    message[char_pos] = alphabet[ascii_val - 1];
                    char_pos++;
                }
            }
            // Write message to file
            BufferedWriter writer = new BufferedWriter(new FileWriter("revealed.txt"));
            for ( int i = message.length - 1; i >= 0; i--)
            {      
                if(message[i] != null)
                {
                    writer.write(message[i]);
                }
            }
            writer.close();

        } catch(Exception e){
        	e.printStackTrace();
        }
    }
}