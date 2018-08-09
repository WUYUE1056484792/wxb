package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.works.StaffService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.apache.axis.session.Session;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/staff")
public class StaffController extends BaseController {
    String menuUrl = "staff/list.do"; //菜单地址(权限用)
    @Resource(name="staffService")
    private StaffService staffService;



    /**
     * 列表
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) {
        logBefore(logger, "员工管理列表");
        // if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
        // //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        try {
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData> varList = staffService.list(page); // 列出DepotInfo列表
            mv.setViewName("works/Staff/Staffinfo/staffinfo_list");
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
     * 批量删除
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll() {
        logBefore(logger, "批量删除员工");
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
                staffService.deleteAll(ArrayDATA_IDS);
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
     * 去修改页面
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() {
        logBefore(logger, "去员工管理页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            pd = staffService.findById(pd); // 根据ID读取
            mv.setViewName("works/Staff/Staffinfo/staffinfo_edit");

            //String addtime = pd.get("ADDTIME").toString();
            //pd.put("ADDTIME", addtime.substring(0, addtime.length() - 2));

            //查询所有role ID和name
            List<PageData> role = staffService.findRole();

            //查询所有店铺
            List<PageData> shop = staffService.findShop();

            System.out.println("pdpd"+pd);

            mv.addObject("role", role);
            mv.addObject("shop", shop);
            mv.addObject("msg", "edit");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }





    /**
     * 修改员工角色
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        logBefore(logger, "修改员工角色");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;}
         * //校验权限
         */ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        staffService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }




    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) {
        logBefore(logger, "删除仓库管理");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
         */PageData pd = new PageData();
        try {
            pd = this.getPageData();
            staffService.delete(pd);
            out.write("success");
            out.close();
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }

    }



    /**
     * 去新增员工页面
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() {
        logBefore(logger, "去新增员工管理页面");
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        try {
            mv.setViewName("works/Staff/Staffinfo/staffinfo_edit");


            //查询所有role ID和name
            List<PageData> role = staffService.findRole();

            //查询所有店铺
            List<PageData> shop = staffService.findShop();

            //查询用户
            List<PageData> user = staffService.findUser();



            mv.addObject("role", role);
            mv.addObject("shop", shop);
            mv.addObject("user", user);


            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }




    /**
     * 新增
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, "新增员工");
        /*
         * if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
         * //校验权限
         */ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("universalid", this.get32UUID()); // 主键

        //根据user_id查询出用户昵称
        PageData user_nickname = staffService.findUserNickname(pd);
        pd.put("user_nickname",user_nickname.getString("user_nickname"));
        System.out.println("user_nickname"+user_nickname.getString("user_nickname"));

        staffService.save(pd);
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
