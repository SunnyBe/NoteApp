import com.sunday.noteapp.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jlleitschuh.gradle.ktlint.KtlintExtension


internal class AndroidLibraryLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            configure<KtlintExtension> { Lint(this) }

            dependencies {
                add("ktlint", libs.findLibrary("ktlint.ruleset.compose").get())
            }
        }
    }
}

internal fun Lint(ktlintExtension: KtlintExtension) {
    ktlintExtension.apply {
        version.set("1.4.1")
        debug.set(true)
        verbose.set(true)
        android.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        ignoreFailures.set(true)
        enableExperimentalRules.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
    }
}