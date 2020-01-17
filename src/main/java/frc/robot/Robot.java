package frc.robot;

// import frc.robot.auto.AutoDrive;
import frc.robot.constants.Robot_Framework;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class Robot extends TimedRobot implements Robot_Framework {
    // AutoDrive auto;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    double IR = m_colorSensor.getIR();
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    @Override
    public void robotInit() {
        
    }

    @Override
    public void robotPeriodic() {
        // dash.update();
    }

    @Override
    public void autonomousInit() {
        jetson.start();
        // auto = new AutoDrive(dash.getAutoMode(), dash.getPants());
    }

    @Override
    public void autonomousPeriodic() {
        // auto.execute();
    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
        drive.executeTank(); //Uses both sticks on driveBox        

        //Shifting gears with driveBox bumpers
        if (driveBox.getRawButton(left_bumper)) {
            gearSole1.set(DoubleSolenoid.Value.kReverse);
            gearSole2.set(DoubleSolenoid.Value.kReverse);
        } else if (driveBox.getRawButton(right_bumper)) {
            gearSole1.set(DoubleSolenoid.Value.kForward);
            gearSole2.set(DoubleSolenoid.Value.kForward);
        } else {
            gearSole1.set(DoubleSolenoid.Value.kOff);
            gearSole2.set(DoubleSolenoid.Value.kOff);
        }

        // //Controlling intake with intakeBox bumpers, toggling between ball and hatch with left trigger
        // if(intakeBox.getTriggerAxis(Hand.kLeft) < 0.5) {
        //     if (intakeBox.getRawButton(left_bumper)) {
        //         leftIntake.set(-0.75);
        //         rightIntake.set(-0.75);
        //     } else if (intakeBox.getRawButton(right_bumper)) {
        //         leftIntake.set(0.75);
        //         rightIntake.set(0.75);
        //     } else {
        //         leftIntake.set(0);
        //         rightIntake.set(0);
        //     }
        // } else {
        //     if (intakeBox.getRawButton(left_bumper)) {
        //         claws.hatchRelease();
        //     } else if (intakeBox.getRawButton(right_bumper)) {
        //         claws.hatchGrab();
        //     }
        // }

        //Flipping intake with driveBox buttons
        // if(driveBox.getRawButton(a_button)) {
        //     intakeRotate.hatchMode();
        // }
        // if(driveBox.getRawButton(b_button)) {
        //     intakeRotate.ballMode();
        //     leftIntake.set(0.15);
        //     rightIntake.set(0.15);
        // }

        //Move elevator and rotate inktae with intakeBox buttons & trigger
        // if(intakeBox.getTriggerAxis(Hand.kLeft) < 0.5) {
        //     intakeRotate.ballMode();
        //     if(intakeBox.getRawButton(x_button)) {
        //         elevator.placeBall(0);
        //     } else if(intakeBox.getRawButton(a_button)) {
        //         elevator.placeBall(1);
        //     } else if(intakeBox.getRawButton(b_button)) {
        //         elevator.placeBall(2);
        //     } else if(intakeBox.getRawButton(y_button)) {
        //         elevator.placeBall(3);
        //     }
        // } else {
        //     intakeRotate.hatchMode();
        //     if(intakeBox.getRawButton(x_button)) {
        //         elevator.placeHatch(0);
        //     } else if(intakeBox.getRawButton(a_button)) {
        //         elevator.placeHatch(1);
        //     } else if(intakeBox.getRawButton(b_button)) {
        //         elevator.placeHatch(2);
        //     } else if(intakeBox.getRawButton(y_button)) {
        //         elevator.placeHatch(3);
        //     }
        // }

        //Enter override mode with intakeBox back button
        // if(intakeBox.getRawButton(back_button)) {
        //     if(Math.abs(intakeBox.getRawAxis(right_y_axis)) > 0.02) {
        //         iRotate.set(intakeBox.getRawAxis(right_y_axis));
        //     } else {
        //         iRotate.set(0);
        //     }

        //     if(intakeBox.getTriggerAxis(Hand.kLeft) > 0.02) {
        //         leftElev.set(-intakeBox.getTriggerAxis(Hand.kLeft));
        //         rightElev.set(-intakeBox.getTriggerAxis(Hand.kLeft));
        //     } else if(intakeBox.getTriggerAxis(Hand.kRight) > 0.02) {
        //         leftElev.set(intakeBox.getTriggerAxis(Hand.kRight));
        //         rightElev.set(intakeBox.getTriggerAxis(Hand.kRight));
        //     } else {
        //         leftElev.set(0);
        //         rightElev.set(0);
        //     }
        // }
        
    }

    @Override
    public void testInit() {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);   
    }

    @Override
    public void testPeriodic() {
        Color detectedColor = m_colorSensor.getColor();
        double IR = m_colorSensor.getIR();
        System.out.println("working");
        System.out.println("Red: " + detectedColor.red);
        System.out.println("Green: " + detectedColor.green);
        System.out.println("Blue: " + detectedColor.blue);
        System.out.println("IR: " + IR);

        int proximity = m_colorSensor.getProximity();
        System.out.println("Proximity: " + proximity);

        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        if (match.color == kBlueTarget) {
            colorString = "Blue";
          } else if (match.color == kRedTarget) {
            colorString = "Red";
          } else if (match.color == kGreenTarget) {
            colorString = "Green";
          } else if (match.color == kYellowTarget) {
            colorString = "Yellow";
          } else {
            colorString = "Unknown";
          }

        System.out.println("Confidence: " + match.confidence);
        System.out.println("Detected Color: " + colorString);

        

        


        
        
        // if(driveBox.getRawButton(left_bumper))
        //     claws.hatchRelease();
        // else if(driveBox.getRawButton(right_bumper))
        //     claws.hatchGrab();
        // else if(driveBox.getRawButton(b_button))
        //     claws.ballGrab();
        // System.out.println(leftElev.getSensorCollection().getPulseWidthPosition() + " "
        //                 + rightClaw.getSensorCollection().getPulseWidthPosition());
    }

    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {

    }

}