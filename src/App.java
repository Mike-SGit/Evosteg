
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// import jdk.internal.org.jline.reader.Widget;

import java.awt.*;


public class App {

    public static void encode(String pngname) {
        Scanner input = new Scanner(System.in);

        System.out.println("message to encode: ");
        String message = input.nextLine();  
        message = "!!" + message + "!!";


        //convert string to array of bytes
        byte[] byteString = message.getBytes();

        

        File f = new File(pngname);
		try {
            BufferedImage inImage = ImageIO.read(f);

            int height = inImage.getHeight();
            int width = inImage.getWidth();
            int x = 0;
            int y = 0;

            int bitmask = 0x7F;

            for(byte charByte : byteString){
                    //11111111
                    //00000000
                    //00000000
                for(int i = 0; i < 8; i++){
                    Color c = new Color(inImage.getRGB(x,y));
                    
                    int red = c.getRed();
                    int green = c.getGreen();
                    byte blue = (byte)c.getBlue();

                    System.out.println("blue before : " + blue);

                    Byte temp = (byte)(charByte >> 7);
                    System.out.println("charByte : " + charByte);
                    System.out.println("temp : " + temp);
                    //clear least significant bit by & 0xFE
                    blue = (byte)((blue & 0xFE)| temp);
                    charByte = (byte)(charByte << 1);
                    // System.out.println(blue);
                    System.out.println("setting blue to : " + blue);
                    Color newColor = new Color(red,green,blue);

                    inImage.setRGB(x,y,newColor.getRGB());
                    y++;
                    if(y == width){
                        y = 0;
                        x++;
                    }
                }
            }

            String outname = pngname + "Encoded";
            File outputfile = new File(outname);
            ImageIO.write(inImage, "png", outputfile);

            } catch (IOException e) {
                e.printStackTrace();
            }

            

    }

    public static void decode(String pngname) {

        // File f = new File(pngname);
		// try {
        //     BufferedImage inImage = ImageIO.read(f);
        // }

        return;


    }
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        int option = 9;
        String pngname;

        System.out.println("Enter 1 for message encoding or 0 for image decoding: ");
        option = input.nextInt();
        // String bufferconsume = input.nextLine1(); 

        if (option != 1 && option != 0) return;

        Scanner input1 = new Scanner(System.in);

        System.out.println("png file name: ");
        pngname = input1.nextLine();  

        

        if (option == 1){
            encode(pngname);
            return;
        }
        if (option == 0){
            decode(pngname);
            return;
        }
    


    }
}



//1110 
//0001 
//
//0001  
//1100

//1010
//