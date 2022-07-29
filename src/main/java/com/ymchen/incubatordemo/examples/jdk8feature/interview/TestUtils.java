package com.ymchen.incubatordemo.examples.jdk8feature.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class TestUtils {


    @Test
    public void testSortByName() {
        Extension[] extensionArray = {
                new Extension("aa", "d1", "1"),
                new Extension("aa", "d3", "1"),
                new Extension("aa", "d2", "1"),
                new Extension("aa", "d2", "2"),
                new Extension("bb", "aa", "1"),
                new Extension("aa", "d2", ""),
                new Extension("aa", "d2", null)};
        List<Extension> extensions = Utils.sortByName(Arrays.asList(extensionArray));


        //extensions.forEach(System.out::println);
        Extension[] expectedArray = {
                new Extension("aa", "d1", "1"),
                new Extension("aa", "d2", ""),
                new Extension("aa", "d2", null),
                new Extension("aa", "d2", "1"),
                new Extension("aa", "d2", "2"),
                new Extension("aa", "d3", "1"),
                new Extension("bb", "aa", "1")};

        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertEquals(expectedArray[i].getFirstName(), extensions.get(i).getFirstName());
            Assert.assertEquals(expectedArray[i].getLastName(), extensions.get(i).getLastName());
            Assert.assertEquals(expectedArray[i].getExt(), extensions.get(i).getExt());
        }


    }

    @Test
    public void testSortByExtType() {
        Extension[] extensionArray = {
                new Extension("AO"),
                new Extension("Dept"),
                new Extension("Other"),
                new Extension("TMO"),
                new Extension("User")};
        List<Extension> extensions = Utils.sortByExtType(Arrays.asList(extensionArray));

        Extension[] expectedArray = {
                new Extension("User"),
                new Extension("Dept"),
                new Extension("AO"),
                new Extension("TMO"),
                new Extension("Other")};

        //extensions.forEach(System.out::println);
        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertEquals(expectedArray[i].getExtType(), extensions.get(i).getExtType());
        }

    }

    @Test
    public void testSumByQuarter() {
        SaleItem[] saleItemArray = {
                new SaleItem(1, 1.0),
                new SaleItem(2, 3.0),
                new SaleItem(3, 2.0),
                new SaleItem(4, 1.0),
                new SaleItem(7, 1.5),
                new SaleItem(11, 4.0),
                new SaleItem(12, 2.0)};
        List<SaleItem> saleItems = Arrays.asList(saleItemArray);

        List<QuarterSalesItem> quarterSalesItems = Utils.sumByQuarter(saleItems);


        //quarterSalesItems.forEach(System.out::println);
        QuarterSalesItem[] expectedArray = {
                new QuarterSalesItem(1, 6.0),
                new QuarterSalesItem(2, 1.0),
                new QuarterSalesItem(3, 1.5),
                new QuarterSalesItem(4, 6.0)};
        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertEquals(expectedArray[i].getQuarter(), quarterSalesItems.get(i).getQuarter());
            Assert.assertEquals(expectedArray[i].getAmount(), quarterSalesItems.get(i).getAmount(), 0.0);
        }

    }

    @Test
    public void testMaxByQuarter() {
        SaleItem[] saleItemArray = {
                new SaleItem(1, 1.0),
                new SaleItem(2, 3.0),
                new SaleItem(3, 2.0),
                new SaleItem(4, 1.0),
                new SaleItem(5, 2.0),
                new SaleItem(8, 1.0),
                new SaleItem(11, 4.0),
                new SaleItem(12, 2.0)};
        List<SaleItem> saleItems = Arrays.asList(saleItemArray);

        List<QuarterSalesItem> quarterSalesItems = Utils.maxByQuarter(saleItems);

        //quarterSalesItems.forEach(System.out::println);
        QuarterSalesItem[] expectedArray = {
                new QuarterSalesItem(1, 3.0),
                new QuarterSalesItem(2, 2.0),
                new QuarterSalesItem(3, 1.0),
                new QuarterSalesItem(4, 4.0)};
        for (int i = 0; i < expectedArray.length; i++) {
            Assert.assertEquals(expectedArray[i].getQuarter(), quarterSalesItems.get(i).getQuarter());
            Assert.assertEquals(expectedArray[i].getAmount(), quarterSalesItems.get(i).getAmount(), 0.0);
        }

    }

    @Test
    public void testGetUnUsedKeys() {
        int[] allKeys = {0, 1, 2, 3, 4, 5};
        int[] usedKeys = {2, 4, 5};
        int[] unUsedKeys = Utils.getUnUsedKeys(allKeys, usedKeys);

        int[] expectedUnUsedKeys = {0, 1, 3};
        Assert.assertArrayEquals(expectedUnUsedKeys, unUsedKeys);

    }
}
