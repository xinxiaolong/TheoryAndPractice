package xinxiaolong.com.designmode.wrapper.starbucks.coffee;

import xinxiaolong.com.designmode.wrapper.starbucks.Beverage;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public class Espresso extends Beverage {


    public Espresso() {
        description = "浓缩咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
