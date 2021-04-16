package oliver.composition.bird.delegation.impl;

import oliver.composition.bird.EggLayable;
import oliver.composition.bird.Tweetable;
import oliver.composition.bird.delegation.EggLayAbility;
import oliver.composition.bird.delegation.TweetAbility;

/**
 * 描述
 *
 * @author Oliver
 * @version 1.0
 * @date 2021/03/01 10:41:11
 */
public class Ostrich implements Tweetable, EggLayable {

    //叫行为组合
    private TweetAbility tweetAbility = new TweetAbility();

    //叫行为组合
    private EggLayAbility eggLayAbility = new EggLayAbility();

    @Override
    public void layEgg() {
        eggLayAbility.layEgg();
    }

    @Override
    public void tweet() {
        tweetAbility.tweet();
    }
}
