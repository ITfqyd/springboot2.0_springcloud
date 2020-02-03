package com.cxyxs.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxyxs.mybatisplus.dao.UserMapper;
import com.cxyxs.mybatisplus.entity.User;
import com.cxyxs.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description：
 * Author: 程序猿学社
 * Date:  2020/2/2 18:51
 * Modified By:
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
