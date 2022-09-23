package com.neu.pay.config;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;



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





//解决跨域问题

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)

public class AlipayConfig {



//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓



    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号

    /*public static String APPID = "2021000117665332";*/

    public static String APPID = "2021000121667943";

    // 私钥 pkcs8格式的

    /*public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCjv/PfcTTSRuVjOO/Ou4keHKfXEpymPVALUh+vU5ErirRfgL9DehTN1hCyVnCOMRXXdF62sjaoFvQvp7rSMQGN0HBWZUZvU93p1MZq5nXshy8AUOAgijtX/BrWLlBtq1rUECuTG4ycoP+Z1wqN2Kt/69dLVQjbEGvcfFfVHEt1VdARaGMTOGRVZ3SEbwUnA3bS2kFL10R3YYAgTsD4KrmXP2BJbCJLks/OO6CT+u/UFAAwmjvOeUmlrkAs0M6WPADnAn7kMytxRJiBa31u0auNj2tK3cWEQ40kDOfwWuJ8sQDirLV8Nd5Ilj/BggJRZ6eY9fvo7iqb3xdkiCLZC6J9AgMBAAECggEAetILFVlQCThr7ZRTKni3TAKqUUMAhCAQ3aznXxYLGOGckNV8oBv9ZrZdJ6Z2HfDrNQNv1xCstj8hQG1KT0W6qKfi2qpzviTlmfGiMaRSZuqucko3EbEc+c16Q5PRd7DDG7RVOq27Ne/8R2Nn4yTU3coDXdmWHtmeTW5mYs4qOwLCKy+ACCvjcVi9/7L2CKQJMCVHelMO7L0Y1LQNeynHt5DDFhqSfHOn14kUuFk+H/I08UyKR8n5iyaaFlOJWSMD6F9HseF/0wJ7ux3bl8dZFYKyCJhx7zT2LlH6WQl9FR+k6Km8PXaVpUoDUe/SqkbGtn4+Z+mLxV62B83npHW8cQKBgQDelCYuNCe8O4+BTmktizot4iJ6zt3/9EjYToNQv4KS+LCjgUqOxzmOFicceQdUOfFng3YOF054pqMcP3akSee9IEB0aextLSG/2UaCohNhbxZFsIa00sUmjUbg843ijUsamoZ04cO3R5R11wsnO8UQz7HT1iZ+ow+2KBGz7sDLmwKBgQC8VnDKRw6YqIS7Jpo3ilGOVF0Ti+WTQbn9CEkgBgowvI15RbdqRoJbI0u38qHUrLHEgjht2rm8PfmipCQxMJ8GKNu/L6FfcBmFMmqlvFVgfNzOzRgJTMdN4umQBe0HG8QBnID4LQX/x1NyEbHZDYuAnV52U5u3+gFg/nJ6ZuZnxwKBgQC28Pm4vDF0q1e2JiaYwnidQeKpIeKh/7zn3RBdjCrcdf9pRFltpR4PRPH8mxWFCuL5+7lKt6lNzVj/XMI9ImmQrzOK+4zR52h38TPdJy3axZ1+xirR8PMOTsxVFJon/Y4fXabp5q4gtHq3qHqIUr4UpUE6JigZqhi91Y1HYROhxQKBgEb3r4KZKefeQD2hOyVIipi+M2BlK7nEX4f4Mk3N2NMqiYiz+3whW4dqEmMbG13fQRESHJ9QdwJ2u/VMMpMShim1BbDSCtXCkOs+5y7Se71i73VBlRgUBVnU8juh8AHotYAB8BsUJBmQi2IFRuXKE9H/+pNNx/SYUSKyXoK++X0LAoGBAMk0G21rgOUAH2u1c0/7YlspqJ4LtHp/GBK1quz3lc/5RhoO4ULoA/c+wC54KJ668PAboRER8COw94TPwlnkmn08Q6rvIcSE1Ns1UNOg3TAlRrivqLSnQnK8Bgk1Q68edZTffmcuIE4jPDgu7soECUAc5cYh/UBU595CRL6iPt6j";*/
    public static String RSA_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCLIvCZfnAAym9ELr3ot2lBEr9hSzqhVtO+LGbq5O4SNMdVEYFIAOG+XCNVhrI0clSHncihIotq4qji1EQnu+5pjpgZs/akZMhMH3XdIMn8JUvWNV1FIJ2yz6G84Y66IAxQMmREh/Hl57UY/0OBEuUO6WTXJlF3+F6rCzjLi48aKQ49nv0G3SSzyxFSrvOb3/RfRsbTBgItMwGD3LRpNkY5vCN70fIPsx5pwEUUiK5owIf2QJuO72StoDbJFIaadryfcQcTbWUbmhR3/J6T+p+AzBv47VjNgKhbkZVasOAoK7nFQcGWVbSD3nYEPypd+IMYvIDzOLfG5XKsHZr6Dg8hAgMBAAECggEAdWAhZuviItij6vofnY30zj+5kaN8L5VnBZzBqdE9AcDik+IzlQGhbwydhk4eUd5NbybtlPnmcWILJWf6oyaIZyYzLCPEo0SeMJGtxcEehIcWJfxY1bNBNhKxF/2UgXH90T3hlmq058WD4jWdxeqhr+W2+0XJHpM5kcPiwtvV+jN/Tt5Y+bDGRZ6le6Tx+Pc4BhQcgAFnqpAcWuF5dUZzyAN/EFYEjADnxZEXO4hfCqQpl17AJUCzUQShx+m1/U90Ox/hfNNvUAjhHJfEO0aF5rO93IJlU05Tk1y3PexS8fxlth4pzFDH6vTNLQy7yZGCJyATV4UlkVO+DBvj8SgXAQKBgQDJA4h/Vr6EiLuaCt0TMnl4xZtbO5Mzoop0tigkz2PFkgOOCnf4wOHDNHxmNh4ugccH8c8zcUCntBJVFyOjBjfKHOrYewrjsJTYGj05PvEjgICpFO6ObseqCgh7WSfwnTz9FN2WNNe37tJgeI2cvVVE8ni9FFYDVRzHh1LCImPlEQKBgQCxMk0X2g20105xfJEyUajShmuksWSX0eXbTp2wnd1xovzMV2SlvkYOjyQ5DBno5pgOpQOLOTct1ktJ00N46dmxNHNjmvDtHSxuayAY+hoiaVT9SXbcmxhdsSNCFWkX2neSKBUoMDLzQNJjk9yq4FA2R45c8Zg9Fupw2Q7gW3pJEQKBgQCJn9emh3dtOkhnUSTHbflozLJvJjzET3bILacSfMNsk2nHqDc3oqdvhNHfERJc+RGRyOb564I+z7T1eSA3D5zGzkGj8aok9dMSmfRs60Eh82sjWGd0MTiWnMPt377OqHkHNW/41TkUf3ieACvEsoRc7j3oBrJVGcLYgTgSHoWSYQKBgBT4av9DoxOf13y+zUAzQdOIbPzUsZ72DEnkB+17AVYNf/V+8j5bQlc+wX6u2/IgOCajiMPVRZ57GfXmtrl8k3LRTkafc5vcNAHtU6MhFi60YIaJRzPcdwm/iPitJ/7WsAfwDnDvI1h+d2epfBgM6/FAjqyZOvcf2yb3yTbsHJDBAoGAB6ibPiNymOpQErJPK0OOzXPahj1kMPOIFvXzPUNZM+cp08CrWH4eoW+aqwgYQ7xLFuDAIbal4gtcYsx5aX5j0GJj0OMrOnsgltEb/Pxxin19AJwttAzzZyTtSNdzXwCVM2OS0iAijGD8k9ISldi6VR3lgPOU3RcSXlo+sWEamgk=";
    // 支付宝公钥

