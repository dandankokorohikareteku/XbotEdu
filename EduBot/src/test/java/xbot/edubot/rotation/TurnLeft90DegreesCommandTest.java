package xbot.edubot.rotation;

import org.junit.Test;

import xbot.edubot.rotation.RotationTestVisualizer.OrientationTest;
import xbot.edubot.rotation.RotationTestVisualizer.SelectableOrientationTest;

public class TurnLeft90DegreesCommandTest extends BaseOrientationEngineTest implements SelectableOrientationTest {
    @Test
    public void goLeft90FromStart() {
        setUpTestEnvironmentFor90(0);
        runTestEnv();
    }

    @Test
    public void goLeft90FromNeg90() {
        setUpTestEnvironmentFor90(-90);
        runTestEnv();
    }

    @Test
    public void goLeft90FromNeg150() {
        setUpTestEnvironmentFor90(-150);
        runTestEnv();
    }

    @Test
    public void goLeft90From150() {
        setUpTestEnvironmentFor90(150);
        runTestEnv();
    }

    @Override
    public void invokeOrientationTest(OrientationTest test) {
        switch (test) {
            case GO_LEFT_90_FROM_0:
                this.goLeft90FromStart();
                break;
            case GO_LEFT_90_FROM_NEG_90:
                this.goLeft90FromNeg90();
                break;
            case GO_LEFT_90_FROM_NEG_150:
                this.goLeft90FromNeg150();
                break;
            case GO_LEFT_90_FROM_150:
                this.goLeft90From150();
                break;
            default:
                throw new RuntimeException("The requested orientation test is not available in this test class.");
        }
    }
}
