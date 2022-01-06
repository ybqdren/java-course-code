package ybqdren.test;

import com.github.ybqdren.baseannotation.pojo.Cat;
import org.junit.Test;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/7 7:00
 * @package ybqdren.test
 * @description
 **/
public class AnnotationTest {

    @Test
    public void testPostConstructAndPreDestroy(){
        Cat cat = new Cat("miaomiao");
        cat.setYears(2);
    }
}
