package be.feeps.epicpets.particles.ParticlesList;

import be.feeps.epicpets.particles.EpicParticles;
import be.feeps.epicpets.utils.MathUtils;
import be.feeps.epicpets.utils.ParticleEffect;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Created by feeps on 07/06/2017.
 */
public class ParticlesSparks extends EpicParticles {

    private boolean up;
    private float height;
    private int step;

    public ParticlesSparks(Player player){
        super(player);
    }

    public void update() {
        if (up) {
            if (height < 1.6)
                height += 0.02;
            else
                up = false;
        } else {
            if (height > 0)
                height -= 0.02;
            else
                up = true;
        }
        float inc = (2 * MathUtils.PI) / 100;
        float angle = step * inc;
        Vector v = new Vector();
        v.setX(MathUtils.cos(angle) * 0.5);
        v.setZ(MathUtils.sin(angle) * 0.5);

        ParticleEffect.VILLAGER_HAPPY.display(0,0,0,0,1, this.epicPetsPlayer.getPet().getPetLoc().clone().add(v).add(0, height, 0), 100);
        step += 4;
    }
}
