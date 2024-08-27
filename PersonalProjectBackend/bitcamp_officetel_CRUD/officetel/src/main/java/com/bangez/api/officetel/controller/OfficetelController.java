package com.bangez.api.officetel.controller;

import com.bangez.api.common.model.Box;
import com.bangez.api.common.model.MessengerVo;
import com.bangez.api.common.model.PageDTO;
import com.bangez.api.common.service.impl.PageServiceImpl;
import com.bangez.api.officetel.model.OfficetelDTO;
import com.bangez.api.officetel.service.OfficetelServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/officetel")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "dd")})
public class OfficetelController {
    private final OfficetelServiceImpl offictelService;
    private final OfficetelRouter router;
    private final PageServiceImpl pageService;

    @PostMapping( "/insert")
    public ResponseEntity<MessengerVo> insert(@RequestBody OfficetelDTO dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(offictelService.insert(dto));
    }

    @PatchMapping( "/modify")
    public ResponseEntity<MessengerVo> modify(@RequestBody OfficetelDTO dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(offictelService.modify(dto));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<MessengerVo> removebyId(@PathVariable Long id){
        return ResponseEntity.ok(offictelService.removebyId(id));
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPlayer(
            @RequestParam(value = "q", required = true) String q,
            @RequestParam(value = "dto", required = false) OfficetelDTO dto ,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "oTvalue", required = false) List<String> oTvalue,
            @RequestParam(value = "pTvalue", required = false) List<String> pTvalue,
            @RequestParam(value = "cost", required = false) List<String> cost,
            @RequestParam(value = "user", required = false) String user,
            Pageable pageable
    ) {
        log.info("id" + id);
        long totalCount = offictelService.countOfficetel();
        PageDTO pageDto = pageService.getPageDTO(totalCount, pageable.getPageSize(), pageable.getPageNumber());
        List<?> o = router.execute(q, dto, oTvalue, pTvalue, cost, pageable, id, user);

        Box box = new Box();
        box.setPageDTO(pageDto);
        box.setList(o);

        return ResponseEntity.ok(box);
    }


}
