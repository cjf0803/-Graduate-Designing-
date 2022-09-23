package com.neu.pay.controller;

import com.alipay.api.AlipayApiException;

import com.alipay.api.AlipayClient;

import com.alipay.api.DefaultAlipayClient;

import com.alipay.api.request.AlipayTradePagePayRequest;



import com.neu.pay.config.AlipayConfig;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;





import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



import java.util.UUID;



@Controller
@RequestMapping("zhifu")

//解决跨域问题

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class PayController {



    // @Autowired

    //private PetBiz petBiz;



//新增宠物

//    宠物出售信息一旦发布不允许修改，所以pet不写宠物信息修改

   /* @ResponseBody

    @RequestMapping(value = "/addPet")

    public String addPet(String petname, String pettype, String petimage,int price,String puname,String paddress,String petinfo,String petpath,String petcontact){

        Pet pet =new Pet(petname,pettype,petimage,price,puname,paddress,petinfo,petpath,petcontact);

        boolean boPet=petBiz.addPet(pet);

        if(boPet==true){

            return "success";

        }else{

            return "error";

        }

    }







    //查询所有  分页

    @ResponseBody

    @RequestMapping(value = "/petList")

    public Map<String,Object> petList(Integer index){

        if(index==null){

            index=1;

        }

        //pets页面一页显示5条数据

        int size=5;

        List<Pet> list=petBiz.petList(index, size);

        //创建Map将list和其他参数都存入map中

        Map<String,Object> map=new HashMap<String,Object>();

        //将值存入map中，vue后台取值

        map.put("list", list);

        map.put("index", index);

        return map;

    }



    //根据petid删除宠物信息

    @ResponseBody

    @RequestMapping("/deletePet")

    public String deletePet(int petid){

        boolean boDel=petBiz.deletePet(petid);

        if(boDel==true){

            return "success";

        }else {

            return "error";

        }

    }



    //根据id查询

    @ResponseBody

    @RequestMapping("/findById")

    public Pet findById(int petid){

        Pet pet=petBiz.findById(petid);

        return pet;

    }



    //chartPie

    @ResponseBody

    @RequestMapping(value = "/countState")

    public List<Map<Object,Object>> countType(Map<String,Object> map){

        return  petBiz.countState();

    }



    //支付宝沙箱支付

    @ResponseBody

    @RequestMapping("alipay")

    public void alipay(String petname, int price,HttpServletRequest re quest, HttpServletResponse response) throws AlipayApiException, IOException {

        //根据支付宝的配置生成一个支付客户端

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL

                , AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json",

                AlipayConfig.charset, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        AlipayTradePagePayRequest requestali = new AlipayTradePagePayRequest();

        requestali.setReturnUrl(AlipayConfig.return_url);

        requestali.setNotifyUrl(AlipayConfig.notify_url);

        //通知数据中的out_trade_no为商户系统中创建的订单号

        String out_trade_no = UUID.randomUUID().toString();

        requestali.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","

                + "\"total_amount\":\"" + price + "\","

                + "\"subject\":\"" + "'乐宠'宠物购买平台" + "\","

                + "\"body\":\"" + petname + "\","

                + "\"timeout_express\":\"100m\","

                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");



        //请求

        String result = "";

        try {

            result = alipayClient.pageExecute(requestali).getBody();

        } catch (AlipayApiException e) {

            e.printStackTrace();

        }



        //输出

        response.setContentType("text/html;charset=" + AlipayConfig.charset);

        response.getWriter().write(result);// 直接将完整的表单html输出到页面

        response.getWriter().flush();

        response.getWriter().close();

    }

    */

    //支付宝沙箱支付

    @GetMapping("/alipay/{totalPrice}")
    public void alipay(@PathVariable("totalPrice") double totalPrice, HttpServletRequest request, HttpServletResponse response) throws AlipayApiException, IOException {

        System.out.println(totalPrice);

        //根据支付宝的配置生成一个支付客户端

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL

                , AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json",

                AlipayConfig.charset, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        AlipayTradePagePayRequest requestali = new AlipayTradePagePayRequest();

        requestali.setReturnUrl(AlipayConfig.return_url);

        requestali.setNotifyUrl(AlipayConfig.notify_url);

        //通知数据中的out_trade_no为商户系统中创建的订单号

        String out_trade_no = UUID.randomUUID().toString();

        requestali.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","

                + "\"total_amount\":\"" + totalPrice + "\","

                + "\"subject\":\"" + "吾爱理财" + "\","

//                + "\"body\":\"" + cname + "\","

                + "\"timeout_express\":\"100m\","

                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");



        //请求

        String result = "";

        try {

            result = alipayClient.pageExecute(requestali).getBody();

            System.out.println(result.toString());

        } catch (AlipayApiException e) {

            e.printStackTrace();

        }




        //输出

        response.setContentType("text/html;charset=" + AlipayConfig.charset);

        response.getWriter().write(result);// 直接将完整的表单html输出到页面

        response.getWriter().flush();

        response.getWriter().close();

    }



}