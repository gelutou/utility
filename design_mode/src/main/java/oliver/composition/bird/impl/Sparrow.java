package oliver.composition.bird.impl;

import oliver.composition.bird.EggLayable;
import oliver.composition.bird.Flyable;
import oliver.composition.bird.Tweetable;

/**
 * 描述 麻雀会飞会叫会下蛋
 * 这样的问题在于每个会下蛋的鸟都要重写下蛋方法，通过组合和委托消除重复代码
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/03/01 10:37:38
 */
public class Sparrow implements Flyable, Tweetable, EggLayable {

    @Override
    public void layEgg() {

    }

    @Override
    public void fly() {

    }

    @Override
    public void tweet() {

    }
}
