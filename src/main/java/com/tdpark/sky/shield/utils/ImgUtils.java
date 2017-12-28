package com.tdpark.sky.shield.utils;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImgUtils {
    private static final int FONT_SIZE = 10;
    private static final int FONT_GAP = 3;
    private static final int WORD_LENGHT = 4;
    private static final int WIDTH = WORD_LENGHT * FONT_SIZE + FONT_GAP ;
            private static final int HEIGHT = 16;
    //private static final Font mFont = new Font("Times New Roman", Font.PLAIN, FONT_SIZE);
    private static final String [] CODES = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9"}; 
    //private static final String [] CODES = {"1","2","3","4","5","6","7","8","9"}; 
    
    
    public static void main(String[] args) {
        for(int idx=0;idx<100;idx++){
            randImgCode("e:/code");
        }
    }
    public static void randImgCode(String path){
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        // 设置透明 start
        image = g.getDeviceConfiguration().createCompatibleImage(WIDTH,
                HEIGHT, Transparency.TRANSLUCENT);
        g = image.createGraphics();
        Random random = new Random(); 
        
        String code = "";
        for(int idx=0;idx<WORD_LENGHT;idx++){
            int i = random.nextInt(CODES.length);
            code += CODES[i];
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(CODES[i],FONT_SIZE*idx+FONT_GAP,HEIGHT - new Random().nextInt(8));  
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path + "/" + code + ".png");
            ImageIO.write(image, "PNG", os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(os != null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    public static ImgCodeDomain randImgCode() {
        try {
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            // 设置透明 start
            image = g.getDeviceConfiguration().createCompatibleImage(WIDTH,
                    HEIGHT, Transparency.TRANSLUCENT);
            g = image.createGraphics();
            Random random = new Random(); 
            
            String code = "";
            for(int idx=0;idx<WORD_LENGHT;idx++){
                int i = random.nextInt(CODES.length);
                code += CODES[i];
                g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
                g.drawString(CODES[i],FONT_SIZE*idx+FONT_GAP,HEIGHT - new Random().nextInt(8));  
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte [] bytes = null;
            try {
                ImageIO.write(image, "PNG", baos);
                bytes = baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ImgCodeDomain codeDomain = new ImgCodeDomain();
            codeDomain.setImgCode(code);
            codeDomain.setBytes(bytes);
            return codeDomain;
        } catch (Exception e) {
            return null;
        }
    }
    public static class ImgCodeDomain{
        private String imgCode;
        private byte[] bytes;
        public String getImgCode() {
            return imgCode;
        }
        public void setImgCode(String imgCode) {
            this.imgCode = imgCode;
        }
        public byte[] getBytes() {
            return bytes;
        }
        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }
        
    }
}
