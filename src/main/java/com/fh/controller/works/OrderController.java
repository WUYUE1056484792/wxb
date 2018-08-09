package com.fh.controller.works;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.works.CustomerService;
import com.fh.service.works.OrderService;
import com.fh.util.Const;
import com.fh.util.PageData;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * 类名称：AppuserController
 * 创建人：FH 
 * 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/order")
public class OrderController extends BaseController {
	

	@Resource(name="orderService")
	private OrderService orderService;




	/**
	 * 显示订单列表
	 */
	@RequestMapping(value="/listOrder")
	public ModelAndView listCustomer(Page page){
		System.out.println("显示订单列表");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();


			page.setPd(pd);
			List<PageData>	orderList = orderService.listOrder(page);			//列出顾客列表


			mv.setViewName("works/order/order_list");
			mv.addObject("orderList", orderList);

			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}

		return mv;
	}

	/**
	 * 去订单详情页面
	 */
	@RequestMapping(value="/goOrderDetail")
	public ModelAndView goOrderDetail(){
		System.out.println("去订单详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		try {

			pd = orderService.findOrderByOid(pd);								//根据order_id查询
			List<PageData> orderGood=orderService.ListOrderGood(pd);          //根据订单号根据order_id查询订单商品信息

			mv.setViewName("works/order/order_detail");

			mv.addObject("pd", pd);
			mv.addObject("orderGood", orderGood);


		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
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
