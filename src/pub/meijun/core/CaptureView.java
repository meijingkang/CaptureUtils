package pub.meijun.core;

import com.sun.prism.paint.*;
import pub.meijun.listener.OnPicCapturedListener;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.security.cert.PolicyNode;

/**
 * Created by meijun on 17-4-15.
 */
public class CaptureView extends JFrame {
    private Point releasedPoint = null;
    private Point pressPoint = null;
    private final Panel comp;
    private Point movePoint;

    public CaptureView(BufferedImage fullScreenPic) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //通过调用GraphicsEnvironment的getDefaultScreenDevice方法获得当前的屏幕设备了
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        // 全屏设置
        gd.setFullScreenWindow(this);


        //先设置一个大小
       //this.setSize(fullScreenPic.getWidth(), fullScreenPic.getHeight());
        comp = new Panel(fullScreenPic);
        this.add(comp);


        comp.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 3) {
                    //如果点击鼠标右键第一次清除上一次选中的区域,第二次直接退出


                    if(pressPoint!=null && movePoint!=null){
                        //person want to save pic
                        if(mListener!=null){
                            BufferedImage image = fullScreenPic.getSubimage(((int) pressPoint.getX()), ((int) pressPoint.getY()), ((int) (movePoint.getX() - pressPoint.getX())), ((int) (movePoint.getY() - pressPoint.getY()))) ;
                            mListener.onPicCaptured(pressPoint,movePoint,image);
                        }
                    }else{

                    }
                }else if(e.getButton()==1) {
                    boolean isDoubleClick = checkDoubleClick();
                    if(isDoubleClick){
                        System.exit(0);
                    }
                }
            }
            long firstClick = 0;
            private boolean checkDoubleClick() {
                System.out.println("ddd : "+ System.currentTimeMillis());
                if(System.currentTimeMillis()-firstClick<200){
                    return true;
                }
                firstClick = System.currentTimeMillis();


                return  false;

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == 1) {
                    if (movePoint != null) {
                        movePoint = null;
                    }
                    //1是左键
                    pressPoint = new Point(e.getX(), e.getY());
                    comp.updateUI();
                }


            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("release");
                releasedPoint = new Point(e.getX(), e.getY());
                // comp.invalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        comp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (e.getButton() == 0) {
                    //0是左键
                    System.out.println("mouseDragged");
                    movePoint = new Point(e.getX(), e.getY());
                    comp.updateUI();
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }
    private OnPicCapturedListener mListener;
    public void setOnPicCapturedListener(OnPicCapturedListener listener) {
        this.mListener = listener;
    }


    class Panel extends JPanel {

        private final BufferedImage fullScreenPic;

        public Panel(BufferedImage image) {
            this.fullScreenPic = image;

        }

        boolean isdraw = false;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(fullScreenPic, 0, 0, fullScreenPic.getWidth(), fullScreenPic.getHeight(), null);

            g.setColor(new Color(191, 63, 191, ((int) (0.64 * 255))));
            g.fillRect(0, 0, fullScreenPic.getWidth(), fullScreenPic.getHeight());
            g.setColor(new Color(191, 63, 191, 0));
            if (pressPoint != null && movePoint != null) {
                g.setColor(Color.BLUE);
                g.drawImage(fullScreenPic,((int) pressPoint.getX()), ((int) pressPoint.getY()), ((int) (movePoint.getX())), ((int) (movePoint.getY())),
                        ((int) pressPoint.getX()), ((int) pressPoint.getY()), ((int) (movePoint.getX())), ((int) (movePoint.getY())),
                        null);
            }
        }


    }


}
