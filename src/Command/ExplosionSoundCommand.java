package Command;

public class ExplosionSoundCommand implements Command {

    private ExplosionSound explosionSound;

    public ExplosionSoundCommand(ExplosionSound explosionSound) {
        this.explosionSound = explosionSound;
    }

    @Override
    public void execute() {
        explosionSound.playExplosionSound();
    }
}
