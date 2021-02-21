package com.example.zhuzhourailway.Controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.UUID;

@Controller
public class OrderController {
    private final String APP_ID = "2021000117611999";
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYTTKDXsEn46BiJR3SKujBAqKOiSYI6jtLp1AaGTtl9QpwBbNENClc2HvOoXc+fwwH54p7GGz2jWXeJrYvMjDZQlAY85kyS+g0dGVInyhpA4Tj2Kt+96C9vButgMJ+OvXIz7pJ3su6GJiipTb/n+0OJWnKh9gae2PZlxSYDba5Q/tETTKBSPbeuMjoqiZO7SyY3CgsjF0np1ApR8zj+5/KFLiUVETMQYjBE66mXSoH7WlguVEPkqde6J2KJLxnQTR/5J5vYI1HYTuDDbwVAAVRYrCejaxhF7jYVufY/IHD3zp45jTfqeW6ZNW/AbXbxkoqqhgQad8zT+ZgoBVN/GWJAgMBAAECggEAOcpxNHAsExrIrPUwj6NAlpyNWlCoYhGfVmGyf3S7mUCWKVPAZkz0tQpgUFPsj4Zd5lHxtivajOOyl81QrC/7YTVMd2tX4kHzZpQUKq5aAx8Wue5phNAp6lUKm5eUNZnh0cUhBw7Hd2IF6RGQAQeKSCfiMLvDQKk5hkNRyTDK+Rk/i/DN0xiyaPUTvEIFo5+rbRuwzlhbWFBjtPkkJRPEM2v++smzolcLj/fqdCZHTkdwZY0mBbB8c+SFugytpJQqu5IHVgvW5MEkjo/xlwoOrtcZWlHtgdd5hB8TZvnjodUUmtM9XycjpTAypQ62AY6wPasI2c1ly/RtNxAoozHUAQKBgQDg4XNWJAhlziaxM3pbAs+XSELCpEwTsgjEPMAoc//ddgR9eTVoQPvRZmRF/xBBEI0UQx4noRVYWsoPGQI+M5smJ32ZoqVM3YD6wL5LIf4hD6SDvcmjBWp63bBdKE38kPmd1PgxBi7rsxlePvRlTPw+b7kCFVCGg1YGfyumIu2YaQKBgQCtYJUooDjPTtYRw7RIlVDGzVWuZTJ5oU+xDUxgY7amdamCepqdqmqCXnqYK+zj0DrASk8SA3wXq3RP8wZUrpX2dxJehkkOlTG6xU+lQUoO0zRPsxD1Lv03G0xTsnrweAbGmeJRkV+bJwDFzoWvl60pdOgKxaCo2I3aqAoTeQXAIQKBgB3FOuOYeZQgqnai/fu2b4zzgAMMhRXGZ+qysC2qzaCf0xBZT3yxvaSHDZdGoPw8GLEqAX/1j0HXIxDEmstpQYzXhYUlRwTZ/g00Hv0LTv1JeNoH8f2m5eXGG2b5wvlAqAsA+qpiyytg1mbnpqsSegI6ACAKelKUaHsLsteXoCShAoGBAKh+Y5c+1tcJ69sJ0cx0aDM8XJeGu54fGf6cSOyoG1r8oIcwtdCiTK50OTpn/emMdXxo+x0ri87JDtmf+jgUD6OeJpj8ckWc0t6LYRLeN6Xetkj6YnGVf/qtKelxXC8Uxqj95keVO0/igMq8IYn7owrWFahaVf+hrYKhqmEwTVlhAoGAd1xycwWIRduRMVAR6uGnmn/iqRQexz8ePsER2qpSqMxmNz2ZJ63GfbR11k24SeOhFRIFIMKUib3pfnFmYbZxuZOeN/PV3lOJlIo/rxoWuJ8TfuIAhX4MfIi8BIJruZSW2Mh/6L5twVfoZoVdYEC6+YykkmmL9JPReTa1EZ92sW4=";
    private final String CHARSET = "UTF-8";
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrFbexiwo5gvtM9r379wTlwOH81Fp37gIbrfVyzHFTKt2c0UDY8B+Bq40lj2vgEsdK8G9zs0keNjURIB6WVR7wxkX7hPe46qvexKTWMxmKRtHgKFtzamLSurcgGDh1j2XZFoU97umCZz6qu1zC4vhkSP8aGtb/qIrrjfyzMjFmvimhSmtgFWp1LfF4eH65y+97X1xeptfRD4b4iBp2MiLZu6zx9ay3SVulTLRIa7x03IdYL+Oaf9iFfT8gPSw42z+QetDyLeNOyBgsaxNgeiDA3T6qynPoDcVfSUus+fUSOammJToDcuXFGSoiNar9cNpIFbPEtnOISZKNTG3NafrwIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1:8080/alipaynotice";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://127.0.0.1:8080/alipayreturn";
    @RequestMapping("/alipay")
    public void alipay(HttpServletResponse httpResponse) throws IOException {

        SecureRandom r= new SecureRandom();
        //实例化客户端,填入所需参数
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //在公共参数中设置回跳和通知地址
        request.setReturnUrl(RETURN_URL);
        request.setNotifyUrl(NOTIFY_URL);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //生成随机Id
        String out_trade_no = UUID.randomUUID().toString();
        //付款金额，必填
        String total_amount =Integer.toString(1);
        //订单名称，必填
        String subject ="邹璐";
        //商品描述，可空
        String body = "尊敬的会员欢迎购买奥迪A8 2021款 A8L 60 TFSl quattro豪华型";
        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    @ResponseBody
    @RequestMapping("/alipayreturn")
    public void alipayreturn(){
        System.out.println("成功1");
    }

    @ResponseBody
    @RequestMapping("/alipaynotice")
    public void alipaynotice(){
        System.out.println("成功2");
    }

}
