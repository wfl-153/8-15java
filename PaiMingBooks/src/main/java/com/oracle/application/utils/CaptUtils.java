package com.oracle.application.utils;

/**
 * @Author 王飞龙
 * @Date 2023/8/14 13:09
 * @Version 1.0
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zhaoran
 * @title: CaptUtils
 * @projectName
 * @description: TODO
 * @date
 * @sign:
 */
public class CaptUtils {
    public static Map<String, String> createCaptString() throws IOException {
        //前后端不分离时 是同一个服务器进行发布的项目 后台代码 前端页面  一个用户请求在页面和服务中会有一个cookie + SessionId的信息进行维护
        //原来是将验证码保存到请求的会话中,当用户在页面填写完验证码后将自己编写的验证码和后台保存的验证码进行匹配
        //1234   7890  如何区分哪个验证码对应的是哪个用户的请求
        //前后分离的  后台服务和前端页面2个服务器 sessionId有效 标识=验证码对应= 用户名
        //通过redis进行验证码的保存信息 ,同时还需要服务端为前端生成一个客户端标识
        Map<String,String> map=new HashMap<>();
        int length = 4;
        String str = "ABCDEFGHJKMNOPQRSTUVWXYZabcdefghjkmnopqrstuvwxyz1234567890";
        StringBuilder sb = new StringBuilder();
        int len = str.length() - 1;
        double r;
        for (int i = 0; i < length; i++) {
            r = (Math.random()) * len;
            sb.append(str.charAt((int) r));
        }
        map.put("captCodeValue",sb.toString());//后续需要服务进行redis保存
        int width = 80;

        int height = 25;

        BufferedImage img = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);


        Graphics g = img.getGraphics();


        g.setColor(Color.WHITE);

        g.fillRect(0, 0, width, height);

        Random rd=new Random();
        for (int i = 0; i < 50; i++) {
            g.setColor(new Color(rd.nextInt(100) + 155, rd.nextInt(100) + 155,
                    rd.nextInt(100) + 155));
            g.drawLine(rd.nextInt(width), rd.nextInt(height),
                    rd.nextInt(width), rd.nextInt(height));
        }


        g.setColor(Color.GRAY);

        g.drawRect(0, 0, width - 1, height - 1);


        Font[] fonts = { new Font("隶书", Font.BOLD, 18),
                new Font("楷体", Font.BOLD, 18), new Font("宋体", Font.BOLD, 18),
                new Font("幼圆", Font.BOLD, 18) };

        for (int i = 0; i < length; i++) {

            g.setColor(new Color(rd.nextInt(150), rd.nextInt(150), rd
                    .nextInt(150)));

            g.setFont(fonts[rd.nextInt(fonts.length)]);

            g.drawString(sb.charAt(i) + "", width / sb.length() * i
                    + 2, 18);

        }


        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img,"png",baos);

        String s = Base64.getEncoder().encodeToString(baos.toByteArray());
        s = s.replaceAll("\n","")
                .replaceAll("\r","");
        String base64Value="data:image/jpg;base64," + s;
        //img  src
        map.put("base64Value",base64Value);//将图片流对象变为字符串 并设置为base64编码 图片类型
        return map;
    }
}

