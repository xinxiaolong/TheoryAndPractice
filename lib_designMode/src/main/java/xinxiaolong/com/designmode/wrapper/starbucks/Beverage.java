package xinxiaolong.com.designmode.wrapper.starbucks;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public abstract class Beverage {

    public String description="unKnown beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
