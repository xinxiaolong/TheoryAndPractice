package xinxiaolong.com.designmode.factory.pizza.model;

import java.util.ArrayList;
import java.util.List;

import xinxiaolong.com.designmode.util.PrintUtil;

/**
 * Created by xiaolong on 2017/12/4.
 * email：xinxiaolong123@foxmail.com
 */

public abstract class PizzaModel {

    String name;                                         //披萨名称
    String dough;                                        //面粉
    List<String> spicesList = new ArrayList<String>();   //佐料系列

    /**
     * 准备制作
     */
    public void prepare(){
        PrintUtil.println("正在准备"+getName());
        PrintUtil.println("开始和面...");
        PrintUtil.println("开始添加佐料...");
        for (String spices:spicesList) {
            PrintUtil.println("添加佐料:"+spices);
        }
    }

    public void bake(){
        PrintUtil.println("烘焙25分钟...");
    }

    public void cut(){
        PrintUtil.println("切成8块。");
    }

    public void box(){
        PrintUtil.println("将切好的披萨打包装进包装盒子。");
    }

    public abstract String getName();
}
