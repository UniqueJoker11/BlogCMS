package colin.app.core.dao;

import colin.app.common.bean.Page;
import colin.app.core.dao.common.CommonDao;
import colin.app.core.pojo.UserEntity;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by joker on 14-9-13.
 */
@Repository
public class UserManageDao extends CommonDao<UserEntity> {
    /**
     * 单一数据删除
     *
     * @param userEntity
     */
    @Override
    public boolean deleteObjInfo(UserEntity userEntity) {
        return false;
    }

    /**
     * 单一数据更新
     *
     * @param userEntity
     */
    @Override
    public boolean updateObjInfo(UserEntity userEntity) {
        return false;
    }

    /**
     * 排序+分页功能+条件查询
     *
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    @Override
    public <E> List<E> getOrderObjects(Class cl, Map map, String orderstr, Integer beginpos, Integer count) {
        return null;
    }

    /**
     * 排序(升序)+分页功能+条件查询
     *
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    @Override
    public <E> List<E> getOrderAscObjects(Class cl, Map map, String orderstr, Integer beginpos, Integer count) {
        return null;
    }

    /**
     * 分页查询 ，传一个hql语句. 和一个参数数组.
     *
     * @param hql       hql语句
     * @param bindValue 数组参数
     * @param first     分页起点
     * @param count     每页的记录总数
     * @return 返回List集合
     */
    @Override
    public List pageQuery(String hql, Object[] bindValue, Integer first, Integer count) {
        return null;
    }

    /**
     * 根据 主键 查询某个对象
     *
     * @param c
     * @param id
     * @return
     */
    @Override
    public <E> E selectObjectById(Class c, Serializable id) {
        return null;
    }

    /**
     * 根据条件,查询一个对象.
     *
     * @param c
     * @param map map放条件查询参数 调用的时候?: String username="xiejin" ;
     *            map.put("username",username);
     * @return
     */
    @Override
    public <E> E selectUniqueObject(Class c, Map map) {
        return null;
    }

    /**
     * 带条件的查询.返回list集合
     *
     * @param c
     * @param map 根据map里面放置的参数
     * @return 返回一个list对象集合
     */
    @Override
    public <E> List<E> seletcObjectByMap(Class c, Map map) {
        return null;
    }

    /**
     * 一个泛型方法:支持条件查询,排序,分页查询.
     *
     * @param cl         需要查询的类
     * @param map        map中put("uname","谢晋"); null or map
     *                   模糊查询用("uname","%"+uname+"%")
     * @param orderStr   是否需要排序(升序) null or "属性字段"
     * @param beginIndex 分页开始位置 null or Integer
     * @param count      记录条数 null or Integer
     * @return
     */
    @Override
    public <E> List<E> selectObjInfoByMapCondtionAndOrderAndPageQuery(Class cl, Map map, String orderStr, Integer beginIndex, Integer count) {
        return null;
    }

    @Override
    public <E> boolean existsSearchObj(Class clazz, Map<String, Object> params) {
        return false;
    }

    /**
     * 分页数据查找
     *
     * @param paramsMap
     */
    @Override
    public Page<UserEntity> searchObjPageInfo(Map<String, Object> paramsMap) {
      /*  //初始化设置当前的分页大小
        int pageSize = 10;
        //设定当前的其实页数
        int currentPage = 1;
        if (paramsMap.get("pageSize") != null && !paramsMap.get("pageSize").toString().equals("")) {
            pageSize = Integer.valueOf(paramsMap.get("pageSize").toString());
        }
        if (paramsMap.get("currentPage") != null && !paramsMap.get("currentPage").toString().equals("")) {
            currentPage = Integer.valueOf(paramsMap.get("currentPage").toString());
        }
        //获取到当前页的所有内容
        List<UserEntity> currentList=this.getOrderObjects(UserEntity.class,paramsMap,null,currentPage,pageSize);

        Criteria criteria = this.getCurrentSession().createCriteria(UserEntity.class);
        //获取到当前的所有对象数量
        List<UserEntity> aticleList = super.getHibernateTemplate().loadAll(UserEntity.class);
        //初始化当前的分页对象
        Page<UserEntity> pageResult = new Page<UserEntity>();
        //查询出所有的记录和计算出所有的分页数
        pageResult.setTotalRecord(aticleList.size());
        int allPageSize = 0;
        if (aticleList.size() % pageSize != 0) {
            allPageSize = aticleList.size() / pageSize + 1;
        } else {
            allPageSize = aticleList.size() / pageSize;
        }
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPage(allPageSize);
        //此处查询可以查询处当前页的数据，还需要所有的数据行和总记录数
        pageResult.setCurrentPage(currentPage);
        pageResult.setResultList(currentList);*/
        return null;
    }
}
