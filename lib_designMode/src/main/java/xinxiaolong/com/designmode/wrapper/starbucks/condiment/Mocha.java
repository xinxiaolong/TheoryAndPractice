package xinxiaolong.com.designmode.wrapper.starbucks.condiment;

import xinxiaolong.com.designmode.wrapper.starbucks.Beverage;
import xinxiaolong.com.designmode.wrapper.starbucks.CondimentDecorator;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage=beverage;
    }

    @Override
    public double cost() {
        return 2.0+beverage.cost();
    }
    @Override
    public String getDescription() {
        return beverage.getDescription()+",加摩卡";
    }
}
