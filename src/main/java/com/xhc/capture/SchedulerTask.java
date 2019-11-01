package com.xhc.capture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version 1.0
 * @ClassName SchedulerTask
 * @Author Administrator
 * @Date 2019/6/3 12:46
 */
@Component
public class SchedulerTask {
    @Value("${image.path}")
    private String path;

    /**
     * 定时对桌面进行截图并保存
     *
     * @Author xuhongchun
     * @Description
     * @Date 10:47 2019/11/1
     * @Param []
     * @return void
     */
    @Scheduled(fixedDelay = 60000)
    public void capture() throws IOException, AWTException {
        LocalDateTime today = LocalDateTime.now();
        //对桌面截图
        BufferedImage image = screenshot();
        //创建图片文件夹路径
        File imageDir = createDirectory(today);
        //创建图片名称
        String fileName = today.format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".png";
        //写入截图图片
        ImageIO.write(image, "png", new File(imageDir, fileName));
        System.out.println(today.toString() + "---截图成功");
    }

    /**
     * 对桌面进行截图
     *
     * @return java.awt.image.BufferedImage
     * @Author xuhongchun
     * @Description
     * @Date 10:04 2019/11/1
     * @Param []
     */
    private BufferedImage screenshot() throws AWTException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        return robot.createScreenCapture(screenRectangle);
    }

    /**
     * 创建文件夹存放路径
     *
     * @Author xuhongchun
     * @Description
     * @Date 10:56 2019/11/1
     * @Param [today]
     * @return java.io.File
     * @throws
     */
    private File createDirectory(LocalDateTime today) {
        StringBuilder stringBuilder = new StringBuilder(path);
        String imageDirPath = stringBuilder
                .append(File.separator)
                .append(today.getYear())
                .append(File.separator)
                .append(today.getMonthValue())
                .append(File.separator)
                .append(today.getDayOfMonth())
                .toString();
        File imageDir = new File(imageDirPath);
        if (imageDir.exists()) {
            return imageDir;
        }
        if (imageDir.mkdirs()) {
            return imageDir;
        }
        return null;
    }
}