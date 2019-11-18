package com.cskaoyan.mall.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


@MappedTypes(Date.class)
public class String2DateHandler implements TypeHandler<Date> {


    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
      preparedStatement.setString(i,parseDate2String(date));
    }

    private String parseDate2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(date);
        //s = "'"+s+"'";
        return s;
    }

    @Override
    public Date getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return parseString2Date(string);
    }

    @Override
    public Date getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return parseString2Date(string);
    }

    @Override
    public Date getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return parseString2Date(string);
    }

    private Date parseString2Date(String string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition parsePosition = new ParsePosition(0);
        return format.parse(string,parsePosition);
    }
}
