package be.feeps.epicpets.particles;

import be.feeps.epicpets.EpicPetsPlayer;
import org.bukkit.entity.Player;

/**
 * Created by feeps on 06/06/2017.
 */
public abstract class EpicParticles {
    protected EpicPetsPlayer epicPetsPlayer;


    public EpicParticles(Player player) {
        this.epicPetsPlayer = EpicPetsPlayer.instanceOf(player);
        this.epicPetsPlayer.setEpicParticles(this);
    }

    public abstract void update();

    public void stop(){
        this.epicPetsPlayer.setEpicParticles(null);
    }

}
