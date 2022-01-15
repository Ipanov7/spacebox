package com.spacebox.api

import com.spacebox.api.client.IpfsClient
import com.spacebox.api.controller.ContentController
import com.spacebox.api.controller.TaskController
import com.spacebox.api.integration.IntegrationTest
import com.spacebox.api.manager.ContentManager
import com.spacebox.api.manager.TaskManager
import com.spacebox.api.repository.JpaTaskRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import strikt.api.expectThat
import strikt.assertions.isNotEqualTo

class ApiApplicationTests(
	@Autowired private val contentController: ContentController,
	@Autowired private val contentManager: ContentManager,
	@Autowired private val taskController: TaskController,
	@Autowired private val taskManager: TaskManager,
	@Autowired private val taskRepository: JpaTaskRepository,
	@Autowired private val ipfsClient: IpfsClient
) : IntegrationTest() {

	@Test
	fun contextLoads() {
		expectThat(contentController) isNotEqualTo null
		expectThat(contentManager) isNotEqualTo null
		expectThat(ipfsClient) isNotEqualTo null
		expectThat(taskController) isNotEqualTo null
		expectThat(taskManager) isNotEqualTo null
		expectThat(taskRepository) isNotEqualTo null
	}

}
