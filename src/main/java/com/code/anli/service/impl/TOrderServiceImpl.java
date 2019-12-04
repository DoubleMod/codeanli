package com.code.anli.service.impl;

import com.code.anli.entity.TOrder;
import com.code.anli.mapper.TOrderMapper;
import com.code.anli.service.ITOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author masin
 * @since 2019-12-04
 */
@Service("orderService")
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

}
