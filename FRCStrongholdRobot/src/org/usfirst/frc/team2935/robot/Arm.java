package org.usfirst.frc.team2935.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

//Outlines the Arm subsystem of the robot
public class Arm{
	
//Declaration of objects used in the program
	Joystick control = new Joystick(0);
	VictorSP rotate  = new VictorSP(3),
	         intake  = new VictorSP(2);
	AnalogPotentiometer pot = new AnalogPotentiometer(0,360,0);
	
	private double potVal = 0; 
//Constructor for the class
	public Arm(){
	}
	
//Method for obtaining the current potentiometer value	
	public double getPotValue()
	{
		potVal = pot.get();
		return potVal;
	} 
//Method declaration outlining the controls for the rotating the arm	
	public void rotate(double speed)//Speed is parameter of how fast to run motors/turn the arm
	{
		while(control.getRawButton(6))//While button 10 is being pressed rotate the arm at inputed speed
		{
			rotate.set(speed);
		}
			rotate.set(0);//When not pressed set arm rotation to 0
			
		while(control.getRawButton(7))//While button 10 is being pressed rotate the arm in reverse at inputed speed
		{
			rotate.set(-speed);
		}
		rotate.set(0);//When not pressed set arm rotation to 0
	}

//Method declaration outlining the controls for the in-taking 
	public void intake(double speed)//Speed is parameter of how fast to run motors/in-take 
	{
			if(control.getRawButton(2))//If button 2 is pressed then make in-take run at inputed speed
			{
				intake.set(speed);
			}
			else if(control.getRawButton(3))//If button 3 is pressed then make in-take run at inputed speed in reverse
			{
				intake.set(-speed);
			}
			else//Else set the in-take motor to 0
			{
				intake.set(0);
			}
	 }
	
//Method declaration outlining the autonomous out-taking of the ball
	public void autoShoot(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										     //for how long to run
		intake.set(speed);
		Timer.delay(time);
		intake.set(0);
	}	
	
//Method declaration outlining the autonomous rotation of the arm	
	public void autoRotate(double speed, double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										     //for how long to run
		rotate.set(speed);
		Timer.delay(time);
		rotate.set(0);
		Timer.delay(0.5);
	}
	
//Method declaration outlining the autonomous shooting functionality	
	public void shoot(double speed,double time,double time2)//Speed is parameter of how fast to run motors and time is parameter of  
	{										                //for how long to run
		if(control.getRawButton(4))//If button 4 is pressed then set the arm to shooting position and shoot the ball
		{
			rotate.set(speed);
			Timer.delay(time);
			rotate.set(0);
			intake.set(speed);
			Timer.delay(time2);
			intake.set(0);
			rotate.set(-speed);
			Timer.delay(time);
		}
	}
	
//Method declaration outlining the autonomous rotation of the arm	
	public void allIn(double speed, double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										    //for how long to run
		if(control.getRawButton(5))//If button 5 is pressed then move the arm all the way in
		{
			rotate.set(-speed);
			Timer.delay(time);
			rotate.set(0);
		}		
	}
	public void autoIntake(double speed)
	{
		intake.set(speed);
	}
  }

