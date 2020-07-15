package com.yed.controller;

import com.yed.bean.IacsUrlDataVo;
import com.yed.bean.weixin.ImgMessage;
import com.yed.bean.weixin.Media;
import com.yed.servcie.weixin.SendMessageService;
import com.yed.utils.HttpRequestUtil;
import com.yed.utils.SendWeChatUtils;
import com.yed.utils.weixin.WeiXinParamesUtil;
import com.yed.utils.weixin.WeiXinUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WeChatController
 * @Description TODO
 * @Author CaoSS
 * @Date 20/7/3 14:37
 * @Version 1.0
 */
@Controller
public class WeChatController {

    @PostMapping({"/sendWeChat"})
    public String send(@RequestParam(value = "context") String context) {
        SendWeChatUtils msgUtils = new SendWeChatUtils();

        try {
            String token = msgUtils.getToken("wwd9ac590a1ae91b37", "Y0TlmrXYZpuWd867mPzvtzjyU1hPFiX1AMbrHxAg-vw");
            String postdata = msgUtils.createpostdata("4e745aa72f6c39ff972c2590e4b2e804|CaoShaoShuai", "text", 1000002, "content", context);
            String resp = msgUtils.post("utf-8", SendWeChatUtils.CONTENT_TYPE, (new IacsUrlDataVo()).getSendMessage_Url(), postdata, token);
            System.out.println("获取到的token======>" + token);
            System.out.println("请求数据======>" + postdata);
            System.out.println("发送微信的响应数据======>" + resp);
            return "/index";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/index";
    }

    @PostMapping({"/sendImage"})
    public String sendImage(@RequestParam(value = "image") MultipartFile image) throws IOException {

        // 获取文件名
        String fileName = image.getOriginalFilename();

        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(System.currentTimeMillis() + "", prefix);

        // MultipartFile to File
        image.transferTo(excelFile);

        //1.创建图片消息对象
        ImgMessage message = new ImgMessage();
        //1.1非必需
        message.setTouser("@all");  //不区分大小写

        //1.2必需
        message.setMsgtype("image");
        message.setAgentid(1000002);

        //2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
        String accessToken = WeiXinUtil.getAccessToken("wwd9ac590a1ae91b37","Y0TlmrXYZpuWd867mPzvtzjyU1hPFiX1AMbrHxAg-vw").getToken();
        System.out.println("accessToken:" + accessToken);
        String media_id = WeiXinUtil.getMediaId(accessToken, "image", excelFile);
        Map map = (Map) JSONObject.toBean(new JSONObject(media_id), Map.class);
        String mid = (String) map.get("media_id");
        excelFile.delete();
        System.err.println(mid);
        Media imag = new Media();
        imag.setMedia_id(mid);
        message.setImage(imag);
        //3.发送消息：调用业务类，发送消息
        try {
            SendMessageService sms = new SendMessageService();
            String s = sms.sendMessage(accessToken, message);
            return "/index";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/index";

    }

    public static void main(String[] args) {
        String str="{\"errcode\":0,\"errmsg\":\"ok\",\"type\":\"image\",\"media_id\":\"3hmvHdVQ49w9YB7KOAvFVRnbWyvx-W0dFbazExzpUAI7VJipKjYWjJy3e42KAbuh6\",\"created_at\":\"1593862794\"}";
        Map<String,Object> map = (Map) JSONObject.toBean(new JSONObject(str), Map.class);
        String media_id = (String) map.get("media_id");
        System.err.println(media_id);
    }

}
