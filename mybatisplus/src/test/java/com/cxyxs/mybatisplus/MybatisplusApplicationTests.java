package com.cxyxs.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxyxs.mybatisplus.dao.UserMapper;
import com.cxyxs.mybatisplus.entity.User;
import com.cxyxs.mybatisplus.service.UserService;
import com.cxyxs.mybatisplus.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * 程序猿学社
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisplusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }


    /**
     * 查询所有数据
     */
    @Test
    void select() {
        List<User> list= userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /**
     * 查询根据id返回对象
     */
    @Test
    void selectById(){
        User user = userMapper.selectById(22);
        System.out.println(user.toString());
    }

    /**
     * 根据List的id集合查询对应的用户list
     */
    @Test
    void selectBatchIds(){
        List<User> list = userMapper.selectBatchIds(Arrays.asList(1,2,10));
        list.forEach(System.out::println);
    }

    @Test
    void selectByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("age","20");
        //查询年龄为20岁的所有用户，可以拼接多个值
        //注意：建议尽量减少使用map这种方式。因为可能字段名可能存在修改的情况，如果，项目开发一段时间后，再修改，影响太大
        List<User> list = userMapper.selectByMap(map);
        list.forEach(System.out::println);
    }

    /**
     * 查询大于20岁的学生，名称中包含小的人，带条件判断的，可以采用这种方式
     * SELECT id,name,age,email,create_time FROM user WHERE (name LIKE ? AND age > ?)
     */
    @Test
    void selectListWrapper(){
        //类似于hibernate
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","小");
        queryWrapper.gt("age", "17");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 模糊查询名称包含王,年龄大于18，通过Lambda方式
     * 强烈推荐这种方式：因为他是通过调用字段对应的get方法，实现跟数据库字段的一个映射
     * 而不想之前的代码，key都是实体类的字符串的字段名，万一，字段有变动，根本就无法知道哪里有变动。
     */
    @Test
    void lambdaQueryWrapper(){
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        query.like(User::getName,"王").gt(User::getAge,18);
        //.gt(User::getAge, 20);
        List<User> list = userMapper.selectList(query);
        list.forEach(System.out::println);
    }

    /**
     * 获取自定义的MP(注解版本)
     */
    @Test
    void getAll(){
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        query.like(User::getName,"王").gt(User::getAge,18);
        //.gt(User::getAge, 20);
        List<User> list = userMapper.getAll(query);
        list.forEach(System.out::println);
    }

    /**
     * 获取自定义的MP(xml版本)
     */
    @Test
    void getAllXml(){
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        query.like(User::getName,"王").gt(User::getAge,18);
        //.gt(User::getAge, 20);
        List<User> list = userMapper.getXml(query);
        list.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    void getSelectPage(){
        LambdaQueryWrapper<User> query = Wrappers.<User>lambdaQuery();
        //第一个参数表示当前页
        //第二个参数表示当前页显示多少条
        //第三个参数是否查询count
        Page<User> page = new Page<>(2, 2,false);
        Page<User> userPage = userMapper.selectPage(page, query);
        //需要需要获取总记录数或者总页数，可以自己查看Page源码里面都有那些字段
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 分页查询 Map方式
     */
    @Test
    void getSelectMapsPage(){
        QueryWrapper<User> query = new QueryWrapper<User>();
        //第一个参数表示当前页
        //第二个参数表示当前页显示多少条
        Page<Map<String,Object>> page = new Page<>(2, 2);
        IPage<Map<String, Object>> maps = userMapper.selectMapsPage(page,query);
        maps.getRecords().forEach(System.out::println);
    }

    /**
     * 根据id修改用户信息
     */
    @Test
    void updateUserById(){
        User user = new User();
        user.setId(1);
        user.setName("小短腿鲁班-程序猿学社");
        int result = userMapper.updateById(user);
        System.out.println(result>0 ? "修改成功":"修改失败");
    }

    /**
     * 通过条件修改用户信息
     */
    @Test
    public void updateWrapper(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("age","18");

        User user = new User();
        user.setName("小短腿鲁班-程序猿学社123");
        int result = userMapper.update(user, wrapper);
        System.out.println(result>0 ? "修改成功":"修改失败");
    }

    /**
     * 通过条件修改用户信息(需要修改的字段少，可通过这种方式)
     */
    @Test
    public void updateWrapper1(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("age","18").set("name","小短腿鲁班-程序猿学社456");

        int result = userMapper.update(null, wrapper);
        System.out.println(result>0 ? "修改成功":"修改失败");
    }

    /**
     * 通过条件修改用户信息(需要修改的字段少，可通过这种方式)
     * 强烈推荐，避免出错
     */
    @Test
    public void updatelamWrapper(){
        LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate();
        updateWrapper.eq(User::getAge,"18").set(User::getName,"小短腿鲁班-程序猿学社789");

        int result = userMapper.update(null, updateWrapper);
        System.out.println(result>0 ? "修改成功":"修改失败");
    }

    /**
     * lambda链表方式，根据条件修改用户记录
     */
    @Test
    public void updatelambdaWrapper(){
        boolean result = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getAge, "18").set(User::getName, "小短腿鲁班-程序猿学社1314").update();
        System.out.println(result ? "修改成功":"修改失败");
    }

    /**
     * 根据id删除对应的用户记录
     */
    @Test
    public void deleteById(){
        int result = userMapper.deleteById(21);
        System.out.println(result>0 ? "删除成功":"删除失败");
    }

    /**
     * 根据map里面的条件，删除对应的数据
     */
    @Test
    public void deleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("age","38");
        int result = userMapper.deleteByMap(map);
        System.out.println(result>0 ? "删除成功":"删除失败");
    }

    /**
     * 根据id批量删除
     */
    @Test
    public void deleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(6, 7, 8));
        System.out.println("批量删除条数："+result);
    }

    /**
     * lambda链表方式实现通过条件删除对应数据(推荐使用)
     */
    @Test
    public void deleteChainWrapper(){
        boolean remove = new LambdaUpdateChainWrapper<>(userMapper).eq(User::getAge, 39).remove();
        System.out.println(remove ? "删除成功":"删除失败");
    }

    /**
     * lambda通过条件删除对应数据
     */
    @Test
    public void deleteWrapper(){
        LambdaQueryWrapper<User> lambdaQuery= Wrappers.<User>lambdaQuery();
        lambdaQuery.eq(User::getAge, 39);
        int result = userMapper.delete(lambdaQuery);
        System.out.println(result>0 ? "删除成功":"删除失败");
    }

    /**
     * 新增用户信息
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("程序猿学社456");
        user.setAge(11);
        user.setEmail("1024@qq.com");
        int result = userMapper.insert(user);
        System.out.println("id为："+user.getId());
        System.out.println(result>0 ? "新增成功":"新增失败");
    }

    /**
     * 批量插入
     */
    @Test
    public void saveBatch(){
        List<User> users = new ArrayList<>();
        for(int i=0;i<5;i++){
            User user = new User();
            user.setName("程序猿学社"+i);
            user.setAge(11);
            user.setCreateTime(new Date());
            user.setEmail("1024@qq.com");
            users.add(user);
        }
        boolean result = userService.saveBatch(users);
        System.out.println(result ? "批量新增成功":"批量新增失败");
    }

    /**
     * 不存在则新增，新增则修改
     */
    @Test
    public void saveOrUpdate(){
        User user = new User();
        user.setName("程序猿学社520");
        user.setAge(11);
        user.setCreateTime(new Date());
        user.setEmail("1024@qq.com");
        user.setId(21);
        boolean result = userService.saveOrUpdate(user);
        System.out.println(result ? "成功":"失败");
    }
}
