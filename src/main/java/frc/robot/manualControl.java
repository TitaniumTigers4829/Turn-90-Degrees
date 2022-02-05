// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class manualControl extends CommandBase {
  /** Creates a new manualControl. */
  private modules _modules;
  private DoubleSupplier _speed;
  public manualControl(modules Modules, DoubleSupplier speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    _modules = Modules;
    _speed = speed;
    addRequirements(Modules);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _modules.spin(_speed.getAsDouble() / 4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _modules.spin(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
