import com.android.build.api.dsl.ApplicationExtension
import com.sunday.noteapp.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            val extension = extensions.getByType(ApplicationExtension::class.java)
            configureKotlinAndroid(extension)
        }
    }
}