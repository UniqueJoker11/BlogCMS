package colin.app.core.dao.decorate;

import colin.app.common.CommonConstants;
import colin.app.core.dao.common.CommonDao;
import colin.app.core.dao.rowmapper.DefaultRowMapper;
import colin.app.core.dao.rowmapper.Homework_Menu_Rowmapper;
import colin.app.core.dao.rowmapper.Homework_Role_Menu_Rowmapper;
import colin.app.core.dao.rowmapper.Homework_User_Role_Simple_Rowmapper;
import colin.app.core.pojo.*;
import colin.app.core.vo.HomeworkUserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by DELL on 2015/7/15.
 */
@Repository
public class IndexDao {
    @Autowired
    private CommonDao commonDao;



    /**
     * 根据用户id查询用户的菜单
     *
     * @param params
     * @return
     */
    public List<HomeworkUserMenu> searchUserMenuInfo(Map<String,Object> params) {
        //构造用户角色的rowmapper
        //返回用户的角色ID
        List<Homework_User_Role_Entity> user_role_entityList = commonDao.searchObjectsWithoutPage(Homework_User_Role_Entity.class, params, new DefaultRowMapper<Homework_User_Role_Entity>(Homework_User_Role_Entity.class.getName()),null);
        List<HomeworkUserMenu> homeworkUserMenus=null;
        //根绝角色id查询相应的菜单id
        for(Homework_User_Role_Entity user_role_entity:user_role_entityList){
            Map<String,Object> roleMenuParams=new HashMap<>();
            roleMenuParams.put("role_id", user_role_entity.getRole_id());
            Homework_Role_Menu_Rowmapper role_menu_rowmapper=new Homework_Role_Menu_Rowmapper();
            //根据角色id，获取到角色菜单
            List<Homework_Role_Menu_Entity> role_menu_entityList=commonDao.searchObjectsWithoutPage(Homework_Role_Menu_Entity.class,roleMenuParams,role_menu_rowmapper,null);
            //去除一个用户对应多个角色造成的menu_id重复的现象
            Set<Homework_Role_Menu_Entity> role_menu_entitySet=new HashSet<Homework_Role_Menu_Entity>(role_menu_entityList);
            //声明存放MenuEntity的List集合
            List<Homework_Menu_Entity> userMenuList=new ArrayList<>();
            for(Homework_Role_Menu_Entity role_menu_entity:role_menu_entitySet){
                Map<String,Object> menuParams=new HashMap<>();
                menuParams.put("menu_id",role_menu_entity.getMenu_id());
                //根据角色的菜单id，获取菜单的实体对象
                Homework_Menu_Rowmapper menu_rowmapper=new Homework_Menu_Rowmapper();
                List<Homework_Menu_Entity> menu_entityList=commonDao.searchObjectsWithoutPage(Homework_Menu_Entity.class,menuParams,menu_rowmapper,"menu_order");
                userMenuList.add(menu_entityList.get(0));
            }
            //需要处理下菜单的逻辑结构
            homeworkUserMenus=this.reformatUserMenuInfo(userMenuList);
        }
        return homeworkUserMenus;
    }

    /**
     * 重新整理用户的菜单信息
     * @param userMenuList
     * @return
     */
    public List<HomeworkUserMenu> reformatUserMenuInfo(List<Homework_Menu_Entity> userMenuList){
        List<HomeworkUserMenu> homeworkUserMenus=new LinkedList<HomeworkUserMenu>();
        for(Homework_Menu_Entity homework_menu_entity:userMenuList){
            String menu_id=homework_menu_entity.getMenu_id();
            if(homework_menu_entity.getMenu_parent_id().trim().equals(CommonConstants.MENU_ROOT_VALUE)){
                List<HomeworkUserMenu> subHomeworkUserMenus=new LinkedList<HomeworkUserMenu>();
                for(Homework_Menu_Entity sub_homework_menu_entity:userMenuList){
                    if(menu_id.equals(sub_homework_menu_entity.getMenu_parent_id())){
                        subHomeworkUserMenus.add(transferUserMenuObj(sub_homework_menu_entity,null));
                    }
                }
                homeworkUserMenus.add(transferUserMenuObj(homework_menu_entity,subHomeworkUserMenus));
            }
        }
        Collections.sort(homeworkUserMenus,new HomeworkUserMenuComparator());
        return homeworkUserMenus;
    }

    /**
     * 转换Menu对象
     * @param homework_menu_entity
     * @return
     */
    public HomeworkUserMenu  transferUserMenuObj(Homework_Menu_Entity homework_menu_entity,List<HomeworkUserMenu> homeworkUserMenuList){
        HomeworkUserMenu homeworkUserMenu=new HomeworkUserMenu();
        homeworkUserMenu.setMenu_id(homework_menu_entity.getMenu_id());
        homeworkUserMenu.setMenu_url(homework_menu_entity.getMenu_url());
        homeworkUserMenu.setMenu_descripetion(homework_menu_entity.getMenu_descripetion());
        homeworkUserMenu.setMenu_icon(homework_menu_entity.getMenu_icon());
        homeworkUserMenu.setMenu_name(homework_menu_entity.getMenu_name());
        homeworkUserMenu.setMenu_order(homework_menu_entity.getMenu_order());
        if(homeworkUserMenuList!=null){
            homeworkUserMenu.setHomeworkUserMenuList(homeworkUserMenuList);
        }
        return homeworkUserMenu;
    }
    public class HomeworkUserMenuComparator implements Comparator<HomeworkUserMenu>{

        /**
         * Compares its two arguments for order.  Returns a negative integer,
         * zero, or a positive integer as the first argument is less than, equal
         * to, or greater than the second.<p>
         * <p/>
         * In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.<p>
         * <p/>
         * The implementor must ensure that <tt>sgn(compare(x, y)) ==
         * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>compare(x, y)</tt> must throw an exception if and only
         * if <tt>compare(y, x)</tt> throws an exception.)<p>
         * <p/>
         * The implementor must also ensure that the relation is transitive:
         * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
         * <tt>compare(x, z)&gt;0</tt>.<p>
         * <p/>
         * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
         * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
         * <tt>z</tt>.<p>
         * <p/>
         * It is generally the case, but <i>not</i> strictly required that
         * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
         * any comparator that violates this condition should clearly indicate
         * this fact.  The recommended language is "Note: this comparator
         * imposes orderings that are inconsistent with equals."
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         * first argument is less than, equal to, or greater than the
         * second.
         * @throws NullPointerException if an argument is null and this
         *                              comparator does not permit null arguments
         * @throws ClassCastException   if the arguments' types prevent them from
         *                              being compared by this comparator.
         */
        @Override
        public int compare(HomeworkUserMenu o1, HomeworkUserMenu o2) {
            return Integer.parseInt(o1.getMenu_order())>=Integer.valueOf(o2.getMenu_order())?0:-1;
        }
    }
 }
