import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationFlavorsPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure(ApplicationExtension::class.java) {
                configureFlavors(this)
            }
        }
    }
}