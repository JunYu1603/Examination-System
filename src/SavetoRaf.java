
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junyu
 */
public class SavetoRaf {
    
    private static final int data_size = 104;
    private static final int name_size =20;
    private static final int password_size = 6;
    private static final int gender_size = 6;
    private static final int nation_size = 18;
    private String name, pwd, gender, nation;
    private int birthyear;
    private File cf = new File("./data", "candidate.txt");
    
    
    public void savetoraf() throws FileNotFoundException, IOException{
        RandomAccessFile rafFile = new RandomAccessFile("candidate.dat", "rw");
        Scanner scan;
        
        try {
            scan = new Scanner(cf);

            while (scan.hasNextLine()) {
                String aLine = scan.nextLine();
                Scanner sline = new Scanner(aLine);
                sline.useDelimiter(",");
                while (sline.hasNext()) {
                    name = sline.next();
                    pwd = sline.next();
                    writeString(rafFile, name, name_size);
                    writeString(rafFile, pwd, password_size);
                }
                sline.close();

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read " + cf + " not found!");
        }
        
    }
    
    public static void writeString(RandomAccessFile file, String text, int fixedSize) throws IOException {
        int size = text.length();
        if (size <= fixedSize) {
            file.writeChars(text);
            for (int i = size; i < fixedSize; i++) {
                file.writeChar(' ');
            }
        } else {
            file.writeChars(text.substring(0, fixedSize));
        }
    }
    
}
