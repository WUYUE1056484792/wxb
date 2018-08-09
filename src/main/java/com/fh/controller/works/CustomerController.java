package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.works.CustomerService;
import com.fh.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 类名称：AppuserController
 * 创建人：FH 
 * 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/custmoer")
public class CustomerController extends BaseController {
	

	@Resource(name="customerService")
	private CustomerService customerService;




	/**
	 * 显示顾客列表
	 */
	@RequestMapping(value="/listCustomer")
	public ModelAndView listCustomer(Page page){
		System.out.println("显示顾客列表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();


			page.setPd(pd);
			List<PageData>	customerList = customerService.listCustomer(page);			//列出顾客列表


			mv.setViewName("works/customer/customer_list");
			mv.addObject("customerList", customerList);

			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}

		return mv;
	}

	/**
	 * 去修改顾客页面
	 */
	@RequestMapping(value="/goEditCustomer")
	public ModelAndView goEditCustomer(){
		System.out.println("去修改顾客页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		try {

			pd = customerService.findCustomerByUiId(pd);								//根据ID读取
			List<PageData> statusList=customerService.ListCustomerStatus();
			List<PageData> typeList=customerService.ListCustomerType();
			mv.setViewName("works/customer/customer_edit");
			mv.addObject("msg", "editCustomer");
			mv.addObject("pd", pd);
			mv.addObject("statusList", statusList);
			mv.addObject("typeList", typeList);

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}


	/**
	 * 修改用户信息
	 */
	@RequestMapping(value="/editCustomer")
	public ModelAndView editU(PrintWriter out) throws Exception{
		System.out.println("修改用户信息");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		customerService.editCustomer(pd);

		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	//===================================================================================================
	
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
