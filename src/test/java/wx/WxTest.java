package wx;

import com.yed.FbootRun;
import com.yed.bean.weixin.ImgMessage;
import com.yed.bean.weixin.Media;
import com.yed.servcie.weixin.SendMessageService;
import com.yed.utils.HttpRequestUtil;
import com.yed.utils.weixin.WeiXinParamesUtil;
import com.yed.utils.weixin.WeiXinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName WxTest
 * @Description TODO
 * @Author CaoSS
 * @Date 20/7/3 16:40
 * @Version 1.0
 */
@SpringBootTest(classes = FbootRun.class)
@RunWith(SpringRunner.class)
public class WxTest {

    //3.发送图片消息---无效的media_id
    @Test
    public void testSendImgMessage(){
        //0.设置消息内容
        //1.创建图片消息对象
        ImgMessage message=new ImgMessage();
        //1.1非必需
        message.setTouser("@all");  //不区分大小写

        //1.2必需
        message.setMsgtype("image");
        message.setAgentid(WeiXinParamesUtil.agentId);


        //2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
        String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
        System.out.println("accessToken:"+accessToken);
        String media_id = HttpRequestUtil.post("https://qyapi.weixin.qq.com/cgi-bin/media/upload", "access_token=" + accessToken + "&type=" + message.getMsgtype() + "");
        System.err.println(media_id);
        Media image=new Media();
        image.setMedia_id("3OvyvAqGpMPAoOWo6w8eTCVfIXzi0_vTRia-GT__uMA0");
        message.setImage(image);
        //3.发送消息：调用业务类，发送消息
        SendMessageService sms=new SendMessageService();
        sms.sendMessage(accessToken, message);

    }
}
