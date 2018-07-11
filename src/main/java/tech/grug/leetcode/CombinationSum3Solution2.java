package tech.grug.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feichen on 2018/7/11.
 */
public class CombinationSum3Solution2 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumeCore(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void combinationSumeCore(List<List<Integer>> result, List<Integer> tempList, int n, int k, int start) {
        if (k == 0 && n == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        if (k == 0 || n <= 0) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            combinationSumeCore(result, tempList, n - i, k - 1, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum3Solution2 combinationSum3Solution2 = new CombinationSum3Solution2();
        List<List<Integer>> listList = combinationSum3Solution2.combinationSum3(5, 25);
        System.out.println(JSON.toJSONString(listList));
    }
}
