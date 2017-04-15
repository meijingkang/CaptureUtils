package pub.meijun.core;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by meijun on 17-4-15.
 * 截屏对象
 */
public class CaptureObj {

    String picPath ;

    private Robot robot;

    private Dimension screenrec= Toolkit.getDefaultToolkit().getScreenSize();
    public CaptureObj(){


        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    /**
     * 截取全屏图片
     * @return
     */
    public BufferedImage getFullScreenPic() {
       return  getPic(new Rectangle(0,0, (int) screenrec.getWidth(),(int)screenrec.getHeight()));
    }

    /**
     * 截取指定区域的图片
     * @param rectangle
     * @return
     */
    public BufferedImage getPic(Rectangle rectangle){

        //拷贝屏幕到一个BufferedImage对象screenshot
        BufferedImage screenshot = robot.createScreenCapture(new
                Rectangle((int) rectangle.getY(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight()));
        return screenshot;
    }

    public boolean getPic(Rectangle rectangle,File file){
        BufferedImage pic = getPic(rectangle);
        try {
            boolean success = ImageIO.write(pic, "JPEG", file);
            return success;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


    }




}
