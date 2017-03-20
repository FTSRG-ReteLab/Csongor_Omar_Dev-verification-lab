package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
	
	private TrainController controller;
	private TrainUser user;
	private TrainSensor sensor;
	
    @Before
    public void before() {
        // TODO Add initializations
    	controller = mock(TrainController.class);
    	when(controller.getReferenceSpeed()).thenReturn(100);
    	user = mock(TrainUser.class);
    	
    	
    	sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void testAbsoluteMarginNegative() {
        // TODO Delete this and add test cases based on the issues
    	sensor.overrideSpeedLimit(-1);
    	verify(user, times(1)).setAlarmState(true);
    	
    }
    
    @Test
    public void testAbsoluteMarginZero() { 
    	sensor.overrideSpeedLimit(0);
    	verify(user, times(1)).setAlarmState(true);
    }
    	
    @Test
    public void testAbsoluteMarginMiddle() { 
    	sensor.overrideSpeedLimit(250);
    	verify(user, times(1)).setAlarmState(false);
    }
    @Test
    public void testAbsoluteMarginMax() { 

    	sensor.overrideSpeedLimit(500);
    	verify(user, times(1)).setAlarmState(false);
    }
    
    @Test
    public void testAbsoluteMarginOverMax() { 
    	sensor.overrideSpeedLimit(501);
    	verify(user, times(1)).setAlarmState(true);
    }
    
    @Test
    public void testRelativeUnder() { 
    	sensor.overrideSpeedLimit(49);
    	verify(user, times(1)).setAlarmState(true);
    }
    
    @Test
    public void testRelativeAt() { 
    	sensor.overrideSpeedLimit(50);
    	verify(user, times(1)).setAlarmState(false);
    }
    
    @Test
    public void testRelativeOver() { 
    	sensor.overrideSpeedLimit(51);
    	verify(user, times(1)).setAlarmState(false);
    }
}
