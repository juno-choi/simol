package com.simol.appling.product.controller;

import com.simol.appling.global.api.annotation.ApiController;
import com.simol.appling.global.api.enums.ResponseDataCode;
import com.simol.appling.global.api.enums.Sort;
import com.simol.appling.global.api.response.ResponseData;
import com.simol.appling.product.domain.dto.GetProductListRequest;
import com.simol.appling.product.domain.dto.PostProductRequest;
import com.simol.appling.product.domain.dto.PutProductRequest;
import com.simol.appling.product.domain.vo.PostProductResponse;
import com.simol.appling.product.domain.vo.ProductDetailResponse;
import com.simol.appling.product.domain.vo.ProductListResponse;
import com.simol.appling.product.domain.vo.PutProductResponse;
import com.simol.appling.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API Documentation")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product")
    @Operation(summary = "상품 등록", description = "상품 등록 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostProductResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<ResponseData<PostProductResponse>> product(@RequestBody @Validated PostProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseData.from(ResponseDataCode.CREATE, productService.createProduct(productRequest)));
    }

    @PutMapping("/product")
    @Operation(summary = "상품 수정", description = "상품 수정 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PutProductResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    public ResponseEntity<ResponseData<PutProductResponse>> putProduct(@RequestBody @Validated PutProductRequest putProductRequest) {
        PutProductResponse putProduct = productService.putProduct(putProductRequest);
        return ResponseEntity.ok(ResponseData.from(ResponseDataCode.SUCCESS, putProduct));
    }

    @GetMapping("/product")
    @Operation(summary = "상품 리스트", description = "상품 리스트 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductListResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<ResponseData<ProductListResponse>> getProductList(
            @Schema(description = "페이지 크기", defaultValue = "10", nullable = true) @RequestParam(name = "size", required = false, defaultValue = "10" ) int size,
            @Schema(description = "페이지 번호", defaultValue = "0", nullable = true) @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @Schema(description = "페이지 정렬(proudct id 기준)", defaultValue = "DESC", nullable = true) @RequestParam(name = "sort",required = false, defaultValue = "DESC" ) Sort sort,
            @Schema(description = "검색어(아직 기능 개발 안함)", defaultValue = "", nullable = true) @RequestParam(name = "search", required = false, defaultValue = "") String search) {
                ProductListResponse result = productService.getProductList(GetProductListRequest.from(size, page, sort, search));
        return ResponseEntity.ok(ResponseData.from(ResponseDataCode.SUCCESS, result));
    }

    @GetMapping("/product/{product_id}")
    @Operation(summary = "상품 상세", description = "상품 상세 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "정상", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductListResponse.class))),
            @ApiResponse(responseCode = "500", description = "서버 에러", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    public ResponseEntity<ResponseData<ProductDetailResponse>> getProductDetail(@Schema(description = "상품 번호", defaultValue = "1", nullable = true) @PathVariable(name = "product_id") Long productId) {
        ProductDetailResponse productDetail = productService.getProductDetail(productId);
        return ResponseEntity.ok(ResponseData.from(ResponseDataCode.SUCCESS, productDetail));
    }
}
