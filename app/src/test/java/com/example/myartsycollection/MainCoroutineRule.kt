package com.example.myartsycollection

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule : TestRule, TestCoroutineScope by TestCoroutineScope(), TestWatcher() {
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}