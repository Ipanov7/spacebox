package com.spacebox.api.client.configuration

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.spacebox.api.domain.common.Entry
import feign.Logger
import feign.form.spring.SpringFormEncoder
import feign.gson.GsonDecoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean

class ClientConfiguration {

    @Autowired
    lateinit var messageConverters: ObjectFactory<HttpMessageConverters>

    @Bean
    fun gsonDecoder() = GsonDecoder(
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .registerTypeAdapter(Entry::class.java, GsonEntryDeserializer())
            .create()
    )

    @Bean
    fun feignLoggerLevel() = Logger.Level.BASIC

    @Bean
    fun feignFormEncoder(): SpringFormEncoder = SpringFormEncoder(SpringEncoder(messageConverters))
}