package nz.calc

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import nz.calc.documents.CalcDocument
import nz.calc.documents.Operation
import nz.calc.repository.CalcRepository
import nz.calc.service.CalcService
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class CalcServiceTest {
    lateinit var service: CalcService
    lateinit var calcRepositoryMock: CalcRepository

    @BeforeEach
    fun setUp() {
        calcRepositoryMock = mock()
        calcRepositoryMock.stub {
            on { insert(any<CalcDocument>()) }.then { inv -> Mono.just { inv.getArgument<CalcDocument>(0) } }
        }

        service = CalcService(
                calcRepository = calcRepositoryMock
        )
    }

    @Test
    fun `getASum should sum correctly`() {
        val result = runBlocking {
            service.getASum(14, 22)
        }
        assertEquals(36, result)
    }

    @Test
    fun `getASum should store the result in a database`() {
        runBlocking {
            service.getASum(16, 13)
        }

        val documentCaptor = argumentCaptor<CalcDocument>()
        verify(calcRepositoryMock, only()).insert(documentCaptor.capture())

        val document = documentCaptor.lastValue
        assertEquals(listOf(16, 13), document.parameters)
        assertEquals(29, document.result)
        assertEquals(Operation.SUM, document.operation)
    }

    @Test
    fun `getAProduct should multiply by 2 correctly`() {
        val result = runBlocking {
            service.getAProduct(32)
        }
        assertEquals(64, result)
    }

    @Test
    fun `getAProduct should store the result in a database`() {
        runBlocking {
            service.getAProduct(23)
        }

        val documentCaptor = argumentCaptor<CalcDocument>()
        verify(calcRepositoryMock, only()).insert(documentCaptor.capture())

        val document = documentCaptor.lastValue
        assertEquals(listOf(23), document.parameters)
        assertEquals(46, document.result)
        assertEquals(Operation.PRODUCT, document.operation)
    }

    @Test
    fun `getAPower should power by 2 correctly`() {
        val result = runBlocking {
            service.getAPower(9)
        }
        assertEquals(81, result)
    }

    @Test
    fun `getAPower should store the result in a database`() {
        runBlocking {
            service.getAPower(11)
        }

        val documentCaptor = argumentCaptor<CalcDocument>()
        verify(calcRepositoryMock, only()).insert(documentCaptor.capture())

        val document = documentCaptor.lastValue
        assertEquals(listOf(11), document.parameters)
        assertEquals(121, document.result)
        assertEquals(Operation.POWER, document.operation)
    }
}
