package com.hmdp.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

//    @Resource
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Override
//    public Result queryList() {
//        String id = UUID.randomUUID().toString();
//        String key = CACHE_SHOP_TYPE_KEY + id;
//
//        //1.从redis查询商铺列表
//        List<Object> hashValue = stringRedisTemplate.opsForHash().values(key);
//        //2.判断是否存在
//        if(!hashValue.isEmpty()) {
//            //3.存在，直接返回
//            List<ShopType> shopType = (List<ShopType>)(List)hashValue;
//            return Result.ok(shopType);
//        }
//
//        //4.不存在，查询数据库
//        List<ShopType> shopType = query().orderByAsc("sort").list();
//
//
//        //5.不存在，返回错误
//        if(shopType == null) {
//            return Result.fail("店铺不存在");
//        }
//
//        //6.存在，写入redis
//        stringRedisTemplate.opsForList().leftPushAll(key, String.valueOf(shopType));
//
//        return Result.ok(shopType);
//    }
}
