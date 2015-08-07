package colin.app.core.dao.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by joker on 14-9-13.
 */
@Repository
public class CommonDao {

    Logger logger= Logger.getLogger(CommonDao.class);
    private final static int CURRENT_PAGE=1;
    private final static int PAGE_SIZE=10;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *
     * 方法描述：返回调用的jdbcTemplate 注意事项：
     *
     * @return
     * @Exception 异常对象
     */
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

    /**
     *
     * 方法描述：向数据库添加一个对象 注意事项：
     *
     * @Exception 异常对象
     */
    public<T> boolean addOneObject(T t) {
        StringBuilder addSql = new StringBuilder("insert into ");
        addSql.append(this.getEntityTableName(t));
        try {
            Map<String, Object> addInfoMap = this.initAddSqlFragment(t);
            if (addInfoMap.containsKey("hasError")) {
                return false;
            } else {
                addSql.append(addInfoMap.get("addSqlFragment"));
                namedParameterJdbcTemplate.update(addSql.toString(),
                        (Map<String, Object>) addInfoMap.get("addParams"));
                return true;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 方法描述： 根据ID的值来进行查询某个对象的详细内容 注意事项：
     *
     * @return
     * @Exception 异常对象
     */
    public<T> T searchObjectById(Class<T> entity, String idVal,
                              RowMapper<T> rowMapper) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        // 拼接表明
        searchSql.append(this.getEntityTableNameByClazz(entity));
        // 拼接条件
        String idName = this.getIdName(entity);
        searchSql.append(" where ").append(idName).append("=:").append(idName);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(idName, idVal);
        return this.namedParameterJdbcTemplate.queryForObject(
                searchSql.toString(), params, rowMapper);
    }
    /**
     *
     * 方法描述：   根据主键id来进行查询，无需映射rowMapper，根绝对象来进行映射返回
     * 注意事项：
     * @param entity
     * @param idVal
     * @return
     * @Exception 异常对象
     */
    public<T> T searchObjectByIdWithoutRowmapper(Class<T> entity, String idVal){
        StringBuilder searchSql = new StringBuilder("select * from ");
        // 拼接表明
        searchSql.append(this.getEntityTableNameByClazz(entity));
        // 拼接条件
        String idName = this.getIdName(entity);
        searchSql.append(" where ").append(idName).append("=:").append(idName);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(idName, idVal);
        return this.namedParameterJdbcTemplate.queryForObject(
                searchSql.toString(), params, new BeanPropertyRowMapper<>(entity));
    }
    /**
     * 方法描述：根据查询参数搜索所有满足的对象，此方法不支持分页 注意事项： 只能返回单独对象的list
     *
     * @param entity
     * @param params
     * @param rowMapper
     * @return
     */
    public<T> List<T> searchObjectsWithoutPage(Class<T> entity,
                                            Map<String, Object> params, RowMapper<T> rowMapper, String order) {
        StringBuilder searchPage = new StringBuilder("select * from ");
        searchPage.append(this.getEntityTableNameByClazz(entity));
        if (params != null) {
            searchPage.append(" where ");
            Map<String, Object> searchObjMap;
            try {
                // 分解查询参数
                searchPage.append(this.getSearchSqlFragmentByMap(params));
                if (order != null && order.trim().equals("")) {
                    searchPage.append(" order by " + order);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }
        List<T> resultList = namedParameterJdbcTemplate.query(
                searchPage.toString(), params, rowMapper);
        return resultList;
    }
    /**
     * 方法描述：根据查询参数搜索所有满足的对象，此方法不支持分页 注意事项： 只能返回单独对象的list,映射的对象无需传递rowmapper
     *
     * @param entity
     * @param params
     * @return
     */
    public<T> List<T> searchObjectsWithoutPageAndRowmapper(Class<T> entity,
                                                        Map<String, Object> params, String order) {
        StringBuilder searchPage = new StringBuilder("select * from ");
        searchPage.append(this.getEntityTableNameByClazz(entity));
        if (params != null) {
            searchPage.append(" where ");
            Map<String, Object> searchObjMap;
            try {
                // 分解查询参数
                searchPage.append(this.getSearchSqlFragmentByMap(params));
                if (order != null && order.trim().equals("")) {
                    searchPage.append(" order by " + order);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }
        List<T> resultList = namedParameterJdbcTemplate.query(
                searchPage.toString(), params, new BeanPropertyRowMapper(entity));
        return resultList;
    }
    /**
     * 方法描述：根据查询参数搜索所有满足的对象,不分页
     * @param entity
     * @param params
     * @param rowMapper
     * @return
     */
    public List searchAllObjectsInfo(Class<? extends Object> entity,
                                     Map<String, Object> params, RowMapper rowMapper,String order) {
        StringBuilder searchPage = new StringBuilder("select * from ");
        searchPage.append(this.getEntityTableNameByClazz(entity));
        Map<String, Object> searchObjMap;
        try {
            if(params!=null){
                searchPage .append(" where ");
                // 分解查询参数
                searchPage.append(this.getSearchSqlFragmentByMap(params));
            }
            if(order!=null&&order.trim().equals("")){
                searchPage.append(" order by " + order);
            }
            List resultList = namedParameterJdbcTemplate.query(searchPage.toString(), params, rowMapper);
            return resultList;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }

    }
    /**
     *
     * 方法描述：分页查询对象 注意事项：
     *
     * @param entity
     * @param params
     * @param rowMapper
     * @return
     * @Exception 异常对象
     */
    public<T> List<T> searchObjectsWithPage(Class<T> entity,
                                         Map<String, Object> params, RowMapper<T> rowMapper, String order,
                                         int currentPage, int pageSize) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        // 拼接表
        searchSql.append(this.getEntityTableNameByClazz(entity));
        if (params != null) {
            String searchFragment = this.getSearchSqlFragmentByMap(params);
            searchSql.append(" where ").append(searchFragment);
        }
        if (order != null) {
            searchSql.append(" order by ").append(order);
        }
        if (currentPage < 1) {
            currentPage = this.CURRENT_PAGE;
        }
        if (pageSize < 1) {
            pageSize = this.PAGE_SIZE;
        }
        searchSql.append(" limit ").append((currentPage - 1) * pageSize)
                .append(",").append(pageSize);

        return this.namedParameterJdbcTemplate.query(searchSql.toString(),
                rowMapper);
    }
    /**
     *
     * 方法描述：分页查询对象 注意事项：
     *
     * @param entity
     * @param params
     * @return
     * @Exception 异常对象
     */
    public<T> List<T> searchObjectsWithPageWithoutRowmapper(Class<T> entity,
                                                         Map<String, Object> params, String order,
                                                         int currentPage, int pageSize) {
        StringBuilder searchSql = new StringBuilder("select * from ");
        // 拼接表
        searchSql.append(this.getEntityTableNameByClazz(entity));
        if (params != null) {
            String searchFragment = this.getSearchSqlFragmentByMap(params);
            searchSql.append(" where ").append(searchFragment);
        }
        if (order != null) {
            searchSql.append(" order by ").append(order);
        }
        if (currentPage < 1) {
            currentPage = this.CURRENT_PAGE;
        }
        if (pageSize < 1) {
            pageSize = this.PAGE_SIZE;
        }
        searchSql.append(" limit ").append((currentPage - 1) * pageSize)
                .append(",").append(pageSize);

        return this.namedParameterJdbcTemplate.query(searchSql.toString(),
                new BeanPropertyRowMapper(entity));
    }
    /**
     * 方法描述： 根绝id删除某个对象 注意事项：
     *
     * @param entity
     * @param id
     * @return
     * @Exception 异常对象
     */
    public<T> boolean delObjects(Class<T> entity, String id) {
        StringBuilder delSql = new StringBuilder("delete from ");
        String idName = this.getIdName(entity);
        delSql.append(this.getEntityTableNameByClazz(entity)).append(" where ")
                .append(idName).append("=:").append(idName);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(idName, id);
        int result = this.namedParameterJdbcTemplate.update(delSql.toString(),
                params);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * 方法描述： 根绝id来更新对象数据 注意事项：
     *
     * @param t
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @Exception 异常对象
     */
    public<T> boolean updateObjects(T t) throws IllegalArgumentException,
            IllegalAccessException {
        StringBuilder updateSql = new StringBuilder("update ");
        updateSql.append(this.getEntityTableName(t));
        Map<String, Object> updateFragment = this.initUpdateSqlFragment(t);
        if (updateFragment == null) {
            logger.info("没有获取到需要更新的对象内容");
            return false;
        } else {
            updateSql.append(" set ").append(
                    updateFragment.get("updateSqlFragment").toString());
            updateSql.append(updateFragment.get("updateWhereSqlFragemnt")
                    .toString());
            Map<String, Object> updateParans = (Map<String, Object>) updateFragment
                    .get("updateParams");
            int result = this.namedParameterJdbcTemplate.update(
                    updateSql.toString(), updateParans);
            if (result == 1) {
                return true;
            } else {
                logger.info("更新对象失败");
                return false;
            }
        }
    }

    /**
     * 方法描述： 获取ID名称 注意事项：
     *
     * @param entity
     * @return
     * @Exception 异常对象
     */
    public<T> String getIdName(Class<T> entity) {
        Field[] fields = entity.getDeclaredFields();
        String idName = "";
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                idName = field.getAnnotation(Column.class).name();
                break;
            }
        }
        return idName;
    }

    /**
     *
     * 方法描述：根绝对象获取实体类表名 注意事项：
     *
     * @param t
     * @return
     * @Exception 异常对象
     */
    private<T> String getEntityTableName(T t) {
        Table entity = t.getClass().getAnnotation(Table.class);
        return entity.name();
    }

    /**
     *
     * 方法描述：根绝对象类来获取注解的表名 注意事项：
     *
     * @param entity
     * @return
     * @Exception 异常对象
     */
    @SuppressWarnings("unused")
    private<T> String getEntityTableNameByClazz(Class<T> entity) {
        return entity.getAnnotation(Table.class).name();
    }

    /**
     *
     * 方法描述： 初始化添加对象的SQL元素 注意事项：假如属性值上没有标注@Column注解，则直接以属性字段名为数据表字段名
     *
     * @param t
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @Exception 异常对象
     */
    private<T> Map<String, Object> initAddSqlFragment(T t) {
        StringBuilder addSqlParamsFragment = new StringBuilder("(");
        StringBuilder addSqlValuesFragment = new StringBuilder("(");
        Map<String, Object> insertParams = new HashMap<String, Object>();
        Field[] fields = t.getClass().getDeclaredFields();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(t) != null) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = "";
                    if (column == null) {
                        columnName = field.getName();
                    } else {
                        columnName = column.name();
                    }
                    addSqlParamsFragment.append(columnName).append(",");
                    addSqlValuesFragment.append(":").append(columnName)
                            .append(",");
                    insertParams.put(columnName, field.get(t));
                }
            }
            addSqlParamsFragment.append(")");
            addSqlValuesFragment.append(")");
            String addSqlFragment = addSqlParamsFragment.replace(
                    addSqlParamsFragment.lastIndexOf(","),
                    addSqlParamsFragment.lastIndexOf(",") + 1, "").toString()
                    + " values "
                    + addSqlValuesFragment.replace(
                    addSqlValuesFragment.lastIndexOf(","),
                    addSqlValuesFragment.lastIndexOf(",") + 1, "")
                    .toString();

            resultMap.put("addSqlFragment", addSqlFragment);
            resultMap.put("addParams", insertParams);
        } catch (IllegalArgumentException e) {
            resultMap.put("hasError", true);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            resultMap.put("hasError", true);
            e.printStackTrace();
        } catch (SecurityException e) {
            resultMap.put("hasError", true);
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 方法描述： 返回更新语句的片段 注意事项：
     *
     * @param t
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @Exception 异常对象
     */
    private<T> Map<String, Object> initUpdateSqlFragment(T t)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder updateSqlFragment = new StringBuilder("");
        StringBuilder updateWhereSqlFragemnt = new StringBuilder(" where ");
        Map<String, Object> params = new HashMap<String, Object>();
        for (Field field : fields) {
            if (field.get(t) != null && field.getAnnotation(Id.class) == null) {
                Column commonColumn = field.getAnnotation(Column.class);
                String columnName = "";
                if (commonColumn == null) {
                    columnName = field.getName();

                } else {
                    columnName = commonColumn.name();
                }
                params.put(columnName, field.get(t));
                updateSqlFragment.append(columnName).append("=:")
                        .append(columnName);
            } else if (field.getAnnotation(Id.class) != null) {
                String idName = field.getAnnotation(Column.class).name();
                updateWhereSqlFragemnt.append(idName).append("=:")
                        .append(idName);
                params.put(idName, field.get(t).toString());
            }
        }
        if (updateSqlFragment.equals("")) {
            return null;
        } else {
            Map<String, Object> updateFragmentMap = new HashMap<String, Object>();
            updateFragmentMap.put("updateSqlFragment",
                    updateSqlFragment.toString());
            updateFragmentMap.put("updateParams", params);
            updateFragmentMap.put("updateWhereSqlFragemnt",
                    updateWhereSqlFragemnt.toString());
            return updateFragmentMap;
        }
    }

    /**
     *
     * 方法描述：根绝Map的参数值来查询sql 注意事项：
     *
     * @param params
     * @return
     * @Exception 异常对象
     */
    private String getSearchSqlFragmentByMap(Map<String, Object> params) {
        Set<String> keySet = params.keySet();
        StringBuilder searchFragment = new StringBuilder("");
        for (String key : keySet) {
            searchFragment.append(key).append("=:").append(key).append(" and ");
        }
        return searchFragment.substring(0, searchFragment.lastIndexOf(" and "));
    }

    /**
     *
     * 方法描述：根绝Class获取表明 注意事项：
     *
     * @param t
     * @return
     * @Exception 异常对象
     */
    public<T> String getClazzTableName(Class<T> t) {
        Table table = t.getAnnotation(Table.class);
        return table.name();
    }

    /**
     *
     * 方法描述： 根绝单独的对象存放的值，来查询某个单独的对象List 注意事项：
     *
     * @param t
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @Exception 异常对象
     */
    public<T> Map<String, Object> getSearchSqlFragment(T t)
            throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        StringBuilder searchSqlFragment = new StringBuilder("");
        Map<String, Object> searchParams = new HashMap<String, Object>();
        for (Field field : fields) {
            if (field.get(t) != null) {
                Column column = field.getAnnotation(Column.class);
                String columnName="";
                if(column==null){
                    columnName=field.getName();
                }else{
                    columnName=column.name();
                }
                searchSqlFragment.append(columnName).append("=:")
                        .append(columnName).append("and");
                searchParams.put(columnName, field.get(t));
            }
        }
        searchSqlFragment.substring(0, searchSqlFragment.lastIndexOf("and"));
        Map<String, Object> searchResultMap = new HashMap<String, Object>();
        searchResultMap.put("searchSqlFragment", searchSqlFragment.toString());
        searchResultMap.put("searchParams", searchParams);
        return searchParams;
    }





}
