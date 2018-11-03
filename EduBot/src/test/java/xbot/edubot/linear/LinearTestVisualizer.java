package xbot.edubot.linear;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

public class LinearTestVisualizer {
    
    private JFrame frmLinearTestVisualizer;
    private LinearVisualizationPanel vizPanel;
    private JPanel controlPanel;
    private JSlider speedSlider;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LinearTestVisualizer window = new LinearTestVisualizer();
                    window.frmLinearTestVisualizer.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public LinearTestVisualizer() {
        initialize();
    }
    
    private void initialize() {
        frmLinearTestVisualizer = new JFrame();
        
        frmLinearTestVisualizer.setTitle("Linear test visualizer");
        frmLinearTestVisualizer.setBounds(100, 100, 800, 400);
        frmLinearTestVisualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLinearTestVisualizer.getContentPane().setLayout(new BoxLayout(frmLinearTestVisualizer.getContentPane(), BoxLayout.Y_AXIS));
        
        JSplitPane splitPane = new JSplitPane();
        frmLinearTestVisualizer.getContentPane().add(splitPane);
        
        vizPanel = new LinearVisualizationPanel(800, 500);
        splitPane.setLeftComponent(vizPanel);
        
        controlPanel = new JPanel();
        splitPane.setRightComponent(controlPanel);
        
        speedSlider = new JSlider();
        speedSlider.setMinimum(1);
        speedSlider.setValue(10);
        controlPanel.add(speedSlider);
        
        startTest();
    }
        
    private void startTest() {
        DriveToPositionCommandTest testToRun = new DriveToPositionCommandTest();
        testToRun.setUp();
        
        testToRun.setAsAsync((DriveToPositionCommandTest.LinearEnvironmentState envState) -> {
            vizPanel.updateViz(envState);
            vizPanel.repaint();
            
            if(envState.isFinished) {
                testToRun.stopTestEnv();
            }
            
            testToRun.setAsyncPeriodMultiplier(10d / speedSlider.getValue());
        });
        
        testToRun.vizRun();
        
        /*
        
        
        currentTestEnvironment.setAsAsync((BaseOrientationEngineTest.RotationEnvironmentState envState) -> {
            this.envState = envState;
            vizPanel.updateState(envState);
            vizPanel.repaint();
            
            if(envState.isCommandFinished) {
                currentTestEnvironment.stopTestEnv();
            }
            
            // The slider makes the engine do more physics
            currentTestEnvironment.setAsyncPeriodMultiplier(10d / speedSlider.getValue());
        });
        
        ((SelectableOrientationTest) currentTestEnvironment).invokeOrientationTest(test);
        */
    }
}
