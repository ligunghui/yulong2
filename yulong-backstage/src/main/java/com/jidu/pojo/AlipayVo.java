package com.jidu.pojo;

import lombok.Data;

/**
 * @Author: liguanghui
 * Date: 2020/3/20 0020 下午 2:32
 * @Version:
 * @Description:
 */
@Data
public class AlipayVo {
    private String out_biz_no;
    private String trans_amount;
    private String product_code;
    private String biz_scene;
    private PayeeInfo payee_info;
    private String order_title;

}
