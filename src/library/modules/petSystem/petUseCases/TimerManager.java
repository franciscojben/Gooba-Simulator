package library.modules.petSystem.petUseCases;

import library.modules.petSystem.petEntities.Egg;
import library.modules.petSystem.petEntities.Pet;

import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
    private Timer hatchTimer;
    private Timer hungerTimer;
    private Timer ageTimer;
    private Timer happinessTimer;
    private Timer sicknessTimer;

    /**
     * Begin the hatching timer
     * @param delay Time until hatching is carried out
     * @param egg Egg to be hatched
     */
    public void beginHatchTimer(long delay, Egg egg){
        TimerTask hatchTask = new TimerTask() {
            @Override
            public void run() {
                egg.hatch();
            }
        };
        this.hatchTimer = new Timer();
        this.hatchTimer.schedule(hatchTask, delay);
    }

    /**
     * End the hatching timer
     */
    public void stopHatchTimer(){
        this.hatchTimer.cancel();
        this.hatchTimer.purge();
    }
    /**
     * Begin the hunger timer
     * @param pet Pet
     */
    public void beginHungerTimer(Pet pet){
        TimerTask hungryTask = new TimerTask() {
            @Override
            public void run() {
                pet.starve();
                if (pet.getHungerStatus() >= 100) {
                    sicknessTimer(pet);
                    stopLifeTimers();
                    pet.setHealthStatus(false);
                }
            }
        };
        this.hungerTimer = new Timer();
        int period = (int) ((Math.random() * (7000 - 3000)) + 3000);
        this.hungerTimer.scheduleAtFixedRate(hungryTask,0, period);
    }
    /**
     * Begin the happiness timer
     * @param pet Pet
     */
    public void beginHappinessTimer(Pet pet){
        TimerTask happinessTask = new TimerTask() {
            @Override
            public void run() {

                pet.sadden();
                if (pet.getHappinessStatus() <= 0) {
                    sicknessTimer(pet);
                    stopLifeTimers();
                    pet.setHealthStatus(false);
                }
            }
        };
        this.happinessTimer = new Timer();
        int period = (int) ((Math.random() * (8000 - 4000)) + 4000);
        this.happinessTimer.scheduleAtFixedRate(happinessTask, 1, period);
    }
    /**
     * Begin the Ageing Timer
     * @param pet Pet
     */
    public void beginAgeTimer(Pet pet){
        TimerTask ageTask = new TimerTask() {
            @Override
            public void run() {
                pet.getOlder();
            }
        };
        this.ageTimer = new Timer ();
        this.ageTimer.scheduleAtFixedRate(ageTask, 0, 30 * 60 * 1000);
    }
    /**
     * Begin the Sickness timer
     * @param pet Pet
     */
    public void sicknessTimer(Pet pet){
        TimerTask sickTask = new TimerTask() {
            @Override
            public void run() {
                pet.die();
                stopLifeTimers();
            }
        };
        this.sicknessTimer = new Timer ();
        this.sicknessTimer.schedule(sickTask,50000);
    }
    /**
     * Stop all life function timers - Happiness, age, hunger
     */
    public void stopLifeTimers() {
        this.happinessTimer.cancel();
        this.ageTimer.cancel();
        this.hungerTimer.cancel();
        this.happinessTimer.purge();
        this.ageTimer.purge();
        this.hungerTimer.purge();
    }
    /**
     * Stop the sickness timer
     */
    public void stopSicknessTimer() {
        this.sicknessTimer.cancel();
        this.sicknessTimer.purge();
    }
}
