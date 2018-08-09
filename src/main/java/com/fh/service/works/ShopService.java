package com.fh.service.works;


import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shopService")
public class ShopService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /**
     *
     * @param page
     * @return 店铺列表分页
     * @throws Exception
     */
    public List<PageData> listPdPageShop(Page page)throws Exception{
        return (List<PageData>) dao.findForList("ShopMapper.shoplistPage", page);
    }

    /**
     *
     * @param pd  shop_id
     * @return 查询店铺信息
     * @throws Exception
     */
    public PageData findShopInfo(PageData pd)throws Exception{

        return (PageData)dao.findForObject("ShopMapper.findShopInfo",pd);
    }

    /**
     *
     * @return 店铺状态-
     * @throws Exception
     */
    public List<PageData> listShopType ()throws Exception {

        return (List<PageData> )dao.findForList("ShopMapper.listShopType",null);
    }

    /**
     *
     * @param pd  修改店铺状态 shop_id shop_validate_status_id
     * @throws Exception
     */
    public void updateShopStatus(PageData pd)throws Exception{
        dao.update("ShopMapper.updateShopStatus",pd);
    }
}
