package com.yed.servcie.weixin;




import com.yed.bean.weixin.BaseMessage;
import com.yed.utils.weixin.WeiXinUtil;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.google.gson.Gson;


/**@desc  : 发送消息
 *
 * @author: shirayner
 * @date  : 2017-8-18 上午10:06:23
 */
public class SendMessageService {
    private static Logger log = LoggerFactory.getLogger(SendMessageService.class);

    private  static  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";

    /**
     * @desc ：0.公共方法：发送消息
     *
     * @param accessToken
     * @param message void
     */
    public String sendMessage(String accessToken, BaseMessage message) {

        //1.获取json字符串：将message对象转换为json字符串
        Gson gson = new Gson();
        String jsonMessage =gson.toJson(message);      //使用gson.toJson(user)即可将user对象顺序转成json
        System.out.println("jsonTextMessage:"+jsonMessage);


        //2.获取请求的url
        sendMessage_url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);

        //3.调用接口，发送消息
        JSONObject jsonObject = WeiXinUtil.httpRequest(sendMessage_url, "POST", jsonMessage);
        System.out.println("jsonObject:"+jsonObject.toString());

        //4.错误消息处理
        if (null != jsonObject) {
            if (0 != jsonObject.getInt("errcode")) {
                log.error("创建成员失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsonObject.toString();
    }
}
