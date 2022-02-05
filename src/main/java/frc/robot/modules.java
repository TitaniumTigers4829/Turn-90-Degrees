// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class modules extends SubsystemBase {
  /** Creates a new modules. */
  WPI_TalonSRX FL_Turn = new WPI_TalonSRX(12);
  WPI_TalonSRX FR_Turn = new WPI_TalonSRX(03);
  WPI_TalonSRX BL_Turn = new WPI_TalonSRX(15);
  WPI_TalonSRX BR_Turn = new WPI_TalonSRX(00);
  /*
        public static int frontLeftTurnMotorID = 12;        // SRX
        public static int frontRightTurnMotorID = 03;
        public static int backLeftTurnMotorID = 15;
        public static int backRightTurnMotorID = 00;
  */
  
  public void initTalons(WPI_TalonSRX motor){
    // motor.setSelectedSensorPosition(0);
    // motor.setSafetyEnabled(true);
    motor.setInverted(false);
    motor.configSelectedFeedbackSensor(FeedbackDevice.Analog);
    motor.setSensorPhase(false);
    // motor.config_kP(0, 0.0025);
    motor.config_kP(0, 1.0);
    //between 0.015 and 0.0015
    motor.config_kI(0, 0.0);
    motor.config_kD(0, 0.0);
    motor.config_kF(0, 0.0);
    motor.configMotionCruiseVelocity(3000);
    motor.configMotionAcceleration(2250);
    motor.configNominalOutputForward(0);
    motor.configNominalOutputReverse(0);
    motor.configPeakOutputForward(1);
    motor.configPeakOutputReverse(-1);
    motor.configAllowableClosedloopError(0, 0, 75);
    // motor.setSelectedSensorPosition(0);
  }
  public modules() {
    initTalons(FL_Turn);
    initTalons(FR_Turn);
    initTalons(BL_Turn);
    initTalons(BR_Turn);
  }

  public void _reset(){
    FR_Turn.setSelectedSensorPosition(0.0);
  }

  public void spin(double speed){
    FR_Turn.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putNumber("speed", speed);
  }

  public static double ticksPerDeg = (double) (1024/360);
  public void turnTo(double degree){
    SmartDashboard.putNumber("deg", degree);
    degree = (degree * 512 / 360);
    SmartDashboard.putNumber("deg * 1024 / 360", degree);
    FR_Turn.set(ControlMode.MotionMagic, degree);
    // FR_Turn.set(ControlMode.MotionMagic, 1024 * degree);
    // BL_Turn.set(ControlMode.MotionMagic, 1024 * degree);
    // BR_Turn.set(ControlMode.MotionMagic, 1024 * degree);
  }

  public double motorPos(){
    // return ((FL_Turn.getSelectedSensorPosition() + FR_Turn.getSelectedSensorPosition() + BL_Turn.getSelectedSensorPosition() + BR_Turn.getSelectedSensorPosition()) / 4);
    return FR_Turn.getSelectedSensorPosition();
  }

  public void stop(){
    FL_Turn.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("FR deg: ", 360 * FR_Turn.getSelectedSensorPosition() / 512);
    // SmartDashboard.putNumber("FR deg: ", 360 * FR_Turn.getSelectedSensorPosition() / 1024);
    // SmartDashboard.putNumber("BL deg: ", 360 * BL_Turn.getSelectedSensorPosition() / 1024);
    // SmartDashboard.putNumber("BR deg: ", 360 * BR_Turn.getSelectedSensorPosition() / 1024);
    SmartDashboard.putNumber("raw val", FR_Turn.getSelectedSensorPosition());
  }
}
