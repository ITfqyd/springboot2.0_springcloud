package com.cxyxs.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cxyxs.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  //记得加上这个注解，不然，@Autowired会报红，但是可以查询数据
public interface UserMapper extends BaseMapper<User> {
    /**
     * 通过注解方式实现
     * @param wrapper
     * @return
     */
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 通过xml方式实现
     * @param wrapper
     * @return
     */
    List<User> getXml(@Param(Constants.WRAPPER) Wrapper wrapper);
}