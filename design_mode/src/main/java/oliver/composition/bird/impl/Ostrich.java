package oliver.composition.bird.impl;

import oliver.composition.bird.EggLayable;
import oliver.composition.bird.Tweetable;

/**
 * 描述 鸵鸟会叫会下蛋
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/03/01 10:36:22
 */
public class Ostrich implements Tweetable, EggLayable {

    @Override
    public void layEgg() {
    }

    @Override
    public void tweet() {
    }
}
