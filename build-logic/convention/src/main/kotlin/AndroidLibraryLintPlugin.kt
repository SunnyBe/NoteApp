import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jlleitschuh.gradle.ktlint")
            }

//            extensions.configure<KtlintExtension> {
//                debug.set(true)
//            }
        }
    }
}