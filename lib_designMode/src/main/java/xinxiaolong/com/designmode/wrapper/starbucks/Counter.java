package xinxiaolong.com.designmode.wrapper.starbucks;

import xinxiaolong.com.designmode.wrapper.starbucks.coffee.Espresso;
import xinxiaolong.com.designmode.wrapper.starbucks.condiment.Milk;
import xinxiaolong.com.designmode.wrapper.starbucks.condiment.Mocha;
import xinxiaolong.com.designmode.wrapper.starbucks.condiment.Soy;
import xinxiaolong.com.designmode.wrapper.starbucks.condiment.Whip;

/**
 * Created by xiaolong on 2017/11/29.
 * email：xinxiaolong123@foxmail.com
 */

public class Counter {


    public static void main(String[] arg){
        //浓缩咖啡
        Espresso espresso=new Espresso();

        //加牛奶
        Milk milk1=new Milk(espresso);
        System.out.println(milk1.getDescription()+" "+milk1.cost());

        //加摩卡
        Mocha mocha=new Mocha(milk1);
        System.out.println(mocha.getDescription()+" "+mocha.cost());

        //加豆奶
        Soy soy=new Soy(mocha);
        System.out.println(soy.getDescription()+" "+soy.cost());

        //加搅打奶油
        Whip whip=new Whip(soy);
        System.out.println(whip.getDescription()+" "+whip.cost());

    }
}
