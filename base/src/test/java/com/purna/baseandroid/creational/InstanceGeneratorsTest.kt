package com.purna.baseandroid.creational

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InstanceGeneratorsTest {

    @Test
    fun testSingleInstanceGenerator() {
        val generator = single {
            listOf("Hello", "Prasanth")
        }

        assert(generator.getInstance() === generator.getInstance())
    }

    @Test
    fun testFactoryInstanceGenerator() {
        val generator = factory {
            listOf("Hello", "Prasanth")
        }

        assert(generator.getInstance() !== generator.getInstance())
    }
}