    /*public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4DjuodLs32KHFj0fjaCs/zAYDO+rWEwnom1G0Kaa8JwdjRU0jdAprH7g7RA0mbtWEBp9jIH+HK6UfVftTujOA1wjRYFUo7QcCVB/p3W8SC6lw0HVObEgH4xc/zks3XY8RxtD+mxagTiMfEJm/kutHnlwJm/jVau7V/YkxXXoZddsRcNSdID1J3gWTv7I5hcyWEqXSHO3Ldj+qtt3otIcbQEij5RwyWaGKsXtxuqAS269m01N6dJPprCmopdw1nGqC4emZOfaLA3WujVEE7l8KgIj9OatezyU6Z2B3s4aB/UIDbMoKxCEWmbyliId4NVYRBTwobz3UY0PsSbOpTq5nwIDAQAB";*/
    public static String ALIPAY_PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwzM/D+sOaZojnvNqLuuIAJsLyzZH1uDHiBC3C5HTPhXPIFDGXK9SGVFKzyag1UgmRzlzq7SRNfB3LxpoM1twVm+ISPozDKkWkO2St+zauOaGOie8Rn2D1jVfYG4blqH7eXy5ILH8kjs3841ijsDuUs3KJfb/Fgatz+Bu8a+pPhCRQ7O8AZSaydLLZ6A4EJSWknIIhW9ti4blhjJj6Vyr2fRD0M6swJ5eTHcNMuEwyIrpSNxKR9wnU2DKLG4TmobgTZdtG6V4ATcONaR0JXbF+2UyTKzPZxoKCeOR7SMbGYJ2dhTGXE0F7l4CYxtFvduenYqd1rLMh7dbamkrjNjF6QIDAQAB";

    //success   http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp

    public static String notify_url = "http://localhost:8080/#/frontIndex";



    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问

    public static String return_url = "http://localhost:8080/#/frontIndex";



    // 签名方式

    public static String sign_type = "RSA2";



    // 字符编码格式

    public static String charset = "utf-8";



    // 支付宝网关

    public static String URL = "https://openapi.alipaydev.com/gateway.do";



    // 支付宝网关

    public static String log_path = "C:\\";





//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑



    /**

     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）

     *

     * @param sWord 要写入日志里的文本内容

     */

    public static void logResult(String sWord) {

        FileWriter writer = null;

        try {

            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");

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