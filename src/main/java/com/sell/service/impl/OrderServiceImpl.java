package com.sell.service.impl;

import com.sell.convert.OrderMaster2OrderDTOConvert;
import com.sell.dao.OrderDetailRepository;
import com.sell.dao.OrderMasterRepository;
import com.sell.dto.CarDTO;
import com.sell.dto.OrderDTO;
import com.sell.entity.OrderDetail;
import com.sell.entity.OrderMaster;
import com.sell.entity.ProductInfo;
import com.sell.enums.OrderStatusEnum;
import com.sell.enums.PayStatusEnum;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.service.OrderService;
import com.sell.service.ProductInfoService;
import com.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Create by: LDDFY
 * Date: 2018/3/31
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) throws Exception {
        String orderId = KeyUtil.generateUniqueKey();
        //查询商品（数量，价格）
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        List<String> productIds = new ArrayList<String>();
        for (OrderDetail detail : orderDTO.getOrderDetails()) {
            //productIds.add(detail.getProductId());
            ProductInfo info = productInfoService.findOne(detail.getProductId());
            if (info == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算总价
            orderAmount = new BigDecimal(detail.getProductQuantity()).multiply(info.getProductPrice()).add(orderAmount);
            BeanUtils.copyProperties(info, detail);
            detail.setDetailId(KeyUtil.generateUniqueKey());
            detail.setOrderId(orderId);
            orderDetailRepository.save(detail);
        }

        //写入订单数据库
        OrderMaster master = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, master);
        orderDTO.setOrderId(orderId);
        master.setOrderId(orderId);
        master.setOrderAmount(orderAmount);
        master.setOrderStatus(OrderStatusEnum.NEW.getCode());
        master.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(master);

        //扣除库存
        List<CarDTO> carDTOList = orderDTO.getOrderDetails().stream().map(e -> new CarDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.decreaseStock(carDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        //查询订单
        Optional<OrderMaster> master = orderMasterRepository.findById(orderId);
        if (master.get() == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //查询订单详情
        List<OrderDetail> detail = orderDetailRepository.findByOrderId(orderId);
        if (detail == null) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO dto = new OrderDTO();
        BeanUtils.copyProperties(master.get(), dto);
        dto.setOrderDetails(detail);
        return dto;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConvert.getInstance().
                convertList(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage =
                new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) throws Exception {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[订单状态不正确]");
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        OrderMaster master = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, master);
        OrderMaster updateResult = orderMasterRepository.save(master);
        if (updateResult == null) {
            log.error("[订单更新失败]");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("取消订单失败订单中无商品详情信息");
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CarDTO> carDTOList = orderDTO.getOrderDetails().stream()
                .map(e -> new CarDTO(e.getProductId(), e.getProductQuantity())
                ).collect(Collectors.toList());

        productInfoService.increaseStock(carDTOList);
        //如果已经支付则退款。
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单状态不正确]");
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }
        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[完结点订单失败]");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单状态不正确]");
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单不是待支付状态]");
            throw new SellException(ResultEnum.ORDER_STATE_ERROR);
        }

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[修改订单支付状态错误]");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
