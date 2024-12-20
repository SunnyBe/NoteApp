import com.sunday.noteapp.configureDetekt
import com.sunday.noteapp.configureKtLint
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_DETEKT
import com.sunday.noteapp.utils.VersionCatalogMapper.PLUGIN_KTLINT
import com.sunday.noteapp.utils.asPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

internal class AndroidLibraryLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(PLUGIN_KTLINT.asPlugin(target))
            pluginManager.apply(PLUGIN_DETEKT.asPlugin(target))

            configure<KtlintExtension> { configureKtLint(this) }
            configure<DetektExtension> { configureDetekt(this) }
        }
    }
}
