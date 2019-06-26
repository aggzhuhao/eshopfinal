package com.zhuhao.eshop.configure;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092900625758";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCTbAj6H06tAQ1qhWDYntFRaFlDYNGvvbkIH+bJ9XXZlX6AsJ6QWx3IZVYn+S3KPikPzRCg7km55yFHW4XvFRHd4d28PV0argF2YcjVMKpCyzFHbOr2WRShlek96tukN0H+M5e1g5fdHei64zhSc+5L/vsOwo4zSeRhudAX/X8P3tApEGC3I9W48C71JnR9JeNwxgH5XF6SpgwB9Y6qQ7dKFIGH2lRGg2+j39SqAMTxL2foVSabL3Id5J+t1I/i+pojTFfH8457N93a4v6Wf/Zmh98oh+NA7Uf+QRV8qu5s2Vp4d3pkdVcxUHWzLPulGvKr9MBP6xVwUdGIpn7N2kjzAgMBAAECggEAFJf77QzgjbRe7mlxbPFKpkegaJQm1pj3NxZVxBKhAuMqR8nkbJ/92hel4HGjnuPuEoH/96sx62FJOdGkNRDJbSh3NCuSTP7Pv3zLdFSXBDukR6kEbaNmCWiKq5aaVidN9be5UqbuWPorn6fMH2CIgl89LQOqn6mL2Mb/Ncq4rDupT48oGLbYEZkbT7jYciPPQ0lLVoLl5MBmds9hNJXGAK6v/aDXzRvvbeFgy5SK684XfjxlBKorqgL3gv3NbTjQkJm0GY2lZ7q4QSJTFL0zgc4kEnbuQ8OS1r9nzFUYTzsx0CRL8EURDiKT2iM5zFU6dyNiQ3kGLxPqhpQW5Mu6oQKBgQDy5x4aU+MeZSHKtrtTTP2JSEqPdPhVrCWalbndYZlIbBuyvjlNbo6cJUb/tc366bK6LUA9bzA1WBlxjrNwkGWAjSR4egxAwnJaPKPoWyLjZe6yiP3PrIuU6J1/oan8ML0l3CAeg8tlX8aS18eFQHX+lR4JQ5v/xlYEp6+o108yowKBgQCbXvVxwv/SBQCA6RlyB3bqo7oYOwJ2BXLKiNfy1Gy7BnFdY65aXrd9EQQUGF91tiSIBxZ/Yv6kFx6386Nq59A/UyYz8NKAGa/0iUk7OZgRqPHR7Dakk1n+Lwzn/V9/qW5NoMjGJgogtOMPYcF2SytgaDcCBUEVt5IcnXHUyrVFcQKBgGW0sh0R/OowLEsF4j/J3+sIkveoYYb10rT8dlukzxrM8XgRQEsUvK/Sx/yv8jRagBSGd/yBPu1HGzg6KRY7z9RYxOmRKOpAwMLZ20OiTWRrvNuSkrvPIo7SK4hSd6t5+OkOV4CPbK3gwXOg2BEKYZ2ccHwui/Y35LKER//pT2cDAoGAGzMPgt/hSxIHGYo1Iyo9XixVesJx1tlzVBOKwn7/neot1GyL7FQiiSl+bkoqBABj+tBK1vgsnetSPF/RnviAE2XqDTO+liBpFkUmZdDaV1UdKGPS2RLWsVHpT3+ixUWTAtH/8/ha04j3Kw0xAhV8vX7hCmCDGVsuoAXMFLV78HECgYEAj0VdRGfTdTR8IZQT7ZDDJARNwtPT/U6iAvPyqiHgNgQwVXlEbUPE3tWFnwBHHNwBybiSsO17odIhUTlgGX9DGc6z/mihqDoVwJ6Czs2A6EhjRU7DwqH5r9Od3ups38MurvK/zscDP5aI5gUpK/gxCVWFDIUWBPskLaOUsAmsvu8=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk2wI+h9OrQENaoVg2J7RUWhZQ2DRr725CB/myfV12ZV+gLCekFsdyGVWJ/ktyj4pD80QoO5JuechR1uF7xUR3eHdvD1dGq4BdmHI1TCqQssxR2zq9lkUoZXpPerbpDdB/jOXtYOX3R3ouuM4UnPuS/77DsKOM0nkYbnQF/1/D97QKRBgtyPVuPAu9SZ0fSXjcMYB+VxekqYMAfWOqkO3ShSBh9pURoNvo9/UqgDE8S9n6FUmmy9yHeSfrdSP4vqaI0xXx/OOezfd2uL+ln/2ZoffKIfjQO1H/kEVfKrubNlaeHd6ZHVXMVB1syz7pRryq/TAT+sVcFHRiKZ+zdpI8wIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:9527/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1:9527/insertorderandjiesuan";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";



//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void getorder(HttpServletRequest request, HttpServletResponse response,String count) throws IOException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(UUID.randomUUID().toString().replaceAll("-", "").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(count.getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(UUID.randomUUID().toString().replaceAll("-", "").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出
        response.setContentType("text/html;charset=utf-8");
        //System.out.println(result);
        response.getWriter().println(result);
    }



}
