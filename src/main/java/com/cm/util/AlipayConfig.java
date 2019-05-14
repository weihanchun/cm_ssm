package com.cm.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100100636150";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCZlYdmxttadFc+XtpGQdeHFfnoOJ/moOZCJ1Lmrlpx0R6wOUUZM4UL4R6t22PbBrfiP+STi9xF2f2Rr1t69TobY+DchtJwO+djFVPl5J5ECrgzWLISRaJIMIVtj7WJzojaR9MUnon+C1M6zN9kvPw7ZypXURxbnleCzWd0p0pClcciRFO3l3uGRdVsN2HRcwNI314yz4lQwiHvZs2Wdz25CgguFLq1flfjwL9I4+Vrw1x6dcTF6afeCjYC2Gg++C619VKb+QuDJ1umhTjTfXafd1JQ4K1noaSSVHzU/Y+lUIVKV43xcA4wpbdRJlAKLueHd9aAru2IreIE3uDGVzrRAgMBAAECggEANo0kEN9lHlXgfyjVwsq0MSkfINqsARD5eFnbWlBnk7HBgrp0zqS8ByIo8XvRlgRm4BftqnKtBKqPJGXyylO8Fep9/hZtPKwqXck/iZKQnH5qOuKwLUlKgPWrDuVSh+YyOcX1NYeyX+fJ4kLOvktaFAdqxKTsu6YcUzt+GbMp70gQ/v4J4447F/2FwEdHljMmN/96w1w31sta0gWBZsfM2Z9FnySbgj3f4/lVYgsqRSY/mPFfY7zj4fyS4izVFpwaJkRS1u/Nfw9w0q+I2nYoa4V4BWAr38QuvZi+8LvzdXI7K9BcYB81NlvOWh/zxr4y3RSLAvwy3vz2GIujYQAHIQKBgQDrxQmHjy0qlWaYaxdExT6Z8XkFrriTYz6AFj9Ky2YGX0RZjjzPtzyuk9QM64KnUyJn72Ynyuk4DALXTWduvncUyVUKrWEmSbgfRAyfH1iF4869evy0ZCMDKUCLjGP76u9foWvB/YyUMalzUxtoj1wFdbGOUXU8kfrV9O4IZ98qbQKBgQCmwy/sGV6QTl22ZadAmOu2noj1IhzzvTjU1u3j64pprn7jc6zHTtS9l+D6/8j3tWztxxMg9doPRk+5V4w2aYznfFYaA7k2jWxVdSQUIMQ9lk/srKAxO80gCcd1vkkSu+DozJSEtJ0OSoyRLHLgMRjD/PlJk0oUQhQC36/I5kXTdQKBgH42FVrfF3OjVK+lLGihGlj4fiu/k4k66HtS7YDRj4U7vHAgh4c+dRqTgkGEVYXTKCjxbANDSUO6kk7FXehXx5F2EbNMiyQe9JSwLf2EnzPVdpfSXbMHpAKOM8JAXkm+Q3QzCmdnnri6ORbGV3bKv50gmvMvwg2xq9CbdE1FpZ6lAoGAWwLViZ4C3PDX9i6eTAPpSL5W8qnOcTuI46f1Jtmqraic+Zds6U1dj6dvEq08SexK6a4nA2sJuiJeakGcdSm/y6ow93umvl4KjM0khpOnZhE9x2Yi+xuvBFgx8lGKYd4Fjifwukg0GtOWybgfbbl90aCjkIMA//NcJoFb12HNI+0CgYEA4tgJ5K9Xe8hsohelwupnicRV1qVpLh6QljcC3LMzujoFqCrbB4VHNPhalKa6FueBgRGu5ixE+AkAlclXV9+crYzpYQLuWO+4Uq9btUE0DFaf+2FevQEFRuequGNRx//DAlQClUoNz3w9dXJ0ENNGB2EWoBSTwlCf2rDYIgmGFQs=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvMTnwUwXzJTPaYs7SH39kGkeDZw8l/flHwckfXjrdOk9ww87Zl9O1hhFM0BOT02xn0NQTChwLpEfrtxUXCWYS1Rb1PGWQb1DcemJfRNDFzhacuZBvv7rNKkqL+9Z8reCKuuEmwwFCpnOdPdTlljALZa2eCv5IU6a4wH9uG7iBdsbBm1PWhpBepq0ZZbBcoEah2j/4JbvANNZK+QbminmHOxg/HcaagXhHHfXjlJFMGWK7hcUDYdZYsCEP/VRmvRSmNFexHJd0kUlFbPE/n50FiEos5CCfRsJOEOjFUBy5nV5s9ULL/Bj1v9pkIbSyS3LESQNOo9aUaP7eQ3pPPk0UwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/cm/forepayed";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/cm/forepayed";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "d:\\test\\pay";


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
}

