package frc.robot.constants;

import frc.robot.auto.*;
import frc.robot.teleop.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public interface Robot_Framework extends Drive_Constants, Control_Constants, ID_Constants, PID_Constants {
    DriverStation ds = DriverStation.getInstance();

    Compressor compressor = new Compressor(0);
    
    WPI_TalonSRX fLeft = new WPI_TalonSRX(front_left_drive);
    // WPI_TalonSRX mLeft = new WPI_TalonSRX(middle_left_drive);
    CANSparkMax bLeft = new CANSparkMax(back_left_drive, MotorType.kBrushless);

    WPI_TalonSRX fRight = new WPI_TalonSRX(front_right_drive);
    // WPI_TalonSRX mRight = new WPI_TalonSRX(middle_right_drive);
    CANSparkMax bRight = new CANSparkMax(back_right_drive, MotorType.kBrushless);

    // WPI_TalonSRX leftClaw = new WPI_TalonSRX(left_claw);
    // WPI_TalonSRX rightClaw = new WPI_TalonSRX(right_claw);
    // WPI_TalonSRX leftElev = new WPI_TalonSRX(left_elevator);
    // WPI_TalonSRX rightElev = new WPI_TalonSRX(right_elevator);
    // WPI_TalonSRX iRotate = new WPI_TalonSRX(intake_rotation);
    // WPI_TalonSRX leftIntake = new WPI_TalonSRX(left_intake);
    // WPI_TalonSRX rightIntake = new WPI_TalonSRX(right_intake);
    // WPI_TalonSRX leftClimb = new WPI_TalonSRX(left_climb);
    // WPI_TalonSRX rightClimb = new WPI_TalonSRX(right_climb);

    // DigitalInput bottomElevLim = new DigitalInput(bottom_elevator_limit);
    // DigitalInput topElevLim = new DigitalInput(top_elevator_limit);
    // DigitalInput iRotateLim = new DigitalInput(intake_rotate_limit);

    SpeedControllerGroup left = new SpeedControllerGroup(fLeft, bLeft);
    SpeedControllerGroup right = new SpeedControllerGroup(fRight, bRight);

    DifferentialDrive tank = new DifferentialDrive(left, right);

    XboxController driveBox = new XboxController(drive_controller);
    // XboxController intakeBox = new XboxController(intake_controller);

    DoubleSolenoid gearSole1 = new DoubleSolenoid(0, 2);
    DoubleSolenoid gearSole2 = new DoubleSolenoid(1, 3);
    // DoubleSolenoid climbSole1 = new DoubleSolenoid(2, 3);
    // DoubleSolenoid climbSole2 = new DoubleSolenoid(4, 5);
    // DoubleSolenoid wingSole1 = new DoubleSolenoid(6, 7);
    // DoubleSolenoid wingSole2 = new DoubleSolenoid(8, 9);

    // AHRS gyro = new AHRS(I2C.Port.kMXP);

    EchoServer jetson = new EchoServer();
    // NetworkTables dash = new NetworkTables();

    Drive drive = new Drive();
    // IntakeRotate intakeRotate = new IntakeRotate();
    // Elevator elevator = new Elevator();
}