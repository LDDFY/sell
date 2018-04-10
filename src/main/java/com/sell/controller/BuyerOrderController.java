package com.sell.controller;

import com.sell.convert.OrderForm2OrderDTOConvert;
import com.sell.dto.OrderDTO;
import com.sell.enums.ResultEnum;
import com.sell.exception.SellException;
import com.sell.form.OrderForm;
import com.sell.service.BuyerService;
import com.sell.service.OrderService;
import com.sell.util.ResultVoUtil;
import com.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家端控制器
 * Create by: LDDFY
 * Date: 2018/4/2
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order/")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult)
            throws Exception {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单] 参数不正确 result={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConvert.getInstance().convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetails())) {
            log.error("[购物车不能为空] 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY,
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", result.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表
    @GetMapping("list")
    public ResultVO<List<OrderDTO>> list(@RequestParam(value = "openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[查询订单列表] openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> result = orderService.findList(openId, request);
        return ResultVoUtil.success(result.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam(value = "openId") String openId,
                                     @RequestParam(value = "orderId") String orderId) throws Exception {
        OrderDTO dto = buyerService.findOrderOne(openId, orderId);
        return ResultVoUtil.success(dto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO<OrderDTO> cancel(@RequestParam(value = "openId") String openId,
                                     @RequestParam(value = "orderId") String orderId) throws Exception {
        buyerService.cancelOrder(openId, orderId);
        return ResultVoUtil.success();
    }

}
