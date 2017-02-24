import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;


public class PartyFinder {
	
	private static int soundLevelRight;
	private static int soundLevelLeft;
	private static SoundSensor soundRight = new SoundSensor(SensorPort.S1);
	private static SoundSensor soundLeft = new SoundSensor(SensorPort.S3);
	
	public static void main(String [] args) throws Exception
    {

		Button.ESCAPE.addButtonListener(new ButtonListener(){
			@Override
			public void buttonPressed(Button b) {
				System.exit(1);
			}

			@Override
			public void buttonReleased(Button b) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
	   	   
        while (! Button.ESCAPE.isDown()) {
        	LCD.clear();
        	soundLevelRight = soundRight.readValue();
        	soundLevelLeft = soundLeft.readValue();
        	
        	if (soundLevelLeft > 20 && soundLevelRight > 20) {
	        	LCD.drawInt(soundLevelRight, 0, 0, 0);
	        	LCD.drawInt(soundLevelLeft, 0, 0, 1);
	        	
	        	//Hvilken er st퓊st?
	        	if (soundLevelLeft > soundLevelRight) {
	        		//Venstre side har mest lyd, drej til venstre
	        		Car.forward(80, 0);
	        		Thread.sleep(50);
	        		
	        	} else if (soundLevelLeft == soundLevelRight) {
	        		// Lige store, k퓊 ligeud
	        		Car.forward(80, 80);
	        		Thread.sleep(50);
	        	}
	        	else {
	        		//H퓂re side har mest lyd, drej til h퓂re
	        		Car.forward(0, 80);
	        		Thread.sleep(50);
	        	}
        	}
        	
        	else {
        		Car.forward(0, 0);
        	}
        	
        	
       }
        
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000); 

    }

}
