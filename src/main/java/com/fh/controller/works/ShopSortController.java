package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.works.ShopSortService;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Statistics")
public class ShopSortController extends BaseController {
    String menuUrl = "Statistics/Statistics.do"; //菜单地址(权限用)
    @Resource(name="shopSortService")
    private ShopSortService shopSortService;




    /**
     * 统计图
     */
    @RequestMapping(value = "/Statistics")
    public ModelAndView list(Page page) {
        logBefore(logger, "统计图");
        // if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        // //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            page.setPd(pd);
            //查询使用中店铺数量
            PageData shopCountUSE = shopSortService.findShopCountUSE();
            pd.put("shopCountUSE",shopCountUSE.get("count"));

            //查询审核中店铺数量
            PageData shopCountAPP = shopSortService.findShopCountAPP();
            pd.put("shopCountAPP",shopCountAPP.get("count"));

            //查询冻结店铺数量
            PageData shopCountSHOP_FRE = shopSortService.findShopCountSHOP_FRE();
            pd.put("shopCountSHOP_FRE",shopCountSHOP_FRE.get("count"));


            PageData shop = new PageData();
            shop.put("shopCountUSE",shopCountUSE.get("count"));
            shop.put("shopCountAPP",shopCountAPP.get("count"));
            shop.put("shopCountSHOP_FRE",shopCountSHOP_FRE.get("count"));

            pd.put("shop",shop);


            PageData user = new PageData();
            //用户分布
            pd.put("user_status_id","NORMAL");//正常用户
            PageData NORMAL_USER = shopSortService.findCountNORMAL_USER(pd);
            user.put("NORMAL_USER",NORMAL_USER.get("count"));

            pd.put("user_status_id2","DISABLED");//不可用用户
            pd.put("user_status_id3","DELETE");//删除用户
            pd.put("user_status_id4","FREEZE");//冻结用户
            PageData IMNORMAL_USER = shopSortService.findCountNORMAL_USER(pd);
            user.put("IMNORMAL_USER",IMNORMAL_USER.get("count"));
            pd.put("user",user);






            /*//订单折线分布,查询七日订单的订单销售金额
            PageData sevenOrderSum = new PageData();
            //分别获取今天到七天前的日期
            pd.put("day",Dateago.currentDay());//7
            sevenOrderSum.put("sevenday",Dateago.currentDay().substring(5,10));
            PageData findSevenOrderSum7 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum7){
                sevenOrderSum.put("seven","0");
            }else{
                System.out.println("findSevenOrderSum7"+findSevenOrderSum7);
                sevenOrderSum.put("seven",findSevenOrderSum7.get("amount"));
            }


            pd.put("day",Dateago.oneAgoDay());//6
            sevenOrderSum.put("sixday",Dateago.oneAgoDay().substring(5,10));
            PageData findSevenOrderSum6 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum6){
                sevenOrderSum.put("six","0");
            }else{
                sevenOrderSum.put("six",findSevenOrderSum6.get("amount"));
            }


            pd.put("day",Dateago.twoAgoDay());
            sevenOrderSum.put("fiveday",Dateago.twoAgoDay().substring(5,10));
            PageData findSevenOrderSum5 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum5){
                sevenOrderSum.put("five","0");
            }else{
                sevenOrderSum.put("five",findSevenOrderSum5.get("amount"));
            }


            pd.put("day",Dateago.threeAgoDay());
            sevenOrderSum.put("fourday",Dateago.threeAgoDay().substring(5,10));
            PageData findSevenOrderSum4 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum4){
                sevenOrderSum.put("four","0");
            }else{
                sevenOrderSum.put("four",findSevenOrderSum4.get("amount"));
            }


            pd.put("day",Dateago.fourAgoDay());
            sevenOrderSum.put("threeday",Dateago.fourAgoDay().substring(5,10));
            PageData findSevenOrderSum3 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum3){
                sevenOrderSum.put("three","0");
            }else {
                sevenOrderSum.put("three",findSevenOrderSum3.get("amount"));
            }


            pd.put("day",Dateago.fiveAgoDay());
            sevenOrderSum.put("twoday",Dateago.fiveAgoDay().substring(5,10));
            PageData findSevenOrderSum2 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum2){
                sevenOrderSum.put("two","0");
            }else{
                sevenOrderSum.put("two",findSevenOrderSum2.get("amount"));
            }


            pd.put("day",Dateago.sixAgoDay());
            sevenOrderSum.put("oneday",Dateago.sixAgoDay().substring(5,10));
            PageData findSevenOrderSum1 = shopSortService.findSevenOrderSum(pd);
            if(null==findSevenOrderSum1){
                sevenOrderSum.put("one","0");
            }else{
                sevenOrderSum.put("one",findSevenOrderSum1.get("amount"));
            }*/





         //重复
            //查询平台七日订单总金额
            PageData shopOrderSum = new PageData();
            //查询数据表中的order_show平台七日总金额变化,并且展示在平台订单总金额折线图中
            List<PageData> findOrderSum = shopSortService.findOrderSum(pd);

            for(int i=0;i<findOrderSum.size();i++){
                if(null==findOrderSum.get(i).get("amounts")){
                    findOrderSum.get(i).put("amounts","0");
                }
                shopOrderSum.put("OrderDate"+i,findOrderSum.get(i).get("show_date").toString());
                shopOrderSum.put("orderAmounts"+i,findOrderSum.get(i).get("amounts").toString());
            }
            System.out.println("shopOrderSum"+shopOrderSum);




            //店铺排名
            PageData shopRanking = new PageData();
            //查询数据表中的shop_show店铺总金额排名,并且展示在店铺订单总金额排名图中
            List<PageData> findShopRanking = shopSortService.findShopRanking(pd);

            for(int i=0;i<findShopRanking.size();i++){
                shopRanking.put("ShopName"+i,findShopRanking.get(i).getString("shop_name"));
                shopRanking.put("ShopAmount"+i,findShopRanking.get(i).get("amounts").toString());
            }
            System.out.println("shopRanking"+shopRanking);


            //商品分类饼状图
            PageData shopSort = new PageData();
            //查询数据表中的good_type_show商品分类饼状图,并且展示在商品分类占比饼状图中
            List<PageData> findShopSort = shopSortService.findShopSort(pd);

            for(int i=0;i<findShopSort.size();i++){
                shopSort.put("ShopSortName"+i,findShopSort.get(i).getString("name"));
                shopSort.put("ShopSortAmount"+i,findShopSort.get(i).get("amounts").toString());
            }
            System.out.println("shopSort"+shopSort);



            //买家top10柱状图
            PageData shopCustomer = new PageData();
            //查询数据表中的customer_show商品分类饼状图,并且展示在商品分类占比饼状图中
            List<PageData> findShopCustomer = shopSortService.findShopCustomer(pd);
            System.out.println("zzzzzzz"+findShopCustomer);

            for(int i=0;i<findShopCustomer.size();i++){
                shopCustomer.put("ShopCustomerName"+i,findShopCustomer.get(i).getString("name"));
                shopCustomer.put("ShopCustomerAmount"+i,findShopCustomer.get(i).get("amounts").toString());
            }
            System.out.println("shopCustomer"+shopCustomer);



            mv.setViewName("works/Statistics/statistics");
            mv.addObject("pd", pd);
            mv.addObject("shopCustomer", shopCustomer);//买家排名
            mv.addObject("shopSort", shopSort);//店铺一级分类
            mv.addObject("shopRanking", shopRanking);//店铺排名
            mv.addObject("shop", shop);//店铺分布
            mv.addObject("user", user);//用户分布
            //mv.addObject("sevenOrderSum", sevenOrderSum);//用户分布
            mv.addObject("shopOrderSum", shopOrderSum);//平台订单总额


            mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
            System.out.println("@@pd   " + pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }




    /**
     *
     */
    /*@RequestMapping(value="shenheReserve")
    public @ResponseBody PageData shenheReserve()throws Exception{
        logBefore(logger, "审批预约");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        //ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //根据reserve_id查询预约详情
        commissionService.editReserveStatus(pd);
        pd.put("result", "success");
        return pd;
    }*/






    /* ===============================权限================================== */
    public Map<String, String> getHC() {
        Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
        org.apache.shiro.session.Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }

    /* ===============================权限================================== */

}
