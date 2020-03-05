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

@Component
public class SchedulerTask {
    @Value("${image.path}")
    private String path;

    /**
     * 定时对桌面进行截图并保存
     *
     * @throws IOException  图片写入错误
     * @throws AWTException 获取错误
     * @author xuhongchun
     * @date 12:25 2019/11/1
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
     * @return {@link BufferedImage} 返回截图缓存图片流
     * @throws AWTException 获取错误
     * @author xuhongchun
     * @date
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
     * @param today 当前时间
     * @return {@link File} 截图存放文件夹
     * @author xuhongchun
     * @date
     */
    private File createDirectory(LocalDateTime today) {
        String imageDirPath = path +
                File.separator +
                today.getYear() +
                File.separator +
                today.getMonthValue() +
                File.separator +
                today.getDayOfMonth();
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