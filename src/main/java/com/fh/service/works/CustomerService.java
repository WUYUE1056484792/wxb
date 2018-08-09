package com.fh.service.works;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("customerService")
public class CustomerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//======================================================================================
	

	
	/*
	*用户列表(供应商用户)
	*/
	public List<PageData> listCustomer(Page page)throws Exception{
		return (List<PageData>) dao.findForList("CustomerMapper.datalistPageCustomer", page);
	}


	public List<PageData> ListCustomerStatus()throws Exception{
		PageData pd=new PageData();
		pd.put("dictionary_type","user_status_id");   //查询顾客状态
		pd.put("dictionary_status","1");
		return (List<PageData>) dao.findForList("CustomerMapper.ListDictionary",pd );
	}

	public List<PageData> ListCustomerType()throws Exception{
		PageData pd=new PageData();
		pd.put("dictionary_type","user_type_id");  //查询顾客类型
		pd.put("dictionary_status","1");
		return (List<PageData>) dao.findForList("CustomerMapper.ListDictionary", pd);
	}

	public PageData findCustomerByUiId(PageData pd)throws Exception{
		return (PageData) dao.findForObject("CustomerMapper.findCustomerByUiId", pd);
	}

	/*
	 * 修改用户
	 */
	public void editCustomer(PageData pd)throws Exception{
		dao.update("CustomerMapper.editCustomer", pd);
	}
	
}
