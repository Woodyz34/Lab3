import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    protected CarView frame;
    // A list of cars, modify if needed
    protected static ArrayList<Cars> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                car.move();
                if (car.getX()>=700){
                    car.setX(700);
                    car.currentSpeed=0;
                    car.direction = 3;}
                if (car.getX() <= 0) {
                    car.direction = 1;
                    car.currentSpeed = 0;
                    car.setX(0);
                }



            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Cars car : cars
        ) {
            car.brake(brake);
        }
    }
    void startCars() {
        for (Cars car : cars) {
            if (car.currentSpeed == 0) {
                car.startEngine();
            }
        }
    }
    void stopCars() {
        for (Cars car : cars) {
                car.currentSpeed = 0;

        }
    }
    void höjFlak(){
        for(Cars car : cars) {
            if (car instanceof Scania)
                ((Scania) car).höjFlak(25);
        }
    }
    void sänkFlak(){
        for(Cars car : cars) {
            if (car instanceof Scania)
                ((Scania) car).sänkFlak(25);
        }
    }
    void turboOn(){
        for (Cars car : cars) {
            if (car instanceof Saab95)
                ((Saab95)car).setTurboOn();
        }
    }

    void turboOff(){
        for(Cars car : cars) {
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }

}
