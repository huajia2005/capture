package com.xhc.capture;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @ClassName SchedulerTask
 * @Author Administrator
 * @Date 2019/6/3 12:46
 */
@Component
public class SchedulerTask {
   // private ;//"yyyy-MM-dd:HH:mm");
    private  String path="C:\\Users\\Administrator\\Desktop";

    /**
     * 定时对桌面进行截图.
     * @throws IOException
     * @throws AWTException
     */
    @Scheduled(fixedDelay = 900000)
    public void capture() throws IOException, AWTException {
        System.out.println(1);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        String directory=sim.format(new Date());
        File screenFile = new File(path+File.separator+directory);
        if (!screenFile.exists()) {
            screenFile.mkdir();
        }
        sim=new SimpleDateFormat("HH-mm-ss");
        String files=sim.format(new Date())+".png";
        File f = new File(screenFile,files);
        System.out.println(f.getName());
        ImageIO.write(image, "png", f);
        //自动打开
    }
}