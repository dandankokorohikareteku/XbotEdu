package xbot.edubot.rotation;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Ignore;

import xbot.common.command.BaseCommandTest;
import xbot.common.math.XYPair;
import xbot.edubot.rotation.BaseOrientationEngineTest.AsyncRotationIntervalJob;
import xbot.edubot.rotation.BaseOrientationEngineTest.RotationEnvironmentState;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import xbot.edubot.rotation.RotationTestVisualizer.OrientationTest;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSlider;

public class RotationTestVisualizer {
    private JFrame frmOrientationTestVisualizer;
    private RotationVisualizationPanel vizPanel;
    BaseOrientationEngineTest currentTestEnvironment;
    RotationEnvironmentState envState = new RotationEnvironmentState();
    private JPanel controlPanel;
    private JComboBox testSelectionBox;
    private JSlider speedSlider;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RotationTestVisualizer window = new RotationTestVisualizer();
                    window.frmOrientationTestVisualizer.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public RotationTestVisualizer() {
        initialize();
    }
    
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmOrientationTestVisualizer = new JFrame();
        frmOrientationTestVisualizer.setTitle("Orientation test visualizer");
        frmOrientationTestVisualizer.setBounds(100, 100, 600, 400);
        frmOrientationTestVisualizer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmOrientationTestVisualizer.getContentPane().setLayout(new BoxLayout(frmOrientationTestVisualizer.getContentPane(), BoxLayout.Y_AXIS));
        
        JSplitPane splitPane = new JSplitPane();
        frmOrientationTestVisualizer.getContentPane().add(splitPane);
        
        vizPanel = new RotationVisualizationPanel(400, 300);
        splitPane.setLeftComponent(vizPanel);
        
        controlPanel = new JPanel();
        splitPane.setRightComponent(controlPanel);
        
        testSelectionBox = new JComboBox();
        testSelectionBox.addItemListener((e) -> {
            if(e.getStateChange() == ItemEvent.SELECTED) {
                setOrientationTest((OrientationTest)e.getItem());
            }
        });
        
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        testSelectionBox.setModel(new DefaultComboBoxModel(OrientationTest.values()));
        testSelectionBox.setSelectedIndex(0);
        controlPanel.add(testSelectionBox);
        
        speedSlider = new JSlider();
        speedSlider.setMinimum(1);
        speedSlider.setValue(10);
        controlPanel.add(speedSlider);
        
        vizPanel.updateState(envState);
        
        setOrientationTest((OrientationTest)testSelectionBox.getSelectedItem());
    }
    
    private void setOrientationTest(OrientationTest test) {
        if(currentTestEnvironment != null) {
            currentTestEnvironment.stopTestEnv();
        }
        
        switch(test) {
            case GO_LEFT_90_FROM_0:
            case GO_LEFT_90_FROM_150:
            case GO_LEFT_90_FROM_NEG_150:
            case GO_LEFT_90_FROM_NEG_90:
                currentTestEnvironment = new TurnLeft90DegreesCommandTest();
                break;
            case ROTATE_TO_ORIENTATION:
                currentTestEnvironment = new GoToOrientationTest();
                break;
        }
        
        currentTestEnvironment.setUp();
        
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
        
    }

    public static enum OrientationTest {
        GO_LEFT_90_FROM_0,
        GO_LEFT_90_FROM_NEG_90,
        GO_LEFT_90_FROM_NEG_150,
        GO_LEFT_90_FROM_150,
        ROTATE_TO_ORIENTATION
    }
    
    public static interface SelectableOrientationTest {
        void invokeOrientationTest(OrientationTest test);
    }
}
