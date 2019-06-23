package com.purna.base.creational

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Purna on 2019-06-23 as a part of Image-Viewers
 **/

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