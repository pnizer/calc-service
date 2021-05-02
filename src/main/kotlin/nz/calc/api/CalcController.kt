package nz.calc.api

import kotlinx.coroutines.flow.Flow
import nz.calc.documents.CalcDocument
import nz.calc.service.CalcService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class CalcController(
        private val calcService: CalcService,
) {
    @RequestMapping("sum", method = [RequestMethod.POST])
    suspend fun getASum(@RequestBody request: GetASumRequest): GetASumResponse {
        return GetASumResponse(
                result = calcService.getASum(request.x, request.y)
        )
    }

    @RequestMapping("product", method = [RequestMethod.POST])
    suspend fun getAProduct(@RequestBody request: GetAProductRequest): GetAProductResponse {
        return GetAProductResponse(
                result = calcService.getAProduct(request.a)
        )
    }

    @RequestMapping("power", method = [RequestMethod.POST])
    suspend fun getAPower(@RequestBody request: GetAPowerRequest): GetAPowerResponse {
        return GetAPowerResponse(
                result = calcService.getAPower(request.s)
        )
    }

    @RequestMapping("history", method = [RequestMethod.GET])
    suspend fun getHistory(): Flow<CalcDocument> {
        return calcService.getHistory()
    }
}

data class GetASumRequest(
        val x: Int,
        val y: Int,
)

data class GetASumResponse(
        val result: Int,
)

data class GetAProductRequest(
        val a: Int,
)

data class GetAProductResponse(
        val result: Int,
)

data class GetAPowerRequest(
        val s: Int,
)

data class GetAPowerResponse(
        val result: Int,
)
