package com.fh.service.works;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("goodService")
public class GoodService {
    @Resource(name="daoSupport")
    private DaoSupport dao;

    /*
     *列表
     */
    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.datalistPageGood", page);
    }


    /*
     * 通过universlid获取数据
     */
    public PageData findById(PageData pd)throws Exception{
        return (PageData)dao.findForObject("GoodMapper.findById", pd);
    }


    /*
     * 查询一级导航目录
     */
    public List<PageData> findGood_type_one()throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findGood_type_one", null);
    }

    /*
     * 查询二级导航目录
     */
    public List<PageData> findGood_type_two(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findGood_type_two", pd);
    }


    /*
     * 查询三级导航目录
     */
    public List<PageData> findGood_type_three(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findGood_type_three", pd);
    }


    /*
     * 查询商品单位
     */
    public List<PageData> findUnit()throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findUnit", null);
    }


    /*
     * 查询商品导航
     */
    public List<PageData> findNavigation(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findNavigation", pd);
    }


    /*
     * 查询二级导航
     */
    public List<PageData> findTwoNavigation(PageData pd)throws Exception{
        return (List<PageData>)dao.findForList("GoodMapper.findTwoNavigation", pd);
    }


    /*
     * 新增商品价格
     */
    public void savePrice(PageData pd)throws Exception{
        dao.save("GoodMapper.savePrice", pd);
    }

    /*
     * 查询商品价格是否有
     */
    public PageData findCountPrice(PageData pd)throws Exception{
        return (PageData)dao.findForObject("GoodMapper.findCountPrice", pd);
    }


    /*
     * 修改商品信息
     */
    public void edit(PageData pd)throws Exception{
        System.out.println("@@@  edit  pd "+pd);
        dao.update("GoodMapper.edit", pd);
    }



    /*
     * 修改商品价格
     */
    public void editPrice(PageData pd)throws Exception{
        System.out.println("@@@  editPrice  pd "+pd);
        dao.update("GoodMapper.editPrice", pd);
    }
    /*
     * 修改商品状态为删除
     */
    public void delete(PageData pd)throws Exception{
        System.out.println("@@@  delete  pd "+pd);
        dao.update("GoodMapper.delete", pd);
    }

    /*
     * 批量删除
     */
    public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
        dao.delete("GoodMapper.deleteAll", ArrayDATA_IDS);
    }


    /*
     * 新增商品
     */
    public void save(PageData pd)throws Exception{
        dao.save("GoodMapper.save", pd);
    }

}
