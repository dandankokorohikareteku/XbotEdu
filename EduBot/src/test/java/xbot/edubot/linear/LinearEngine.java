package xbot.edubot.linear;

public class LinearEngine {

    double velocity = 0;
    double friction = 0.005;
    double power_factor = 0.1;
    double totalDistance = 0;
    
    public LinearEngine() {
        
    }

    public double step(double forwardPower) {
        // apply motor power
        velocity += forwardPower * power_factor;
        
        // apply friction model to velocity
        if(velocity > 0) {
            velocity -= friction;
            velocity = Math.max(0, velocity);
        } else {
            velocity += friction;
            velocity = Math.min(0, velocity);
        }
        
        totalDistance += velocity;
        return totalDistance;
    }
    
    public double getVelocity() {
        return velocity;
    }
    
    public double getDistance() {
        return totalDistance;
    }
}
