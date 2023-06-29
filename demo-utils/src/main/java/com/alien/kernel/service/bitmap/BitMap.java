package com.alien.kernel.service.bitmap;

/**
 * @Auther: FengYunJun
 * @Date: 2019/3/12 13:54
 * @Description: bitMap test
 */
public class BitMap {
    private byte[] bits;
    private int capacity;


    public BitMap(int capacity) {
        this.capacity = capacity;
        bits = new byte[(capacity >> 3) + 1];
    }

    /**
     * 添加数 (先获取数的索引)
     *
     * @param
     * @return
     */

    public void add(int num) {
        int arrayindex = num >> 3;
        int position = num & 0x07;
        bits[arrayindex] |= 1 << position;
    }

    /**
     * 判断是否包含  ( //将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可)
     *
     * @param
     * @return
     */

    public boolean contain(int num) {
        int arrayindex = num >> 3;
        int position = num & 0x07;
        return (bits[arrayindex] & (1 << position)) != 0;
    }

    /**
     * 删除数  ( //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.)
     *
     * @param
     * @return
     */

    public void clear(int num) {
        int arrayindex = num >> 3;
        int position = num & 0x07;
        bits[arrayindex] &= ~(1 << position);
    }


    public static void main(String[] args) {
        BitMap bitMap = new BitMap(32);
        bitMap.add(10);
        bitMap.add(11);
        System.out.println(bitMap.contain(10));
        //bitMap.clear(10);
        System.out.println(bitMap.contain(10));
        System.out.println(bitMap.bits[1]);
    }
}
