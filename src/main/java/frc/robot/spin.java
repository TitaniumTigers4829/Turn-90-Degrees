// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class spin extends CommandBase {
  /** Creates a new spin. */
  private modules _modules;
  public spin(modules Modules) {
    // Use addRequirements() here to declare subsystem dependencies.
    _modules = Modules;
    addRequirements(Modules);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("COMMAND RUNNING", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _modules.turnTo(90);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("COMMAND RUNNING", false);
    _modules.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double pos = 90 * modules.ticksPerDeg;
    return (_modules.motorPos()  > (pos - 50)) && (_modules.motorPos() < (pos + 50));
  }
}
