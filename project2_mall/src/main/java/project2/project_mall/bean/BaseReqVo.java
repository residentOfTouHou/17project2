package project2.project_mall.bean;
import lombok.Data;

@Data
public class BaseReqVo<T> {
    T data;
    String errmsg;
    int errno;
}
