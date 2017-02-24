import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.util.Stopwatch;


public class RegisterClap {
	
	private static SoundSensor sound = new SoundSensor(SensorPort.S1);
	private static int lowSoundThreshold = 30; //Skift denne!
    private static int loudSoundThreshold = 90;
    private static Stopwatch timer = new Stopwatch();
    private static int elapsed;
    private static boolean isClap;
	
	private static void waitForLoudSound() throws Exception {
        int soundLevel = sound.readValue();

        while ( soundLevel < loudSoundThreshold ) {
            soundLevel = sound.readValue();
            
            if (soundLevel <= lowSoundThreshold) {
            	timer.reset();
            }
        } 
    }
	
	private static void checkForClap() throws Exception {
		int soundLevel = sound.readValue();
		int timeThreshold = 250; //ms
		
		while (timer.elapsed() < timeThreshold) {
			soundLevel = sound.readValue();
			if (soundLevel < lowSoundThreshold) {
				isClap =  true;
			}
		
		}
		
	}
	
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
        LCD.drawString("dB level: ",0,0);
        LCD.refresh();
	   	   
        while (! Button.ESCAPE.isDown())
        {
            waitForLoudSound();		    			   
            if (timer.elapsed() <= 25) {
            	checkForClap();
            }
            LCD.clear();
            LCD.drawString("Is clap? " + isClap, 0, 0);
            Thread.sleep(2000);
            LCD.drawString("Clap now!",0,4); 
            isClap = false;
       }
        
       LCD.clear();
       LCD.drawString("Program stopped", 0, 0);
       Thread.sleep(2000); 

    }
}
