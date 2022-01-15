package com.spacebox.api.client.configuration

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import feign.Logger
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import org.springframework.context.annotation.Bean

class ClientConfiguration {

    @Bean
    fun gsonEncoder() = GsonEncoder()

    @Bean
    fun gsonDecoder() = GsonDecoder(
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create()
    )

    @Bean
    fun feignLoggerLevel() = Logger.Level.BASIC
}