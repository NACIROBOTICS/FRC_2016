package org.usfirst.frc.team2935.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class Robot extends IterativeRobot{
	
//Declaration of objects used in the class
	Drivetrain chassis;
	Arm mechanism;
	Compressor mainCompressor;
	CameraServer server;
	CameraServer server1;
	SendableChooser chooser;
	
//Private variable used in selecting autonomous mode	
	private int mode = 1;	
//Boolean variable used to set autonomous on or off
	private boolean flag = true;
//Boolean variable used to determine whether or not to release ball	
	private boolean flag2 = true;

    public void robotInit() {  
    	
//Initialization of the objects declared
    	chassis = new Drivetrain();
    	mechanism = new Arm();
    	mainCompressor = new Compressor(0);
    	
//Initialize Camera 	
    	server = CameraServer.getInstance();
    	//server.setQuality(25);
    	server.startAutomaticCapture("cam0",0);
    	
//Initialize Dash-board with radio buttons for different the autonomous 
    	chooser = new SendableChooser();
    	chooser.addDefault("Move", 1);
    	chooser.addDefault("Move(Outake)", 2);
    	chooser.addDefault("Low Bar", 3);
    	chooser.addDefault("Low Bar(Outake)", 4);
    	chooser.addObject("Moat", 5);
    	chooser.addObject("Moat(Outake)", 6);
    	chooser.addObject("RoughTerrain", 7);
    	chooser.addObject("RoughTerrain(Outake)", 8);
    	chooser.addObject("RockWall", 9);
    	chooser.addObject("RockWall(Outake)", 10);
    	chooser.addObject("Portcullis", 11);
    	chooser.addObject("Portcullis(Outake)", 12);
    	chooser.addObject("Rampart", 13);
    	chooser.addObject("Rampart(Outake)", 14);
    	chooser.addObject("BRAVE!", 15);
    	chooser.addObject("LowGoal", 16);
    	chooser.addObject("Spy 188(Do not use)", 17);
    	chooser.addObject("Double Cross", 18);
    	SmartDashboard.putData("Autonomous Selector", chooser);
    }
    
    public void autonomousInit() { 
//Convert and input the selected autonomous into the variable mode which will run the selected autonomous    	
    	mode  = (int) chooser.getSelected();
    }
//Autonomous function to run the chosen autonomous
    public void autonomousPeriodic() {
    	
    	if(flag)//Flag is only true once per game so autonomous can only run once per game
    	{
    		switch(mode) //Switch to autonomous which corresponds with the radio button that was chosen 
    		{
    			case 1:
    			case 2:
    				chassis.move(0.65,2.0);
    				break;
    			case 3:
    			case 4:	
    				chassis.lowBar(0.65,3.0);
    				break;
    			case 5:
    			case 6:	
    				chassis.moat(0.65,4.0);
    				break;
    			case 7:
    			case 8:	
    				chassis.roughTerrain(0.65,4.0);
    				break;
    			case 9:
    			case 10:	
    				chassis.rockWall(0.65,4.0);
    				break;	
    			case 11:
    			case 12:
    				mechanism.autoRotate(0.425,1.2);
    				mechanism.autoIntake(-0.27);
    				chassis.portcullis(0.65,4.0);
    				break;
    			case 13:
    			case 14:	
    				chassis.rampart(0.65,4.0);
    				break;	
    			case 15:
    				chassis.brave(3.8,1.65,2.75);
    				break;	
    			case 16:
    				chassis.lowGoal(3.6, 1.4, 2.75);
    				break;
    			case 17:
    				chassis.move(0.75,3.3);
    				chassis.move(0.1,0.05);
        			mechanism.autoRotate(0.35,0.6); 
        			mechanism.autoShoot(0.85,0.5); 
        			mechanism.autoRotate(-0.35,0.6);
        			chassis.move(-0.75,3.3);
        			chassis.stop(0.1);
        			chassis.turn(0.6,'r',0.125);
        			chassis.lowBar(0.65, 3.2);
        			chassis.stop(0.1);
        			chassis.lowBar(-0.65, 3.0);
        			break;
    			case 18:
    				chassis.move(0.65,3.4);
    				chassis.stop(0.3);
    				mechanism.autoRotate(0.6, 0.5);
    				mechanism.autoShoot(0.7, 0.5);
    				mechanism.autoRotate(-0.6,0.5);
    				chassis.move(-0.65,3.4);
    				chassis.stop(0.3);
    				chassis.move(0.65,3.4);
    				break;
    	}
    	chassis.move(0, 1.0);//Stop the drive-train using the move method which is defined in Drive-train class 
    	flag = false;        //Set flag to false to prevent autonomous from repeating
    	}
    	
    	if(flag2)//Flag2 is only true once per game so autonomous out-taking can only run once per game
    	{
    		if(mode==2||mode==4||mode==6||mode==8||mode==10||mode==14)//Only the specified radio buttons run the out take autonomous
    		{
    			mechanism.autoRotate(0.35,1.0);  //First rotate the arm outwards into position using method from Arm class
    			mechanism.autoShoot(0.6,1.0);  //Second run the the in-take in reverse to release the ball using method from Arm class
    			mechanism.autoRotate(-0.35,0.8); //Third rotate the arm back into starting position using method from Arm class
    		}
    		else if(mode==12)//Only radio button 12 will run this out take program
    		{
    		mechanism.autoRotate(-0.56,0.7);//First rotate the arm inwards into position using method from Arm class
    		mechanism.autoShoot(0.5, 1.0);//Second run the the in-take in reverse to release the ball using method from Arm class
    		mechanism.autoRotate(-0.56, 0.5);
    		}
    		else if(mode==15||mode == 16)//Only radio button 15 will run this out take program
    		{
    		chassis.move(0.2);
    		mechanism.autoRotate(0.35,1.0);//First rotate the arm outwards into position using method from Arm class
    		mechanism.autoShoot(1.0,1.3);//Second run the the in-take in reverse to release the ball using method from Arm class
    		mechanism.autoRotate(-0.35,1.4);//Third rotate the arm back into starting position using method from Arm class
    		}
        chassis.move(0, 1.0);//Stop the drive-train using the move method which is defined in Drive-train class
    	flag2 = false;//Set flag2 to false so that the in-take autonomous does not run again
    	}
    }

    public void teleopPeriodic() {
    	while (isEnabled() && isOperatorControl())
    	{	
//Setting both flags to true so that the next time the robot is turned on autonomous runs    		
    		flag = true;
    		flag2 = true;
//Turning the compressor on for filling the tanks    		
    		mainCompressor.setClosedLoopControl(true);
//Call method from Drive-train class to drive 
    		chassis.drive();
    		Timer.delay(0.01);
//Call method from Arm class to use the in-take method
    		mechanism.intake(1.0);
    		Timer.delay(0.01);
//Call method from Arm class to use the rotate method
    		mechanism.rotate(0.80);
    		Timer.delay(0.01);
//Call method from Arm class to use the shoot method
    		mechanism.shoot(0.6, 0.5,0.5);
    		Timer.delay(0.01);
//Call method from Arm class to use the automatic arm rotate-in method
    		mechanism.allIn(0.8, 0.75);
    		Timer.delay(0.01);
    	}
    }    
    public void testPeriodic() {
    
    }    
}
