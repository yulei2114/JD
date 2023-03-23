package mybatis_spring.UserMapper;

import org.apache.ibatis.annotations.Param;

public interface SelectCustomers {
    public int findById(@Param("id") int id);
    public void insertOne(@Param("id") int id);
}
