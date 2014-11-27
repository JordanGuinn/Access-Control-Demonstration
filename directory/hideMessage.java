import java.io.*;
import java.util.Scanner;

/**
  * @author Jordan Guinn
 * Takes HTML file as first argument and text file as second argument,
 * at which point it hides the content of the second file into the
 * first file, thereby recreating it
 */

public class hideMessage {

    public static void main(String[] args){

    	FileInputStream fileInputStream=null;

        File file = new File(args[0]);
        File file2 = new File(args[1]);

        byte[] htmlBytes = new byte[(int) file.length()];
        byte[] messageBytes = new byte[(int) file2.length()];

        String htmlBin[] = new String[(int) file.length()];
        String messageBin[] = new String[(int) file2.length()];

        char[] alphabet = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        try {
            // Read in both files to use
	    	fileInputStream = new FileInputStream(file);
	    	fileInputStream.read(htmlBytes);
            fileInputStream = new FileInputStream(file2);
            fileInputStream.read(messageBytes);
            fileInputStream.close();
 
            // Convert HTML file to binary
	    	for (int i = 0; i < htmlBytes.length; i++) {
                htmlBin[i] = String.format("%8s", Integer.toBinaryString(htmlBytes[i] & 0xFF)).replace(' ', '0');       	
            }
            // Convert hidden message to binary
            for (int i = 0; i < messageBytes.length; i++) {
                messageBin[i] = String.format("%8s", Integer.toBinaryString(messageBytes[i] & 0xFF)).replace(' ', '0');
            }

            // Store ASCII values on binary data into array
            int new_html_size = htmlBytes.length;
            int ascii_chars[] = new int[new_html_size + 1000];
            for (int i = 0; i < htmlBytes.length; i++) {
                ascii_chars[i] = Integer.parseInt(htmlBin[i], 2);;
            }

            // Insert blank characters into array of ASCII values
            for(int i = 0; i < file2.length(); i++){
                int ascii_val = 0;
                while((char) messageBytes[i] != alphabet[ascii_val]){
                    ascii_chars[new_html_size] = 32;
                    ascii_val++;
                    new_html_size++;
                }
            ascii_chars[new_html_size++] = 160;
            }

            // Convert ASCII values into actual characters
            Character new_html[] = new Character[new_html_size];
            for (int i = 0; i < new_html_size; i++) {
                new_html[i] = (char) ascii_chars[i];
            }

            // Write characters to file
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0]));
            for ( int i = 0; i < new_html_size; i++)
            {      
                if(new_html[i] != null)
                {
                    writer.write(new_html[i]);
                }
            }
            writer.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}