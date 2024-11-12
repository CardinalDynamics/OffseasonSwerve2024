package frc.robot.subsystems;
import java.io.File;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;


public class SwerveSubsystem extends SubsystemBase{
    SwerveDrive swerveDrive;
    
    public SwerveSubsystem(File directory) {
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        try {
            swerveDrive = new SwerveParser(directory).createSwerveDrive(Units.feetToMeters(4.5));
        } catch(Exception e) {
            SmartDashboard.putString("error", e.getMessage());
        }
    }

    public void drive(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier angularRotationX) {
        swerveDrive.drive(new Translation2d(translationX.getAsDouble() * swerveDrive.getMaximumVelocity(),
            translationY.getAsDouble() * swerveDrive.getMaximumVelocity()),
            angularRotationX.getAsDouble() * swerveDrive.getMaximumAngularVelocity(),
            true,
            false);
    }

    public void lock() {
        swerveDrive.lockPose();
    }
}
