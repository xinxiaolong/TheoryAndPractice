package xinxiaolong.com.designmode.wrapper.starbucks.condiment;

import xinxiaolong.com.designmode.wrapper.starbucks.Beverage;
import xinxiaolong.com.designmode.wrapper.starbucks.CondimentDecorator;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage=beverage;
    }

    @Override
    public double cost() {
        return 1.0+beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",加豆奶";
    }
}
