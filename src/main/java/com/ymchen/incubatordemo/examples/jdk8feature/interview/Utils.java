package com.ymchen.incubatordemo.examples.jdk8feature.interview;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Question1, sort by firstName , lastName and  ext,
     * if firstName is the same then sort by lastName and
     * ext, please note lastName and ext can be empty string or null
     **/
    public static List<Extension> sortByName(List<Extension> extensions) {


        return extensions.stream().sorted(
                Comparator.comparing(Extension::getFirstName).
                        thenComparing(extension -> nullToEmpty(extension.getLastName())).
                        thenComparing(extension -> nullToEmpty(extension.getExt()))).
                collect(Collectors.toList());
    }

    /**
     * Question2, sort extType, extType is a string and can
     * be "User", "Dept", "AO", "TMO", "Other",
     * sort by User > Dept > AO > TMO > Other;
     * There might be new extType value, it will be in last order is it's not in prefined list.
     **/
    public static List<Extension> sortByExtType(List<Extension> extensions) {

        List<String> extTypeList = Arrays.asList("User", "Dept", "AO", "TMO", "Other");

        return extensions.stream().sorted((e1, e2) -> (
                extTypeList.indexOf(e1.getExtType()) - extTypeList.indexOf(e2.getExtType())
        )).collect(Collectors.toList());
    }

    /**
     * Question3, sum all sales items by quarter, sum the amount for the same quarter item.
     **/
    public static List<QuarterSalesItem> sumByQuarter(List<SaleItem> saleItems) {
        Map<Integer, Double> map = new HashMap<>();
        for (SaleItem saleItem : saleItems) {
            map.merge(calculateQuarterByMonth(saleItem.getMonth()), saleItem.getAmount(), Double::sum);
        }

        return mapToQuarterSalesItemList(map);
    }

    /**
     * Question4, max all sales items by quarter, get the max amount from the item belongs to the same quarter
     **/
    public static List<QuarterSalesItem> maxByQuarter(List<SaleItem> saleItems) {
        Map<Integer, Double> map = new HashMap<>();
        for (SaleItem saleItem : saleItems) {
            map.compute(calculateQuarterByMonth(saleItem.getMonth()), (k, v) -> {
                v = (null != v && v > saleItem.getAmount()) ? v : saleItem.getAmount();
                return v;
            });
        }
        return mapToQuarterSalesItemList(map);
    }

    //Question5

    /**
     * We have all Keys like 0-10000 with random order, it can be other keys in real cases;
     * usedKeys is an array to store all used keys like :[2,3,4] with random order;
     * We want to get all unused keys, in this example it would be: [0,1,5,6,7,8,9,....]
     */

    public static int[] getUnUsedKeys(int[] allKeys, int[] usedKeys) {
        List<Integer> usedKeyList = Arrays.stream(usedKeys).boxed().collect(Collectors.toList());
        List<Integer> allKeyList = Arrays.stream(allKeys).boxed().collect(Collectors.toList());
        return allKeyList.stream().filter(iterm -> !usedKeyList.contains(iterm)).mapToInt(Integer::valueOf).toArray();
    }

    /**
     * @param str
     * @return
     */
    private static String nullToEmpty(String str) {
        return (null == str || 0 == str.trim().length()) ? "" : str;
    }

    /**
     * calculate quarter by month
     *
     * @param month
     * @return
     */
    private static int calculateQuarterByMonth(int month) {
        return 0 == month % 3 ? month / 3 : month / 3 + 1;
    }

    /**
     * translate map to List<QuarterSalesItem>
     *
     * @param map
     * @return
     */
    private static List<QuarterSalesItem> mapToQuarterSalesItemList(Map<Integer, Double> map) {
        List<QuarterSalesItem> quarterSalesItems = new ArrayList<>(Collections.emptyList());

        map.forEach((key, value) -> {
            QuarterSalesItem quarterSalesItem = new QuarterSalesItem();
            quarterSalesItem.setQuarter(key);
            quarterSalesItem.setAmount(value);
            quarterSalesItems.add(quarterSalesItem);
        });

        return quarterSalesItems;
    }
}
