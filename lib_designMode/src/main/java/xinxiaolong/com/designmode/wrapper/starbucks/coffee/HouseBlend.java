package xinxiaolong.com.designmode.wrapper.starbucks.coffee;

import xinxiaolong.com.designmode.wrapper.starbucks.Beverage;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public class HouseBlend extends Beverage {


    public HouseBlend() {
        description = "综合咖啡";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
