package com.fh.service.works;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("orderService")
public class OrderService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//======================================================================================
	

	
	/*
	*用户列表(供应商用户)
	*/
	public List<PageData> listOrder(Page page)throws Exception{
		return (List<PageData>) dao.findForList("OrderMapper.datalistPageOrder", page);
	}

	public PageData findOrderByOid(PageData pd)throws Exception{

		return (PageData) dao.findForObject("OrderMapper.FindOrderByOid",pd );
	}

	public List<PageData> ListOrderGood(PageData pd)throws Exception{

		return (List<PageData>) dao.findForList("OrderMapper.ListOrderGood",pd );
	}


	
}
