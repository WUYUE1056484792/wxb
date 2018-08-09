package com.fh.job;

import com.fh.service.works.ShopSortService;
import com.fh.util.Dateago;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lis on 16/7/6.
 */
@Component
public class TestQuartzJob {


    @Resource(name="shopSortService")
    private ShopSortService shopSortService;


    //@Scheduled(cron = "*/5 * * * * ?")
    //public void czy() {
    //    System.out.println("Hello 计时器" + new Date());
    //}


    //买家top10定时刷新
    //@Scheduled(cron = "*/30 * * * * ?")
    @Scheduled(cron = "0 0 1 * * ?")
    public void shenheReserve() throws Exception{
        System.out.println("执行了买家");


        //删除type为customer_show的数据
        shopSortService.deleteShopCustomer();
        //查询订单中的buyer_id的编号以及买家姓名，七天内订单总金额，总订单数，当前时间
        List<PageData> buyerTop10 = shopSortService.selectBuyer();

        // 获取当前时间
        Date now = new Date();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = sdfd.format(now);

        PageData pd = new PageData();
        pd.put("add_time",datetime);
        if(buyerTop10.size()>=10){  //大于10
            for(int i=0;i<10;i++){
                pd.put("id",buyerTop10.get(i).getString("buyer_id"));
                pd.put("name",buyerTop10.get(i).getString("user_nickname"));
                pd.put("amounts",buyerTop10.get(i).get("amount").toString());
                pd.put("counts",buyerTop10.get(i).get("count").toString());
                pd.put("uuid", UuidUtil.get32UUID());
                pd.put("type_id","customer_show");
                //e_statistics表中添加数据
                shopSortService.saveBuyerTop10(pd);
            }
        }else if(buyerTop10.size()>=0){  //小于10大于0
            for(int j=0;j<buyerTop10.size();j++){
                pd.put("id",buyerTop10.get(j).getString("buyer_id"));
                pd.put("name",buyerTop10.get(j).getString("user_nickname"));
                pd.put("amounts",buyerTop10.get(j).get("amount").toString());
                pd.put("counts",buyerTop10.get(j).get("count").toString());
                pd.put("uuid", UuidUtil.get32UUID());
                pd.put("type_id","customer_show");
                //e_statistics表中添加前10名数据
                shopSortService.saveBuyerTop10(pd);
            }
        }






        /*一级分类*/
        // 获取当前时间
        Date n = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = s.format(n);

        PageData goodType = new PageData();
        goodType.put("add_time",d);
        //删除type为good_type_show的数据
        shopSortService.deleteGoodType();
        //查询一级分类表中的good_type_one_id的编号以及分类名称，七天内订单总金额，总订单数，当前时间
        List<PageData> findGoodTypeList = shopSortService.findGoodTypeList();

        for(int i=0;i<findGoodTypeList.size();i++){
            if(null==findGoodTypeList.get(i).get("amount")){
                findGoodTypeList.get(i).put("amount","0");
            }
            goodType.put("id",findGoodTypeList.get(i).getString("good_type_one_id"));
            goodType.put("name",findGoodTypeList.get(i).getString("good_type_one_name"));
            goodType.put("amounts",findGoodTypeList.get(i).get("amount").toString());
            goodType.put("counts",findGoodTypeList.get(i).get("count").toString());
            goodType.put("uuid", UuidUtil.get32UUID());
            goodType.put("type_id","good_type_show");
            System.out.println("fffffffff"+goodType);
            //e_statistics表中添加数据
            shopSortService.saveGoodType(goodType);
        }







        /*一级分类*/
        // 获取当前时间
        Date n1 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = s.format(n1);

        PageData shopRank = new PageData();
        shopRank.put("add_time",d1);
        //删除type为good_type_show的数据
        shopSortService.deleteShopRank();
        //查询店铺排名的相关数据
        List<PageData> findShopRankList = shopSortService.findShopRankList();

        for(int i=0;i<10;i++){
            if(null==findShopRankList.get(i).get("amount")){
                findShopRankList.get(i).put("amount","0");
            }
            shopRank.put("id",findShopRankList.get(i).getString("shop_id"));
            shopRank.put("name",findShopRankList.get(i).getString("shop_name"));
            shopRank.put("amounts",findShopRankList.get(i).get("amount").toString());
            shopRank.put("counts",findShopRankList.get(i).get("count").toString());
            shopRank.put("uuid", UuidUtil.get32UUID());
            shopRank.put("type_id","shop_show");
            System.out.println("sssssss"+shopRank);
            //e_statistics表中添加数据
            shopSortService.saveShopRank(shopRank);
        }







        /*七日平台订单总额折线图*/
        //删除type为order_show的数据
        shopSortService.deleteOrderSum();


        // 获取当前时间
        Date n2 = new Date();
        SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d2 = s.format(n2);

        PageData sevenOrderSum = new PageData();
        sevenOrderSum.put("add_time",d2);

        //分别获取今天到七天前的日期
        pd.put("day", Dateago.currentDay());//7
        sevenOrderSum.put("show_date",Dateago.currentDay().substring(5,10));
        PageData findSevenOrderSum7 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum7){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum7"+findSevenOrderSum7);
            sevenOrderSum.put("Sum",findSevenOrderSum7.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("mmmmmmmmmm"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);



        //6天的订单总额
        pd.put("day", Dateago.oneAgoDay());//6
        sevenOrderSum.put("show_date",Dateago.oneAgoDay().substring(5,10));
        PageData findSevenOrderSum6 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum6){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum6"+findSevenOrderSum6);
            sevenOrderSum.put("Sum",findSevenOrderSum6.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);



        //5天的订单总额
        pd.put("day", Dateago.twoAgoDay());//5
        sevenOrderSum.put("show_date",Dateago.twoAgoDay().substring(5,10));
        PageData findSevenOrderSum5 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum5){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum5"+findSevenOrderSum5);
            sevenOrderSum.put("Sum",findSevenOrderSum5.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);



        //4天的订单总额
        pd.put("day", Dateago.threeAgoDay());//4
        sevenOrderSum.put("show_date",Dateago.threeAgoDay().substring(5,10));
        PageData findSevenOrderSum4 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum4){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum4"+findSevenOrderSum4);
            sevenOrderSum.put("Sum",findSevenOrderSum4.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);




        //3天的订单总额
        pd.put("day", Dateago.fourAgoDay());//3
        sevenOrderSum.put("show_date",Dateago.fourAgoDay().substring(5,10));
        PageData findSevenOrderSum3 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum3){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum3"+findSevenOrderSum3);
            sevenOrderSum.put("Sum",findSevenOrderSum3.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);




        //2天的订单总额
        pd.put("day", Dateago.fiveAgoDay());//2
        sevenOrderSum.put("show_date",Dateago.fiveAgoDay().substring(5,10));
        PageData findSevenOrderSum2 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum2){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum2"+findSevenOrderSum2);
            sevenOrderSum.put("Sum",findSevenOrderSum2.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);




        //1天的订单总额
        pd.put("day", Dateago.sixAgoDay());//1
        sevenOrderSum.put("show_date",Dateago.sixAgoDay().substring(5,10));
        PageData findSevenOrderSum1 = shopSortService.findSevenOrderSum(pd);
        if(null==findSevenOrderSum1){
            sevenOrderSum.put("Sum","0");
        }else{
            System.out.println("findSevenOrderSum1"+findSevenOrderSum1);
            sevenOrderSum.put("Sum",findSevenOrderSum1.get("amount"));
        }
        sevenOrderSum.put("uuid",UuidUtil.get32UUID());
        sevenOrderSum.put("type_id","order_show");
        System.out.println("ssssssss"+sevenOrderSum);
        //新增当天订单总额数据
        shopSortService.saveOrderSum(sevenOrderSum);



    }



}
