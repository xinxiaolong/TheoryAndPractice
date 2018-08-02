package xinxiaolong.com.designmode.factory.pizza.store;

import java.util.ArrayList;
import java.util.List;

import xinxiaolong.com.designmode.factory.pizza.model.PizzaModel;
import xinxiaolong.com.designmode.factory.pizza.model.PizzaType;

/**
 * Created by xiaolong on 2017/12/4.
 * email：xinxiaolong123@foxmail.com
 *
 */

public abstract class BaseStore {


    public PizzaModel orderPizza(PizzaType pizzaType){
        PizzaModel pizzaModel=createPizza(pizzaType);
        pizzaModel.prepare();
        pizzaModel.bake();
        pizzaModel.cut();
        pizzaModel.box();
        return pizzaModel;
    }

    public abstract PizzaModel createPizza(PizzaType pizzaType);

}
