package net.bugorfeature.basket

import net.bugorfeature.basket.command.BasketCommandRunner
import net.bugorfeature.basket.service.DefaultConfigService
import org.junit.Rule
import org.junit.contrib.java.lang.system.SystemOutRule
import org.junit.contrib.java.lang.system.TextFromStandardInputStream
import spock.lang.Specification

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream
/**
 * Specification for InputReader
 *
 * @author Andy Geach
 */
class InputReaderSpec extends Specification {

    //@Rule
    //OutputCapture output = new OutputCapture()

    @Rule
    TextFromStandardInputStream systemIn = emptyStandardInputStream();

    @Rule
    SystemOutRule systemOut = new SystemOutRule().enableLog();

    InputReader reader
    DefaultConfigService configService
    InputStream mockInput
    BasketCommandRunner mockRunner

    def setup() {
        reader = new InputReader()

        configService = Mock(DefaultConfigService)
        reader.setConfigService(configService)

        mockRunner = Mock(BasketCommandRunner)
        reader.setRunner(mockRunner)
    }

    def cleanup() {

    }

    def "startup with no args results in default config"() {
        when:
            reader.run()
            //systemIn.provideLines()

        then:
            1 * configService.buildDefault()
            systemOut.getLog().contains("Welcome to basket")

    }

    def "startup with one arg results in custom config"() {
        when:
            reader.run("spring args", "src/main/resources/config.xml")

        then:
            1 * configService.read(_)
            systemOut.getLog().contains("Welcome to basket")

    }
}
