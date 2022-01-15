package com.spacebox.api.integration

import com.spacebox.api.ApiApplication
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import org.testcontainers.utility.DockerImageName

@ExtendWith(SpringExtension::class)
@SpringBootTest(
    classes = [ApiApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@ContextConfiguration(initializers = [IntegrationTest.Companion.Initializer::class])
@Testcontainers
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    protected val mockMvc: MockMvc? = null

    companion object {

        val mapper = ObjectMapper()

        private const val IPFS_PORT = 5001

        @JvmStatic
        val ipfsContainer: GenericContainer<Nothing> = GenericContainer<Nothing>(DockerImageName.parse("ipfs/go-ipfs"))
            .withExposedPorts(IPFS_PORT)

        @JvmStatic
        val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:14.1").apply {
            withDatabaseName("db")
            withUsername("sa")
            withPassword("sa")
        }

        init {
            ipfsContainer.start()
            postgreSQLContainer.start()
        }

        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(applicationContext: ConfigurableApplicationContext) {
                TestPropertyValues.of(
                    "spring.datasource.url=${postgreSQLContainer.jdbcUrl}",
                    "ipfs.node.url=${ipfsContainer.host}:${ipfsContainer.getMappedPort(IPFS_PORT)}",
                )
                    .applyTo(applicationContext)
            }
        }

    }
}