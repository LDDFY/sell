package com.sell.util;

import com.sell.vo.ResultVO;

/**
 * 返回查询结果
 * Create by: LDDFY
 * Date: 2018/3/26
 */
public class ResultVoUtil {

    public static ResultVO success(Object productVOList) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(productVOList);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
