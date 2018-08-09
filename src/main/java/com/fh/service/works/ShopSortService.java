package com.fh.service.works;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("shopSortService")
public class ShopSortService {
    @Resource(name = "daoSupport")
    private DaoSupport dao;


    /*
     * 查询所有店铺数据
     */
    public PageData findShopCount()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCount",null);
    }

    /*
     * 查询正常使用店铺数量
     */
    public PageData findShopCountUSE()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCountUSE",null);
    }

    /*
     * 查询不正常使用店铺数量
     */
    public PageData findShopCountNOUSE()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCountNOUSE",null);
    }

    /*
     * 查询审核中店铺数量
     */
    public PageData findShopCountAPP()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCountAPP",null);
    }

    /*
     * 查询冻结店铺数量
     */
    public PageData findShopCountSHOP_FRE()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCountSHOP_FRE",null);
    }


    /*
     * 查询编辑中的店铺数量
     */
    public PageData findShopCountSHOP_SET()throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findShopCountSHOP_SET",null);
    }



    /*
     * 查询正常用户的数量
     */
    public PageData findCountNORMAL_USER(PageData pd)throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findCountNORMAL_USER",pd);
    }



    /*
     * 查询七日订单总金额
     */
    public PageData findSevenOrderSum(PageData pd)throws Exception{
        return (PageData)dao.findForObject("ShopSortMapper.findSevenOrderSum",pd);
    }



    /*
     * 查询七日店铺前十名订单总额
     */
    public List<PageData> findShopRanking(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findShopRanking",pd);
    }


    /*
     * 查询七日平台订单总金额变化折线图
     */
    public List<PageData> findOrderSum(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findOrderSum",pd);
    }



    /*
     * 查询七日店铺分类
     */
    public List<PageData> findShopSort(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findShopSort",pd);
    }


    /*
     * 查询七日买家top
     */
    public List<PageData> findShopCustomer(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findShopCustomer",pd);
    }


    /*
     * 删除买家top10
     */
    public void deleteShopCustomer()throws Exception{
        dao.delete("ShopSortMapper.deleteShopCustomer", null);
    }


    /*
     * 删除订单金额
     */
    public void deleteOrderSum()throws Exception{
        dao.delete("ShopSortMapper.deleteOrderSum", null);
    }



    /*
     * 查询真实的七日买家订单总额最高排序
     */
    public List<PageData> selectBuyer()throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.selectBuyer",null);
    }


    /*
     * 新增买家订单总额和总数
     */
    public void saveBuyerTop10(PageData pd)throws Exception{
        dao.save("ShopSortMapper.saveBuyerTop10", pd);
    }



    /*
     * 删除商品分类的数据
     */
    public void deleteGoodType()throws Exception{
        dao.delete("ShopSortMapper.deleteGoodType", null);
    }



    /*
     * 查询一级商品分类
     */
    public List<PageData> findGoodTypeList()throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findGoodTypeList",null);
    }


    /*
     * 新增商品一级分类数据
     */
    public void saveGoodType(PageData pd)throws Exception{
        dao.save("ShopSortMapper.saveGoodType", pd);
    }




    /*
     * 删除排名的数据
     */
    public void deleteShopRank()throws Exception{
        dao.delete("ShopSortMapper.deleteShopRank", null);
    }



    /*
     * 查询店铺排名的相关数据
     */
    public List<PageData> findShopRankList()throws Exception{
        return (List<PageData>)dao.findForList("ShopSortMapper.findShopRankList",null);
    }


    /*
     * 新增店铺排名数据
     */
    public void saveShopRank(PageData pd)throws Exception{
        dao.save("ShopSortMapper.saveShopRank", pd);
    }


    /*
     * 新增当天订单总额数据
     */
    public void saveOrderSum(PageData pd)throws Exception{
        dao.save("ShopSortMapper.saveOrderSum", pd);
    }




}
