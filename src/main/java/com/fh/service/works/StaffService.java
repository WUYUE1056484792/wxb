package com.fh.service.works;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.Const;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("staffService")
public class StaffService {

    @Resource(name = "daoSupport")
    private DaoSupport dao;

    /*
     *列表
     */
    public List<PageData> list(Page page)throws Exception{
        return (List<PageData>)dao.findForList("StaffMapper.datalistPage", page);
    }


    /*
     * 通过universlid获取数据
     */
    public PageData findById(PageData pd)throws Exception{
        return (PageData)dao.findForObject("StaffMapper.findById", pd);
    }


    /*
     * 批量删除
     */
    public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
        dao.delete("StaffMapper.deleteAll", ArrayDATA_IDS);
    }


    /*
     * 查询角色编号和名称
     */
    public List<PageData> findRole()throws Exception{
        return (List<PageData>)dao.findForList("StaffMapper.findRole", null);
    }


    /*
     * 查询角色编号和名称
     */
    public List<PageData> findShop()throws Exception{
        return (List<PageData>)dao.findForList("StaffMapper.findShop", null);
    }



    /*
     * 根据用户编号查询用户昵称
     */
    public PageData findUserNickname(PageData pd)throws Exception{
        return (PageData)dao.findForObject("StaffMapper.findUserNickname", pd);
    }

    /*
     * 查询角色编号和名称
     */
    public List<PageData> findUser()throws Exception{
        return (List<PageData>)dao.findForList("StaffMapper.findUser", null);
    }




    /*
     * 新增
     */
    public void save(PageData pd)throws Exception{
        dao.save("StaffMapper.save", pd);
    }


    /*
     * 修改员工角色
     */
    public void edit(PageData pd)throws Exception{
        System.out.println("@@@  edit  pd "+pd);
        dao.update("StaffMapper.edit", pd);
    }


    /*
     * 删除
     */
    public void delete(PageData pd)throws Exception{
        dao.delete("StaffMapper.delete", pd);
    }

}
