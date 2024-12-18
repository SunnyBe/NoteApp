import com.sunday.noteapp.configureDetekt
import com.sunday.noteapp.configureKtLint
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension


internal class AndroidLibraryLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            configure<KtlintExtension> { configureKtLint(this) }
            configure<DetektExtension> { configureDetekt(this) }
        }
    }
}
