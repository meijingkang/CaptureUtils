package pub.meijun.listener;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by meijun on 17-4-15.
 */
public interface OnPicCapturedListener {

    void  onPicCaptured(Point point1, Point point2, BufferedImage image);
}
