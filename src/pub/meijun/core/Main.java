package pub.meijun.core;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import pub.meijun.listener.OnPicCapturedListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by meijun on 17-4-15.
 */
public class Main {
    public static void main(String[] args) {
        //捕捉全屏图片,
        CaptureObj captureObj = new CaptureObj();
        File file = new File("/home/meijun/桌面/test/a.JPEG");
        //  captureObj.getPic(new Rectangle(0,0,500,500), file);
        BufferedImage fullScreenPic = captureObj.getFullScreenPic();

        CaptureView captureView = new CaptureView(fullScreenPic);
        captureView.setVisible(true);
        captureView.setOnPicCapturedListener(new OnPicCapturedListener() {
            @Override
            public void onPicCaptured(Point point1, Point point2, BufferedImage image) {
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
                jfc.showDialog(new JLabel(), "选择文件保存地址");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    System.out.println("文件夹:"+file.getAbsolutePath());
                }else if(file.isFile()){
                    System.out.println("文件:"+file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());

                try {
                    ImageIO.write(image,"JPEG",new File(file,"temp.jpg"));
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //用户选择要截图的坐标
        //隐藏全屏图片
        //启动线程不停截图
    }

}
