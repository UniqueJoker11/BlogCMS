package colin.app.core.dao.rowmapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;


public class DefaultRowMapper<T> implements RowMapper<T> {
    private String className = "";

    public DefaultRowMapper(String className) {
        this.className = className;
    }

    /**
     * Implementations must implement this method to map each row of data in the
     * ResultSet. This method should not call {@code next()} on the ResultSet;
     * it is only supposed to map values of the current row.
     *
     * @param rs     the ResultSet to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row
     * @throws java.sql.SQLException if a SQLException is encountered getting column values (that
     *                               is, there's no need to catch SQLException)
     */
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {

        T t = null;

        try {
            t = (T) Class.forName(this.className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            Field[] fields = t.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);// 对于似有属性予以访问
                String columnName = field.getAnnotation(Column.class).name();// 获取列的名称
                try {
                    field.set(t, rs.getObject(columnName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return t;
        } else {
            return t;
        }
    }
}