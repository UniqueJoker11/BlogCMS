package colin.app.core.dao.common;

import colin.app.common.bean.Page;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joker on 14-9-13.
 */
public abstract class CommonDao<T> extends NamedParameterJdbcDaoSupport {

    /**
     * 单一增加数据
     */

    public boolean addObjInfo(T t) {
        //声明增加SQL语句
        StringBuilder insertSql = new StringBuilder("insert into ");
        StringBuilder insertSqlVal = new StringBuilder(" values(");
        //获取实体类的表明
        insertSql.append(this.getEntityTableName(t)).append(" (");
        //获取每个字段的对应表字段
        Map<String, Object> addParamsMap = getEntityParamsGroup(t, 0);

        insertSql.append(addParamsMap.get("insertSql").toString()).replace(insertSql.length() - 1, insertSql.length(), ")");
        insertSqlVal.append(addParamsMap.get(insertSqlVal).toString()).replace(insertSqlVal.length() - 1, insertSqlVal.length(), ")");
        insertSql.append(insertSqlVal);
        int result = this.getNamedParameterJdbcTemplate().update(insertSql.toString(), (Map<String, Object>) addParamsMap.get("params"));
        if (result != 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 单一数据删除
     */
    public boolean deleteObjInfo(T t) {
        //删除语句
        StringBuilder delSql = new StringBuilder("delete from ");
        delSql.append(this.getEntityTableName(t)).append(" where ");
        //拼接删除条件
        Map<String, Object> delParamMap = getEntityParamsGroup(t, 1);
        delSql.append(delParamMap.get("delSql").toString());
        delSql.subSequence(0, delSql.lastIndexOf("  and "));
        int result = this.getNamedParameterJdbcTemplate().update(delSql.toString(), (Map<String, Object>) delParamMap.get("params"));
        if (result != 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 单一数据更新,该方法仅支持主键更新
     */
    public boolean updateObjInfo(T t) {
        StringBuilder updateSql = new StringBuilder("update ");
        updateSql.append(this.getEntityTableName(t)).append(" set ");
        Map<String, Object> paramsMap = this.getEntityParamsGroup(t, 2);

        updateSql.append(paramsMap.get("updateSql").toString().substring(paramsMap.get("updateSql").toString().length()-1)).append(" where ").append(paramsMap.get("updateSqlCondition"));
       int result=this.getNamedParameterJdbcTemplate().update(updateSql.toString(), (Map<String, Object>) paramsMap.get("params"));
        if(result!=1){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 排序+分页功能+条件查询
     *
     * @param <E>
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    public abstract <E> List<E> getOrderObjects(final Class cl, final Map map,
                                                final String orderstr, final Integer beginpos, final Integer count);

    /**
     * 排序(升序)+分页功能+条件查询
     *
     * @param <E>
     * @param cl       当前操作对象
     * @param map      条件参数
     * @param orderstr 排序字段 如果为null不排序
     * @param beginpos 分页起点 如果为null不分页
     * @param count    每页的记录总数 如果为null不分页
     * @return 返回List集合
     */
    public abstract <E> List<E> getOrderAscObjects(final Class cl, final Map map,
                                                   final String orderstr, final Integer beginpos, final Integer count);

    /**
     * 分页查询 ，传一个hql语句. 和一个参数数组.
     *
     * @param hql       hql语句
     * @param bindValue 数组参数
     * @param first     分页起点
     * @param count     每页的记录总数
     * @return 返回List集合
     */
    public abstract List pageQuery(final String hql, final Object[] bindValue,
                                   final Integer first, final Integer count);


    /**
     * 根据 主键 查询某个对象
     *
     * @param <E>
     * @param c
     * @param id
     * @return
     */
    public abstract <E> E selectObjectById(final Class c, final Serializable id);

    /**
     * 根据条件,查询一个对象.
     *
     * @param <E>
     * @param c
     * @param map map放条件查询参数 调用的时候?: String username="xiejin" ;
     *            map.put("username",username);
     * @return
     */
    public abstract <E> E selectUniqueObject(final Class c, final Map map);

    /**
     * 带条件的查询.返回list集合
     *
     * @param <E>
     * @param c
     * @param map 根据map里面放置的参数
     * @return 返回一个list对象集合
     */
    public abstract <E> List<E> seletcObjectByMap(final Class c, final Map map);

    /**
     * 一个泛型方法:支持条件查询,排序,分页查询.
     *
     * @param <E>        类别
     * @param cl         需要查询的类
     * @param map        map中put("uname","谢晋"); null or map
     *                   模糊查询用("uname","%"+uname+"%")
     * @param orderStr   是否需要排序(升序) null or "属性字段"
     * @param beginIndex 分页开始位置 null or Integer
     * @param count      记录条数 null or Integer
     * @return
     */
    @SuppressWarnings("unchecked")
    public abstract <E> List<E> selectObjInfoByMapCondtionAndOrderAndPageQuery(
            final Class cl, final Map map, final String orderStr,
            final Integer beginIndex, final Integer count);

    public abstract <E> boolean existsSearchObj(Class clazz, Map<String, Object> params);

    /**
     * 分页数据查找,初始化，首次调用该方法。此后调用新的的方法
     */

    public abstract Page<T> searchObjPageInfo(Map<String, Object> paramsMap);

    private final String getEntityTableName(T t) {
        Table table = t.getClass().getAnnotation(Table.class);
        return table.name();
    }

    /**
     * 返回查询语句和其参数Map
     *
     * @param t
     * @param choose 0:增加一个对象，1:删除一个对象，2：修改一个对象,3:查询
     * @return
     */
    private final Map<String, Object> getEntityParamsGroup(T t, int choose) {
        //获取实体类的Fields
        Field[] fields = t.getClass().getDeclaredFields();
        //存放结果对象
        Map<String, Object> resultMap = new HashMap<>();
        //参数
        Map<String, Object> params = new HashMap<String, Object>();
        //根据条件返回相应的Sql和变量
        switch (choose) {
            //插入
            case 0:
                //声明存放插入语句的sql
                StringBuilder insertSql = new StringBuilder(""), insertSqlCondition = new StringBuilder("");
                //循环遍历fields,获取变量的名和值
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            insertSql.append(columnName + ",");
                            insertSqlCondition.append(":").append(columnName).append(",");
                            //存放参数
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error("拼接增加语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("insertSql", insertSql.toString());
                resultMap.put("insertSqlCondition", insertSqlCondition.toString());
                resultMap.put("params", params);
                break;
            //删除
            case 1:
                StringBuilder delSql = new StringBuilder("");
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            delSql.append(columnName).append("=:").append(columnName).append(" and ");
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error("拼接删除语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("delSql", delSql.toString());
                resultMap.put("params", params);
                break;
            //修改一个对象,根据主键更新
            case 2:
                StringBuilder updateSql = new StringBuilder(""), updateSqlCondition = new StringBuilder();
                for (Field field : fields) {
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            updateSql.append(columnName).append("=:").append(columnName).append(",");
                            if (field.getAnnotation(Id.class) != null) {
                                updateSqlCondition.append(columnName).append("=:").append(columnName);
                            }
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error("拼接修改语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put("updateSql", updateSql.toString());
                resultMap.put("updateSqlCondition", updateSqlCondition.toString());
                resultMap.put("params", params);
                break;
            //根据条件查询一个对像，精确查询，非like查询
            case 3:
                StringBuilder querySql = new StringBuilder("");
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        if (field.get(t) != null) {
                            String columnName = field.getAnnotation(Column.class).name();
                            querySql.append(columnName).append("=:").append(columnName).append(" and ");
                            params.put(columnName, field.get(t));
                        }
                    } catch (IllegalAccessException e) {
                        logger.error("拼接删除语句时出错！");
                        e.printStackTrace();
                    }
                }
                resultMap.put(" querySql", querySql.toString());
                resultMap.put("params", params);
                break;
            default:

                break;

        }
        return resultMap;
    }
}
