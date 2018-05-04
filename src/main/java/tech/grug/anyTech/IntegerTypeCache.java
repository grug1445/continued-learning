package tech.grug.anyTech;

/**
 * 整型缓存机制
 * 以下缓存只在 自动装箱（autobox）时有用.new 对象无用
 */
public class IntegerTypeCache {

    public static void main(String[] args) {


        /**
         * 运行日志当大于127时,autobox的引用判断为false
         */
        for (int i = 0; i < 200; i++) {

            /**
             * Integer Cache test
             *
             * 缓存默认-128至127,范围可能通过-XX:AutoBoxCacheMax=size 修改
             */
            Integer integer1 = new Integer(i);
            Integer integer2 = new Integer(i);
            Integer integer3 = i;
            Integer integer4 = i;
            boolean result1 = integer1 == integer2;
            boolean result2 = integer3 == integer4;
            System.out.println("integer1 " + integer1 + " =  integer2 " + integer2 + " result is " + result1);
            System.out.println("integer3 " + integer3 + " =  integer4 " + integer4 + " result is " + result2);

            /**
             * Long Cache test
             * 缓存范围为 -128至127
             */

            Long long1 = new Long(i);
            Long long2 = new Long(i);
            Integer long3 = i;
            Integer long4 = i;
            boolean result3 = long1 == long2;
            boolean result4 = long3 == long4;
            System.out.println("long1 " + long1 + " =  long2 " + long2 + " result is " + result3);
            System.out.println("long3 " + long3 + " =  long4 " + long4 + " result is " + result4);

            /**
             *Short Cache test
             *  缓存范围为 -128至127
             */

            Short short1 = new Short((short) i);
            Short short2 = new Short((short) i);
            Short short3 = (short) i;
            Short short4 = (short) i;
            boolean result5 = short1 == short2;
            boolean result6 = short3 == short4;
            System.out.println("short1 " + short1 + " =  short2 " + short2 + " result is " + result5);
            System.out.println("short3 " + short3 + " =  short4 " + short4 + " result is " + result6);
            System.out.println();
        }


        /**
         * Character 缓存大小为 unicode 0-127
         */
        Character character1 = new Character('1'); //unicode 49
        Character character2 = '1';
        Character character3 = '1';
        System.out.println("character1 ==  character2 " + (character1 == character2));//false
        System.out.println("character3 ==  character4 " + (character2 == character3));//true

        Character character4 = new Character('大');//unicode>127
        Character character7 = new Character('大');
        Character character5 = '大';
        Character character6 = '大';
        System.out.println("character4 ==  character5 " + (character4 == character5));//false
        System.out.println("character5 ==  character6 " + (character5 == character6));//false
        System.out.println("character4 ==  character7 " + (character4 == character7));//false

        /**
         * Byte 缓存大小为 -128至127
         * autobox的引用比较全部为true
         */
        for (byte i = Byte.MIN_VALUE; i < Byte.MAX_VALUE; i++) {
            Byte byte1 = new Byte(i);
            Byte byte2 = new Byte(i);

            Byte byte3 = i;
            Byte byte4 = i;

            System.out.println("byte1 " + byte1 + " == byte2 " + byte2 + " is " + (byte1 == byte2));//false
            System.out.println("byte3 " + byte3 + " == byte4 " + byte4 + " is " + (byte3 == byte4));//true
            System.out.println();

        }


    }

}
