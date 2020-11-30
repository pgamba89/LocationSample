package com.example.meepappsample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.meepappsample.model.ResponseModel
import com.example.meepappsample.repository.RemoteRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MapsModelViewTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    fun runBlockingTest(
        context: CoroutineContext = EmptyCoroutineContext,
        testBody: suspend TestCoroutineScope.() -> Unit
    ): Unit {
    }

    @Mock
    private lateinit var repository: RemoteRepository

    @Mock
    lateinit var observer: Observer<List<ResponseModel>>

    lateinit var modelView: MapsModelView

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        modelView = MapsModelView(repository)
    }

    @Test
    fun getLocations() = runBlockingTest {

        val locationsMock = listOf(
            ResponseModel("1","lisbon1"),
            ResponseModel("2","lisbon2"),
            ResponseModel("3","lisbon3")
        )

        Mockito.`when`(repository.getLocations()).thenReturn(locationsMock)
        modelView.locationResponse.observeForever(observer)

        verify(observer).onChanged(locationsMock)
        assertEquals(3, modelView.locationResponse.value?.size)
    }
}