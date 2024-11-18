
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.*;

public class Steganographer {

    public static void encode(String pngname) {
        Scanner input = new Scanner(System.in);

        System.out.println("message to encode: ");
        String message = input.nextLine();  
        message = "!!" + message + "!!";

        //convert string to array of bytes
        byte[] byteString = message.getBytes();

        //100001
        System.out.println(" byte 0: " + byteString[0]);
        System.out.println(" byte 1: " + byteString[1]);
        //1100001
        System.out.println(" byte 2: " + byteString[2]);


        File f = new File(pngname);
		try {
            BufferedImage inImage = ImageIO.read(f);
            int height = inImage.getHeight();
            int width = inImage.getWidth();
            System.out.println("image height: " + height);
            int x = 0;
            int y = 0;

            int bitmask = 0x7F;

            for(byte charByte : byteString){
                for(int i = 0; i < 8; i++){
                    //100001
                    Color c = new Color(inImage.getRGB(x,y));
                    int red = c.getRed();
                    int green = c.getGreen();
                    byte blue = (byte)c.getBlue();

                    Byte temp = (byte)(charByte >> i);
                    temp = (byte)(temp & 0x01);
                    blue = (byte)((blue & 0xFE)| temp);
                    

                    // System.out.println("setting blue to : " + blue);
                    Color newColor = new Color(red,green,blue);
                    if (blue == 76){
                        System.out.print("0");
                    } else {
                        System.out.print("1");
                    }
                    inImage.setRGB(x,y,newColor.getRGB());
                    x++;
                    if(x+1 == width){
                        x = 0;
                        y++;
                    }
                }
            }

            String outname = "Encoded" + pngname;
            File outputfile = new File(outname);
            ImageIO.write(inImage, "png", outputfile);

            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void decode(String pngname) {


        String secret = "";
        File f = new File(pngname);
		try {
            BufferedImage inImage = ImageIO.read(f);
            int height = inImage.getHeight();
            int width = inImage.getWidth();
        
        int found = 0;
        int finish = 0;
        int i = 0;
        int x = 0;
        int y = 0;
        byte temp = 0x00;
        byte temp2 = 0x00;
        byte temp3 = 0x00;
        byte temp4 = 0x00;
        int prevchar = 0;
        // System.out.println("temp is now: "+ temp);

        while(finish == 0){
            for(i = 0; i < 8; i++){
                temp = (byte)(temp << 1);
                Color c = new Color(inImage.getRGB(x,y));
                byte blue = (byte)c.getBlue();

                blue = (byte)(blue & 0x01);

                temp = (byte)(temp | blue);
                // System.out.println("after pass "+ i + "temp is now: "+ temp);

                x++;
                    if(x+1 == width){
                        x = 0;
                        y++;
                    }
            }
            
            for(int j = 0; j < 8; j++){
                temp2 = (byte)(temp2 << 1);
                temp2 = (byte)(temp2 |(temp & 0x01));
                temp = (byte)(temp >> 1);
            }
            if (prevchar == 33 && temp2 == 33 && x > 17) finish = 1;
            prevchar = temp2;
            if (temp2 != 33) secret = secret + Character.toString((char) temp2);

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println(secret);
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