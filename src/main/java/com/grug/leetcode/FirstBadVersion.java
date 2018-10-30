package com.grug.leetcode;

/**
 * Created by feichen on 2018/5/28.
 * <p>
 * https://leetcode-cn.com/problems/first-bad-version/description/
 * <p>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * <p>
 * 给定 n = 5
 * <p>
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * <p>
 * 所以，4 是第一个错误的版本。
 */
public class FirstBadVersion {

    public int count = 0;


    public static void main(String[] args) {
        FirstBadVersion firstBadVersion = new FirstBadVersion();
        System.out.println(firstBadVersion.firstBadVersion(30));
        System.out.println("call isBadVersion count is " + firstBadVersion.count);
    }

    class Pos {
        int startPos;
        int endPos;
    }

    public int firstBadVersion(int n) {
        Pos pos = new Pos();
        pos.startPos = 1;
        pos.endPos = n;
        return find(pos);
    }

    public int find(Pos pos) {
        if (pos.endPos == pos.startPos) {
            return pos.endPos;
        }
        int result = pos.endPos - pos.startPos;
        int temp = result & 1;
        int position;
        if (temp == 0) {
            position = pos.startPos + result / 2;
        } else {
            position = pos.startPos + (result + 1) / 2;
        }
        if (isBadVersion(position)) {
            if (!isBadVersion(position - 1)) {
                return position;
            }
            Pos pos1 = new Pos();
            pos1.startPos = pos.startPos;
            pos1.endPos = position - 1;
            return find(pos1);
        } else {
            if (isBadVersion(position + 1)) {
                return position+1;
            }
            Pos pos2 = new Pos();
            pos2.startPos = position + 1;
            pos2.endPos = pos.endPos;
            return find(pos2);
        }
    }



    boolean isBadVersion(int version) {
        count++;
        if (version > 27) {
            return true;
        } else {
            return false;
        }
    }


}
