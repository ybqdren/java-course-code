package com.github.ybqdre.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/29 12:41
 * @package com.github.ybqdre.observer
 * @description
 **/
public class MMTikToker extends AbstractTikToker {

    List<AbstractFans> fansList = new ArrayList<>();

    void startSell(){}

    void endSell(){}

    @Override
    void addFans(AbstractFans fans) {

    }

    @Override
    void notifyFans(String msg) {
        for(AbstractFans fans : fansList){
            fans.acceptMsg(msg);
        }
    }
}
