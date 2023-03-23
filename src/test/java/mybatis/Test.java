package mybatis;

import com.jd.entity.Customer;
import org.apache.ibatis.annotations.Param;

public interface Test {
    public int getCustomerById(@Param("id") int id);
    public void insertOne(@Param("id") int id);
}
