import edu.duke.*;

import java.io.File;

public class ConvertGray {
    //convert gray
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return outImage;
    }

    public void tesGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }

    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource img = new ImageResource(f);
            ImageResource grayImg = makeGray(img);
            String fileName =  img.getFileName();
            fileName = "convertedGray" + fileName;
            grayImg.setFileName(fileName);
            grayImg.save();
        }
    }

    //invert image
    public ImageResource invertImage(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for(Pixel pixel : outImage.pixels()){
            Pixel newPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = 255 - newPixel.getRed();
            int green  = 255 - newPixel.getGreen();
            int blue = 255 - newPixel.getRed();
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
        return outImage;
    }

    public void selectAndInvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource ir = new ImageResource(f);
            ImageResource invert = invertImage(ir);
            String filename = ir.getFileName();
            filename = "inverted-" + filename;
            invert.setFileName(filename);
            invert.save();
//            invert.draw();
        }
    }

    public static void main(String[] args) {
        ConvertGray c = new ConvertGray();
//        c.tesGray();
//        c.selectAndConvert();
        c.selectAndInvert();
    }
}
