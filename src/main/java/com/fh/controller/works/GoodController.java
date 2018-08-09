package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.works.GoodService;
import com.fh.service.works.StaffService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="/good")
public class GoodController extends BaseController{
    String menuUrl = "good/list.do"; //菜单地址(权限用)
    @Resource(name="goodService")
    private GoodService goodService;
    @Resource(name="staffService")
    private StaffService staffService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        logBefore(logger, "商品管理列表");
        // if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        // //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData> varList = goodService.list(page); // 列出DepotInfo列表

            for(int i=0;i<varList.size();i++){
                if(null==varList.get(i).get("good_count")){
                    varList.get(i).put("good_count","0");
                }

                if(null==varList.get(i).get("good_price")){
                    varList.get(i).put("good_price","0");
                }
            }





            mv.setViewName("works/goodinfo/goodinfo_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
            mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
            System.out.println("@@varList   " + varList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }



    /**
     * 去修改页面
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() {
        logBefore(logger, "去商品编辑页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = goodService.findById(pd); // 根据ID读取
            mv.setViewName("works/goodinfo/goodinfo_edit");

            //查询商品一级分类
            List<PageData> good_type_one = goodService.findGood_type_one();

            //查询商品单位
            List<PageData> unit = goodService.findUnit();

            System.out.println("pdpdpdpdpdpd"+pd);
            //查询一级导航
            List<PageData> navigation = goodService.findNavigation(pd);

            //查询商品二级分类
            //List<PageData> good_type_two = goodService.findGood_type_two();

            //查询所有店铺
            //List<PageData> shop = staffService.findShop();



            mv.addObject("good_type_one", good_type_one);
            mv.addObject("unit", unit);
            mv.addObject("navigation", navigation);
            //mv.addObject("shop", shop);
            mv.addObject("msg", "edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }





    /**
     * 修改商品信息
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        logBefore(logger, "修改商品信息");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;}
         * //校验权限
         */ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        goodService.edit(pd);


        //判断商品价格表里有木有这个价格，有就修改，没有就新增
        PageData count = goodService.findCountPrice(pd);
        if(("0").equals(count.get("count").toString())){
            System.out.println("价格表里没有");
            pd.put("universalid", UuidUtil.get32UUID());
            pd.put("good_price_id", UuidUtil.get32UUID());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            pd.put("addtime",df.format(new Date()));
            goodService.savePrice(pd);
        }else{
            System.out.println("价格表里有");
            goodService.editPrice(pd);
        }



        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }


    /**
     * 查询二级分类
     */
    @RequestMapping(value="/findGood_type_two")
    public @ResponseBody List<PageData> findGood_type_two(HttpServletRequest request)throws Exception{
        logBefore(logger, "新增tihuomainfo");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        //ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //根据一级分类查询二级分类
        List<PageData> good_type_two = goodService.findGood_type_two(pd);

        return good_type_two;
    }


    /**
     * 根据店铺编号查询一级导航
     */
    @RequestMapping(value="/findNavigationOne")
    public @ResponseBody List<PageData> findNavigationOne(HttpServletRequest request)throws Exception{
        logBefore(logger, "根据店铺编号查询一级导航");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        //ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //根据店铺编号查询一级导航
        List<PageData> navigation_one = goodService.findNavigation(pd);

        return navigation_one;
    }

    /**
     * 查询二级分类
     */
    @RequestMapping(value="/findGood_type_three")
    public @ResponseBody List<PageData> findGood_type_three(HttpServletRequest request)throws Exception{
        logBefore(logger, "查询三级");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        //ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //根据一级分类查询三级分类
        List<PageData> good_type_three = goodService.findGood_type_three(pd);

        return good_type_three;
    }




    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) {
        logBefore(logger, "删除商品");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
         */PageData pd = new PageData();
        try {
            pd = this.getPageData();
            goodService.delete(pd);
            out.write("success");
            out.close();
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

    }



    /**
     * 批量删除
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll() {
        logBefore(logger, "批量删除商品");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;}
         * //校验权限
         */PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pd = this.getPageData();
            List<PageData> pdList = new ArrayList<PageData>();
            String DATA_IDS = pd.getString("DATA_IDS");
            if (null != DATA_IDS && !"".equals(DATA_IDS)) {
                String ArrayDATA_IDS[] = DATA_IDS.split(",");
                goodService.deleteAll(ArrayDATA_IDS);
                pd.put("msg", "ok");
            } else {
                pd.put("msg", "no");
            }
            pdList.add(pd);
            map.put("list", pdList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);
    }




    /**
     * 根据查询二级导航
     */
    @RequestMapping(value="/findTwoNavigation")
    public @ResponseBody List<PageData> findTwoNavigation(HttpServletRequest request)throws Exception{
        logBefore(logger, "查询二级导航");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
        //ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        //根据一级分类查询二级分类
        List<PageData> navigation_two = goodService.findTwoNavigation(pd);

        return navigation_two;
    }




    /**
     * 去新增商品页面
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() {
        logBefore(logger, "去新增商品管理页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("works/goodinfo/goodinfo_edit");

            //查询所有店铺
            List<PageData> shop = staffService.findShop();


            //查询商品一级分类
            List<PageData> good_type_one = goodService.findGood_type_one();

            //查询商品单位
            List<PageData> unit = goodService.findUnit();

            //查询一级导航
            List<PageData> navigation = goodService.findNavigation(pd);


            /*//查询所有role ID和name
            List<PageData> role = staffService.findRole();

            //查询用户
            List<PageData> user = staffService.findUser();
            mv.addObject("role", role);

            mv.addObject("user", user);*/

            mv.addObject("navigation", navigation);
            mv.addObject("good_type_one", good_type_one);
            mv.addObject("shop", shop);
            mv.addObject("unit", unit);
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }


    /**
     * 新增商品
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, "新增商品");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
         * //校验权限
         */ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("universalid", this.get32UUID()); // 主键

        Random random = new Random();
        String good_id="";
        for (int i=0;i<19;i++)
        {
            good_id+=random.nextInt(10);
        }
        System.out.println("good_id"+good_id);
        pd.put("good_id",good_id);
        pd.put("sale_status_id","on_sale");

        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        pd.put("addtime",df.format(new Date()));

        System.out.println("pdpdpdpdpdpd"+pd);
        //保存商品信息
        goodService.save(pd);
        //新增商品价格
        goodService.savePrice(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }


    /* ===============================权限================================== */
    public Map<String, String> getHC() {
        Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
        org.apache.shiro.session.Session session = currentUser.getSession();
        return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
    }

    /* ===============================权限================================== */

}
