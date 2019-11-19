/**
 *
 */
package com.cskaoyan.mall.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class String2IntArrayTypeHandler extends BaseTypeHandler<List<Integer>> {

    private static final String splitCharset = ",";


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Integer> integers, JdbcType jdbcType) throws SQLException {
        String str = arrayToString(integers);
        preparedStatement.setString(i,str);
    }



    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String str = resultSet.getString(s);
        return stringToArray(str);
    }



    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String str = resultSet.getString(i);
        return stringToArray(str);
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String str = callableStatement.getString(i);
        return stringToArray(str);
    }

    private static String arrayToString(List<Integer> array) {
        StringBuilder res  = new StringBuilder("[");
        if(array != null && array.size() > 0){
            for (Integer integer : array) {
                res.append(integer).append(",");
            }
            res.deleteCharAt(res.length() - 1);
            res.append("]");
        }
        return res.length() > 0 ? res.toString() : null;
    }

    private List<Integer> stringToArray(String str) {
        List<Integer> list = new ArrayList<>();
        String s = str.replaceAll("\"", "");
        String s1 = s.replaceAll("\\[", "");
        String ss = s1.replaceAll(" ","");
        String rs = ss.replaceAll("\\]", "");

        if(rs != null && !"".equals(rs)) {
            String[] split = rs.split(",");
            for (String s2 : split) {
                list.add(Integer.parseInt(s2));
            }
        }
        return list;
    }
}
