import com.barry.task.RenameAppVersionNameTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project> {

  @Override
  void apply(Project project) {
    project.task('renameAppVersionName', type: RenameAppVersionNameTask)
    project.tasks.getByName('preBuild').dependsOn('renameAppVersionName')
  }

}