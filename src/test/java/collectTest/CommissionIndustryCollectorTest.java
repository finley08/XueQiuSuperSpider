package collectTest;

import entryFirst.IndustryInfoToDBAcceptor;
import org.decaywood.collector.CommissionIndustryCollector;
import org.decaywood.entity.Industry;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author: decaywood
 * @date: 2015/11/25 10:45
 */
public class CommissionIndustryCollectorTest {

    @Test
    public void test() throws Exception {
        IndustryInfoToDBAcceptor industryInfoToDBAcceptor = new IndustryInfoToDBAcceptor();
        CommissionIndustryCollector commissionIndustryCollector = new CommissionIndustryCollector();
        List<Industry> list = commissionIndustryCollector.collectLogic();
        industryInfoToDBAcceptor.consumLogic(list);
        //Assert.assertEquals(list.size(), 77);
    }

    @Test
    public void testNull() throws Exception {
        CommissionIndustryCollector commissionIndustryCollector = new CommissionIndustryCollector(null);
        List<Industry> list = commissionIndustryCollector.collectLogic();
        Assert.assertEquals(list.size(), 77);
    }

}
