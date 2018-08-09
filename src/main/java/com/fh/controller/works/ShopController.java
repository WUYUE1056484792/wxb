package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Role;
import com.fh.service.works.ShopService;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类名称：ShopController
 * 创建人：czy
 * 创建时间：2018年7月6日
 * @version
 */
@Controller
public class ShopController extends BaseController {
    @Resource(name="shopService")
    private ShopService shopService;

    /**
     *
     * @param page
     * @return 店铺列表分页
     * @throws Exception
     */
    @RequestMapping(value ="listShopPage")
    public ModelAndView listShop(Page page)throws Exception{
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        page.setPd(pd);
        List<PageData>   shopTypes =  shopService.listShopType();
        List<PageData>	shopList =shopService.listPdPageShop(page);

        mv.addObject("shopList", shopList);

        mv.addObject("pd", pd);
        mv.addObject("shopTypes", shopTypes);
        mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        mv.setViewName("works/shop/shop_list");
        return mv;
    }

    /**
     *
     shop_id
     * @return 查询店铺信息
     * @throws Exception
     */
    @RequestMapping(value ="findShopInfo")
    public ModelAndView findShopInfo() {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        PageData  shop= null;
        List<PageData> shopTypes= new ArrayList<PageData>();

        try {
            shop = shopService.findShopInfo(pd);
            shopTypes =  shopService.listShopType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mv.setViewName("works/shop/shop_edit");
        mv.addObject("shop", shop);
        mv.addObject("shopTypes", shopTypes);
        mv.addObject("msg", "shopEdit");
        mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
        return mv;
    }

    /**
     *
     * 修改店铺状态 shop_id shop_validate_status_id
     * @throws Exception
     */
    @RequestMapping(value = "shopEdit")
    public ModelAndView shopEdit(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            shopService.updateShopStatus(pd);
            mv.addObject("msg","success");
        } catch (Exception e) {
            mv.addObject("msg","failed");
            e.printStackTrace();
        }

        mv.setViewName("save_result");

        return mv;
    }

    /* ===============================权限================================== */
    public Map<String, String> getHC(){
        Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
        Session session = currentUser.getSession();
        return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
    }
    /* ===============================权限================================== */
}
