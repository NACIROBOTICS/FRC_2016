package org.usfirst.frc.team2935.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

//Outlines the drive-train subsystem of the robot
public class Drivetrain{
	
//Declaration of objects used in the class 	
	Solenoid shifterHigh   = new Solenoid(0);
	Solenoid shifterLow    = new Solenoid(1);
	VictorSP right1        = new VictorSP(0),
			 right2        = new VictorSP(1),
	         left1         = new VictorSP(4),
			 left2         = new VictorSP(5);   
	Joystick control       = new Joystick(0);
	
//Declaration of variables used in the class	
	private double turn;
	private double move;
	private final double THRESHOLD_TURN = 0.25;//Defines the threshold that must be passed on joy-stick to begin turning 
	private final double THRESHOLD_MOVE = 0.1;//Defines the threshold that must be passed on joy-stick to begin moving laterally 

//Constructor for the class	
	public Drivetrain(){		
	}

//Method declaration outlining the drive controls for the drive-train	
	public void drive()
	{
		if(Math.abs(control.getRawAxis(0))>THRESHOLD_TURN)//If the joy-stick position is greater than threshold assign value
		{
			turn = control.getRawAxis(0);//Variable turn is assigned the current value of the joy-stick
		}
		else//If not greater than threshold the variable is assigned a default value of 0
		{
			turn = 0;//Variable turn is assigned the value 0
		}
		if(Math.abs(control.getRawAxis(1))>THRESHOLD_MOVE)//If the joy-stick position is greater than threshold assign value
		{
			move = control.getRawAxis(1);//Variable move is assigned the current value of the joy-stick
		}
		else//If not greater than threshold the variable is assigned a default value of 0
		{
			move = 0;//Variable move is assigned the value 0
		}
//Reversing the left side of the drive train
		invert();
//Controls for single joy-stick tank drive
		left1.set (-move-turn);
		left2.set (-move-turn);
		right1.set(-move+turn);
		right2.set(-move+turn);
		
		if(control.getRawButton(1))//If button 1 is pressed on the controller then engage the piston for the shifter
		{
			shifterHigh.set(true);//Set the shifter-High solenoid to open to engage high speed ratio
			shifterLow.set(false);//Set the shifter-Low solenoid to closed to allow for high speed ratio
		}
		else//If button is not pressed or is released then disengage high speed and return to torque ratio
		{
			shifterHigh.set(false);//Set the shifter-High solenoid to closed to allow for torque ratio
			shifterLow.set(true);  //Set the shifter-Low solenoid to open to disengage high speed
		}
	}	
	
//Function declaration for setting drive-train motors to 0	
	public void stop(double time)//Time is the parameter of how long to stop
	{
		invert();//Call the invert function
		right1.set(0);
		right2.set(0);
		left1.set(0);
		left2.set(0);
		Timer.delay(time);
	}
	
//Function declaration for inverting the direction of spin for the left drive-train motors	
	public void invert()
	{
		//Reversing the left side of the drive train
		left1.setInverted(true);
		left2.setInverted(true);
	}

//Method declaration outlining the autonomous forward/backward movement of the drive-train	
	public void move(double speed,double time)//Speed is parameter of how fast to run motors 
	{                                          //and time is parameter of for how long to run
		invert();//Call the invert function
		right1.set(speed);
		right2.set(speed);
		left1.set(speed);
		left2.set(speed);
		Timer.delay(time);
	}
//Method declaration outlining the autonomous forward/backward movement of the drive-train	
	public void move(double speed)//Speed is parameter of how fast to run motors 
	{                                
			invert();//Call the invert function
			right1.set(speed);
			right2.set(speed);
			left1.set(speed);
			left2.set(speed);
	}
	
//Method declaration outlining the autonomous turning motion of the drive-train
	public void turn(double speed, char side,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{													//for how long to run and side is parameter of which side to turn	
		invert();   //Call the invert function
		switch(side)//Depending on the side inputed the it will turn in the corresponding at a speed and for a time
		{
		case 'l':
			left1.set(speed);
			left2.set(speed);
			right1.set(-speed);
			right2.set(-speed);
			Timer.delay(time);
			break;
		case 'r':
			left1.set(-speed);
			left2.set(-speed);
			right1.set(speed);
			right2.set(speed);
			Timer.delay(time);
		}
	}
	
//Method declaration outlining the autonomous forward/backward movement of the drive-train under the low-Bar
	public void lowBar(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{											//for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}
	
//Method declaration outlining the autonomous forward/backward movement of the drive-train over the moat	
	public void moat(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										//for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}
	
//Method declaration outlining the autonomous forward/backward movement of the drive-train over the rough-Terrain
	public void roughTerrain(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										          //for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}

//Method declaration outlining the autonomous forward/backward movement of the drive-train over the rock-Wall
	public void rockWall(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										      //for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}

//Method declaration outlining the autonomous forward/backward movement of the drive-train over the ramparts
	public void rampart(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										     //for how long to run
		invert();        //Call the invert function		
		move(speed,time);//Call the move method
	}
		
//Method declaration outlining the autonomous forward/backward movement of the drive-train under the portcullis
	public void portcullis(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										       //for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}

//Method declaration outlining the autonomous forward/backward movement of the drive-train under the portcullis
	public void cheval(double speed,double time)//Speed is parameter of how fast to run motors and time is parameter of  
	{										       //for how long to run
		invert();        //Call the invert function
		move(speed,time);//Call the move method
	}
//Method declaration outlining the autonomous movement of the drive-train over the defense, and to the low goal
	public void lowGoal(double time, double time2,double time3)
	{
		invert();             //Call the invert function
		rockWall(0.65,time);    //Call the low-Bar method
		stop(1.25);           //Call the stop function
		turn(0.6,'l',time2);  //Call the turn function
		stop(1.0);            //Call the stop function
		move(0.6,time3);      //Call the move method
		//stop(1.0);          //Call the stop function
	}
	public void brave(double time, double time2,double time3)
	{
		invert();            //Call the invert function
		lowBar(0.65,time);   //Call the low-Bar method
		stop(1.25);          //Call the stop function
		turn(0.6,'r',time2); //Call the turn function
		stop(1.0);           //Call the stop function
		move(0.6,time3);     //Call the move method
		//stop(1.0);         //Call the stop function
	}
}
